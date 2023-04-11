package com;
//declares the package name for the class
import java.awt.*;
import java.time.LocalDateTime;
//imports
public class CalendarEmptyClickEvent extends AWTEvent {
    private LocalDateTime dateTime;
//declares a private instance variable called dateTime of type LocalDateTime. This variable will hold the date and time associated with the event
    public CalendarEmptyClickEvent(Object source, LocalDateTime dateTime) {
        //super method of CalendarEmptyClickEvent to go over old method
        super(source, 0);
        this.dateTime = dateTime;
    }
//constructor for the CalendarEmptyClickEvent class. It takes two parameters: source, which is the object that triggered the event
    //calls the super method to invoke the superclass constructor, passing source and 0 as parameters
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    //this class provides a custom event type for a GUI calendar application
    //When the user clicks on a date with no associated events, a new object is created and dispatched to interested event listeners
}
