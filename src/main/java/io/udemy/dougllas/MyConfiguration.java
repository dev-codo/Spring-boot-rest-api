package io.udemy.dougllas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//@Profile("PROD") // will only execute in PROD env, check also application.properties
// OR...
@Development // custom annotation
public class MyConfiguration {

    @Bean("appNameHere")
    public String applicationName() {
        return "Sales system";
    }

    @Bean
    public CommandLineRunner execute() {
        return args -> {
            System.out.println("---> RUNNING MYCONFIGURATION");
        };
    }

}
