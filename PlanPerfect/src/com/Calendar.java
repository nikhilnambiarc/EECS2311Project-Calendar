package com;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
//code represents an abstract class "Calendar" which extends the JComponent class 
// has various instance variables, methods and constructors
public abstract class Calendar extends JComponent {
//START_TIME: a constant of type "LocalTime" that is set to 00:00 (midnight)
    protected static final LocalTime START_TIME = LocalTime.of(0, 0);
//The class "Calendar" is an abstract class, meaning it cannot be instantiated but can be inherited by other classes 
    //that provide their own implementation for the abstract methods in the "Calendar" class
    protected static final LocalTime END_TIME = LocalTime.of(22, 59);
//END_TIME: a constant of type "LocalTime" that is set to 22:59 (10:59 PM
    protected static final int MIN_WIDTH = 500;
    //MIN_WIDTH: a constant integer value that represents the minimum width of the calendar
    protected static final int MIN_HEIGHT = MIN_WIDTH;
//MIN_HEIGHT: a constant integer value that represents the minimum height of the calendar.
    protected static final int HEADER_HEIGHT = 30;
    //HEADER_HEIGHT: a constant integer value that represents the height of the header
    public static final int TIME_COL_WIDTH = 100;
    //TIME_COL_WIDTH: a constant integer value that represents the width of the time column
    JFrame frm = new JFrame();
//frm: an instance of the "JFrame" class from the "javax.swing" package that is used to create the calendar window
    // An estimate of the width of a single character (not exact but good
    // enough)
    private static final int FONT_LETTER_PIXEL_WIDTH = 7;
    private ArrayList<CalendarEvent> events;
    private double timeScale;
    //FONT_LETTER_PIXEL_WIDTH: a constant integer value that represents the estimated width of a single character in pixels
    private double dayWidth;
    //g2: an instance of the "Graphics2D" class from the "java.awt" package that is used to draw graphics on the calenda
    private Graphics2D g2;
//events: an instance of the "ArrayList" class from the "java.util" package that stores the events on the calendar
    private EventListenerList listenerList = new EventListenerList();
//timeScale: a double value that represents the scale at which time is displayed on the calendar
    public Calendar() {
        this(new ArrayList<>());
    }
    public Calendar(Calendar calendar,Clock clock){
 //dayWidth: a double value that represents the width of a single day on the calendar
    }
//Calendar is a constructor with no arguments that initializes the "events" instance variable to an empty ArrayList
    //sets up the event listeners and timer for the calendar
    Calendar(ArrayList<CalendarEvent> events) {
        this.events = events;
        setupEventListeners();
        setupTimer();
    }
//a constructor with two arguments of type "Calendar" and "Clock" respectively
    public static LocalTime roundTime(LocalTime time, int minutes) {
        LocalTime t = time;

        if (t.getMinute() % minutes > minutes / 2) {
            //pass the string as a parameter
            t = t.plusMinutes(minutes - (t.getMinute() % minutes));
        } else if (t.getMinute() % minutes < minutes / 2) {
            t = t.minusMinutes(t.getMinute() % minutes);
        }

        return t;
    }
//a constructor with one argument of type "ArrayList<CalendarEvent>" that initializes the "events" instance variable to the passed ArrayList, and sets up the event listeners and timer for the calendar
    // Setup Event Listener
    private void setupEventListeners() {
        //new setup via object MouseAdapter
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (!checkCalendarEventDoubleClick(e.getPoint())) {
                        checkCalendarEmptyClick(e.getPoint());
                    }
                } else {
                    if (!checkCalendarEventClick(e.getPoint())) {
                        checkCalendarEmptyClick(e.getPoint());
                    }
                }
            }
        });
    }
//end of constructor section
    // Check Calendar Event Double Click
    private boolean checkCalendarEventDoubleClick(Point p) {
        double x0, x1, y0, y1;
        for (CalendarEvent event : events) {
            if (!dateInRange(event.getDate()))
                continue;

            x0 = dayToPixel(event.getDate().getDayOfWeek());
            y0 = timeToPixel(event.getStart());
            x1 = dayToPixel(event.getDate().getDayOfWeek()) + dayWidth;
            y1 = timeToPixel(event.getEnd());

            if (p.getX() >= x0 && p.getX() <= x1 && p.getY() >= y0 && p.getY() <= y1) {
                showEventGoal(event);
                return true;
            }
        }
        return false;
    }

    // Show Event Goal
    private void showEventGoal(CalendarEvent event) {
        JOptionPane.showMessageDialog(this, "My Goal: " + event.getGoal(), "Event Goal",
                JOptionPane.INFORMATION_MESSAGE);
    }

    protected abstract boolean dateInRange(LocalDate date);

    private boolean checkCalendarEventClick(Point p) {
        double x0, x1, y0, y1;
        for (CalendarEvent event : events) {
            if (!dateInRange(event.getDate())) continue;

            x0 = dayToPixel(event.getDate().getDayOfWeek());
            y0 = timeToPixel(event.getStart());
            x1 = dayToPixel(event.getDate().getDayOfWeek()) + dayWidth;
            y1 = timeToPixel(event.getEnd());

            if (p.getX() >= x0 && p.getX() <= x1 && p.getY() >= y0 && p.getY() <= y1) {
                fireCalendarEventClick(event);
                return true;
            }
        }
        return false;
    }

    private boolean checkCalendarEmptyClick(Point p) {
        final double x0 = dayToPixel(getStartDay());
        final double x1 = dayToPixel(getEndDay()) + dayWidth;
        final double y0 = timeToPixel(START_TIME);
        final double y1 = timeToPixel(END_TIME);

        if (p.getX() >= x0 && p.getX() <= x1 && p.getY() >= y0 && p.getY() <= y1) {
            LocalDate date = getDateFromDay(pixelToDay(p.getX()));
            fireCalendarEmptyClick(LocalDateTime.of(date, pixelToTime(p.getY())));
            return true;
        }
        return false;
    }

    protected abstract LocalDate getDateFromDay(DayOfWeek day);

    // CalendarEventClick methods

    public void addCalendarEventClickListener(CalendarEventClickListener l) {
        listenerList.add(CalendarEventClickListener.class, l);
    }
//a static method that takes a "LocalTime" object and an integer representing the number of minutes
    //and rounds the time to the nearest multiple of that number of minutes
    public void removeCalendarEventClickListener(CalendarEventClickListener l) {
        listenerList.remove(CalendarEventClickListener.class, l);
    }

    // Notify all listeners that have registered interest for
    // notification on this event type.
    private void fireCalendarEventClick(CalendarEvent calendarEvent) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        CalendarEventClickEvent calendarEventClickEvent;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CalendarEventClickListener.class) {
                calendarEventClickEvent = new CalendarEventClickEvent(this, calendarEvent);
                ((CalendarEventClickListener) listeners[i + 1]).calendarEventClick(calendarEventClickEvent);
            }
        }
    }

    // CalendarEmptyClick methods
//a method that sets up the event listeners for the calendar, including a mouse click listener that 
    //checks for double clicks on events and empty areas of the calendar
    public void addCalendarEmptyClickListener(CalendarEmptyClickListener l) {
        listenerList.add(CalendarEmptyClickListener.class, l);
    }

    public void removeCalendarEmptyClickListener(CalendarEmptyClickListener l) {
        listenerList.remove(CalendarEmptyClickListener.class, l);
    }

    private void fireCalendarEmptyClick(LocalDateTime dateTime) {
        Object[] listeners = listenerList.getListenerList();
        CalendarEmptyClickEvent calendarEmptyClickEvent;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == CalendarEmptyClickListener.class) {
                calendarEmptyClickEvent = new CalendarEmptyClickEvent(this, dateTime);
                ((CalendarEmptyClickListener) listeners[i + 1]).calendarEmptyClick(calendarEmptyClickEvent);
            }
        }
    }

    private void calculateScaleVars() {
        int width = getWidth();
        int height = getHeight();

        if (width < MIN_WIDTH) {
            width = MIN_WIDTH;
        }

        if (height < MIN_HEIGHT) {
            height = MIN_HEIGHT;
        }

        // Units are pixels per second
        timeScale = (double) (height - HEADER_HEIGHT) / (END_TIME.toSecondOfDay() - START_TIME.toSecondOfDay());
        dayWidth = (width - TIME_COL_WIDTH) / numDaysToShow();
    }

    protected abstract int numDaysToShow();
//method that takes a "Point" object representing a mouse click, and checks if the click was a double click on an event
    // Gives x val of left most pixel for day col
    protected abstract double dayToPixel(DayOfWeek dayOfWeek);

    private double timeToPixel(LocalTime time) {
        return ((time.toSecondOfDay() - START_TIME.toSecondOfDay()) * timeScale) + HEADER_HEIGHT;
    }

    private LocalTime pixelToTime(double y) {
        return LocalTime.ofSecondOfDay((int) ((y - HEADER_HEIGHT) / timeScale) + START_TIME.toSecondOfDay()).truncatedTo(ChronoUnit.MINUTES);
    }

    private DayOfWeek pixelToDay(double x) {
        double pixel;
        DayOfWeek day;
        for (int i = getStartDay().getValue(); i <= getEndDay().getValue(); i++) {
            day = DayOfWeek.of(i);
            pixel = dayToPixel(day);
            if (x >= pixel && x < pixel + dayWidth) {
                return day;
            }
        }
        return null;
    }
//a method that takes a "CalendarEvent" object and shows the event goal in a pop-up window
    @Override
    protected void paintComponent(Graphics g) {
        calculateScaleVars();
        g2 = (Graphics2D) g;

        // Rendering hints try to turn anti-aliasing on which improves quality
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set background to white
        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Set paint colour to black
        g2.setColor(Color.white);

        drawDayHeadings();
        drawTodayShade();
        drawGrid();
        drawTimes();
        drawEvents();
        drawCurrentTimeLine();
    }

    protected abstract DayOfWeek getStartDay();

    protected abstract DayOfWeek getEndDay();

    private void drawDayHeadings() {
        int y = 20;
        int x;
        LocalDate day;
        DayOfWeek dayOfWeek;

        for (int i = getStartDay().getValue(); i <= getEndDay().getValue(); i++) {
            dayOfWeek = DayOfWeek.of(i);
            day = getDateFromDay(dayOfWeek);

            String text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + day.getDayOfMonth() + "/" + day.getMonthValue();
            x = (int) (dayToPixel(DayOfWeek.of(i)) + (dayWidth / 2) - (FONT_LETTER_PIXEL_WIDTH * text.length() / 2));
            g2.drawString(text, x, y);
        }
    }

    private void drawGrid() {
        // Save the original colour
        final Color ORIG_COLOUR = g2.getColor();

        // Set colour to grey with half alpha (opacity)
        Color alphaGray = new Color(255, 255, 255, 0);
        Color alphaGrayLighter = new Color(255, 255, 255, 128);
        g2.setColor(alphaGray);

        // Draw vertical grid lines
        double x;
        for (int i = getStartDay().getValue(); i <= getEndDay().getValue(); i++) {
            x = dayToPixel(DayOfWeek.of(i));
            g2.draw(new Line2D.Double(x, HEADER_HEIGHT, x, timeToPixel(END_TIME)));
        }

        // Draw horizontal grid lines
        double y;
        int x1;
        for (LocalTime time = START_TIME; time.compareTo(END_TIME) <= 0; time = time.plusMinutes(30)) {
            y = timeToPixel(time);
            if (time.getMinute() == 0) {
                g2.setColor(alphaGray);
                x1 = 0;
            } else {
                g2.setColor(alphaGrayLighter);
                x1 = TIME_COL_WIDTH;
            }
            g2.draw(new Line2D.Double(x1, y, dayToPixel(getEndDay()) + dayWidth, y));
        }

        // Reset the graphics context's colour
        g2.setColor(ORIG_COLOUR);
    }

    private void drawTodayShade() {
        LocalDate today = LocalDate.now();

        // Check that date range being viewed is current date range
        if (!dateInRange(today)) return;

        final double x = dayToPixel(today.getDayOfWeek());
        final double y = timeToPixel(START_TIME);
        final double width = dayWidth;
        final double height = timeToPixel(END_TIME) - timeToPixel(START_TIME);

        final Color origColor = g2.getColor();
        Color alphaGray = new Color(200, 200, 200, 64);
        g2.setColor(alphaGray);
        g2.fill(new Rectangle2D.Double(x, y, width, height));
        g2.setColor(origColor);
    }

    private void drawCurrentTimeLine() {
        LocalDate today = LocalDate.now();

        // Check that date range being viewed is current date range
        if (!dateInRange(today)) return;

        final double x0 = dayToPixel(today.getDayOfWeek());
        final double x1 = dayToPixel(today.getDayOfWeek()) + dayWidth;
        final double y = timeToPixel(LocalTime.now());

        final Color origColor = g2.getColor();
        final Stroke origStroke = g2.getStroke();

        g2.setColor(new Color(255, 127, 110));
        g2.setStroke(new BasicStroke(2));
        g2.draw(new Line2D.Double(x0, y, x1, y));

        g2.setColor(origColor);
        g2.setStroke(origStroke);
    }

    private void drawTimes() {
        int y;
        for (LocalTime time = START_TIME; time.compareTo(END_TIME) <= 0; time = time.plusHours(1)) {
            y = (int) timeToPixel(time) + 15;
            g2.drawString(time.toString(), TIME_COL_WIDTH - (FONT_LETTER_PIXEL_WIDTH * time.toString().length()) - 5, y);
        }
    }

    private void drawEvents() {
        double x;
        double y0;

        for (CalendarEvent event : events) {
            if (!dateInRange(event.getDate())) continue;

            x = dayToPixel(event.getDate().getDayOfWeek());
            y0 = timeToPixel(event.getStart());

            Rectangle2D rect = new Rectangle2D.Double(x, y0, dayWidth, (timeToPixel(event.getEnd()) - timeToPixel(event.getStart())));
            Color origColor = g2.getColor();
            g2.setColor(event.getColor());
            g2.fill(rect);
            g2.setColor(origColor);

            // Draw time header

            // Store the current font state
            Font origFont = g2.getFont();

            final float fontSize = origFont.getSize() - 1.6F;

            // Create a new font with same properties but bold
            Font newFont = origFont.deriveFont(Font.BOLD, fontSize);
            g2.setFont(newFont);

            g2.drawString(event.getStart() + " - " + event.getEnd(), (int) x + 5, (int) y0 + 11);

            // Unbolden
            g2.setFont(origFont.deriveFont(fontSize));

            // Draw the event's text
            g2.drawString(event.getText(), (int) x + 5, (int) y0 + 23);

            // Reset font
            g2.setFont(origFont);
        }
    }

    protected double getDayWidth() {
        return dayWidth;
    }

    // Repaints every minute to update the current time line
    private void setupTimer() {
        Timer timer = new Timer(1000*60, e -> repaint());
        timer.start();
    }

    protected abstract void setRangeToToday();
//a method that takes a "Point" object representing a mouse click, and checks if the click was on an event
    public void goToToday() {
        setRangeToToday();
        repaint();
    }

    public void addEvent(CalendarEvent event) {
        events.add(event);
        repaint();
    }

    public boolean removeEvent(CalendarEvent event) {
        boolean removed = events.remove(event);
        repaint();
        return removed;
    }

    public void setEvents(ArrayList<CalendarEvent> events) {
        this.events = events;
        repaint();
    }
//an abstract method that takes a "LocalDate" object and returns a boolean indicating whether the date is within the range of dates displayed on the calendar
    public void setFontSize(int size) {
        Font font = getFont().deriveFont((float) size);
        setFont(font);
    }


    public void setFontType(String type) {
        Font font = getFont();
        font = new Font(type, font.getStyle(), font.getSize());
        setFont(font);
    }
//This method is use to check if there is any event that is passed according to the current time.
    public ArrayList<CalendarEvent> getEventAlreadyPassed() {
        ArrayList<CalendarEvent> eventPassedAlready = new ArrayList<>();
        LocalDate DateOfToday = LocalDate.now();
        for (CalendarEvent eventpassed : events) {

            if (eventpassed.getDate().isBefore(DateOfToday)) {
                eventPassedAlready.add(eventpassed);
            }
        }
        return eventPassedAlready;
    }

}
