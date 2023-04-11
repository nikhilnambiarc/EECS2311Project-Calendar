package com;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
 //imports

import javax.swing.*;
 
import static java.awt.GridBagConstraints.BASELINE_LEADING;
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.HORIZONTAL;
public final class NewEventPanel extends JPanel {
 //code defines a Java Swing panel named "NewEventPanel" for creating a new calendar event
  private static final long serialVersionUID = 1L;
 
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
    .ofLocalizedDate(FormatStyle.FULL)
    .withLocale(Locale.US);
 //panel has three components: a read-only text field for the event's date, a text field for the event's title, and a text area for the event's description
  private final LocalDate date;
 
  private final JTextField titleField = new JTextField();
 
  private final JTextArea descriptionArea = new JTextArea();
 
  public NewEventPanel(LocalDate date) {
    super(new GridBagLayout());
 //class takes a LocalDate object as a constructor argument, which is used to initialize the date field
    if (date == null)
      throw new IllegalArgumentException();
 
    this.date = date;
 //title and description fields can be accessed through public getter methods
    initializeComponents();
  }
 //new LocalDate object to be super-method later
  LocalDate date() {
    return date;
  }
 //panel is built using the GridBagLayout manager, which allows for more complex layout management
  public String title() {
    return titleField.getText();
  }
 //simpler layout managers like BorderLayout
  public String description() {
    return descriptionArea.getText();
  }
 
  private void initializeComponents() {
 //code initializes some constants
    var dateField = new JTextField(date.format(DATE_FORMATTER));
    dateField.setEditable(false);
 //uses the DateTimeFormatter class to format the date in a human-readable format using the US timing construct
    descriptionArea.setPreferredSize(new Dimension(300, 150));
    var descriptionPane = new JScrollPane(descriptionArea);
 //creates the necessary Swing components and sets their properties
   
    var constraints = new GridBagConstraints();
    constraints.anchor = BASELINE_LEADING;
    constraints.insets = new Insets(4, 4, 4, 4);
 //disabling the date field and adding a scroll pane to the description area
    constraints.gridx = 0;
    add(new JLabel("Date:"),        constraints);
    add(new JLabel("Title:"),       constraints);
    add(new JLabel("Description:"), constraints);
 //components are added to the panel using GridBagConstraints to specify the layout constraints
    constraints.gridx   = 1;
    constraints.weightx = 1;
    constraints.fill    = HORIZONTAL;
    add(dateField,  constraints);
    add(titleField, constraints);
 //component's grid position and fill properties. Overall, this code defines a reusable Swing panel
   
    constraints.weighty = 1;
    constraints.fill    = BOTH;
   //creating new calendar events with date, title, and description fields
    add(new JScrollPane(descriptionPane), constraints);
  }
}
