package study.jpatoyproject.domain;

import lombok.Getter;
import study.jpatoyproject.domain.post.Post;
import study.jpatoyproject.util.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
public class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToOne(mappedBy = "item",fetch = FetchType.LAZY)
    private Post post;
}
