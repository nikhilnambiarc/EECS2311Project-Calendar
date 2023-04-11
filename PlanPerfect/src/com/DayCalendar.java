package com;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class DayCalendar extends Calendar {

    public LocalDate calDate;

    public DayCalendar(ArrayList<CalendarEvent> events) {
        super(events);
        calDate = LocalDate.now();
    }

    @Override
    public boolean dateInRange(LocalDate date) {
        return calDate.equals(date);
    }

    @Override
    public LocalDate getDateFromDay(DayOfWeek day) {
        return calDate;
    }

    @Override
    public int numDaysToShow() {
        return 1;
    }

    @Override
    public DayOfWeek getStartDay() {
        return calDate.getDayOfWeek();
    }

    @Override
    public DayOfWeek getEndDay() {
        return calDate.getDayOfWeek();
    }

    @Override
    public void setRangeToToday() {
        calDate = LocalDate.now();
    }

    @Override
    public double dayToPixel(DayOfWeek dayOfWeek) {
        return TIME_COL_WIDTH;
    }

    public void nextDay() {
        calDate = calDate.plusDays(1);
        repaint();
    }

    public void prevDay() {
        calDate = calDate.minusDays(1);
        repaint();
    }

    public void updateAndRepaint() {
        repaint();
    }

    public void addEvents(List<CalendarEvent> newEvents) {
        if (newEvents != null) {
            for (CalendarEvent event : newEvents) {
                addEvent(event);
            }
            updateAndRepaint();
        }
    }
}
