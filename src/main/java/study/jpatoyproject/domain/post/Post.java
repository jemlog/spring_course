package study.jpatoyproject.domain.post;

import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import study.jpatoyproject.domain.Item;
import study.jpatoyproject.domain.Member;
import study.jpatoyproject.util.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 연관관계 편의 메서드
    public void createPost(Member member)
    {
        this.member = member;
        member.getPostList().add(this);
    }


}
