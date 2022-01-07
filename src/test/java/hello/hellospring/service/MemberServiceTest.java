package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //test는 직관적으로 쉽게 알아볼 수 있도록 메소드 이름을 한글로 적기도 한다. (빌드 될 때 테스트 코드는 포함되지 않음)
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository(); //각 케이스별로 clear 해주기 위해서 가져와야 함.
    //위의 경우 새로운 repository를 생성한 것이므로 MemberService에서 생성한 것과 다른 인스턴스가 될 수 있음. 따라서 MemberService입장에서는 생성자를 이용해서 외부에서 생성해주는 것이 좋음.
    //이걸 dependency Injection

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //test code는 뭔가가 주어졌을 때, 이걸 하면, 이렇게 나와야 한다는 걸 테스트 하는 것 -> given, when, then
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);


        //then
        Member result = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*

        try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
    }
}
