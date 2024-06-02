package Models;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JPanel titlePanel = new JPanel();
        ImageIcon AirlineImage = new ImageIcon("src\\Resources\\FlightImage.png");
        JLabel titleImage = new JLabel(AirlineImage, SwingConstants.CENTER);
        titlePanel.add(titleImage);
        titlePanel.setBackground(new Color(0, 0, 0));

        add(titlePanel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel(new BorderLayout());

        JLabel headingLabel = new JLabel("Passenger List", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Serif", Font.BOLD, 30));
        headerPanel.add(headingLabel, BorderLayout.NORTH);
        headerPanel.setBackground(new Color(211, 231, 239));

        String[] columnNames = {"Name", "Phone", "Email", "Passport", "Gender", "Nationality",
                "Flight Code", "Departure City", "Destination City", "Date",
                "Aircraft", "Seat"};

        tableModel = new DefaultTableModel(columnNames, 0);
        passengerTable = new JTable(tableModel);
        passengerTable.setFont(new Font("Open Sans", Font.BOLD, 12));
        passengerTable.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(passengerTable);
        headerPanel.add(scrollPane, BorderLayout.CENTER);

        loadPassengerList();

        add(headerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(100,70,100,100);

        JButton backButton = new JButton("Back");
        //backButton.setSize(50,30);
        backButton.setPreferredSize(new Dimension(70, 30));
        backButton.setBackground(new Color(146, 19, 19));
        backButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        backButton.setMargin(new Insets(10, 5, 10, 5));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminHomePage page = new AdminHomePage();
                page.setVisible(true);
                dispose();
            }
        });
        add(buttonPanel, BorderLayout.SOUTH);
    }



    private void loadPassengerList() {
        Passenger passengerList = new Passenger();
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
