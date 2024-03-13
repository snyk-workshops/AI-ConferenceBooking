package org.workshop.aiconferencebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.workshop.aiconferencebooking.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
