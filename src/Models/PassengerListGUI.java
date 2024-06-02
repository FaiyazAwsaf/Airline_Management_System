package Models;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PassengerListGUI extends JFrame {
    private JTable passengerTable;
    private DefaultTableModel tableModel;

    public PassengerListGUI() {
        setTitle("Passenger List");
        setSize(1300, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel headingLabel = new JLabel("Passenger List", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Serif", Font.BOLD, 30));
        add(headingLabel, BorderLayout.NORTH);

        String[] columnNames = {"Name", "Phone", "Email", "Passport", "Gender", "Nationality",
                "Flight Code", "Departure City", "Destination City", "Date",
                "Aircraft", "Seat"};

        tableModel = new DefaultTableModel(columnNames, 0);
        passengerTable = new JTable(tableModel);
        passengerTable.setFont(new Font("Open Sans", Font.BOLD, 12));
        passengerTable.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(passengerTable);
        add(scrollPane, BorderLayout.CENTER);

        loadPassengerList();
    }

    private void loadPassengerList() {
        PassengerList passengerList = new PassengerList();
        List<Passenger> passengers = passengerList.getPassengers();
        for (Passenger passenger : passengers) {
            tableModel.addRow(new Object[]{
                    passenger.getName(), passenger.getContact(), passenger.getEmail(), passenger.getPassportID(),
                    passenger.getGender(), passenger.getNationality(), passenger.getFlightCode(), passenger.getDepartureCity(),
                    passenger.getDestinationCity(), passenger.getDepartureDateAndTime(), passenger.getAirplaneModel(), passenger.getSeatNumber()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PassengerListGUI gui = new PassengerListGUI();
            gui.setVisible(true);
        });
    }
}
