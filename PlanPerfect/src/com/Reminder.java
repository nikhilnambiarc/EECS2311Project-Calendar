package com;

import java.time.LocalDateTime;

public class Reminder {
   private String name;
   //a String that represents the name of the reminder
   private LocalDateTime dateTime;
   //LocalDateTime object that represents the date and time of the reminder
   private boolean completed;
   //boolean that indicates whether the reminder has been completed

   //constructor takes two parameters
   //class has getter and setter methods for all three fields
   public Reminder(String name, LocalDateTime dateTime) {
       this.name = name;
       this.dateTime = dateTime;
       this.completed = false;
   }
//name of Reminder - can be altered later
   
   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public LocalDateTime getDateTime() {
       return dateTime;
   }

   public void setDateTime(LocalDateTime dateTime) {
       this.dateTime = dateTime;
   }
//method is overridden to return the name of the reminder
   @Override
   public String toString() {
       return getName();
   }
//completion status updated
   public boolean isCompleted() {
       return completed;
   }
   //class provides a simple way to represent a reminder and store information
   public void setCompleted(boolean completed) {
       this.completed = completed;
   }
}
