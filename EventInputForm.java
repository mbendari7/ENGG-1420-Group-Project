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
                Event ev;
                String t = (String) typeB.getSelectedItem();
                int cap = Integer.parseInt(capF.getText());
                if (t.equals("Workshop"))
                    ev = new Workshop(idF.getText(), titleF.getText(), dateF.getText(), "Campus", cap, specF.getText());
                else if (t.equals("Seminar"))
                    ev = new Seminar(idF.getText(), titleF.getText(), dateF.getText(), "Campus", cap, specF.getText());
                else
                    ev = new Concert(idF.getText(), titleF.getText(), dateF.getText(), "Campus", cap, specF.getText());
                manager.addEvent(ev);
                JOptionPane.showMessageDialog(frame, "Event Created!");
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