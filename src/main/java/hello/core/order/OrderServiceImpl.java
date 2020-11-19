package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderServiceImpl implements OrderService {

    //final을 선언함으로써 생성자 의존성 주입 시에 해당 필드를 초기화하지 않으면 오류가 뜨도록 함!!!
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //setter를 통한 의존성 주입
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

    //생성자를 통한 의존성 주입 -> 생성자가 하나일 경우에는 @Autowired를 붙이지 않아도 됨 ==> 생성자 의존성 주입을 사용하자!!!!
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) { //동일한 타입의 빈이 2개 이상일 경우 파라미터명으로 빈 이름 매칭하여 데려옴
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
