package com.example.demoIO;

import com.example.demoIO.entity.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoIoApplication {

    @Bean
    public static Entity getEntity() {
        return new Entity();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoIoApplication.class, args);
//        Options opt = new OptionsBuilder()
//                .include(ServiceImp.class.getSimpleName())
//                .include(Service.class.getSimpleName())
//                .include(Repository.class.getSimpleName())
//                .forks(3)
//                .build();
//
//        new Runner(opt).run();
        System.out.println("S T A R T E D.........");
    }
}
