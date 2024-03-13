package org.workshop.aiconferencebooking.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @JsonView(JsonViews.Sparse.class)
    @Id
    @GeneratedValue
    private Long id;

    @JsonView(JsonViews.Sparse.class)
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Talk> talks = new ArrayList<>();

    @JsonView(JsonViews.Sparse.class)
    String name;

    @JsonView(JsonViews.Sparse.class)
    String description;

    @JsonView(JsonViews.Sparse.class)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Start date cannot be empty")
    Date startDate;

    @JsonView(JsonViews.Sparse.class)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "End date cannot be empty")
    Date endDate;

    public Event(){}

    public Event(String name, String description, Date startDate, Date endDate) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
