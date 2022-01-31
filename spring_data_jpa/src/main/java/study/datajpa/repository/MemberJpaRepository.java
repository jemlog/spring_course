package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member,Long> {

    // 도메인 특화 기능
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    List<Member> findTop2By();

    // 실무에서 자주 쓰는 기능!
    // 장점 : 애플리케이션 로딩 시점에 sql로 파싱을 한다. 그때 오류가 있으면 에러 터트린다.
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    // 값 조회
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // 바로 dto로 조회하는 문법!! 기억하자
    // 극한의 성능 최적화!
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    // list를 파라미터로 받은 후 그 내부에 값이 있는지 확인 할 ㅅ ㅜ있다.
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    /**
     * 여러 종류의 반환형을 선택 할 수 있다.
     */
    List<Member> findListByUsername(String username);

    Member findMemberByUsername(String username);

    Optional<Member> findOptionalByUsername(String name);

    // Slice는 totalCount를 구하는 쿼리가 나가지 않는다.
    // Page는 totalCount 쿼리가 나간다.
    // 파라미터로는 paging 쿼리를 생성하는 Pageable 인터페이스를 보내야 한다.

    Slice<Member> findByAge(int age, Pageable pageable);
}
