package org.workshop.aiconferencebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.workshop.aiconferencebooking.model.Talk;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Long> {
}
