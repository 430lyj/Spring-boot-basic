package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface가 interface를 받을 때에는 implements가 아닌 extends 사용 + interface는 다중 상속 가능
//JPA에는 JpaRepository를 받아야 함.
//<Member, Member pk의 형태>
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL로 'select m from Member m where m.name = ?'을 짜게 되고 이를 SQL로 돌리게 됨.
    //findeByNameAndId(String name, Long id) 이런식으로 하면 아이디와 이름으로 검색 커스텀 가능. -> 인터페이스 이름만으로도 기능 생성 가능.
    @Override
    Optional<Member> findByName(String name);
}
//Spring data JPA가 SpringDataJpaMemberRepository라는 interface가 있는 걸 확인(상속 받은 것)하고 자동으로 빈에 등록해줌.
