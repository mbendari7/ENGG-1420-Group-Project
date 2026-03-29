
// EventInputForm.java
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventInputForm {

    BookingManager bookingManager; // lets this form use BookingManager
    public EventInputForm() { // Changed to constructor
        JFrame frame = new JFrame("Phase 1: Create Event");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // FIXED: prevents closing entire app
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Event ID:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Title:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Capacity:"));
        panel.add(new JTextField());

        panel.add(new JLabel("Type:"));
        String[] eTypes = { "Workshop", "Seminar", "Concert" };
        panel.add(new JComboBox<>(eTypes));

        JButton btn = new JButton("Create Event");
        panel.add(new JLabel(""));
        panel.add(btn);

        frame.add(panel);
        frame.setLocationRelativeTo(null); // Centers window
        frame.setVisible(true);
    }
    // Phase 2 constructor
    // this version connects the form to BookingManager
    public EventInputForm(BookingManager bookingManager) {
        this.bookingManager = bookingManager; // save BookingManager so we can add/search events

        JFrame frame = new JFrame("Phase 2: Event Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 650);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // top panel for creating events
        JPanel createPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        // text fields for event info
        JTextField eventIdField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField dateTimeField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField capacityField = new JTextField();

        // dropdown for event type
        String[] eTypes = { "Workshop", "Seminar", "Concert" };
        JComboBox<String> typeBox = new JComboBox<>(eTypes);

        // one extra field for topic / speaker / age restriction
        JTextField extraField = new JTextField();

        createPanel.add(new JLabel("Event ID:"));
        createPanel.add(eventIdField);

        createPanel.add(new JLabel("Title:"));
        createPanel.add(titleField);

        createPanel.add(new JLabel("Date/Time:"));
        createPanel.add(dateTimeField);

        createPanel.add(new JLabel("Location:"));
        createPanel.add(locationField);

        createPanel.add(new JLabel("Capacity:"));
        createPanel.add(capacityField);

        createPanel.add(new JLabel("Type:"));
        createPanel.add(typeBox);

        createPanel.add(new JLabel("Topic / Speaker / Age Restriction:"));
        createPanel.add(extraField);

        JButton createButton = new JButton("Create Event");
        createPanel.add(new JLabel(""));
        createPanel.add(createButton);

        // middle panel for search
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search by Title");

        searchPanel.add(new JLabel("Search Title:"), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // text area to show results
        JTextArea resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        mainPanel.add(createPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(searchPanel, BorderLayout.SOUTH);

        // create event button logic
        createButton.addActionListener(e -> {
            try {
                // get values from fields
                String eventId = eventIdField.getText().trim();
                String title = titleField.getText().trim();
                String dateTime = dateTimeField.getText().trim();
                String location = locationField.getText().trim();
                int capacity = Integer.parseInt(capacityField.getText().trim());
                String selectedType = (String) typeBox.getSelectedItem();
                String extraValue = extraField.getText().trim();

                Event newEvent = null; // will hold the event object

                // create the correct event subclass
                if (selectedType.equals("Workshop")) {
                    newEvent = new Workshop(eventId, title, dateTime, location, capacity, extraValue);
                } else if (selectedType.equals("Seminar")) {
                    newEvent = new Seminar(eventId, title, dateTime, location, capacity, extraValue);
                } else if (selectedType.equals("Concert")) {
                    newEvent = new Concert(eventId, title, dateTime, location, capacity, extraValue);
                }

                // store the event in BookingManager
                bookingManager.addEvent(newEvent);

                // show success message
                resultsArea.setText("Event created successfully:\n\n" + formatEvent(newEvent));

                // clear fields after creating
                eventIdField.setText("");
                titleField.setText("");
                dateTimeField.setText("");
                locationField.setText("");
                capacityField.setText("");
                extraField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Capacity must be a number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error creating event.");
            }
        });

        // search button logic
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim(); // get search text

            ArrayList<Event> results = bookingManager.searchEventsByTitle(searchText); // search events

            if (results.size() == 0) {
                resultsArea.setText("No matching events found.");
            } else {
                String output = "Search Results:\n\n";

                for (int i = 0; i < results.size(); i++) {
                    output += formatEvent(results.get(i)) + "\n\n";
                }

                resultsArea.setText(output);
            }
        });

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    // formats one event into readable text
    private String formatEvent(Event event) {
        return "ID: " + event.getEventId()
                + "\nTitle: " + event.getTitle()
                + "\nDate/Time: " + event.getDateTime()
                + "\nLocation: " + event.getLocation()
                + "\nCapacity: " + event.getCapacity()
                + "\nType: " + event.getEventType()
                + "\nStatus: " + event.getStatus();
    }
}