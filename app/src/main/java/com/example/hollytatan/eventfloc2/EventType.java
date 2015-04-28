package com.example.hollytatan.eventfloc2;

/**
 * Created by hollytatan on 28/04/15.
 */
public class EventType {
    private int eventTypeID;
    private String eventType;

    public EventType(int eventTypeID, String eventType) {
        this.eventTypeID = eventTypeID;
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(int eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

}
