package study.jpatoyproject.domain.post;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("K")
public class KnowhowPost extends Post{

    private String image = "no_image_not_yet";
}
