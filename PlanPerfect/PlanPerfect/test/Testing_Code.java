
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.Calendar;
import com.CalendarEvent;
import com.WeekCalendar;



import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;



public class Testing_Code {

    
    private Calendar calendar;
    private ArrayList<CalendarEvent> events;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String name;
    
// Testing the buttons
@Test
public void testGetName() {
    LocalDate startDate = LocalDate.of(2023, 3, 20);
    LocalTime startTime = LocalTime.of(10, 0);
    LocalTime endTime = LocalTime.of(11, 0);
    CalendarEvent event = new CalendarEvent(startDate, startTime, endTime, "Test Event");

    assertEquals("Test Event", event.getText());
}

// =========//=========//=========Add Event Testing=========//=========//=========//=========
@Test
public void Buttontest_AddEvent() {
    WeekCalendar cal = new WeekCalendar(null);
    ArrayList<CalendarEvent> events = new ArrayList<>();

    LocalDate startDate = LocalDate.of(2023, 2, 14);
    LocalTime startTime = LocalTime.of(04, 0);
    LocalTime endTime = LocalTime.of(07, 0);
    CalendarEvent event = new CalendarEvent(startDate, startTime, endTime, "Add Event test 1");

    cal.addEvent(event);

    assertEquals(1, events);
    assertEquals(event, events); // Checking the size that we added in test case and original

}

// Testing the first corner of Add Event
@Test
public void Buttontest_AddEvent2() {
    WeekCalendar cal = new WeekCalendar(null);
    ArrayList<CalendarEvent> events = new ArrayList<>();

    LocalDate startDate = LocalDate.of(2023, 2, 14);
    LocalTime startTime = LocalTime.of(00, 0); // Starting of the day
    LocalTime endTime = LocalTime.of(02, 0);
    CalendarEvent event = new CalendarEvent(startDate, startTime, endTime, "Add Event test 2");

    cal.addEvent(event);

    assertEquals(2, events);
    assertEquals(event, events); // Checking the size that we added in test case and original
}

// Testing the second corner of add event
@Test
public void Buttontest_AddEvent3() {
    WeekCalendar cal = new WeekCalendar(null);
    ArrayList<CalendarEvent> events = new ArrayList<>();

    LocalDate startDate = LocalDate.of(2023, 2, 14);
    LocalTime startTime = LocalTime.of(23, 10);
    LocalTime endTime = LocalTime.of(23, 59); // end of the day
    CalendarEvent event = new CalendarEvent(startDate, startTime, endTime, "Add Event test 3");

    cal.addEvent(event);

    assertEquals(3, events);
    assertEquals(event, events); // Checking the size that we added in test case and original
}
// =========//=========//=======//=Delete Event Testing=//=======//=========//=========//=========

@Test
public void Buttontest_DeleteEvent1() {
    WeekCalendar cal = new WeekCalendar(null);
    ArrayList<CalendarEvent> events = new ArrayList<>();

    LocalDate startDate = LocalDate.of(2023, 2, 14);
    LocalTime startTime = LocalTime.of(04, 0);
    LocalTime endTime = LocalTime.of(07, 0);
    CalendarEvent event = new CalendarEvent(startDate, startTime, endTime, "Test Event");
    cal.removeEvent(event);

    assertEquals(3, events.size());
}

@Test
public void Buttontest_DeleteEvent2() {
    WeekCalendar cal = new WeekCalendar(null);
    ArrayList<CalendarEvent> events = new ArrayList<>();

    LocalDate startDate = LocalDate.of(2023, 2, 14);
    LocalTime startTime = LocalTime.of(00, 0); // Starting of the day
    LocalTime endTime = LocalTime.of(02, 0);
    CalendarEvent event = new CalendarEvent(startDate, startTime, endTime, "Test Event");
    cal.removeEvent(event);

    assertEquals(2, events.size());
}

@Test
public void Buttontest_DeleteEvent3() {
    WeekCalendar cal = new WeekCalendar(null);
    ArrayList<CalendarEvent> events = new ArrayList<>();

    LocalDate startDate = LocalDate.of(2023, 2, 14);
    LocalTime startTime = LocalTime.of(23, 10);
    LocalTime endTime = LocalTime.of(23, 59);
    CalendarEvent event = new CalendarEvent(startDate, startTime, endTime, "Test Event");
    cal.removeEvent(event);

    assertEquals(1, events.size());
}


@Test
public void testCheckConflict() {
    // Create two events with no conflict
    CalendarEvent event1 = new CalendarEvent(LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), "Event 1");
    CalendarEvent event2 = new CalendarEvent(LocalDate.now(), LocalTime.of(13, 0), LocalTime.of(15, 0), "Event 2");
    
    // Test that they don't conflict
    assertFalse(event1.check_Conflict(event2));
    assertFalse(event2.check_Conflict(event1));
    
    // Create two events with overlapping times
    CalendarEvent event3 = new CalendarEvent(LocalDate.now(), LocalTime.of(12, 0), LocalTime.of(14, 0), "Event 3");
    CalendarEvent event4 = new CalendarEvent(LocalDate.now(), LocalTime.of(13, 0), LocalTime.of(15, 0), "Event 4");
    
    // Test that they conflict
    assertTrue(event3.check_Conflict(event4));
    assertTrue(event4.check_Conflict(event3));
    
    // Create two events with the same time
    CalendarEvent event5 = new CalendarEvent(LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), "Event 5");
    CalendarEvent event6 = new CalendarEvent(LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), "Event 6");
    
    // Test that they conflict
    assertTrue(event5.check_Conflict(event6));
    assertTrue(event6.check_Conflict(event5));
}
@Test
public void testAddNoConflict() {
    CalendarEvent event = new CalendarEvent(date, startTime, endTime, name);
    events.add(event);
    assertFalse(event.check_Conflict(event));
}

@Test
public void testAddConflict() {
    CalendarEvent event1 = new CalendarEvent(date, startTime, endTime, name);
    CalendarEvent event2 = new CalendarEvent(date, startTime.plusMinutes(30), endTime.plusMinutes(30), "Conflict Event");
    events.add(event1);
    assertTrue(event2.check_Conflict(event1));
}

}
