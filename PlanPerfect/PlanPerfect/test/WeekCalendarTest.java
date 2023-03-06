import com.Calendar;
import com.CalendarEvent;
import com.WeekCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class WeekCalendarTest {
	public static void main(String[] args) {
		JFrame frm = new JFrame();

		ArrayList<CalendarEvent> events = new ArrayList<>();
		events.add(new CalendarEvent(LocalDate.of(2016, 11, 11), LocalTime.of(14, 0), LocalTime.of(14, 20), "Test 11/11 14:00-14:20"));
		events.add(new CalendarEvent(LocalDate.of(2016, 11, 14), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 14/11 9:00-9:20"));
		events.add(new CalendarEvent(LocalDate.of(2016, 11, 15), LocalTime.of(12, 0), LocalTime.of(13, 20), "Test 15/11 12:00-13:20"));
		events.add(new CalendarEvent(LocalDate.of(2016, 11, 16), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 16/11 9:00-9:20"));
		events.add(new CalendarEvent(LocalDate.of(2016, 11, 17), LocalTime.of(12, 15), LocalTime.of(14, 20), "Test 17/11 12:15-14:20"));
		events.add(new CalendarEvent(LocalDate.of(2016, 11, 18), LocalTime.of(9, 30), LocalTime.of(10, 00), "Test 18/11 9:30-10:00"));
		events.add(new CalendarEvent(LocalDate.of(2016, 11, 18), LocalTime.of(16, 00), LocalTime.of(16, 45), "Test 18/11 16:00-16:45"));

		WeekCalendar cal = new WeekCalendar(events);

		cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
		cal.addCalendarEmptyClickListener(e -> {
			System.out.println(e.getDateTime());
			System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
		});

		JButton goToTodayBtn = new JButton("Today");
		goToTodayBtn.addActionListener(e -> cal.goToToday());

		JButton nextWeekBtn = new JButton(">");
		nextWeekBtn.addActionListener(e -> cal.nextWeek());

		JButton prevWeekBtn = new JButton("<");
		prevWeekBtn.addActionListener(e -> cal.prevWeek());

		JButton nextMonthBtn = new JButton(">>");
		nextMonthBtn.addActionListener(e -> cal.nextMonth());

		JButton prevMonthBtn = new JButton("<<");
		prevMonthBtn.addActionListener(e -> cal.prevMonth());

		//This button allows the user to change to change the font size.
        JButton fontSize = new JButton("Font Sizes");
        fontSize.addActionListener(e -> {
            String[] sizes = {"10", "12", "14", "16", "18", "20"};
            String selectedSize = (String) JOptionPane.showInputDialog(frm, "Select font size", "Font Size", JOptionPane.PLAIN_MESSAGE, null, sizes, sizes[0]);
            if (selectedSize != null) {
                cal.setFontSize(Integer.parseInt(selectedSize));
            }
        });
        //This button allows the user to change to change the font Type.
        JButton fontTypes = new JButton("Font Types");
        fontTypes.addActionListener(e -> {
            String[] fonts = {"Arial", "Helvetica", "Times New Roman", "Courier New", "Verdana", 
            "Lucida Console","Tahoma","Georgia" };
            String selectedFont = (String) JOptionPane.showInputDialog(frm, "Select Font Type", "Font Type", JOptionPane.PLAIN_MESSAGE, null, fonts, fonts[0]);
            if (selectedFont != null) {
                cal.setFontType(selectedFont);
            }
        });
		//This button display the event that is passed
		JButton EventsPassedButton = new JButton("Completed Events");
		EventsPassedButton.addActionListener(e -> {
		   ArrayList<CalendarEvent> EventsPassed = cal.getEventAlreadyPassed();
   
		   //Check if there is any event added that is passed.
		   if (EventsPassed.isEmpty()) {
			   JOptionPane.showMessageDialog(frm, "Nothing happened in the past.");
		   } else {
   
			   StringBuilder stringBuilder = new StringBuilder();
			   for (CalendarEvent event : EventsPassed) {
				   stringBuilder.append(event.toString()).append("\n");
			   }
			   //Display this message at the end
			   JOptionPane.showMessageDialog(frm, stringBuilder.toString(), "Passed Events", JOptionPane.PLAIN_MESSAGE);
		   }
		});

		JPanel weekControls = new JPanel();
		weekControls.add(prevMonthBtn);
		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);
		weekControls.add(nextMonthBtn);
		weekControls.add(fontSize);
        weekControls.add(fontTypes);

		frm.add(weekControls, BorderLayout.NORTH);

		frm.add(cal, BorderLayout.CENTER);
		frm.setSize(1000, 900);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
