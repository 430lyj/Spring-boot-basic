package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test가 끝나면 레포지토리(공용으로 사용되는 요소들 등)를 비워줘서 다음 테스트를 원활하게 수행할 수 있도록 설정해줘야 함.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //optional에서 값을 꺼낼 때는 get()을 이용해서 바로 꺼낼 수 있음.
        // assertions는 assertj 라는 것에서 가져오는 것을 많이 사용
        assertThat(member).isEqualTo(result); // opt + enter 쳐서 static import 하면 Assertions. 부분은 생략 가능
        //실무에서는 build 툴과 엮어서 테스트 케이스를 통과하지 않으면 다음단계로 못 넘어가게 막아버리기도 함.
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);
        // 같은 변수 적을 때 shift + F6 누르면 한꺼번에 rename 가능!!
        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring2").get();
        assertThat(result).isEqualTo(member2);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring1");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("Spring2");
        repository.save(member3);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(3);
    } // test 순서는 보장되지 않음. 따라서 테스트 케이스 설계시 순서와 관련없이 테스트가 가능하도록 설계해야 함
}
