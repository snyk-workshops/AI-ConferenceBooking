package org.workshop.aiconferencebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.workshop.aiconferencebooking.model.Person;
import org.workshop.aiconferencebooking.model.Role;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);

    List<Person> findByEmail(String email);

    List<Person> findByRole(Role role);
}
