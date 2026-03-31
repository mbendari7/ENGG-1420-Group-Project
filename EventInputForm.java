import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventInputForm {
    BookingManager manager;

    public EventInputForm(BookingManager manager) {
        this.manager = manager;
        JFrame frame = new JFrame("Event Management");
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        JPanel createP = new JPanel(new GridLayout(6, 2, 5, 5));
        JTextField idF = new JTextField();
        JTextField titleF = new JTextField();
        JTextField capF = new JTextField();
        JTextField specF = new JTextField();
        JTextField dateF = new JTextField("2026-03-30T19:00");
        JComboBox<String> typeB = new JComboBox<>(new String[] { "Workshop", "Seminar", "Concert" });
        JButton addB = new JButton("Create Event");

        createP.add(new JLabel("ID:"));
        createP.add(idF);
        createP.add(new JLabel("Title:"));
        createP.add(titleF);
        createP.add(new JLabel("Date/Time:"));
        createP.add(dateF);
        createP.add(new JLabel("Capacity:"));
        createP.add(capF);
        createP.add(new JLabel("Type:"));
        createP.add(typeB);
        createP.add(new JLabel("Spec Info:"));
        createP.add(specF);

        JPanel buttonP = new JPanel();
        buttonP.add(addB);

        JPanel searchP = new JPanel();
        JTextField sF = new JTextField(10);
        JButton sB = new JButton("Search Title");
        searchP.add(new JLabel("Search:"));
        searchP.add(sF);
        searchP.add(sB);

        JTextArea area = new JTextArea(10, 30);

        addB.addActionListener(e -> {
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
                JOptionPane.showMessageDialog(frame, "Error: Check capacity format.");
            }
        });

        sB.addActionListener(e -> {
            ArrayList<Event> res = manager.searchEventsByTitle(sF.getText());
            area.setText("Results:\n");
            for (Event ev : res)
                area.append(ev.getEventId() + ": " + ev.getTitle() + " (" + ev.getEventType() + ")\n");
        });

        frame.add(createP, BorderLayout.NORTH);
        frame.add(buttonP, BorderLayout.CENTER);

        JPanel bottomP = new JPanel(new BorderLayout());
        bottomP.add(searchP, BorderLayout.NORTH);
        bottomP.add(new JScrollPane(area), BorderLayout.CENTER);
        frame.add(bottomP, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}