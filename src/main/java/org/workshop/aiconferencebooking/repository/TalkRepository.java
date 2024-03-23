package org.workshop.aiconferencebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.workshop.aiconferencebooking.model.Talk;

import java.util.List;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Long> {

    Talk findByTitleContaining(String title);
    Talk findByDescription(String description);
    List<Talk> findBySpeakerUsername(String username);
}
