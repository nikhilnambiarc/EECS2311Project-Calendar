package com;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderPanel extends JPanel {
   //Have panel, message and box for no reminders case and for a list of reminders contained
   private JLabel titleLabel;
   private JLabel noRemindersLabel;
   private ArrayList<Reminder> reminders;
   private Box reminderBox;
   public ReminderPanel() {
       setLayout(new BorderLayout());
  
       JPanel headerPanel = new JPanel(new BorderLayout());
       add(headerPanel, BorderLayout.NORTH);
       reminders = new ArrayList<>();
       titleLabel = new JLabel("Reminders");
       titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
       titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
       headerPanel.add(titleLabel, BorderLayout.NORTH);
  
       noRemindersLabel = new JLabel("No reminders have been added");
       noRemindersLabel.setFont(new Font("Arial", Font.PLAIN, 16));
       noRemindersLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
       headerPanel.add(noRemindersLabel, BorderLayout.CENTER);
  
       reminderBox = new Box(BoxLayout.Y_AXIS);
       add(reminderBox, BorderLayout.CENTER);
   }
      
   public void addReminder(Reminder reminder) {
       reminders.add(reminder);
       reminders.sort(Comparator.comparing(Reminder::getDateTime));
       refreshRemindersDisplay();
  
       // Schedule the alert
       Timer timer = new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               SoundUtils.playBeep();
               JOptionPane.showMessageDialog(ReminderPanel.this,
                       "Reminder: " + reminder.getName(),
                       "Reminder Alert",
                       JOptionPane.INFORMATION_MESSAGE);
           }
       }, Date.from(reminder.getDateTime().atZone(ZoneId.systemDefault()).toInstant()));       
   }   

   private void addReminderLabel(Reminder reminder) {
       ReminderItem reminderItem = new ReminderItem(reminder, this);
       reminderItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, reminderItem.getPreferredSize().height));
       reminderBox.add(reminderItem);
   }
  
   public void showReminderDetails(int index) {
       if (index >= 0 && index < reminders.size()) {
           Reminder reminder = reminders.get(index);
           JOptionPane.showMessageDialog(this,
                   "Name: " + reminder.getName() + "\n" +
                   "Date: " + reminder.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
                   "Time: " + reminder.getDateTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
                   "Reminder Details",
                   JOptionPane.INFORMATION_MESSAGE);
       }
   }
  
   public Reminder getReminderByName(String name) {
       for (Reminder reminder : reminders) {
           if (reminder.getName().equals(name)) {
               return reminder;
           }
       }
  
       return null;
   }

   private void refreshRemindersDisplay() {
       reminderBox.removeAll();
       for (Reminder reminder : reminders) {
           addReminderLabel(reminder);
       }
  
       if (reminders.isEmpty()) {
           noRemindersLabel.setVisible(true);
       } else {
           noRemindersLabel.setVisible(false);
       }
   }   
  
   public void updateReminder(Reminder oldReminder, Reminder newReminder) {
       int index = reminders.indexOf(oldReminder);
  
       if (index != -1) {
           reminders.set(index, newReminder);
           reminders.sort(Comparator.comparing(Reminder::getDateTime));
           refreshRemindersDisplay();
       }
   }

   public void deleteReminder(Reminder reminder) {
       reminders.remove(reminder);
       refreshRemindersDisplay();
   }

   public ArrayList<Reminder> getReminders() {
       return reminders;
   }   

   public void setFontSize(int size) {
       titleLabel.setFont(titleLabel.getFont().deriveFont((float) size));
       noRemindersLabel.setFont(noRemindersLabel.getFont().deriveFont((float) size));
       for (Component comp : getComponents()) {
           if (comp instanceof JLabel) {
               comp.setFont(comp.getFont().deriveFont((float) size));
           }
       }
   }

   public void setFontType(String type) {
       titleLabel.setFont(new Font(type, Font.BOLD, titleLabel.getFont().getSize()));
       noRemindersLabel.setFont(new Font(type, Font.PLAIN, noRemindersLabel.getFont().getSize()));
       for (Component comp : getComponents()) {
           if (comp instanceof JLabel) {
               comp.setFont(new Font(type, comp.getFont().getStyle(), comp.getFont().getSize()));
           }
       }
   }
}
