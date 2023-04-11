package com;

import java.awt.*;

public class CalendarEventClickEvent extends AWTEvent {

    private CalendarEvent calendarEvent;
//use the getDay method to get the starting date for the week and add or subtract days accordingly
    public CalendarEventClickEvent(Object source, CalendarEvent calendarEvent) {
        super(source, 0);
        this.calendarEvent = calendarEvent;
    }

    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }
}
