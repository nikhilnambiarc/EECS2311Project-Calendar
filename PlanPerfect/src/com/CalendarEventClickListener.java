package com;

import java.util.EventListener;

public interface CalendarEventClickListener extends EventListener {
    // Event dispatch methods
    void calendarEventClick(CalendarEventClickEvent e);
    //returns a representation of the listener to process
    
}

