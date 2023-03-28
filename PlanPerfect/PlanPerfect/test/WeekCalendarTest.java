import com.Calendar;
import com.CalendarEvent;
import com.WeekCalendar;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class WeekCalendarTest {
	public static void main(String[] args) {
		
		JFrame frm = new JFrame();
        frm.setTitle("Plan Perfect");
		JPanel weekControls = new JPanel();
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
	  Object[] GivenOptions = {"Font Type", "Font Size", "Theme"};
	  int choosenChoice = JOptionPane.showOptionDialog(frm, "", "Settings", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null, GivenOptions,GivenOptions[0]);
	  if (choosenChoice  == 1) {
		  String[] fontsizes = {"10", "12", "14", "16", "18"};
		  String Size = (String) JOptionPane.showInputDialog(frm, "Select the font size", "Font Sizes", JOptionPane.PLAIN_MESSAGE, null, fontsizes, fontsizes[0]);
		  if (Size != null) {
			  cal.setFontSize(Integer.parseInt(Size));
			  
		  }
	  } 
	  else if (choosenChoice  == 0) {
		  String[] fontTypes = {"Arial","Times New Roman", "Helvetica", "Courier New", "Verdana", "Lucida Console","Tahoma","Georgia" };
		 
		  String Type = (String) JOptionPane.showInputDialog(frm, "Select Font Type", "Font Type", JOptionPane.PLAIN_MESSAGE, null, fontTypes, fontTypes[0]);
		  if (Type != null) {
			  cal.setFontType(Type);
		  }
	  }
	  else if (choosenChoice  == 2) {
		String[] themes = {"Dark", "Light"};

		String theme = (String) JOptionPane.showInputDialog(frm, "Select theme", "Theme: ", JOptionPane.PLAIN_MESSAGE, null, themes, themes[0]);
		
		if(theme != null) {
			cal.setCalendarTheme(theme);
		}
	  }

  });
		//This button display the event that is passed
		JButton EventsPassedButton = new JButton("Completed Events");
		EventsPassedButton.addActionListener(e -> {
		   ArrayList<CalendarEvent> EventsPassed = cal.getEventAlreadyPassed();
   
		   //Check if there is any event added that is passed.
		   if (EventsPassed.isEmpty()) {
			   JOptionPane.showMessageDialog(frm, "NO EVENT PASSED");
		   } else {
   
			   StringBuilder stringBuilder = new StringBuilder();
			   for (CalendarEvent event : EventsPassed) {
				   stringBuilder.append(event.toString()).append("\n");
			   }
			   //Display this message at the end
			   JOptionPane.showMessageDialog(frm, stringBuilder.toString(), "Passed Events", JOptionPane.PLAIN_MESSAGE);
		   }
		});

		
		weekControls.add(EventsPassedButton); //Adding "Completed Events" in the GUI
		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);
		weekControls.add(SettingsButton);
      

		frm.add(weekControls, BorderLayout.NORTH);
		

		frm.add(cal, BorderLayout.CENTER);
		frm.setSize(800, 800);
		frm.setVisible(true);
		//frm.add(new JScrollPane(), BorderLayout.CENTER);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
