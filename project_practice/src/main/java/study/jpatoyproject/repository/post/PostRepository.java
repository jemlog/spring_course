package study.jpatoyproject.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpatoyproject.domain.post.Post;

public interface PostRepository extends JpaRepository<Post,Long> , CustomPostResitory{
}
