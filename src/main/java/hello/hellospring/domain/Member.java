package hello.hellospring.domain;

import javax.persistence.*;


//JPA는 자바 표준 ORM(객체와 관계형 DB를 매핑하는 것) 인터페이스, hibernate 등이 구현체로 각 업체에서 만든 것들.
@Entity //JPA가 관리하는 entity가 되는 것
public class Member {

    @Id //pk 값이라는 뜻
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 자동생성 해주는 것을 IDENTITY라고 함
    private Long id;

    //@Column(name = "username") //DB column의 username과 매핑
    private String name;

    public Long getId() {
        return id;
    }

    public Member setId(Long id) {
        this.id = id;
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
