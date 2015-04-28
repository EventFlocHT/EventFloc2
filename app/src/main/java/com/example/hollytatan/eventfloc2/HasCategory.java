package com.example.hollytatan.eventfloc2;

/**
 * Created by hollytatan on 28/04/15.
 */
public class HasCategory {
    private int eventID;
    private int eventTypeID;

    public HasCategory(int eventID, int eventTypeID) {
        this.eventID = eventID;
        this.eventTypeID = eventTypeID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(int eventTypeID) {
        this.eventTypeID = eventTypeID;
    }
}
