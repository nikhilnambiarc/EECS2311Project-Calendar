import com.Calendar;
import com.CalendarEvent;
import com.DayCalendar;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;

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

		 

        
        DayCalendar cal = new DayCalendar(events);
        ImageIcon imageIcon = new ImageIcon("path/to/image.jpg");
        
        
        JLabel dateLabel = new JLabel("Date (yyyy-MM-dd):");
        JTextField dateField = new JTextField(10);
        JLabel startTimeLabel = new JLabel("Start Time (hh:mm):");
        JTextField startTimeField = new JTextField(5);
        JLabel endTimeLabel = new JLabel("End Time (hh:mm):");
        JTextField endTimeField = new JTextField(5);
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(20);
        JButton addEventBtn = new JButton("Add Event");

        JPanel addEventPanel = new JPanel();
        addEventPanel.add(dateLabel);
        addEventPanel.add(dateField);
        addEventPanel.add(startTimeLabel);
        addEventPanel.add(startTimeField);
        addEventPanel.add(endTimeLabel);
        addEventPanel.add(endTimeField);
        addEventPanel.add(descriptionLabel);
        addEventPanel.add(descriptionField);
        addEventPanel.add(addEventBtn);

        addEventBtn.addActionListener(e -> {
            LocalDate date;
            LocalTime startTime;
            LocalTime endTime;
            String description;
            try {
                date = LocalDate.parse(dateField.getText());
                startTime = LocalTime.parse(startTimeField.getText());
                endTime = LocalTime.parse(endTimeField.getText());
                description = descriptionField.getText();
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frm, "Invalid date or time format");
                return;
            }

           
            CalendarEvent event = new CalendarEvent(date, startTime, endTime, description);
            cal.addEvent(event);
        });

        
        frm.add(addEventPanel, BorderLayout.NORTH);

       
        frm.add(cal, BorderLayout.CENTER);

        JButton goToTodayBtn = new JButton("Today");
        goToTodayBtn.addActionListener(e -> cal.goToToday());

        JButton nextDayBtn = new JButton(">");
        nextDayBtn.addActionListener(e -> cal.nextDay());

        JButton prevDayBtn = new JButton("<");
        prevDayBtn.addActionListener(e -> cal.prevDay());

	
		

		
        JButton SettingsButton = new JButton("Settings");
        SettingsButton.addActionListener(e -> {
    Object[] GivenOptions = {"Font Type", "Font Size"};
    int Choosedchoice = JOptionPane.showOptionDialog(frm, "", "Settings", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null, GivenOptions,GivenOptions[0]);
    if (Choosedchoice == 1) {
        String[] fontsizes = {"10", "12", "14", "16", "18"};
        String Size = (String) JOptionPane.showInputDialog(frm, "Select the font size", "Font Sizes", JOptionPane.PLAIN_MESSAGE, null, fontsizes, fontsizes[0]);
        if (Size != null) {
            cal.setFontSize(Integer.parseInt(Size));
        }
    } else if (Choosedchoice == 0) {
        String[] fontTypes = {"Arial","Times New Roman", "Helvetica", "Courier New", "Verdana", "Lucida Console","Tahoma","Georgia" };
       
        String Type = (String) JOptionPane.showInputDialog(frm, "Select Font Type", "Font Type", JOptionPane.PLAIN_MESSAGE, null, fontTypes, fontTypes[0]);
        if (Type != null) {
            cal.setFontType(Type);
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
    //Giving user differnet options to input
    JTextField EventName = new JTextField(20);
    JTextField Day = new JTextField(10);
    JTextField start_Time = new JTextField(6);
    JTextField end_Time = new JTextField(6);
    JPanel AddEvent_panel = new JPanel(new GridLayout(0, 2));//Set up the grid layout

    AddEvent_panel.add(new JLabel("Name"));
    AddEvent_panel.add(EventName);
    AddEvent_panel.add(new JLabel("Year/Month/Day (Format: YYYY-MM-DD)"));
    AddEvent_panel.add(Day);
    AddEvent_panel.add(new JLabel("Start Time (Format: HH:mm)"));
    AddEvent_panel.add(start_Time);
    AddEvent_panel.add(new JLabel("End Time (Format: HH:mm)"));
    AddEvent_panel.add(end_Time);
        //Tiltle of the panel and and too close the panel
    int Display = JOptionPane.showConfirmDialog(null, AddEvent_panel, "Add the Event",
    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    //Add when user will enter click Ok
    if (Display == JOptionPane.OK_OPTION) {
    //Using these variables to add the user input into the array
    String name = EventName.getText();   //Getting the name of event that user enter
    LocalDate startDate = LocalDate.parse(Day.getText());  //Getting the day
    LocalTime startTime = LocalTime.parse(start_Time.getText()); //Getting the start time
    LocalTime endTime = LocalTime.parse(end_Time.getText());//Getting the end time
    CalendarEvent newEvent = new CalendarEvent(startDate, startTime, endTime, name);

    events.add(newEvent); //Add all info into the list to store
    cal.repaint(); //Repaint the Calendar to dispalthe event directly
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


		frm.add(cal, BorderLayout.CENTER);
		frm.setSize(1000, 2000);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
