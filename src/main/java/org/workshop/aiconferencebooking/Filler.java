package org.workshop.aiconferencebooking;

import org.springframework.stereotype.Component;
import org.workshop.aiconferencebooking.model.Event;
import org.workshop.aiconferencebooking.model.Person;
import org.workshop.aiconferencebooking.model.Role;
import org.workshop.aiconferencebooking.model.Talk;
import org.workshop.aiconferencebooking.repository.EventRepository;
import org.workshop.aiconferencebooking.repository.TalkRepository;
import org.workshop.aiconferencebooking.service.PersonService;

import java.util.Date;

@Component
public class Filler {

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
        newPerson.setRoles(role);
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
}
