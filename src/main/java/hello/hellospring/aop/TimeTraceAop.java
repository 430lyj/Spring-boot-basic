package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
//Spring bean 으로 등록을 해줘야 함 @Component(컴포넌트 스캔)을 사용하기도 하지만, 컨테이너에 직접 등록해주는 것을 선호. (정형화된 Repository 같은 게 아니기 때문에 명시해주는 게 더 좋음)
@Aspect //AOP에는 Aspect라고 적어줘야 함.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); //joinPoint의 메소드들을 이용해서 원하는 것을 마음대로 조작도 가능!!
        try {
            //다음 메소드로 진행이 된다.
            return joinPoint.proceed(); //이런 조건이면 다음으로 넘어가지마! 이런 것도 설정이 가능함.
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }


    }
}
