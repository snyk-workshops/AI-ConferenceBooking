package org.workshop.aiconferencebooking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AiConferenceBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiConferenceBookingApplication.class, args);
    }

    @Bean
    public CommandLineRunner fill(Filler filler) {
        System.out.println("start filling");
        return (args) -> {
            filler.createAdmin("Admin", "admin");
            filler.createSpeaker("micah", "123123");
            System.out.println("READY!");
        };
    }
}
