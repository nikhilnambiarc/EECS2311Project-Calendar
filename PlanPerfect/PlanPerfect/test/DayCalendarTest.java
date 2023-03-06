import com.Calendar;
import com.CalendarEvent;
import com.DayCalendar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DayCalendarTest {
    public static void main(String[] args) {
        JFrame frm = new JFrame();

        ArrayList<CalendarEvent> events = new ArrayList<>();

        
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
			//Array list of font types
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
            JOptionPane.showMessageDialog(frm, "NO EVENT PASSED");
        } else {

            StringBuilder stringBuilder = new StringBuilder();
            for (CalendarEvent event : EventsPassed) {
                stringBuilder.append(event.toString()).append("\n");
            }
            //Display this message at the end
            JOptionPane.showMessageDialog(frm, stringBuilder.toString(), "Passed Events", JOptionPane.PLAIN_MESSAGE, imageIcon);
        }
     });
        
        


		JPanel weekControls = new JPanel();
        weekControls.add(EventsPassedButton); //Adding "Completed Events" in the GUI
		weekControls.add(prevDayBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextDayBtn);
		weekControls.add(fontSize);
        weekControls.add(fontTypes);

		
		frm.add(weekControls, BorderLayout.NORTH);


		frm.add(cal, BorderLayout.CENTER);
		frm.setSize(1000, 900);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}