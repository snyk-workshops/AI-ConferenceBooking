package org.workshop.aiconferencebooking.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.workshop.aiconferencebooking.exception.EmailTakenException;
import org.workshop.aiconferencebooking.exception.UsernameTakenException;
import org.workshop.aiconferencebooking.model.Person;
import org.workshop.aiconferencebooking.model.Role;
import org.workshop.aiconferencebooking.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person savePerson(Person person) {
        if (person.getPassword() != null && !person.getPassword().isEmpty()) {
            person.setEncryptedPassword(passwordEncoder.encode(person.getPassword()));
        }
        return personRepository.save(person);
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    public List<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person findById(Long id) {
        return personRepository.findById(id).get();
    }

    public void removePerson(Person person) {
        personRepository.delete(person);
    }

    public void removePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Person registerNewPerson(Person person) throws EmailTakenException, UsernameTakenException {
        String username = person.getUsername();
        String email = person.getEmail();

        if (findByUsername(username) != null) {
            throw new UsernameTakenException("Username is already taken: " + username);
        }

        if (!findByEmail(email).isEmpty()) {
            throw new EmailTakenException("Email is already exists: " + email);
        }

        person.setRoles(Role.ROLE_ATTENDEE);

        return savePerson(person);
    }
}