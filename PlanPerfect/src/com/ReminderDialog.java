package com;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class ReminderDialog extends JDialog {
    private JTextField reminderNameField;
    private JComboBox<Integer> hourBox;
    private JComboBox<String> minuteBox;
    private JComboBox<String> amPmBox;
    private JFormattedTextField dateField;
    private JButton saveButton, cancelButton;
    private Reminder reminder;

    public ReminderDialog(Frame owner) {
        super(owner, "Add a Reminder", true);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Reminder name
        JLabel nameLabel = new JLabel("Reminder Name:");
        reminderNameField = new JTextField(20);
        c.gridx = 0;
        c.gridy = 0;
        add(nameLabel, c);
        c.gridx = 1;
        add(reminderNameField, c);

        // Date
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateField = new JFormattedTextField(dateFormatter.format(LocalDate.now()));
        dateField.setColumns(10);
        c.gridx = 0;
        c.gridy = 1;
        add(dateLabel, c);
        c.gridx = 1;
        add(dateField, c);

        // Time
        JLabel timeLabel = new JLabel("Time:");
        hourBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            hourBox.addItem(i);
        }
        minuteBox = new JComboBox<>();
        for (int i = 0; i <= 59; i++) {
            minuteBox.addItem(String.format("%02d", i));
        }
        amPmBox = new JComboBox<>(new String[] { "AM", "PM" });
        JPanel timePanel = new JPanel();
        timePanel.add(hourBox);
        timePanel.add(new JLabel(":"));
        timePanel.add(minuteBox);
        timePanel.add(amPmBox);

        c.gridx = 0;
        c.gridy = 2;
        add(timeLabel, c);
        c.gridx = 1;
        add(timePanel, c);

        // Buttons added
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveReminder());
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(buttonPanel, c);

        pack();
        setLocationRelativeTo(owner);
    }

    //reminder registered into arraylist 
    private void saveReminder() {
        String name = reminderNameField.getText();
        LocalDate date = LocalDate.parse(dateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
        int hour = hourBox.getSelectedIndex() + 1;
        if (amPmBox.getSelectedIndex() == 1) {
            if (hour != 12)
                hour += 12; // Fix the time updating issue
        }
        LocalTime time = LocalTime.of(hour % 24, Integer.parseInt((String) minuteBox.getSelectedItem()));
        reminder = new Reminder(name, LocalDateTime.of(date, time));
        setVisible(false);
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
        reminderNameField.setText(reminder.getName());
        dateField.setValue(reminder.getDateTime().toLocalDate());
        hourBox.setSelectedItem(
                reminder.getDateTime().getHour() % 12 == 0 ? 12 : reminder.getDateTime().getHour() % 12);
        minuteBox.setSelectedItem(reminder.getDateTime().getMinute());
        amPmBox.setSelectedIndex(reminder.getDateTime().getHour() < 12 ? 0 : 1);
    }
}
