package com;

import java.util.EventListener;
//code defines a Java interface named CalendarEmptyClickListener
// This extends the EventListener interface, which is a marker interface used to identify interfaces as event listener interfaces
public interface CalendarEmptyClickListener extends EventListener {
    // Event dispatch methods
    //interface defines a single method
    //This method is meant to be invoked when an empty space is clicked in a calendar
    void calendarEmptyClick(CalendarEmptyClickEvent e);
    //Interface serves as a contract that any class that wishes to handle calendar empty click events must implement
    //Any class that implements this interface must provide an implementation
}

