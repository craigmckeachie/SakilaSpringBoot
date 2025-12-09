package com.pluralsight.SakilaSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SakilaSpringBootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SakilaSpringBootApplication.class, args);

        String[] beanNames = context.getBeanDefinitionNames();

        System.out.println("\nMy Application Beans:");
        for (String beanName : beanNames) {
            Object bean = context.getBean(beanName);
            String packageName = bean.getClass().getPackageName();


            if (packageName.startsWith("com.pluralsight.SakilaSpringBoot")) {
                System.out.println(beanName + " -> " + bean.getClass().getSimpleName());
            }
        }
    }

}
