package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*","order*","save*"};

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) throws Exception{
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();
        LogTraceFilterHandler target = new LogTraceFilterHandler(orderRepositoryV1, logTrace, PATTERNS);
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[]{OrderRepositoryV1.class}, target);
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) throws  Exception{
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        LogTraceFilterHandler target = new LogTraceFilterHandler(orderServiceV1, logTrace, PATTERNS);
        OrderServiceV1 proxy = (OrderServiceV1)Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[] {OrderServiceV1.class}, target);

        return proxy;
    }

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) throws Exception{
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
        LogTraceFilterHandler target = new LogTraceFilterHandler(orderControllerV1, logTrace, PATTERNS);
        OrderControllerV1 proxy = (OrderControllerV1)Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[]{OrderControllerV1.class} , target);

        return proxy;
    }
}
