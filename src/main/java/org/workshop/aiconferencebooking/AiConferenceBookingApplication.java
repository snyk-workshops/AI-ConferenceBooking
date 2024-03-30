package org.workshop.aiconferencebooking;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.workshop.aiconferencebooking.model.Event;
import org.workshop.aiconferencebooking.model.Person;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class AiConferenceBookingApplication {

    private final ServerProperties serverProperties;

    public AiConferenceBookingApplication(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    public CommandLineRunner fill(Filler filler) {
        System.out.println("Start Filling...");
        return (args) -> {
            filler.createAdmin("Admin", "admin");
            filler.createAttendees(10);
            List<Person> speakers = filler.createSpeakers(10);
            for (var speaker : speakers) {
                System.out.printf(
                    "Access talks for %s at: http://localhost:%s/talks?username=%s\n",
                    speaker.getUsername(), serverProperties.getPort(), speaker.getUsername()
                );
            }
            Calendar c = Calendar.getInstance();
            Date start = new Date();
            c.setTime(start);
            c.add(Calendar.DATE, 5);
            Date end = c.getTime();
            Event jcon = filler.createEvent("Java Conference", "Java Conference", start, end);
            // change to username to your own preference for demo purposes
            Person speaker = filler.createSpeaker("micah", "123123");
            System.out.printf(
                "Access talks for %s at: http://localhost:%s/talks?username=%s\n",
                speaker.getUsername(), serverProperties.getPort(), speaker.getUsername()
            );
            filler.createTalkForEvent(jcon, speaker);
            filler.createTalkForEvent(jcon, speaker);
            filler.createTalkForEvent(jcon, speaker);
            filler.createTalksForEvent(20, jcon);
            System.out.println("READY!");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(AiConferenceBookingApplication.class, args);
    }
}
