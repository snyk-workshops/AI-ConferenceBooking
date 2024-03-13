package org.workshop.aiconferencebooking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Temporal;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Talk {

    @JsonView(JsonViews.Sparse.class)
    @Id
    @GeneratedValue
    private Long id;

    @JsonView(JsonViews.Sparse.class)
    @ManyToOne
    private Person speaker;

    @JsonIgnoreProperties("talks")
    @ManyToOne
    private Event event;

    @JsonView(JsonViews.Sparse.class)
    @Column(length = 4000)
    private String title;

    @JsonView(JsonViews.Sparse.class)
    @Column(length = 4000)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Start date cannot be empty")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Start date cannot be empty")
    private Date endDate;

    public Talk(){}

    public Talk(String title, String description, Date startDate, Date endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Person speaker) {
        this.speaker = speaker;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
