package org.workshop.aiconferencebooking.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.workshop.aiconferencebooking.model.Talk;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SearchRepository {

    private final EntityManager em;
    private final DataSource dataSource;

    public SearchRepository(EntityManager em, DataSource dataSource) {
        this.em = em;
        this.dataSource = dataSource;
    }

    public List<Talk> searchTalk(String input) {

        return null;
    }
}
