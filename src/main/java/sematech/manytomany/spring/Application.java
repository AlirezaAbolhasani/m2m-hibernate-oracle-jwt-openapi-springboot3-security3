package sematech.manytomany.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        ApplicationContext context =new  AnnotationConfigApplicationContext();

    }

}
