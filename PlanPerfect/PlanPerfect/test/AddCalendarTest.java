import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class AddCalendarTest {

    private JFrame calendarFrame;
    private JTabbedPane calendarTab;
    private JButton addCalendarBtn;

    @Before
    public void setUp() throws Exception {
        calendarFrame = new JFrame();
        calendarTab = new JTabbedPane();
        addCalendarBtn = new JButton("Add a Calendar");
        calendarFrame.getContentPane().add(calendarTab);
        calendarFrame.getContentPane().add(addCalendarBtn);
        calendarFrame.pack();
        calendarFrame.setVisible(true);
    }

    // Test to ensure that after 2 clicks, calendar can be renamed.
    @Test
    public void testAddCalendarTab() {
        addCalendarBtn.doClick();
        assertEquals("Tab count should be 2 after adding a new calendar", 2, calendarTab.getTabCount());
        assertNotNull("First Calendar tab should be present", calendarTab.getComponentAt(0));
        assertNotNull("Newly added calendar tab should be present", calendarTab.getComponentAt(1));
    }

    // Rename calendar
    @Test
    public void testRenameCalendar() {
        addCalendarBtn.doClick();
        Component newCalendarTab = calendarTab.getComponentAt(1);
        JLabel newCalendarTabLabel = (JLabel) calendarTab.getTabComponentAt(1);
        newCalendarTabLabel.getMouseListeners()[0].mouseClicked(null);
        assertNotNull("JOptionPane for renaming calendar should appear", getOptionPane(calendarFrame));
        JOptionPane.showInputDialog(calendarFrame, "Test");
        assertEquals("Newly added calendar should be renamed to Test", "Test", calendarTab.getTitleAt(1));
        assertEquals("Newly added calendar tab label should be renamed to Test", "Test", newCalendarTabLabel.getText());
    }

    // Helper method to get the JOptionTab shown on the frame
    private JOptionPane getOptionPane(final JFrame frame) {
        JOptionPane optionTab = null;
        Container container = frame.getFocusCycleRootAncestor();
        Component[] parts = container.getComponents();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i] instanceof JOptionPane) {
                optionTab = (JOptionPane) parts[i];
                break;
            }
        }
        return optionTab;
    }
}
