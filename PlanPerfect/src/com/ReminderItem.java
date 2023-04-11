package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReminderItem extends JPanel {
   private Reminder reminder;
   private ReminderPanel reminderPanel;
   private JCheckBox checkBox;
   private JLabel nameLabel;

   public ReminderItem(Reminder reminder, ReminderPanel reminderPanel) {
       this.reminder = reminder;
       this.reminderPanel = reminderPanel;
       setLayout(new FlowLayout(FlowLayout.LEFT));
       checkBox = new JCheckBox();
       checkBox.setSelected(reminder.isCompleted());
       checkBox.addActionListener(e -> {
           reminder.setCompleted(checkBox.isSelected());
       });
      //new checkBox added
       add(checkBox);

       nameLabel = new JLabel(reminder.getName());
       nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
       nameLabel.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                   int index = reminderPanel.getReminders().indexOf(reminder);
                   reminderPanel.showReminderDetails(index);
               }
           }
       });
       add(nameLabel);
   }
//fonts altered for labelling use
   
   public void setFontSize(int size) {
       nameLabel.setFont(nameLabel.getFont().deriveFont((float) size));
   }

   public void setFontType(String type) {
       nameLabel.setFont(new Font(type, nameLabel.getFont().getStyle(), nameLabel.getFont().getSize()));
   }
}
