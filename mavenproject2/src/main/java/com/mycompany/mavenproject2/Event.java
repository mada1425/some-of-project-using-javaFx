package com.mycompany.mavenproject2;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.*;

@Entity
@Table(name = "Event")  // Name of the table in the database
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatically generate event_id
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "event_details", nullable = false)
    private String eventDetails;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "event_date", nullable = false)
    private Date eventDate;
@Column(name = "event_time", nullable = false)
private String eventTime; // Store time as a String


    @Column(name = "event_location", nullable = false)
    private String eventLocation;

    Event() {
    }

    // Getters and setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public Event(String eventName, String eventDetails, String eventLocation) {
        this.eventName = eventName;
        this.eventDetails = eventDetails;
        this.eventLocation = eventLocation;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
    return eventTime;
}

public void setEventTime(String eventTime) {
    this.eventTime = eventTime;
}



    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    
}
