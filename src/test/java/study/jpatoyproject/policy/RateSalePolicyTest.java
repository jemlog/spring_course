package study.jpatoyproject.policy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.jpatoyproject.domain.Address;
import study.jpatoyproject.domain.Gender;
import study.jpatoyproject.domain.Grade;
import study.jpatoyproject.domain.Member;

import static org.junit.jupiter.api.Assertions.*;

class RateSalePolicyTest {

    private SalePolicy salesPolicy = new RateSalePolicy();

    @Test
    @DisplayName("grade별로 나오는 할인가격 테스트")
    public void testDiscountPrice()
    {
        //given
        Member testMember = Member.builder().name("jemin").age(25).grade(Grade.SILVER)
                .gender(Gender.MAN).address(new Address("서울", "성동구")).build();

        //when
        int price = 10000;
        int resultPrice = salesPolicy.discountPrice(price, testMember);

        //then
        Assertions.assertThat(resultPrice).isEqualTo(1000);
    }

}