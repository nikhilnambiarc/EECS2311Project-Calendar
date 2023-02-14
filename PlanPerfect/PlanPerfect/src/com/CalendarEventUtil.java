package com;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CalendarEventUtil {
    public static List<LocalDateTime> getRepeatedDates(LocalDateTime startTime, LocalDateTime endTime,int intervalInDays){
        List<LocalDateTime> repeatedDates = new ArrayList<>();
        LocalDateTime currentDate = startTime;
        while (currentDate.isBefore(endTime)) {
            repeatedDates.add(currentDate);
            currentDate = currentDate.plusDays(intervalInDays);
          }
      
          return repeatedDates;
        }
      }
    

    

