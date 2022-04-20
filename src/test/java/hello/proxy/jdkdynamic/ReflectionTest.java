package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0(){
        Hello hello = new Hello();

        //공통 로직1 시작
        log.info("start");
        String result1 = hello.callA();
        log.info("result={}", result1);
        //공통로직 종료


        //공통 로직1 시작
        log.info("start");
        String result2 = hello.callB();
        log.info("result={}", result2);
        //공통로직 종료
    }

    @Test
    void reflection1() throws Exception{
        Class clazz = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCalllA = clazz.getMethod("callA");
        Object result1 = methodCalllA.invoke(target);
        log.info("result1={}", result1);

        Method methodCalllB = clazz.getMethod("callB");
        Object result2 = methodCalllB.invoke(target);
        log.info("result1={}", result2);

    }

    @Test
    void reflection2() throws Exception{
        Class clazz = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodCalllA = clazz.getMethod("callA");
        dynamicCall(methodCalllA, target);

        Method methodCalllB = clazz.getMethod("callB");
        dynamicCall(methodCalllB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception{
        //공통 로직1 시작
        log.info("start");
        Object result1 = method.invoke(target);
        log.info("result={}", result1);
        //공통로직 종료
    }


    @Slf4j
    static class Hello{

        public String callA(){
            log.info("callA");
            return "A";
        }

        public String callB(){
            log.info("callB");
            return "B";
        }
    }

}

