package com;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//imports
//subclass of the "Calendar" class
public class WeekCalendar extends Calendar {
    
//contains several methods and variables that are used to display and manipulate a weekly calendar view
    
    private Week week;

    //functionality in class interface method
    public WeekCalendar(ArrayList<CalendarEvent> events) {
        //super() method is called with this parameter to initialize the "events" instance variable
        super(events);
        
        week = new Week(LocalDate.now());
    }
//overridden method that returns a "LocalDate" object corresponding to the given "DayOfWeek" value 
    @Override
    protected boolean dateInRange(LocalDate date) {
        
        return Week.getStartOfWeek(date).equals(week.getDay(DayOfWeek.MONDAY));
    }

    // method that returns the number of days to be displayed on the calendar view
    @Override
    protected LocalDate getDateFromDay(DayOfWeek day) {
        return week.getDay(day);
    }

    //methods are overridden methods that return the start and end days of the week
    protected int numDaysToShow() {
        return 7;
        
    }

    //comparing the start of the week obtained from the given date 
    @Override
    protected DayOfWeek getStartDay() {
        return DayOfWeek.MONDAY;
    }

    @Override
    protected DayOfWeek getEndDay() {
        return DayOfWeek.SUNDAY;
    }

    @Override
    protected void setRangeToToday() {
        week = new Week(LocalDate.now());
    }

    //method that converts the given "DayOfWeek" value to a pixel value
    @Override
    protected double dayToPixel(DayOfWeek dayOfWeek) {
        return TIME_COL_WIDTH + getDayWidth() * (dayOfWeek.getValue() - 1);
    }

    //methods increment and decrement the "week" object
    
    public void nextWeek() {
        week = week.nextWeek();
        repaint();
    }

    //methods are used to navigate between weeks on the calendar view
    public void prevWeek() {
        week = week.prevWeek();
        repaint();
    }

    //methods increment and decrement the "week" object by 4 weeks
    public void nextMonth() {
        week = new Week(week.getDay(DayOfWeek.MONDAY).plusWeeks(4));
        repaint();
    }

    //methods are used to navigate between months on the calendar view
    public void prevMonth() {
        week = new Week(week.getDay(DayOfWeek.MONDAY).minusWeeks(4));
        repaint();
        
    }

    //method is used to update the calendar view and repaint it on the screen
    public void updateAndRepaint() {
        repaint();
    }

    //takes a list of "CalendarEvent" objects as a parameter and adds them to the "events" instance variable
    public void addEvents(List<CalendarEvent> newEvents) {
        if (newEvents != null) {
            //calls the "updateAndRepaint()" method to update the calendar view with the new events
            for (CalendarEvent event : newEvents) {
                addEvent(event);
                //specialized version of the "Calendar" class that is designed to display and manipulate a weekly
            }
            //update the calendar view
            updateAndRepaint();
        }
    }
}
