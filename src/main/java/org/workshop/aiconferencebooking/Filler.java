package org.workshop.aiconferencebooking;

import net.datafaker.Faker;
import net.datafaker.providers.movie.Movie;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.workshop.aiconferencebooking.model.Event;
import org.workshop.aiconferencebooking.model.Person;
import org.workshop.aiconferencebooking.model.Role;
import org.workshop.aiconferencebooking.model.Talk;
import org.workshop.aiconferencebooking.repository.EventRepository;
import org.workshop.aiconferencebooking.repository.TalkRepository;
import org.workshop.aiconferencebooking.service.PersonService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class Filler {

    private Faker faker = new Faker();
    Random rand = new Random();
    Calendar c = Calendar.getInstance();

    private final PersonService personService;
    private final EventRepository eventRepository;
    private final TalkRepository talkRepository;

    public Filler(PersonService personService, EventRepository eventRepository, TalkRepository talkRepository) {
        this.personService = personService;
        this.eventRepository = eventRepository;
        this.talkRepository = talkRepository;
    }

    public Person createAdmin(String username, String password) {
        return createPerson(
            username, "ADMIN", "ADMIN", username + "@admin.com",
            null, null, password, Role.ROLE_ADMIN
        );
    }

    public Person createSpeaker(String username, String password) {
        return createPerson(
            username, username, "Speaker", username + "@speaker.com",
            null, null, password, Role.ROLE_SPEAKER
        );
    }

    public Person createAttendee(String username, String password) {
        return createPerson(
            username, username, "Attendee", username + "@attendee.com",
            null, null, password, Role.ROLE_ATTENDEE
        );
    }

    public Person createPerson(
        String username, String firstName, String lastName,
        String email, String phone, String address,
        String password, Role role
    ) {
        var newPerson = new Person(
            username, firstName, lastName,
            email, phone, address
        );
        newPerson.setPassword(password);
        newPerson.setRole(role);
        return personService.savePerson(newPerson);
    }

    public Event createEvent(String name, String description, Date startDate, Date endDate) {
        var newEvent = new Event(name, description, startDate, endDate);
        return eventRepository.save(newEvent);
    }

    public Talk createTalk(String title, String description, Date startDate, Date endDate, Person speaker, Event event) {
        var newTalk = new Talk(title, description, startDate, endDate);
        newTalk.setSpeaker(speaker);
        newTalk.setEvent(event);

        return talkRepository.save(newTalk);
    }

    public void createAttendees(int num) {
        for (int i=0; i < num; i++) {
            createAttendee(faker.name().username(), "123123");
        }
    }

    public List<Person> createSpeakers(int num) {
        List<Person> ret = new ArrayList<>();
        for (int i=0; i < num; i++) {
            ret.add(createSpeaker(faker.name().username(), "123123"));
        }
        return ret;
    }

    public void createTalkForEvent(Event e, Person speaker) {
        c.setTime(new Date());
        c.add(Calendar.DATE, rand.nextInt(4));
        c.add(Calendar.HOUR, rand.nextInt(12));
        Date start = c.getTime();
        c.add(Calendar.HOUR, 1);
        Date end = c.getTime();
        String title, description;
        do {
            String titleName = faker.hobbit().character();
            String titleQuote = faker.hobbit().quote();
            if (titleQuote.length() > 150) {
                titleQuote = titleQuote.substring(0, 150);
            }
            title = String.format("%s Presents: %s", titleName, titleQuote);

            description = faker.hobbit().quote();
        } while (
            talkRepository.findByTitle(title) != null ||
            talkRepository.findByDescription(description) != null
        );
        createTalk(title, description, start, end, speaker ,e);
    }

    public void createTalksForEvent(int num, Event e) {
        List<Person> people = personService.findByRole(Role.ROLE_SPEAKER);
        for (int i=0; i < num; i++) {
            createTalkForEvent(e, people.get(rand.nextInt(people.size()-1)));
        }
    }
}
