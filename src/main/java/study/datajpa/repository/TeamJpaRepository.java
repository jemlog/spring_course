package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

/**
 * repository 어노테이션 생략 가능/
 * interface만 봐도 스프링이 인식한다.
 *
 * @Repository 어노테이션은 JPA 예외를 스프링 공통 처리 예외로 변환
 * 인터페이스에 맞는 구현체는 스프링 데이터 JPA가 알아서 주입해준다.
 */
public interface TeamJpaRepository extends JpaRepository<Team,Long> {

}
