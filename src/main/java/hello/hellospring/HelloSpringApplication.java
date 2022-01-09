package hello.hellospring; //component scan은 이 패키지 하위만 진행해줌. 따라서 이것보다 상위의 패키지에서 component annotation은 작동 x

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}
}
