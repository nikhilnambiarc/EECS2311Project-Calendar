package com;
//imports

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//A LocalDateTime object representing the start date
//A LocalDateTime object representing the end date
//An integer representing the number of days between each repeated date
public class CalendarEventUtil {
  public static List<LocalDateTime> getRepeatedDates(LocalDateTime start, LocalDateTime end, int intervalInDays) {
    List<LocalDateTime> repeatedDates = new ArrayList<>();
    //method returns a list of LocalDateTime objects representing all the repeated dates between the start and end dates
    LocalDateTime currentDate = start;
//Using while loop, iterate through all the dates between the start and end dates, adding each date
    while (currentDate.isBefore(end)) {
      repeatedDates.add(currentDate);
      currentDate = currentDate.plusDays(intervalInDays);
    }
//method is static, so it can be called on the CalendarEventUtil class itself, rather than on an instance of the class
    return repeatedDates;
  }

}
