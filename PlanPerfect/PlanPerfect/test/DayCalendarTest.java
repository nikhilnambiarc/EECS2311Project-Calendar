import com.Calendar;
import com.CalendarEvent;
import com.DayCalendar;
import com.AddACalendar;
import com.ReminderPanel;
import com.ReminderDialog;
import com.Reminder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class DayCalendarTest {
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

		DayCalendar cal = new DayCalendar(events);

		JTabbedPane mainTabbedPane = new JTabbedPane();
		
		AddACalendar addACalendar = new AddACalendar(events, cal);
		addACalendar.setAllCalendarsFontSize(14);
		addACalendar.setAllCalendarsFontType("Arial");
		mainTabbedPane.addTab("Calendar", addACalendar);
		
		ReminderPanel reminderPanel = new ReminderPanel();
		reminderPanel.setFontSize(14);
		reminderPanel.setFontType("Arial");
		mainTabbedPane.addTab("Reminders", reminderPanel);
		
		JPanel buttonPanel = new JPanel();
		JButton addCalendarButton = new JButton("Add a Calendar");
		JButton deleteCalendarButton = new JButton("Delete a Calendar");
		JButton addReminderButton = new JButton("Add a Reminder");
		
		buttonPanel.add(addCalendarButton);
		buttonPanel.add(deleteCalendarButton);
		
		JPanel reminderButtonPanel = new JPanel();
		reminderButtonPanel.add(addReminderButton);
		reminderButtonPanel.setVisible(false);

		JButton editReminderButton = new JButton("Edit a Reminder");
		reminderButtonPanel.add(editReminderButton);

		editReminderButton.addActionListener(e -> {
			String reminderName = JOptionPane.showInputDialog(frm, "Enter the name of the reminder to edit:");
		
			if (reminderName != null) {
				Reminder reminderToEdit = reminderPanel.getReminderByName(reminderName);
		
				if (reminderToEdit != null) {
					ReminderDialog reminderDialog = new ReminderDialog(frm);
					reminderDialog.setReminder(reminderToEdit);
					reminderDialog.setVisible(true);
					Reminder updatedReminder = reminderDialog.getReminder();
		
					if (updatedReminder != null) {
						reminderPanel.updateReminder(reminderToEdit, updatedReminder);
					}
				} else {
					JOptionPane.showMessageDialog(frm, "Reminder not found.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton deleteReminderButton = new JButton("Delete a Reminder");
		reminderButtonPanel.add(deleteReminderButton);

		deleteReminderButton.addActionListener(e -> {
			String reminderName = JOptionPane.showInputDialog(frm, "Enter the name of the reminder to delete:", "Delete Reminder", JOptionPane.QUESTION_MESSAGE);
			if (reminderName != null && !reminderName.isEmpty()) {
				Reminder reminderToDelete = reminderPanel.getReminderByName(reminderName);
				if (reminderToDelete != null) {
					reminderPanel.deleteReminder(reminderToDelete);
				} else {
					JOptionPane.showMessageDialog(frm, "Reminder not found.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});		

		frm.getContentPane().add(reminderButtonPanel, BorderLayout.SOUTH);
		
		addReminderButton.addActionListener(e -> {
			ReminderDialog reminderDialog = new ReminderDialog(frm);
			reminderDialog.setVisible(true);
			Reminder newReminder = reminderDialog.getReminder();
			if (newReminder != null) {
				reminderPanel.addReminder(newReminder);
			}
		});
		
		frm.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		
		mainTabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (mainTabbedPane.getSelectedIndex() == 0) {
					buttonPanel.setVisible(true);
					reminderButtonPanel.setVisible(false);
				} else {
					buttonPanel.setVisible(false);
					reminderButtonPanel.setVisible(true);
				}
			}
		});
		
		frm.getContentPane().add(mainTabbedPane, BorderLayout.CENTER);	

		cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
		cal.addCalendarEmptyClickListener(e -> {
			System.out.println(e.getDateTime());
			System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
		});

		JButton goToTodayBtn = new JButton("Today");
		goToTodayBtn.addActionListener(e -> cal.goToToday());

		JButton nextDayBtn = new JButton(">");
		nextDayBtn.addActionListener(e -> cal.nextDay());

		JButton prevDayBtn = new JButton("<");
		prevDayBtn.addActionListener(e -> cal.prevDay());

		addACalendar.addGoToTodayListener(goToTodayBtn);
		addACalendar.addNextDayListener(nextDayBtn);
		addACalendar.addPrevDayListener(prevDayBtn);

		JButton SettingsButton = new JButton("Settings");
        SettingsButton.addActionListener(e -> {
        	Object[] GivenOptions = {"Font Type", "Font Size"};
        	int Choosedchoice = JOptionPane.showOptionDialog(frm, "", "Settings", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null, GivenOptions,GivenOptions[0]);
        
        	if (Choosedchoice == 1) {
        		String[] fontsizes = {"10", "12", "14", "16", "18"};
        		String Size = (String) JOptionPane.showInputDialog(frm, "Select the font size", "Font Sizes", JOptionPane.PLAIN_MESSAGE, null, fontsizes, fontsizes[0]);
        
        		if (Size != null) {
					addACalendar.setAllCalendarsFontSize(Integer.parseInt(Size));
					reminderPanel.setFontSize(Integer.parseInt(Size));
				}
        		} else if (Choosedchoice == 0) {
        		String[] fontTypes = {"Arial","Times New Roman", "Helvetica", "Courier New", "Verdana", "Lucida Console","Tahoma","Georgia" };
       
        		String Type = (String) JOptionPane.showInputDialog(frm, "Select Font Type", "Font Type", JOptionPane.PLAIN_MESSAGE, null, fontTypes, fontTypes[0]);
        		if (Type != null) {
					addACalendar.setAllCalendarsFontType(Type);
					reminderPanel.setFontType(Type);
				}
   	 		}
		});

		JPanel weekControls = new JPanel();
		weekControls.add(prevDayBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextDayBtn);
		weekControls.add(SettingsButton);
		
		frm.add(weekControls, BorderLayout.NORTH);
		frm.setSize(1000, 2000);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
