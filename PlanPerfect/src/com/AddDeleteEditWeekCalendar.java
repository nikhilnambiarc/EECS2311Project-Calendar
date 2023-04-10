package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//code defines a class called AddDeleteEditWeekCalendar which extends JPanel
//It represents a panel containing a tabbed pane, with each tab displaying a WeekCalendar
public class AddDeleteEditWeekCalendar extends JPanel {
   private JTabbedPane tabbedPane;
   private ArrayList<WeekCalendar> calendars;
   private ArrayList<CalendarEvent> events;
//The WeekCalendar class is not defined in this code and must be provided externally
   public AddDeleteEditWeekCalendar(ArrayList<CalendarEvent> events, WeekCalendar initialCalendar) {
       super(new BorderLayout());
       this.events = events;
       tabbedPane = new JTabbedPane();
       calendars = new ArrayList<>();
       addWeekCalendar("Calendar");
//constructor of AddDeleteEditWeekCalendar takes two arguments: an ArrayList of CalendarEvent objects and an initial WeekCalendar
  //It sets up the GUI components, including two buttons:
       JButton addCalendarButton = new JButton("Add a Calendar");
  //addCalendarButton and deleteCalendarButton
      //When the addCalendarButton is clicked, it adds a new tab with a new WeekCalendar
      //When the deleteCalendarButton is clicked, it prompts the user to enter the name of the calendar to be deleted 
      //and then removes the corresponding tab from the tabbed pane
       addCalendarButton.addActionListener(e -> addWeekCalendar("New Calendar"));
//The AddDeleteEditWeekCalendar class also defines a nested class called CalendarTabComponent
      //This class represents the component that is displayed at the top of each tab
      //It consists of a label with the name of the calendar and allows the user to double-click on the label to rename the calendar
       JButton deleteCalendarButton = new JButton("Delete a Calendar");
       deleteCalendarButton.addActionListener(e -> {
           String calendarName = JOptionPane.showInputDialog("Enter the name of the calendar to delete:");
           if (calendarName != null && !calendarName.trim().isEmpty()) {
               for (int i = 1; i < tabbedPane.getTabCount(); i++) {
                   if (tabbedPane.getTitleAt(i).equals(calendarName)) {
                       tabbedPane.remove(i);
                       calendars.remove(i);
                       break;
                   }
               }
           }
       });
//several methods defined in this class to manipulate the WeekCalendar objects
       JPanel controlPanel = new JPanel();
       controlPanel.add(addCalendarButton);
       controlPanel.add(deleteCalendarButton);

       add(controlPanel, BorderLayout.NORTH);
       add(tabbedPane, BorderLayout.CENTER);
   }
   //The getCurrentCalendar method returns the currently selected WeekCalendar object
   private void addWeekCalendar(String name) {
       WeekCalendar calendar = new WeekCalendar(events);
       calendars.add(calendar);
       int index = tabbedPane.getTabCount();
       tabbedPane.addTab(name, calendar);
       tabbedPane.setTabComponentAt(index, new CalendarTabComponent(tabbedPane, name));
   }
//The setAllCalendarsFontSize and setAllCalendarsFontType methods set the font size and type for all WeekCalendar objects, respectively
   class CalendarTabComponent extends JPanel {
       private JLabel label;

       public CalendarTabComponent(JTabbedPane pane, String title) {
           super(new FlowLayout(FlowLayout.LEFT, 0, 0));
           setOpaque(false);
//Finally, there are three methods (addGoToTodayListener, addNextDayListener, and addPrevDayListener)
           label = new JLabel(title);
           label.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                   if (e.getClickCount() == 2) {
                       String newName = JOptionPane.showInputDialog("Enter new calendar name:");
                       if (newName != null && !newName.trim().isEmpty()) {
                           label.setText(newName);
                           tabbedPane.setTitleAt(pane.indexOfTabComponent(CalendarTabComponent.this), newName);
                       }
                   }
               }
           });
           add(label);
       }
   }
//that add action listeners to buttons that allow the user to navigate through the weeks displayed
   public void addGoToTodayListener(JButton goToTodayBtn) {
       goToTodayBtn.addActionListener(e -> getCurrentCalendar().goToToday());
   }

   public void addNextDayListener(JButton nextDayBtn) {
       nextDayBtn.addActionListener(e -> getCurrentCalendar().nextWeek());
   }

   public void addPrevDayListener(JButton prevDayBtn) {
       prevDayBtn.addActionListener(e -> getCurrentCalendar().prevWeek());
   }

   private WeekCalendar getCurrentCalendar() {
       int selectedIndex = tabbedPane.getSelectedIndex();
       return calendars.get(selectedIndex);
   }

   public void setAllCalendarsFontSize(int size) {
       for (WeekCalendar calendar : calendars) {
           calendar.setFontSize(size);
       }
   }

   public void setAllCalendarsFontType(String type) {
       for (WeekCalendar calendar : calendars) {
           calendar.setFontType(type);
       }
   }

}
