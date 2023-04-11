package com;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
//code defines a Week class which can be used to represent a week period. It has an ArrayList of LocalDate objects which represents the days of the week
public class Week {

    private ArrayList<LocalDate> days;

    // Gets week variables from any date (can be within week)
    public Week(LocalDate date) {
        days = new ArrayList<>();
        //constructor takes a LocalDate object and initializes the days ArrayList with the dates of the current week, starting from Monday and ending at Sunday
        LocalDate monday = getStartOfWeek(date);
        days.add(monday);
        //uses the getStartOfWeek method to get the starting date of the current week and then adds the dates for each day of the week using a loop
        for (int i = 1; i < 7; i++) {
            days.add(monday.plusDays(i));
        }
    }
//getStartOfWeek method takes a LocalDate object and returns the starting date of the week for that date
    //subtracts days from the input date until it reaches the previous Monday
    public static LocalDate getStartOfWeek(LocalDate date) {
        LocalDate day = date;
        
        while (day.getDayOfWeek() != DayOfWeek.MONDAY) {
            
            day = day.minusDays(1);
            //method takes a DayOfWeek object and returns the LocalDate for that day in the week
        }
        return day;
    }

    //uses the getValue method of the DayOfWeek enum to get the index of the day in the days ArrayList
    public LocalDate getDay(DayOfWeek dayOfWeek) {
        // DayOfWeek enum starts with monday == 1
        //methods return a new Week object representing the next or previous week from the current week
        return days.get(dayOfWeek.getValue() - 1);
    }
//additional objects added by components of dayOfWeek type
    public Week nextWeek() {
        //use the getDay method
        final LocalDate friday = getDay(DayOfWeek.FRIDAY);
        return new Week(friday.plusDays(3));
    }

    
    public Week prevWeek() {
        final LocalDate monday = getDay(DayOfWeek.MONDAY);
        return new Week(monday.minusDays(3));
    }
//method returns a string representation of the week
    public String toString() {
        return "Week of the " + getDay(DayOfWeek.MONDAY);
    }
//the main method creates a Week object for the current week using LocalDate.now(), prints it
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        Week currentWeek = new Week(now);
        //print out
        //prints the previous and next weeks using the prevWeek and nextWeek methods
        System.out.println(currentWeek);
        System.out.println(currentWeek.prevWeek());
        System.out.println(currentWeek.nextWeek());
    }

}
