import com.Calendar;
import com.CalendarEvent;
import com.DayCalendar;
import com.AddDeleteEditDayCalendar;
import com.ReminderPanel;
import com.ReminderDialog;
import com.Reminder;
import com.RepeatingEventGenerator;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.List;

public class DayCalendarTest {
    public static void main(String[] args) {
        JFrame frm = new JFrame();
        frm.setTitle("Plan Perfect");

		ArrayList<CalendarEvent> events = new ArrayList<>();
        
        String url = "jdbc:mysql://localhost:3306/CA_Public_Holidays"; 
		String user = "root";
		String password = "EECS2311"; // replace ... with your password 
		
		try{
            String query = "SELECT * FROM 2023_Holidays;"; // replace ... with the correct query
			Connection con = DriverManager.getConnection(url, user, password);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) { 

				String holiday_Name = result.getString("Holiday_Name");
				int day = result.getInt("day");
                int month = result.getInt("month");
                int year = result.getInt("year");

				events.add(new CalendarEvent(LocalDate.of(year, month, day), LocalTime.of(8, 0), LocalTime.of(8, 20), holiday_Name));
				//System.out.println(holiday_id + ", " + holiday_Name + ", " + day + ", " + month + ", " + year);
			}

		} catch (SQLException e) { 
			e.printStackTrace();
		}

		try{
            String query = "SELECT * FROM 2024_Holidays;"; // replace ... with the correct query
			Connection con = DriverManager.getConnection(url, user, password);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) { 

				String holiday_Name = result.getString("Holiday_Name");
				int day = result.getInt("day");
                int month = result.getInt("month");
                int year = result.getInt("year");

				events.add(new CalendarEvent(LocalDate.of(year, month, day), LocalTime.of(8, 0), LocalTime.of(8, 20), holiday_Name));
				//System.out.println(holiday_id + ", " + holiday_Name + ", " + day + ", " + month + ", " + year);
			}

		} catch (SQLException e) { 
			e.printStackTrace();
		}

		try{
            String query = "SELECT * FROM 2025_Holidays;"; // replace ... with the correct query
			Connection con = DriverManager.getConnection(url, user, password);
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) { 
				String holiday_Name = result.getString("Holiday_Name");
				int day = result.getInt("day");
                int month = result.getInt("month");
                int year = result.getInt("year");

				events.add(new CalendarEvent(LocalDate.of(year, month, day), LocalTime.of(8, 0), LocalTime.of(8, 20), holiday_Name));
				//System.out.println(holiday_id + ", " + holiday_Name + ", " + day + ", " + month + ", " + year);
			}

		} catch (SQLException e) { 
			e.printStackTrace();
		}

		 //events.add(new CalendarEvent(LocalDate.of(2016, 11, 11), LocalTime.of(14, 0), LocalTime.of(14, 20), "Test 11/11 14:00-14:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 14), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 14/11 9:00-9:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 15), LocalTime.of(12, 0), LocalTime.of(13, 20), "Test 15/11 12:00-13:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 16), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 16/11 9:00-9:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 17), LocalTime.of(12, 15), LocalTime.of(14, 20), "Test 17/11 12:15-14:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 18), LocalTime.of(9, 30), LocalTime.of(10, 00), "Test 18/11 9:30-10:00"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 18), LocalTime.of(16, 00), LocalTime.of(16, 45), "Test 18/11 16:00-16:45"));
        
        DayCalendar cal = new DayCalendar(events);
        
        JTabbedPane mainTabbedPane = new JTabbedPane() {
            @Override
            public void setSelectedIndex(int index) {
                super.setSelectedIndex(index);
                for (int i = 0; i < getTabCount(); i++) {
                    setForegroundAt(i, Color.BLACK);
                }
            }
        };

        ImageIcon imageIcon = new ImageIcon("path/to/image.jpg");

        // Add a Calendar
        AddDeleteEditDayCalendar addACalendar = new AddDeleteEditDayCalendar(events, cal);
        addACalendar.setAllCalendarsFontSize(14);
        addACalendar.setAllCalendarsFontType("Arial");
        mainTabbedPane.addTab("Calendar", addACalendar);
       
        // Reminder Panel
        ReminderPanel reminderPanel = new ReminderPanel();
        reminderPanel.setFontSize(14);
        reminderPanel.setFontType("Arial");
        mainTabbedPane.addTab("Reminders", reminderPanel);
       
        // Add & Delete Calendars
        JPanel buttonPanel = new JPanel();
        JButton addCalendarButton = new JButton("Add a Calendar");
        JButton deleteCalendarButton = new JButton("Delete a Calendar");
        
        // Add a Reminder
        JButton addReminderButton = new JButton("Add a Reminder");
       
        // Add buttons to panel
        buttonPanel.add(addCalendarButton);
        buttonPanel.add(deleteCalendarButton);
        
        // Reminder Button Panel
        JPanel reminderButtonPanel = new JPanel();
        reminderButtonPanel.add(addReminderButton);
        reminderButtonPanel.setVisible(false);
 
        // Edit Reminder
        JButton editReminderButton = new JButton("Edit a Reminder");
        reminderButtonPanel.add(editReminderButton);

        // Edit Reminder Functionality
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
 
        // Delete Reminder
        JButton deleteReminderButton = new JButton("Delete a Reminder");
        reminderButtonPanel.add(deleteReminderButton);
 
        // Delete Reminder Functionality
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
 
        // Add Reminder Deleteing and Editing Buttons to Reminders Tab
        frm.getContentPane().add(reminderButtonPanel, BorderLayout.SOUTH);
       
        // Add Reminder Functionality
        addReminderButton.addActionListener(e -> {
            ReminderDialog reminderDialog = new ReminderDialog(frm);
            reminderDialog.setVisible(true);
            Reminder newReminder = reminderDialog.getReminder();
            if (newReminder != null) {
                reminderPanel.addReminder(newReminder);
            }
        });
       
        // Add Reminder Adding to Reminders Tab
        frm.getContentPane().add(buttonPanel, BorderLayout.NORTH);
       
        // Close visibility of reminder buttons when on Calendar Tab
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

        //This button display the event that is passed
        JButton EventsPassedButton = new JButton("Completed Events");
        
        EventsPassedButton.addActionListener(e -> {
            ArrayList<CalendarEvent> EventsPassed = cal.getEventAlreadyPassed();

            //Check if there is any event added that is passed.
            if (EventsPassed.isEmpty()) {
                JOptionPane.showMessageDialog(frm, "NO EVENT/REMINDERS PASSED");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (CalendarEvent event : EventsPassed) {
                    stringBuilder.append(event.toString()).append("\n");
                }
                //Display this message at the end
                JOptionPane.showMessageDialog(frm, stringBuilder.toString(), "Passed Events/Reminders", JOptionPane.PLAIN_MESSAGE);
            }
        });
           
        //This button is use to add the event
		JButton ADD_EVENT_BUTTON = new JButton("Add Event");

        //Adding Action Listner
        ADD_EVENT_BUTTON.addActionListener(e -> {
            //Giving user different options to input
            JTextField EventName = new JTextField(20);
            JTextField Day = new JTextField(10);
            JTextField start_Time = new JTextField(6);
            JTextField end_Time = new JTextField(6);
            JLabel repeatLabel = new JLabel("Repeat:");
            JLabel endDateLabel = new JLabel("End Date (Format: YYYY-MM-DD)");
            JTextField endDateField = new JTextField(10);
            JPanel AddEvent_panel = new JPanel(new GridLayout(0, 2));//Set up the grid layout

            JTextField goalField = new JTextField(20);
            
            // Repeat events options and labels
            String[] repeatOptions = { "Never", "Every Day", "Every 2 Days", "Every Week", "Every 2 Weeks",
                    "Every Month", "Every Year" };
            JComboBox<String> repeatDropdown = new JComboBox<>(repeatOptions);
                    
            AddEvent_panel.add(new JLabel("Name"));
            AddEvent_panel.add(EventName);
            AddEvent_panel.add(new JLabel("Year/Month/Day (Format: YYYY-MM-DD)"));
            AddEvent_panel.add(Day);
            AddEvent_panel.add(new JLabel("Start Time (Format: HH:mm)"));
            AddEvent_panel.add(start_Time);
            AddEvent_panel.add(new JLabel("End Time (Format: HH:mm)"));
            AddEvent_panel.add(end_Time);
            AddEvent_panel.add(repeatLabel);
            AddEvent_panel.add(repeatDropdown);
            AddEvent_panel.add(endDateLabel);
            AddEvent_panel.add(endDateField);
            AddEvent_panel.add(new JLabel("My Goal"));
            AddEvent_panel.add(goalField);
            //Tiltle of the panel and and too close the panel
    
            int Display = JOptionPane.showConfirmDialog(null, AddEvent_panel, "Add the Event",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
            if (Display == JOptionPane.OK_OPTION) {
                try {
                    String name = EventName.getText();
                    LocalDate startDate = LocalDate.parse(Day.getText());
                    LocalTime startTime = LocalTime.parse(start_Time.getText());
                    LocalTime endTime = LocalTime.parse(end_Time.getText());
                    RepeatingEventGenerator.RepeatOption repeatOption = RepeatingEventGenerator.repeatOptionFromString(
                            (String) repeatDropdown.getSelectedItem());
                    LocalDate endDate = null;
                    try {
                        endDate = LocalDate.parse(endDateField.getText());
                    } catch (DateTimeParseException ex) {
                        // Ignore exception if no end date is provided
                    }

                    List<CalendarEvent> newEvents = RepeatingEventGenerator.generateRepeatingEvents(
                            new CalendarEvent(startDate, startTime, endTime, name), repeatOption, endDate);

                    cal.addEvents(newEvents);
                    events.addAll(newEvents);

                    for (CalendarEvent newEvent : newEvents) {
                        String goal = goalField.getText();

                        newEvent.setGoal(goal); // Set the goal for each new event
                    }

                    cal.repaint();
                    cal.revalidate();

                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date or time format");
                }
            }
        });

        JButton DELETE_EVENT_BUTTON = new JButton("Delete Event");
        
        DELETE_EVENT_BUTTON.addActionListener(e -> {
            JTextField EnterName = new JTextField(30);
            JPanel DELETE_EVENT_Panel = new JPanel(new GridLayout(1, 1));
            DELETE_EVENT_Panel.add(new JLabel("Enter Event Name to Delete: "));
            DELETE_EVENT_Panel.add(EnterName);

            int Result = JOptionPane.showConfirmDialog(null, DELETE_EVENT_Panel, "Delete the Event",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
            if (Result == JOptionPane.OK_OPTION) {
                String NAME_OF_EVENT = EnterName.getText();
                events.removeIf(event -> event.getText().equals(NAME_OF_EVENT));
                cal.repaint(); //To display the result on GUI
            }
        });

		JPanel weekControls = new JPanel();
        weekControls.add(ADD_EVENT_BUTTON); //Adding "ADD_EVENT_BUTTON" in the GUI
        weekControls.add(DELETE_EVENT_BUTTON);//Adding "DELETE_EVENT_BUTTON" in the GUI
        weekControls.add(EventsPassedButton); //Adding "Completed Events" in the GUI
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
