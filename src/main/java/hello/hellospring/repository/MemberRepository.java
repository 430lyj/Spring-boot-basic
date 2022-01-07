package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Id나 이름으로 찾을 때 null 값이 반환될 수 있는데, 이 때 그냥 null을 반환하지 않고 optional로 감싸서 반환해줌.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
