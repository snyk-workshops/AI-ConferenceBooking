package org.workshop.aiconferencebooking.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workshop.aiconferencebooking.model.Event;
import org.workshop.aiconferencebooking.model.JsonViews;
import org.workshop.aiconferencebooking.repository.EventRepository;

import java.util.List;

@RestController
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @JsonView(JsonViews.Sparse.class)
    @GetMapping("/api/v1/events")
    public List<Event> events() {
        return eventRepository.findAll();
    }
}
