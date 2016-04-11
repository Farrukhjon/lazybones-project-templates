package org.farrukh.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(final UserRepository repository) {
        return args -> {
            Arrays.asList("ali, vali, surayo, muhayo".split(","))
                  .forEach(login -> repository.save(new User(login)));
            repository.findAll()
                      .forEach(System.out::println);
        };
    }

}


