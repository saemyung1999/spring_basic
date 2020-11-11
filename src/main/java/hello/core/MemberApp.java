package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        Member member = new Member(1L, "memberA", Grade.VIP);
        MemberService memberService = appConfig.memberService();
        memberService.join(member);

        OrderService orderService = appConfig.orderService();
        Order order = orderService.createOrder(member.getId(), "itemA", 10000);

    }
}
