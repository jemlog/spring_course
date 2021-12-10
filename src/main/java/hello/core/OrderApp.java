package hello.core;

import hello.core.blog.NoteBook;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
        NoteBook noteBook1 = NoteBook.getInstance();
        NoteBook noteBook2 = NoteBook.getInstance();
        System.out.println("noteBook1 is " + noteBook1);
        System.out.println("noteBook2 is " + noteBook2);

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
//        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
//
//        Long memberId = 1L;
//        Member member = new Member(memberId,"memberA", Grade.VIP);
//        memberService.join(member);
//
//        Order order = orderService.createOrder(memberId, "itemA", 10000);
//        System.out.println("order = " + order);
//
    }
}
