package org.workshop.aiconferencebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.workshop.aiconferencebooking.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public Person findByUsername(String username);

    public List<Person> findByEmail(String email);
}
