import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventInputForm {
    private BookingManager manager;

    public EventInputForm(BookingManager manager) {
        this.manager = manager;
        JFrame frame = new JFrame("Event Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel createP = new JPanel(new GridLayout(8, 2, 5, 5));
        JTextField idF = new JTextField();
        JTextField titleF = new JTextField();
        JTextField dateF = new JTextField("2026-03-30T19:00");
        JTextField locF = new JTextField();
        JTextField capF = new JTextField();
        JComboBox<String> typeB = new JComboBox<>(new String[] { "Workshop", "Seminar", "Concert" });
        JTextField specF = new JTextField(); // Special Info Field
        JButton addB = new JButton("Create Event");

        createP.add(new JLabel("Event ID:"));
        createP.add(idF);
        createP.add(new JLabel("Title:"));
        createP.add(titleF);
        createP.add(new JLabel("Date/Time:"));
        createP.add(dateF);
        createP.add(new JLabel("Location:"));
        createP.add(locF);
        createP.add(new JLabel("Capacity:"));
        createP.add(capF);
        createP.add(new JLabel("Type:"));
        createP.add(typeB);
        createP.add(new JLabel("Special Info:"));
        createP.add(specF);
        createP.add(new JLabel(""));
        createP.add(addB);

        // Results Display
        JTextArea area = new JTextArea(10, 30);
        area.setEditable(false);

        addB.addActionListener(e -> {
            try {
                String eventId = idF.getText().trim();
                String title = titleF.getText().trim();
                String dateTime = dateF.getText().trim();
                String location = locF.getText().trim();
                int capacity = Integer.parseInt(capF.getText().trim());
                String selectedType = (String) typeB.getSelectedItem();
                String extraValue = specF.getText().trim();

                Event newEvent = null;
                if (selectedType.equals("Workshop")) {
                    newEvent = new Workshop(eventId, title, dateTime, location, capacity, extraValue);
                } else if (selectedType.equals("Seminar")) {
                    newEvent = new Seminar(eventId, title, dateTime, location, capacity, extraValue);
                } else if (selectedType.equals("Concert")) {
                    newEvent = new Concert(eventId, title, dateTime, location, capacity, extraValue);
                }

                if (newEvent != null) {
                    manager.addEvent(newEvent);
                    area.setText("Successfully created " + selectedType + ": " + title);
                    // Clear fields
                    idF.setText(""); titleF.setText(""); locF.setText(""); capF.setText(""); specF.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Capacity must be a number.");
            }
        });

        // Search Panel
        JPanel searchP = new JPanel(new FlowLayout());
        JTextField sF = new JTextField(10);
        JButton sB = new JButton("Search Title");
        searchP.add(new JLabel("Search:"));
        searchP.add(sF);
        searchP.add(sB);

        sB.addActionListener(e -> {
            ArrayList<Event> res = manager.searchEventsByTitle(sF.getText());
            area.setText("Search Results:\n");
            for (Event ev : res) {
                area.append(ev.getEventId() + ": " + ev.getTitle() + " [" + ev.getEventType() + "]\n");
            }
        });

        frame.add(createP, BorderLayout.NORTH);
        frame.add(new JScrollPane(area), BorderLayout.CENTER);
        frame.add(searchP, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}