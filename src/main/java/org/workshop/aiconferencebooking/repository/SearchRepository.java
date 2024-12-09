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

    // lowercase the input
    var lowerInput = input.toLowerCase();
    // create a native query to search for talks with the input in the title, description or speaker username format the code to keep it to 80 characters per line
    var query = em.createNativeQuery("select t.* from Talk t join Person p on t.speaker_id = p.id where lower(t.description) like '%" + lowerInput + "%' OR lower(t.title) like '%" + lowerInput + "%' OR lower(p.username) like '%" + lowerInput + "%'", Talk.class);
    // execute the query
    var talks = (List<Talk>) query.getResultList();
    // return the result
    return talks;
    }
}
