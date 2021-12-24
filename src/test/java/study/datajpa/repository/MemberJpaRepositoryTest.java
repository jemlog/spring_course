package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberRepository;

    @Test
    public void findByUsernameAndAgeGreaterThan()
    {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        Assertions.assertThat(result.get(0).getUsername()).isEqualTo("AAA");
    }

    @Test
    public void findTop2()
    {
        memberRepository.findTop2By();
    }

    @Test
    public void testQuery()
    {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);
        List<Member> result = memberRepository.findUser("AAA", 10);
        Assertions.assertThat(result.get(0)).isEqualTo(m1);
    }

     @Test
     public void findByNames()
     {
         Member m1 = new Member("AAA", 10);
         Member m2 = new Member("AAA", 20);
         memberRepository.save(m1);
         memberRepository.save(m2);
         List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
         for (Member member : result) {
             System.out.println("member = " + member);
         }
     }

     @Test
    public void findByTests()
     {
         Member m1 = new Member("AAA", 10);
         Member m2 = new Member("BBB", 20);
         memberRepository.save(m1);
         memberRepository.save(m2);
         Optional<Member> result = memberRepository.findOptionalByUsername("AAA");
         System.out.println(result.get());
     }

    @Test
    public void paging(){
        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",10));
        memberRepository.save(new Member("member3",10));
        memberRepository.save(new Member("member4",10));
        memberRepository.save(new Member("member5",10));

        int age = 10;                      // 페이지는 0번 페이지 부터 시작, 사이즈는 3개이다. Sort는 있어도되고 없어도됨
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        // PageRequest는 구현체! 페이징 쿼리를 생성시킨다.
        // slice는 n + 1개를 가져온다. totalcount만 안 올라가.
        Slice<Member> page = memberRepository.findByAge(age, pageRequest);
        // 총 개수 자체는 5개가 들어있다.
        // getContent를 해야 3개가 들어있다.
        // then
        List<Member> content = page.getContent();     // 내가 페이지 지정한 영역의 데이터 개수
                                                      // 지금 상황은 0번째부터 3개 즉 0,1,2
        System.out.println(content);
        Assertions.assertThat(content.size()).isEqualTo(3);
 //       Assertions.assertThat(page.getTotalElements()).isEqualTo(5);  // page는 그냥 3개만 데이터가 들어있다.
                                                                        // 전체를 구하는건 page랑 별개로 totalCount 쿼리가 나갔기 때문
        Assertions.assertThat(page.getNumber()).isEqualTo(0); // 0번 페이지 부터 시작
 //       Assertions.assertThat(page.getTotalPages()).isEqualTo(2);
        Assertions.assertThat(page.isFirst()).isTrue(); // 첫번째 페이지가 맞나
        Assertions.assertThat(page.hasNext()).isTrue();

    }
}