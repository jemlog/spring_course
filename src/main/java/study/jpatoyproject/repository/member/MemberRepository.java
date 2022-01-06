package study.jpatoyproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpatoyproject.domain.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long>, CustomMemberResitory {

    public List<Member> findByName(String username);
}
