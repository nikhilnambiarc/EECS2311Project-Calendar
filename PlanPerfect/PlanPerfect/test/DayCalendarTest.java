import java.awt.BorderLayout;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.util.logging.Handler;
import com.Calendar;
import com.CalendarEvent;
import com.DayCalendar;
import com.NewEventPanel;

public class DayCalendarTest {
	private final Calendar calendar;
	private LocalDate selectedDate;
	public DayCalendarTest(Calendar calendar,Clock clock){
		if (calendar == null)
      throw new IllegalArgumentException("The calendar may not be null.");
 
    this.calendar     = calendar;
    this.selectedDate = LocalDate.now(clock);
	}
	
		
	
	public static void main(String[] args) {
		 Calendar calendar;
		 LocalDate selectedDate;
		JFrame frm = new JFrame();
		selectedDate=LocalDate.now();
    	String description="";
 
		CalendarEvent c=new CalendarEvent( selectedDate, selectedDate, selectedDate, description);

		ArrayList<String> events = new ArrayList<>();
		events.add(c.addNewEvent(selectedDate, description));
		//events.add(new CalendarEvent(LocalDate.of(2016, 11, 14), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 14/11 9:00-9:20"));
		//events.add(new CalendarEvent(LocalDate.of(2016, 11, 15), LocalTime.of(12, 0), LocalTime.of(13, 20), "Test 15/11 12:00-13:20"));
		//events.add(new CalendarEvent(LocalDate.of(2016, 11, 16), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 16/11 9:00-9:20"));
		//events.add(new CalendarEvent(LocalDate.of(2016, 11, 17), LocalTime.of(12, 15), LocalTime.of(14, 20), "Test 17/11 12:15-14:20"));
		//events.add(new CalendarEvent(LocalDate.of(2016, 11, 18), LocalTime.of(9, 30), LocalTime.of(10, 00), "Test 18/11 9:30-10:00"));
		//events.add(new CalendarEvent(LocalDate.of(2016, 11, 18), LocalTime.of(16, 00), LocalTime.of(16, 45), "Test 18/11 16:00-16:45"));

		//DayCalendar cal = new DayCalendar(events);
		
		// cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
		// cal.addCalendarEmptyClickListener(e -> {
		// 	System.out.println(e.getDateTime());
		// 	System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
		// });

// 		JButton goToTodayBtn = new JButton("Today");
// 		goToTodayBtn.addActionListener(e -> cal.goToToday());

// 		JButton nextDayBtn = new JButton(">");
// 		nextDayBtn.addActionListener(e -> cal.nextDay());

// 		JButton prevDayBtn = new JButton("<");
// 		prevDayBtn.addActionListener(e -> cal.prevDay());

// 		JPanel weekControls = new JPanel();
// 		weekControls.add(prevDayBtn);
// 		weekControls.add(goToTodayBtn);
// 		weekControls.add(nextDayBtn);

// 		frm.add(weekControls, BorderLayout.NORTH);

// 		frm.add(cal, BorderLayout.CENTER);
// 		frm.setSize(1000, 900);
// 		frm.setVisible(true);
// 		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
 }
