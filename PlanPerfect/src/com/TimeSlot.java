package com;

//imports
import java.time.LocalTime;
//class that represents a time interval between a start time and an end time
public class TimeSlot {
    //which has two private instance variables: startTime and endTime, both of which are of type LocalTime
    private LocalTime startTime;
    private LocalTime endTime;
//constructor that takes two parameters, representing the start time and end time of the time slot
    public TimeSlot(LocalTime start, LocalTime end) {
        this.startTime = start;
        this.endTime = end;
    }
    //return the start time and end time of the time slot respectively
    public LocalTime getStart() {
        //class is a part of the java.time package
        LocalTime start = this.startTime;
        //output time slot inputted by user
        return start;
    }
//represents a time without a date or time zone
    public LocalTime getEnd() {
        //manipulating time values, including arithmetic operations like addition and subtraction
        LocalTime end = this.endTime;
        return end;
        //class can be used to represent a period of time, such as a time slot in a schedule or a duration of an event
        //The class provides convenient methods
    }
}



