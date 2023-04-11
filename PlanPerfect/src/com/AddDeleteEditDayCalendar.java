package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddDeleteEditDayCalendar extends JPanel {
      // declare fields
   private JTabbedPane tabbedPane;
   
   private ArrayList<DayCalendar> calendars;
   
   private ArrayList<CalendarEvent> events;
   //"tabbedPane" is a JTabbedPane object that is used to display the calendars
   //"calendars" is an ArrayList that stores all the DayCalendar objects.
   //"events" is an ArrayList of CalendarEvent objects that is passed in to the constructor
   
   public AddDeleteEditDayCalendar(ArrayList<CalendarEvent> events, DayCalendar initialCalendar) {
       super(new BorderLayout());
       this.events = events;
      //constructor takes in two parameters, the events and the initialCalendar, and sets up the layout of the panel
       tabbedPane = new JTabbedPane();
       calendars = new ArrayList<>();
       addCalendar("Calendar");

       JButton addCalendarButton = new JButton("Add a Calendar");
       addCalendarButton.addActionListener(e -> addCalendar("New Calendar"));

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

       JPanel controlPanel = new JPanel();
       controlPanel.add(addCalendarButton);
       controlPanel.add(deleteCalendarButton);

       add(controlPanel, BorderLayout.NORTH);
       add(tabbedPane, BorderLayout.CENTER);
   }
   
   private void addCalendar(String name) {
       DayCalendar calendar = new DayCalendar(events);
       calendars.add(calendar);
       int index = tabbedPane.getTabCount();
       tabbedPane.addTab(name, calendar);
       tabbedPane.setTabComponentAt(index, new CalendarTabComponent(tabbedPane, name));
   }

   class CalendarTabComponent extends JPanel {
       private JLabel label;

       public CalendarTabComponent(JTabbedPane pane, String title) {
           super(new FlowLayout(FlowLayout.LEFT, 0, 0));
           setOpaque(false);

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

   public void addGoToTodayListener(JButton goToTodayBtn) {
       goToTodayBtn.addActionListener(e -> getCurrentCalendar().goToToday());
   }

   public void addNextDayListener(JButton nextDayBtn) {
       nextDayBtn.addActionListener(e -> getCurrentCalendar().nextDay());
   }

   public void addPrevDayListener(JButton prevDayBtn) {
       prevDayBtn.addActionListener(e -> getCurrentCalendar().prevDay());
   }

   private DayCalendar getCurrentCalendar() {
       int selectedIndex = tabbedPane.getSelectedIndex();
       return calendars.get(selectedIndex);
   }

   public void setAllCalendarsFontSize(int size) {
       for (DayCalendar calendar : calendars) {
           calendar.setFontSize(size);
       }
   }

   public void setAllCalendarsFontType(String type) {
       for (DayCalendar calendar : calendars) {
           calendar.setFontType(type);
       }
   }

}
