import com.Calendar;
import com.CalendarEvent;
import com.WeekCalendar;
import com.Reminder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

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

		// Create a tabbed pane where mutiple calendars will go
		JTabbedPane calendarTab = new JTabbedPane();

		// "First Calendar" in Day Calendar
		WeekCalendar firstCalendar = new WeekCalendar(events);
		calendarTab.addTab("First Calendar", firstCalendar);

		// Add mouse listener to the tab component of "First Calendar"
		// Allows user to edit calendar name
		JLabel firstCalendarTab = new JLabel("First Calendar");
		firstCalendarTab.addMouseListener(new MouseAdapter() {
			@Override
    		public void mouseClicked(MouseEvent e) {
				// Double-click calendar name to edit
        		if (e.getClickCount() == 2) {
            	String newFirstCalendarName = JOptionPane.showInputDialog("Enter a new name for the calendar:", calendarTab.getTitleAt(0));
            		if (newFirstCalendarName != null && !newFirstCalendarName.isEmpty()) {
                		calendarTab.setTitleAt(0, newFirstCalendarName);
                		firstCalendarTab.setText(newFirstCalendarName);
            		}
        		}
    		}
		});
		// This is the first calendar tab
		calendarTab.setTabComponentAt(0, firstCalendarTab);

		// Button for user to add a calendar
		JButton addCalendarBtn = new JButton("Add a Calendar");

		addCalendarBtn.addActionListener(e -> {
			// User prompted to add new calendar
			String calendarName = JOptionPane.showInputDialog("Enter a name for the new calendar:");
			if (calendarName == null || calendarName.isEmpty()) {
				return;
			}
		
			// New DayCalendar object for the new tab
			WeekCalendar newCalendar = new WeekCalendar(events);
		
			// Event listeners for new calendar
			newCalendar.addCalendarEventClickListener(ev -> System.out.println(ev.getCalendarEvent()));
			newCalendar.addCalendarEmptyClickListener(ev -> {
				System.out.println(ev.getDateTime());
				System.out.println(Calendar.roundTime(ev.getDateTime().toLocalTime(), 30));
			});
		
			int tabPosition = calendarTab.getTabCount();
			// New calendar added to tabbed pane, along with other calendars if created
			calendarTab.addTab(calendarName, newCalendar);

    			// Rename calendar
			JLabel newCalendarTab = new JLabel(calendarName);
			newCalendarTab.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// Double-click calendar name to edit
					if (e.getClickCount() == 2) {
						String newCalendarName = JOptionPane.showInputDialog("Enter a new name for the calendar:", calendarTab.getTitleAt(tabPosition));
						if (newCalendarName != null && !newCalendarName.isEmpty()) {
							calendarTab.setTitleAt(tabPosition, newCalendarName);
							newCalendarTab.setText(newCalendarName);
						}
            				}
        			}
    			});

			calendarTab.setTabComponentAt(tabPosition, newCalendarTab);

    			// Select the newly created tab
    			calendarTab.setSelectedIndex(tabPosition);
	
			// Add the tabbed pane to the JFrame's content pane
			frm.getContentPane().add(calendarTab, BorderLayout.CENTER);

			// Select the newly created tab
			calendarTab.setSelectedIndex(calendarTab.getTabCount()-1);
		
			// Repaint the JFrame to show the new tab
			frm.revalidate();
			frm.repaint();
		});

		// ADD A REMINDER

		JButton addReminderButton = new JButton("Add a Reminder");
        	addReminderButton.addActionListener(e -> new Reminder(events).setVisible(true)); // creating a new Reminder object

		JPanel weekControls = new JPanel();
		weekControls.add(prevMonthBtn);
		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);
		weekControls.add(nextMonthBtn);
		weekControls.add(fontSize);
        	weekControls.add(fontTypes);
		weekControls.add(addCalendarBtn);
		weekControls.add(addReminderButton);

		frm.add(weekControls, BorderLayout.NORTH);
		frm.add(calendarTab, BorderLayout.CENTER);
		frm.setSize(1000, 900);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
