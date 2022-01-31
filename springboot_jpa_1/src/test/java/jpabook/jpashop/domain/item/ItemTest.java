package jpabook.jpashop.domain.item;

import jpabook.jpashop.exeption.NotEnoughStockException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemTest {

    @Test
    void 재고_개수_증가_확인()
    {
        //given
        Item item = new Item();
        item.setStockQuantity(10);
        //when
        item.addStock(10);
        //then
        Assertions.assertThat(item.getStockQuantity()).isEqualTo(20);
    }

    @Test
    void 재고수량은_0개미만일수없다()
    {
        //given
        Item item = new Item();
        item.setStockQuantity(10);
        //when
        org.junit.jupiter.api.Assertions.assertThrows(NotEnoughStockException.class,()->{
            item.removeStock(11);
        });
        //then
    }

}