package com;

import java.time.LocalDateTime;

public class Reminder {
   private String name;
   private LocalDateTime dateTime;
   private boolean completed;

   public Reminder(String name, LocalDateTime dateTime) {
       this.name = name;
       this.dateTime = dateTime;
       this.completed = false;
   }

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

   @Override
   public String toString() {
       return getName();
   }

   public boolean isCompleted() {
       return completed;
   }
   public void setCompleted(boolean completed) {
       this.completed = completed;
   }
}
