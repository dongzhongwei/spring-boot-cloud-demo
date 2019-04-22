package com.ddw.demo.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import javax.servlet.ServletException;
import java.lang.reflect.Method;

/**
 * @author ddw
 * @version 1.0
 * @date 2019-03-25 16:50
 * @Description
 */
public class KK implements Person {

    @Override
    public String doSomething() {
        System.out.println("OK");
        try {
            throw new RuntimeException("我是故意的");
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return "OK";
    }
}
// 代理类实现的接口
interface Person {
    String doSomething();
}

// 执行的 MethodBeforeAdvice
class SimpleBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("前置通知");
//        System.out.println("Currently Processing " + method);
    }
}

// 执行的 MethodBeforeAdvice
class SimpleBeforeAdvice1 implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("前置通知1");
//        System.out.println("Currently Processing " + method);
    }
}

// 执行的 AfterReturningAdvice
class SimpleAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("后置通知");
    }
}

// 执行的 MethodInterceptor
class SimpleMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("环绕通知--执行方法钱");
        invocation.proceed();
        System.out.println("环绕通知--执行方法后");
        return "SimpleBeforeAdvice";
    }
}




class ProxyFactoryTest {
    public static void main(String[] args) {
        // 被代理的类
        KK kk = new KK();
        // 创建 Proxy 的工厂类
        ProxyFactory proxyFactory = new ProxyFactory(kk);
        // 代理类中要运用的 Advice
        MethodBeforeAdvice simpleBeforeAdvice = new SimpleBeforeAdvice();
        MethodBeforeAdvice simpleBeforeAdvice1 = new SimpleBeforeAdvice1();

        AfterReturningAdvice simpleAfterReturningAdvice = new SimpleAfterReturningAdvice();

        // 代理类中要运用的 MethodInterceptor
        MethodInterceptor simpleMethodInterceptor = new SimpleMethodInterceptor();

        ThrowsAdvice throwsAdvice = new SimpleThrowsAdvice();

        // 给 proxyFactory 添加 Advice
        proxyFactory.addAdvice(simpleBeforeAdvice1);
        proxyFactory.addAdvice(simpleBeforeAdvice);
        proxyFactory.addAdvice(simpleAfterReturningAdvice);
        proxyFactory.addAdvice(simpleMethodInterceptor);
        proxyFactory.addAdvice(throwsAdvice);

        // 通过 proxyFactory 创建 代理类
        Person nKK = (Person)proxyFactory.getProxy();
        // 执行代理类的方法
        nKK.doSomething();
    }
}
