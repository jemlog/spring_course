package study.querydsl.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import java.util.List;

import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;


@SpringBootTest
@Transactional
public class QueryDslBasicTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;
    @BeforeEach
    public void before(){
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL()
    {
        // member1을 찾아라
        Member singleResult = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1").getSingleResult();
        Assertions.assertThat(singleResult.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl()
    {
          // Q 멤버를 사용해야 한다. 쿼리 내부에 Q 객체를 넘겨줘야함
        Member member1 = queryFactory.select(member)  // JPQL 빌더
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        Assertions.assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search()
    {
        Member findMember = queryFactory.selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.eq(10)))
                .fetchOne();

        Assertions.assertThat(findMember.getUsername()).isEqualTo("member1");
    }
    @Test
    public void searchAndParam()
    {
        Member findMember = queryFactory.selectFrom(member)
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10)
                )
                .fetchOne();

        Assertions.assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultFetch()
    {
//        List<Member> fetch = queryFactory.selectFrom(member)
//                .fetch();
//
//        Member member = queryFactory.selectFrom(QMember.member).fetchOne();
//
//        Member member1 = queryFactory.selectFrom(QMember.member).fetchFirst();

        QueryResults<Member> memberQueryResults = queryFactory.selectFrom(QMember.member)
                .fetchResults();

        memberQueryResults.getTotal();
        List<Member> results = memberQueryResults.getResults();
    }


    /**
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순
     * 2. 회원 이름 오름차순
     * 단 2에서 회원 이름이 없으면 마지막에 출력 nulls last
     */

@Test
public void sort()
{
   em.persist(new Member(null,100));
    em.persist(new Member("member5",100));
    em.persist(new Member("member6",100));

    List<Member> fetch = queryFactory.selectFrom(member)
            .where(member.age.eq(100))
            .orderBy(member.age.desc(), member.username.asc().nullsLast())  // null 값 들어있으면 마지막으로!!
            .fetch();

    Member member5 = fetch.get(0);
    Member member6 = fetch.get(1);
    Member memberNull = fetch.get(2);
    Assertions.assertThat(member5.getUsername()).isEqualTo("member5");
    Assertions.assertThat(member6.getUsername()).isEqualTo("member6");
    Assertions.assertThat(memberNull.getUsername()).isNull();
}

@Test
    public void paging1()
{
    List<Member> result = queryFactory.selectFrom(member)
            .orderBy(member.username.desc())
            .offset(1)
            .limit(2)
            .fetch();

    Assertions.assertThat(result.size()).isEqualTo(2);
}

    @Test
    public void paging2()
    {
        QueryResults<Member> result = queryFactory.selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();
        Assertions.assertThat(result.getTotal()).isEqualTo(4);
        Assertions.assertThat(result.getLimit()).isEqualTo(2);
        Assertions.assertThat(result.getOffset()).isEqualTo(1);
        Assertions.assertThat(result.getResults().size()).isEqualTo(2);
    }

    @Test
    public void join()
    {
        List<Member> result = queryFactory.selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();
        Assertions.assertThat(result)
                .extracting("username")
                .containsExactly("member1","member2");
    }

    /**
     * 예 ) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
     */

    @Test
    public void join_on_filtering(){
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .join(member.team, team) // member.team과 team의 id값으로 매칭
                .where(team.name.eq("teamA"))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void fetchJoinNo()
    {
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        Assertions.assertThat(loaded).as("페치조인 미적용").isFalse();

    }
    @Test
    public void fetchJoinUse()
    {
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team,team).fetchJoin() // join.fetchJoin으로 접근하자!!
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        Assertions.assertThat(loaded).as("페치조인 미적용").isTrue();

    }

    /**
     * 나이가 가장 많은 회원 조회
     */
    @Test
    public void subQuery()
    {

        QMember memberSub = new QMember("memberSub"); // 중복되는 경우에는 이름 지정

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions.select(memberSub.age.max())
                                .from(memberSub)
                )).fetch();

        Assertions.assertThat(result).extracting("age")
                .containsExactly(40);
    }

    /**
     * 나이가 평균 이상인 회원 조회
     */
    @Test
    public void subQueryGoe()
    {

        QMember memberSub = new QMember("memberSub"); // 중복되는 경우에는 이름 지정

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        JPAExpressions.select(memberSub.age.avg())
                                .from(memberSub)
                )).fetch();

        Assertions.assertThat(result).extracting("age")
                .containsExactly(30,40); // 해당 리스트의 객체들 age 속성이 뭐랑 뭐가 있는지 분석
    }

    /**
     * 나이가 평균 이상인 회원 조회
     */
    @Test
    public void subQueryIn()
    {

        QMember memberSub = new QMember("memberSub"); // 중복되는 경우에는 이름 지정

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        JPAExpressions.select(memberSub.age)
                                .from(memberSub)
                        .where(memberSub.age.gt(10))
                )).fetch();

        Assertions.assertThat(result).extracting("age")
                .containsExactly(20,30,40); // 해당 리스트의 객체들 age 속성이 뭐랑 뭐가 있는지 분석
    }

    @Test
    public void selectSubQuery()
    {

        QMember memberSub = new QMember("memberSub");
        List<Tuple> result = queryFactory
                .select(member.username,
                        JPAExpressions
                                .select(memberSub.age.avg())
                .from(memberSub))
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }

    }

    @Test
    public void basicCase()
    {
        List<String> fetch = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        for (String s : fetch) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void complexCase()
    {
        List<String> 기타 = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20살")
                        .when(member.age.between(21, 30)).then("21~30살").otherwise("기타"))
                .from(member)
                .fetch();
        for (String s : 기타) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void simpleProjection()
    {
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .fetch();
    }

    @Test
    public void tupleProjection()
    {
        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : result) {  // tuple은 쿼리 dsl에 종속적이므로 레포지토리 밖으로는 보내지 말자
            String username = tuple.get(member.username);  //tuple에서 값으로 get 조회 가능
            Integer age = tuple.get(member.age);
            System.out.println("age = " + age);
            System.out.println("username = " + username);
        }
    }

    @Test
    public void findDtoByJPQL(){
        List<MemberDto> result = em.createQuery("select new study.querydsl.dto.MemberDto(m.username,m.age) from Member m", MemberDto.class)
                .getResultList(); // new operation , 생성자 방식만 사용 가능
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoBySetter()
    {
        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class,  //bean이 setter로 data injection
                        member.username, member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }
    @Test
    public void findDtoByField()
    {
        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,  // getter setter 없어도 field에 값을 꽃아줌
                        member.username.as("name")  // field 방식은 프로퍼티 이름 맞춰줘야 한다.
                        , member.age))
                .from(member)
                .fetch();
        for (UserDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByConstructor()
    {
        List<UserDto> result = queryFactory
                .select(Projections.constructor(UserDto.class,  // getter setter 없어도 field에 값을 꽃아줌
                        member.username,  // 생성자 방식은 타입 보고 들어간다.
                        member.age))      // 다른 값 하나 더 넣으면 런타임 에러가 난다.
                .fetch();
        for (UserDto userDto : result) {
            System.out.println("memberDto = " + userDto);
        }
    }

    @Test
    public void findDtoByQueryProjection()
    {
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age))  // 추가로 들어오면 바로 에러난다!
                .from(member)                                   // 실무에서의 고민거리... q파일 생성해야함
                .fetch();                                       // queydsl에 의존성을 가지게 된다!
                                                                // dto를 깔끔하게 가져가고 싶을때는 고민해야함
                                                                // 이게 아니면 앞에서 배운 기술들 사용
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void dynamicQuery_BooleanBuilder()
    {
        String usernameParam = "member1";
        Integer ageParam = null;

        List<Member> result = searchMember1(usernameParam,ageParam);
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {

        BooleanBuilder builder = new BooleanBuilder(member.username.eq(usernameCond)); // 파라미터는 옵션
        if( usernameCond != null)
        {
            builder.and(member.username.eq(usernameCond));
        }

        if (ageCond != null)
        {
            builder.and(member.age.eq(ageCond));
        }
        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();

    }

    @Test
    public void dynamicQuery_Where_Param()
    {
        String usernameParam = "member1";
        Integer ageParam = null;

        List<Member> result = searchMember2(usernameParam,ageParam);
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory.selectFrom(member)
                .where(usernameEq(usernameCond),ageEq(ageCond))  // where에 null 들어가면 무시된다.
                .fetch();
    }

    private BooleanExpression ageEq(Integer ageCond) {  // 쉽다....
        return ageCond != null ? member.age.eq(ageCond): null ;
    }

    private BooleanExpression usernameEq(String usernameCond) {

        return usernameCond != null ? member.username.eq(usernameCond) :null ;

    }

}