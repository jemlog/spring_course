package jpabook.jpashop.domain.item;

import jpabook.jpashop.exeption.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // DDD 엔티티 자체에서 해결할 수 있는건 비즈니스 로직을 엔티티
    // 내부에 집어넣자.

    public void addStock(int quantity)
    {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity)
    {
        this.stockQuantity -= quantity;
        if(this.stockQuantity < 0)
        {
            throw new NotEnoughStockException("재고의 개수가 0 미만일 수는 없다.");
        }
    }

}
