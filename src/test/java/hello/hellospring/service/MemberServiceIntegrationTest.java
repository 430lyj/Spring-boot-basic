package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//MemberServiceTest는 그냥 JVM 상에서 자바로 테스트 한 거(단위 테스트 - 시간이 매우 짧게 걸리므로 효율적일 때가 많음)였다면 스프링부트로도 테스트(통합 테스트)를 해봐야 함.
//Spring container 없이 테스트 해보는 훈련(단위 테스트 훈련)을 해야 함. 그게 더 좋은 테스트일 확률이 높음.
@SpringBootTest
@Transactional //이 annotation을 해두면 테스트한 데이터가 디비에 반영되지 않음. afterEach 해서 매번 디비의 마지막 쿼리를 지워주는 것(롤백)과 같은 역할
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("spring");

        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
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
