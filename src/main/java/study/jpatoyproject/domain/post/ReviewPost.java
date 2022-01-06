package study.jpatoyproject.domain.post;


import study.jpatoyproject.domain.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("R")
public class ReviewPost extends Post{




}
