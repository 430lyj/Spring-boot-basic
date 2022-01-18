package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //데이터 변경 혹은 추가 시에는 Transactional이 필요하니까 추가해줌. 여기서는 회원가입 기능에서만 사용되므로 회원가입 위에다가만 추가해도 됨.
public class MemberService {
    // test 껍데기 자동 생성 -> cmd + shift+ T
    private final MemberRepository memberRepository;

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
