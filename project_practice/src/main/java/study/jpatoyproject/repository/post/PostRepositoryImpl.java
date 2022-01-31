package study.jpatoyproject.repository.post;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import study.jpatoyproject.domain.dto.member.MemberResponseDto;
import study.jpatoyproject.domain.dto.member.MemberSearch;
import study.jpatoyproject.domain.dto.member.QMemberResponseDto;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;
import static study.jpatoyproject.domain.QMember.member;


public class PostRepositoryImpl implements CustomPostResitory {

    private final JPAQueryFactory queryFactory;
    public PostRepositoryImpl(EntityManager em)
    {
        this.queryFactory = new JPAQueryFactory(em);
    }


}
