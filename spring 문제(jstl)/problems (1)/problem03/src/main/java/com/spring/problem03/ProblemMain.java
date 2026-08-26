package com.spring.problem03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProblemMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("=== Singleton 확인 ===");

        // TODO [문제 3-1] counterBean을 두 번 꺼내 c1, c2에 저장하세요.
        CounterBean c1 = context.getBean(CounterBean.class); // 수정하세요
        CounterBean c2 = context.getBean(CounterBean.class); // 수정하세요

        // TODO [문제 3-2] c1.increment()를 3번 호출하세요.
        c1.increment();
        c1.increment();
        c1.increment();
        // TODO [문제 3-3] c2.getCount()를 출력하세요. (예상 결과: 3)
        //System.out.println("[Singleton] c2.getCount() = " /* + ??? */);
        System.out.println("[Singleton] c2.getCount() = " + c2.getCount());

        // TODO [문제 3-4] c1 == c2 비교 결과를 출력하세요. (예상 결과: true)
        //System.out.println("[Singleton] c1 == c2 : " /* + ??? */);
        System.out.println("[Singleton] c1 == c2 : " + (c1 == c2));

        System.out.println("\n=== Prototype 확인 ===");
        // TODO [문제 3-5] temporaryBean을 두 번 꺼내 t1, t2에 저장하세요.
        TemporaryBean t1 = context.getBean(TemporaryBean.class); // 수정하세요
        TemporaryBean t2 = context.getBean(TemporaryBean.class); // 수정하세요

        // TODO [문제 3-6] t1.getId()와 t2.getId()를 출력하세요. (예상 결과: 서로 다른 값)
        System.out.println("[Prototype] t1.id = " + t1.getId());
        System.out.println("[Prototype] t2.id = " + t1.getId());

        // TODO [문제 3-7] t1 == t2 비교 결과를 출력하세요. (예상 결과: false)
        //System.out.println("[Prototype] t1 == t2 : " /* + ??? */);
        System.out.println("[Prototype] t1 == t2 : " + (t1 == t2));

        ((ClassPathXmlApplicationContext) context).close();
    }
}
