package org.workshop.aiconferencebooking;

import org.springframework.stereotype.Component;
import org.workshop.aiconferencebooking.model.Person;
import org.workshop.aiconferencebooking.model.Role;
import org.workshop.aiconferencebooking.service.PersonService;

@Component
public class Filler {

    private final PersonService personService;

    public Filler(PersonService personService) {
        this.personService = personService;
    }

    public void createAdmin(String username, String password) {
        createPerson(
            username, "ADMIN", "ADMIN", username + "@admin.com",
            null, null, password, Role.ROLE_ADMIN
        );
    }

    public void createSpeaker(String username, String password) {
        createPerson(
            username, username, "Speaker", username + "@speaker.com",
            null, null, password, Role.ROLE_SPEAKER
        );
    }

    public void createAttendee(String username, String password) {
        createPerson(
            username, username, "Attendee", username + "@attendee.com",
            null, null, password, Role.ROLE_ATTENDEE
        );
    }

    public void createPerson(
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
        personService.savePerson(newPerson);
    }
}
