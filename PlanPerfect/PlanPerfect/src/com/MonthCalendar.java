package com;

// import java.time.DayOfWeek;
// import java.time.LocalDate;
// import java.util.ArrayList;


// public class MonthCalendar extends Calendar {
    
// }
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.*;


public class MonthCalendar extends JFrame {

    private LocalDate currentDate;

    public MonthCalendar() {
        this.currentDate = LocalDate.now();
        initUI();
    }

    private void initUI() {
        // Set the layout manager for the JFrame
        setLayout(new GridLayout(0, 7)); // 7 columns for 7 days of the week

        // Generate an array of LocalDate objects for the current month
        LocalDate[] daysOfMonth = new LocalDate[currentDate.lengthOfMonth()];
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        for (int i = 0; i < daysOfMonth.length; i++) {
            daysOfMonth[i] = firstDayOfMonth.plusDays(i);
        }

        // Add a JPanel for each day of the month
        for (LocalDate day : daysOfMonth) {
            JPanel panel = new JPanel();
            panel.add(new JLabel(String.valueOf(day.getDayOfMonth())));
            // Add any additional information to the panel
            add(panel);
        }

        // Set the JFrame properties
        setTitle("Calendar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MonthCalendar();
    }
}
