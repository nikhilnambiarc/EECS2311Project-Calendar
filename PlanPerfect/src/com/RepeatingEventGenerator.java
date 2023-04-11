package com;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepeatingEventGenerator {
    public enum RepeatOption {
        NEVER, DAILY, EVERY_2_DAYS, WEEKLY, EVERY_2_WEEKS, MONTHLY, YEARLY
    }

    public static List<CalendarEvent> generateRepeatingEvents(CalendarEvent event, RepeatOption repeatOption,
            LocalDate endDate) {
        List<CalendarEvent> events = new ArrayList<>();
        LocalDate eventDate = event.getDate();
        events.add(event);

        while (!eventDate.isAfter(endDate)) {
            switch (repeatOption) {
                case DAILY:
                    eventDate = eventDate.plusDays(1);
                    break;
                case EVERY_2_DAYS:
                    eventDate = eventDate.plusDays(2);
                    break;
                case WEEKLY:
                    eventDate = eventDate.plusWeeks(1);
                    break;
                case EVERY_2_WEEKS:
                    eventDate = eventDate.plusWeeks(2);
                    break;
                case MONTHLY:
                    eventDate = eventDate.plusMonths(1);
                    break;
                case YEARLY:
                    eventDate = eventDate.plusYears(1);
                    break;
                default:
                    return events;
            }

            if (!eventDate.isAfter(endDate)) {
                CalendarEvent newEvent = new CalendarEvent(eventDate, event.getStart(), event.getEnd(),
                        event.getText());
                events.add(newEvent);
            }
        }

        return events;
    }
//use switch cases for different event cases
    public static RepeatingEventGenerator.RepeatOption repeatOptionFromString(String option) {
        switch (option) {
            case "Every Day":
                return RepeatingEventGenerator.RepeatOption.DAILY;
            case "Every 2 Days":
                return RepeatingEventGenerator.RepeatOption.EVERY_2_DAYS;
            case "Every Week":
                return RepeatingEventGenerator.RepeatOption.WEEKLY;
            case "Every 2 Weeks":
                return RepeatingEventGenerator.RepeatOption.EVERY_2_WEEKS;
            case "Every Month":
                return RepeatingEventGenerator.RepeatOption.MONTHLY;
            case "Every Year":
                return RepeatingEventGenerator.RepeatOption.YEARLY;
            default:
                return RepeatingEventGenerator.RepeatOption.NEVER;
        }
    }
}
