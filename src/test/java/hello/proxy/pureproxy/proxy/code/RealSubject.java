package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
<<<<<<< HEAD
public class RealSubject implements Subject{
=======
public class RealSubject implements Subject {
>>>>>>> cf46abd592ed7d3d46150a21c2fc98843ff81775
    @Override
    public String operation() {
        log.info("실제 객체 호출");
        sleep(1000);
        return "data";
    }

    private void sleep(int millis) {
<<<<<<< HEAD
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
=======
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
>>>>>>> cf46abd592ed7d3d46150a21c2fc98843ff81775
