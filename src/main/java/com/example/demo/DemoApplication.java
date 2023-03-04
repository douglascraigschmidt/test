package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import static java.lang.System.exit;

@SpringBootApplication
@EnableJpaRepositories
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(MovieRepository repository) {
        return args -> {
            var list = repository.findAll();

            System.out.println("list.size() = " + list.size());
            list
                .forEach(movie ->
                         System.out.print("id = " + movie.id
                                          + "\nvector = "
                                          + movie.vector));

           exit(0);
        };
    }
}
