package study.jpatoyproject.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import study.jpatoyproject.domain.Member;
import study.jpatoyproject.domain.QMember;
import study.jpatoyproject.domain.dto.member.MemberResponseDto;
import study.jpatoyproject.domain.dto.member.MemberSearch;
import study.jpatoyproject.domain.dto.member.QMemberResponseDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;
import static study.jpatoyproject.domain.QMember.member;


public class MemberRepositoryImpl implements CustomMemberResitory{

    private final JPAQueryFactory queryFactory;
    public MemberRepositoryImpl(EntityManager em)
    {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MemberResponseDto> findAllByCondition(MemberSearch memberSearch, Pageable pageable) {

        QueryResults<MemberResponseDto> results = queryFactory
                .select(new QMemberResponseDto(member.id,
                                            member.name,
                                            member.age,
                                            member.grade,
                                            member.gender,
                                            member.address))
                .from(member)
                .where(
                        nameEq(memberSearch.getName()),
                        ageGOE(memberSearch.getAge())
                )
                .orderBy(member.age.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        long total = results.getTotal();
        List<MemberResponseDto> listResults = results.getResults();
        return new PageImpl<>(listResults,pageable,total);


    }

    private BooleanExpression ageGOE(Integer age) {
        return age == null ? null : member.age.goe(age);
    }

    private BooleanExpression nameEq(String name) {
        return isEmpty(name) ? null : member.name.eq(name);
}
}
