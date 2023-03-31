import com.Calendar;
import com.CalendarEvent;
import com.TimeSlot;
import com.WeekCalendar;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
// import java.com.Calendar;
import javax.imageio.ImageIO;


public class WeekCalendarTest {
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
		//events.add(new CalendarEvent(LocalDate.of(2023, 03, 8), LocalTime.of(10, 0), LocalTime.of(14, 20), "Test 11/11 14:00-14:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 14), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 14/11 9:00-9:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 15), LocalTime.of(12, 0), LocalTime.of(13, 20), "Test 15/11 12:00-13:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 16), LocalTime.of(9, 0), LocalTime.of(9, 20), "Test 16/11 9:00-9:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 17), LocalTime.of(12, 15), LocalTime.of(14, 20), "Test 17/11 12:15-14:20"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 18), LocalTime.of(9, 30), LocalTime.of(10, 00), "Test 18/11 9:30-10:00"));
		// events.add(new CalendarEvent(LocalDate.of(2016, 11, 18), LocalTime.of(16, 00), LocalTime.of(16, 45), "Test 18/11 16:00-16:45"));


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

		// JButton nextMonthBtn = new JButton(">>");
		// nextMonthBtn.addActionListener(e -> cal.nextMonth());

		// JButton prevMonthBtn = new JButton("<<");
		// prevMonthBtn.addActionListener(e -> cal.prevMonth());



//This is setting button, inside that button we are giving user to customize different things
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
       ADD_EVENT_BUTTON.addActionListener(e -> {
          
        
            // Giving user different options to input
            JTextField EventName = new JTextField(20);
            JTextField Day = new JTextField(10);
            JTextField start_Time = new JTextField(6);
            JTextField end_Time = new JTextField(6);
            JPanel AddEvent_panel = new JPanel(new GridLayout(0, 2)); //Set up the grid layout
        
            AddEvent_panel.add(new JLabel("Name"));
            AddEvent_panel.add(EventName);
            AddEvent_panel.add(new JLabel("Year/Month/Day (Format: YYYY-MM-DD)"));
            AddEvent_panel.add(Day);
            AddEvent_panel.add(new JLabel("Start Time (Format: HH:mm)"));
            AddEvent_panel.add(start_Time);
            AddEvent_panel.add(new JLabel("End Time (Format: HH:mm)"));
            AddEvent_panel.add(end_Time);
        
            //Title of the panel and to close the panel
            int Display = JOptionPane.showConfirmDialog(null, AddEvent_panel, "Add the Event",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
            //Add when user will enter click Ok
            if (Display == JOptionPane.OK_OPTION) {
                //Using these variables to add the user input into the array
                String name = EventName.getText();   //Getting the name of event that user enter
                LocalDate startDate = LocalDate.parse(Day.getText());  //Getting the day
                LocalTime startTime = LocalTime.parse(start_Time.getText()); //Getting the start time
                LocalTime endTime = LocalTime.parse(end_Time.getText());//Getting the end time
                CalendarEvent Event = new CalendarEvent(startDate, startTime, endTime, name);
        
                // Check for conflicts if there are events in the array
                boolean Event_conflict = false;
                for (CalendarEvent event : events) {
                    if (event.check_Conflict(Event)) {
                        Event_conflict = true;
                        break;
                    }
                }
        
                //if the conflict is occurring then display message and break otherwise set the event
                if (Event_conflict) {
                    // Display message with conflicting events
                    StringBuilder sb = new StringBuilder();
                    sb.append("The event conflicts with the following existing events:\n");
                    for (CalendarEvent event : events) {
                        if (event.check_Conflict(Event)) {
                            sb.append(event.toString()).append("\n");
                        }
                    }
                    sb.append("Please choose a different time slot.\n");
                            
                    // Show the message dialog
                    JOptionPane.showMessageDialog(null, sb.toString(), "Event Conflict", JOptionPane.ERROR_MESSAGE);
                            
                    // Determine available time slots
                    ArrayList<TimeSlot> availableSlots = new ArrayList<>();
                    for (int i = 0; i < events.size() - 1; i++) {
                        CalendarEvent currEvent = events.get(i);
                        CalendarEvent nextEvent = events.get(i + 1);
                        if (currEvent.getEndsBefore(nextEvent.getStart())) {
                            TimeSlot slot = new TimeSlot(currEvent.getEnd(), nextEvent.getStart());
                            availableSlots.add(slot);
                        }
                    }
                    if (!availableSlots.isEmpty()) {
                        // Display message with available time slots
                        sb = new StringBuilder();
                        sb.append("Available time slots: \n");
                        for (TimeSlot slot : availableSlots) {
                            sb.append(slot.getStart().toString()).append(" - ").append(slot.getEnd().toString()).append("\n");
                        }
                        System.out.println("Available time slots message:\n" + sb.toString());
                        JOptionPane.showMessageDialog(null, sb.toString(), "Available Time Slots", JOptionPane.INFORMATION_MESSAGE);
                    }
                    return;
                    
                } else {
                    // Add event to array and repaint calendar
                    events.add(Event);
                    cal.repaint();
                    return;
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
		
		//weekControls.add(prevMonthBtn);
		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);
		//weekControls.add(nextMonthBtn);
		
		

		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);

		weekControls.add(SettingsButton);
      


		frm.add(weekControls, BorderLayout.NORTH);
		

		frm.add(cal, BorderLayout.CENTER);
		frm.setSize(1000, 1000);
		frm.setVisible(true);
		//frm.add(new JScrollPane(), BorderLayout.CENTER);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
			JComponent j =new JComponent() {
				
			};
			// Create a new BufferedImage with the size of the frame
			BufferedImage image = new BufferedImage(frm.getWidth(), frm.getHeight(), BufferedImage.TYPE_INT_RGB);
	
			// Get the Graphics2D object for the image
		Graphics2D g2d = image.createGraphics();

			// Render the calendar to the image
				frm.paint(g2d);

			// Dispose of the Graphics2D object to free up resources
				g2d.dispose();

			// Save the image to a file
		try {
   		 File output = new File("calendar.png");
   	 	ImageIO.write(image, "png", output);
		} catch (IOException e) {
    	e.printStackTrace();
}
		
	}
}
