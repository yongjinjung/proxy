package hello.proxy.cglib.code;

import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodIntercepter implements MethodInterceptor {

    private final Object target;

    public TimeMethodIntercepter(Object target) {
        this.target = target;
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        Long startTime = System.currentTimeMillis();

        Object result = methodProxy.invoke(target, args);

        Long endTime = System.currentTimeMillis();
        Long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
