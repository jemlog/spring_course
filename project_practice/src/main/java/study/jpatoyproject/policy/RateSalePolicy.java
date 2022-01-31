package study.jpatoyproject.policy;

import study.jpatoyproject.domain.Grade;
import study.jpatoyproject.domain.Member;

public class RateSalePolicy implements SalePolicy{

    private static final double RATE_VIP = 0.3;
    private static final double RATE_GOLD = 0.2;
    private static final double RATE_SILVER = 0.1;
    private static final double RATE_BRONZE = 0.05;

    // 클린코드 3장 : 함수의 크기를 최대한 줄이자
    @Override
    public int discountPrice(int price, Member member) {
        return checkGradeAndCalcDiscount(price, member.getGrade());
    }


    // 클린코드 3장 : 함수의 이름은 함수의 역할을 잘 드러내야 한다.
    private int checkGradeAndCalcDiscount(int price, Grade grade) {

        if(grade == Grade.VIP)
        {
            Double result = Double.valueOf(price) * RATE_VIP;
            return result.intValue();
        }
        else if(grade == Grade.GOLD)
        {
            Double result = Double.valueOf(price) * RATE_GOLD;
            return result.intValue();
        }
        else if(grade == Grade.SILVER)
        {
            Double result = Double.valueOf(price) * RATE_SILVER;
            return result.intValue();
        }
        else
        {
            Double result = Double.valueOf(price) * RATE_BRONZE;
            return result.intValue();
        }
    }
}
