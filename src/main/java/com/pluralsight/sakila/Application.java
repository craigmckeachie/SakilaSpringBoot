package com.pluralsight.sakila;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        // This line starts the entire Spring Boot application.
        // It does 3 main things:
        // 1. Creates the Spring "ApplicationContext" (this is like the brain of Spring that manages everything).
        // 2. Scans for your @Component classes and creates them automatically.
        // 3. Starts the web server (if your app had web controllers), or calls CommandLineRunner beans.
        ApplicationContext context = SpringApplication.run(Application.class, args);


        // After the line above runs, your app is running!
        // If you have a CommandLineRunner (like UserInterface), its run() method will now be called.
        //It does this for you.
        // UserInterface userInterface = new UserInterface();
        // userInterface.run()

        //This is just logging code to show you what Spring Boot is doing.
        //More specifically, it's showing you which objects it created for you.
        String[] beanNames = context.getBeanDefinitionNames();

        System.out.println("\nMy Application Beans:");
        for (String beanName : beanNames) {
            Object bean = context.getBean(beanName);
            String packageName = bean.getClass().getPackageName();

            if (packageName.startsWith("com.pluralsight.sakila")) {
                System.out.println(beanName + " -> " + bean.getClass().getSimpleName());
            }
        }
    }

}
