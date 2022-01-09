package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //이걸 해줘야 스프링 컨테이너에 서비스로 등록을 하고, 컨트롤러에서 등록하고자 할 때도 바로 사용 가능
public class MemberService {
    // test 껍데기 자동 생성 -> cmd + shift+ T
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join (Member member){
        //동명의 중복회원은 회원가입 불가
        /* 자주 사용되는 내용은 메소드로 따로 뽑음 -> Ctrl + T
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{
            throw IllegalStateException("이미 존재하는 회원입니다");
        }); // ifPresent 는 optional 이기 때문에 사용 가능. Optional 사용 안할시 if !null 이런 식으로 사용될 수 있음.
         */

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //좀 더 정갈하게 사용 가능
                        .ifPresent(m-> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 개별 회원 Id 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
