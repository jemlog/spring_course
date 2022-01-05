package study.jpatoyproject.policy;

import study.jpatoyproject.domain.Grade;
import study.jpatoyproject.domain.Member;

public interface SalePolicy {

    int discountPrice(int price, Member member);
}
