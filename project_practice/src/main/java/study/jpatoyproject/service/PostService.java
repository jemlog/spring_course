package study.jpatoyproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostService {

    //TODO: 포스트 작성 기능 , DTYPE에 따른 조건부 포스트 조회 기능 + 페이징 기능 , 포스트 삭제 기능
    //FIXME: 얼른 고쳐야 함
}
