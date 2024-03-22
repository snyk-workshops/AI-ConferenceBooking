package org.workshop.aiconferencebooking;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.workshop.aiconferencebooking.model.Event;
import org.workshop.aiconferencebooking.model.Person;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class AiConferenceBookingApplication {

    private final Filler filler;

    public AiConferenceBookingApplication(Filler filler) {
        this.filler = filler;
    }

    @PostConstruct
    public void init() {
        System.out.println("start filling");
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
        System.out.println("READY!");
    }

    public static void main(String[] args) {
        SpringApplication.run(AiConferenceBookingApplication.class, args);
    }
}
