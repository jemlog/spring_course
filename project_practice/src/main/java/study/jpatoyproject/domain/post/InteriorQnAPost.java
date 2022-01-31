package study.jpatoyproject.domain.post;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("I")
public class InteriorQnAPost extends Post{
}
