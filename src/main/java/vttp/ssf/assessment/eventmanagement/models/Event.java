package vttp.ssf.assessment.eventmanagement.models;

import java.util.Date;

public class Event {

    
    private Integer eventId;

    private String eventName;

    private Integer eventSize;

    private Date eventDate;

    //private Date eventDateConverted;

    private Integer participants;

    public Event(){

    }

    public Event(Integer eventId, String eventName, Integer eventSize, Date eventDate, Integer participants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventSize = eventSize;
        this.eventDate = eventDate;
        this.participants = participants;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getEventSize() {
        return eventSize;
    }

    public void setEventSize(Integer eventSize) {
        this.eventSize = eventSize;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }
    
}
