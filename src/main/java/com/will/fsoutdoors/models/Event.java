package com.will.fsoutdoors.models;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String category;
    private String fishTypes;
    private int fee;
    private String location;
    private String description;
    private String start;
    private String until;

//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "mm-dd-yyyy hh:mm")
//    private Date start;

//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "mm-dd-yyyy hh:mm")
//    private Date until;

    @ColumnDefault(value = "true")
    private boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "mm-dd-yyyy hh:mm")
    private Date created;

    public Event() {
    }

    @OneToMany(mappedBy = "event",
    cascade = CascadeType.ALL)
    private List<Entry> entries;

    @OneToOne
    @JoinColumn(name = "host")
    private User host;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }


    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFishTypes() {
        return fishTypes;
    }

    public void setFishTypes(String fishTypes) {
        this.fishTypes = fishTypes;
    }
}
