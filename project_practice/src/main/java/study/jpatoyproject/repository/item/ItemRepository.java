package study.jpatoyproject.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpatoyproject.domain.Item;
import study.jpatoyproject.domain.post.Post;

public interface ItemRepository extends JpaRepository<Item,Long> , CustomItemResitory {
}
