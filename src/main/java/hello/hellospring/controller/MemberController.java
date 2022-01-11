package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //이 annotation을 보고 스프링이 실행될 때, 스프링 컨테이너에서 컨트롤러들을 들고 있다 -> 스프링 컨테이너에서 스프링 빈이 관리된다고 표현. (MVC 템플릿 엔진 이미지 참고)
public class MemberController {

    private final MemberService memberService;
    // new MemberService 해서 사용해도 되지만, 그렇게 하면 여러 컨트롤러(ex. 주문 등)에서 불필요하게 여러 memberService 객체가 생성될 수 있다.
    // 그래서 생성자를 이용해서 스프링 컨테이너에 등록한 후 동일한 객체를 스프링 컨테이너에서 받아서 쓸 수 있도록 함. -> @Autowired annotation이 하는 일
    @Autowired //이걸 사용하면 스프링 컨테이너에 있는 memberService와 딱 연결시켜준다! (Dependency Injection : 의존 관계 주입 임)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // service 와 repository도 각각 @ annotation을 통해서 컨테이너에 등록해줘야 함!
    //controller 이용해서 외부 요청 받고, service에서 비즈니스 로직을 만들고, repository에서 데이터를 저장하는 게 정형화된 패턴!

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(MemberForm form) { //Memberform의 name 속성과 input 태그의 이름이 name인 것을 찾고 setName을 이용해서 매칭해줌.
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
