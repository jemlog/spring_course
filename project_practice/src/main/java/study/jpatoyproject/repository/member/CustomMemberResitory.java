package study.jpatoyproject.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.jpatoyproject.domain.Member;
import study.jpatoyproject.domain.dto.member.MemberResponseDto;
import study.jpatoyproject.domain.dto.member.MemberSearch;

import java.util.List;

public interface CustomMemberResitory {

    Page<MemberResponseDto> findAllByCondition(MemberSearch memberSearch , Pageable pageable);
}
