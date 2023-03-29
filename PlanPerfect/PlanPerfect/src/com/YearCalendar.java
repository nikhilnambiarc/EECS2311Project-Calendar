package com;

import javax.swing.*;
import java.awt.*;

public class YearCalendar extends JFrame {
    
    public YearCalendar() {
        setTitle("Year View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new GridLayout(3, 4));
        
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        
        for (int i = 0; i < 12; i++) {
            JPanel monthPanel = new JPanel(new GridLayout(0, 7));
            JLabel monthLabel = new JLabel(months[i]);
            monthLabel.setHorizontalAlignment(JLabel.CENTER);
            monthPanel.add(monthLabel);
            monthPanel.add(new JLabel("Sun"));
            monthPanel.add(new JLabel("Mon"));
            monthPanel.add(new JLabel("Tue"));
            monthPanel.add(new JLabel("Wed"));
            monthPanel.add(new JLabel("Thu"));
            monthPanel.add(new JLabel("Fri"));
            monthPanel.add(new JLabel("Sat"));
            
            int numDays = getNumDays(i + 1);
            int firstDay = getFirstDay(i + 1);
            int dayOfWeek = 1;
            for (int j = 1; j <= numDays; j++) {
                if (j == 1) {
                    for (int k = 1; k <= firstDay; k++) {
                        monthPanel.add(new JLabel(""));
                        dayOfWeek++;
                    }
                }
                monthPanel.add(new JLabel(String.valueOf(j)));
                dayOfWeek++;
                if (dayOfWeek == 8) {
                    dayOfWeek = 1;
                }
            }
            
            mainPanel.add(monthPanel);
        }
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private int getNumDays(int month) {
        switch (month) {
            case 2:
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }
    
    private int getFirstDay(int month) {
        switch (month) {
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 5;
            case 4:
                return 1;
            case 5:
                return 3;
            case 6:
                return 6;
            case 7:
                return 1;
            case 8:
                return 4;
            case 9:
                return 7;
            case 10:
                return 2;
            case 11:
                return 5;
            default:
                return 7;
        }
    }
    
    
}
