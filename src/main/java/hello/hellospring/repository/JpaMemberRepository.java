package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //jpa 라이브러리 추가 시 스프링 부트가 Entity Manager 이라는 것을 자동 생성.따라서 우리는 만들어진 것을 injection 받으면 된다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //member를 영구 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);//find할 대상이 되는 type과 식별자 pk 값을 넣어주면 된다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny(); //하나만 찾으면 되므로
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); //객체(정확히는 entity) 대상으로 쿼리를 날림. (jpql) -> Member entity를 조회해 (as m) + member 그 자체를 select
    }
}
