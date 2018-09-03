package com.ansoft.shutterbox.Data;

/**
 * Created by Abinash on 3/7/2016.
 */
public class ListItemData {

    String eventType;
    String eventName;
    String[] imagesLink;
    String eventDate;
    String eventDuration;
    String eventDescription;
    String eventTheme;
    int eventMaxPart;
    int eventCost;
    String eventLocation;

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String[] getImagesLink() {
        return imagesLink;
    }

    public void setImagesLink(String[] imagesLink) {
        this.imagesLink = imagesLink;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(String eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventTheme() {
        return eventTheme;
    }

    public void setEventTheme(String eventTheme) {
        this.eventTheme = eventTheme;
    }

    public int getEventMaxPart() {
        return eventMaxPart;
    }

    public void setEventMaxPart(int eventMaxPart) {
        this.eventMaxPart = eventMaxPart;
    }

    public int getEventCost() {
        return eventCost;
    }

    public void setEventCost(int eventCost) {
        this.eventCost = eventCost;
    }

}
