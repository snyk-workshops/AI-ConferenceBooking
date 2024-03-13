package org.workshop.aiconferencebooking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.workshop.aiconferencebooking.model.Event;
import org.workshop.aiconferencebooking.model.Person;
import org.workshop.aiconferencebooking.model.Role;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
            Person speaker  = filler.createSpeaker("micah", "123123");
            filler.createAttendees(10);
            filler.createSpeakers(10);
            Calendar c = Calendar.getInstance();
            Date start = new Date();
            c.setTime(start);
            c.add(Calendar.DATE, 5);
            Date end = c.getTime();
            Event jcon = filler.createEvent("Java Conference", "Java Conference", start, end);
            filler.createTalksForEvent(10, jcon);
//            c.setTime(start);
//            c.add(Calendar.DATE, 2);
//            c.add(Calendar.HOUR, 4);
//            end = c.getTime();
//            filler.createTalk("Java 101", "Java 101", start, end, speaker, jcon);

            System.out.println("READY!");
        };
    }
}
