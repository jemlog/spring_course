package study.jpatoyproject.domain;

import lombok.Getter;
import study.jpatoyproject.domain.post.Post;

import javax.persistence.*;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToOne(mappedBy = "item",fetch = FetchType.LAZY)
    private Post post;
}
