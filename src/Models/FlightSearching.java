package Models;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlightSearching extends JFrame {
    private JComboBox<String> sourceComboBox;
    private JComboBox<String> destinationComboBox;
    private JTable searchResultsTable;
    private DefaultTableModel searchResultsTableModel;
    private static final String FILE_PATH = "src\\Data_Storage\\flights_data.csv";

    public FlightSearching() {
        setTitle("Search Flights");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        ImageIcon AirlineImage = new ImageIcon("src\\Resources\\FlightImage.png");
        JLabel titleImage = new JLabel(AirlineImage, SwingConstants.CENTER);
        titlePanel.add(titleImage);
        titlePanel.setBackground(new Color(0, 0, 0));
        add(titlePanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 10, 10);
        searchPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        searchPanel.setBackground(new Color(225, 217, 203));

        JLabel sourceLabel = new JLabel("Departure City");
        sourceLabel.setFont(new Font("Hind", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(sourceLabel, gbc);

        sourceComboBox = new JComboBox<>(new String[]{"Dhaka", "Chittagong", "Sylhet", "Barisal", "London", "Chennai", "Kolkata", "Toronto", "Dubai"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        searchPanel.add(sourceComboBox, gbc);

        JLabel destinationLabel = new JLabel("Destination");
        destinationLabel.setFont(new Font("Hind", Font.BOLD, 15));
        gbc.gridx = 2;
        gbc.gridy = 0;
        searchPanel.add(destinationLabel, gbc);

        destinationComboBox = new JComboBox<>(new String[]{"Dhaka", "Chittagong", "Sylhet", "Barisal", "London", "Chennai", "Kolkata", "Toronto", "Dubai"});
        gbc.gridx = 3;
        gbc.gridy = 0;
        searchPanel.add(destinationComboBox, gbc);

        JButton searchButton = new JButton("Search");
        gbc.gridx = 4;
        gbc.gridy = 0;
        searchButton.setPreferredSize(new Dimension(80, 12));
        searchButton.setBackground(new Color(9, 13, 97));
        searchButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        searchButton.setForeground(Color.WHITE);
        searchPanel.add(searchButton, gbc);

        add(searchPanel, BorderLayout.CENTER);

        JPanel searchResultTablePanel = new JPanel(new BorderLayout());
        searchResultsTableModel = new DefaultTableModel(new Object[]{"Flight Code", "Departure City", "Destination", "Departure Date and Time", "Airplane Model", "Seats"}, 0);
        searchResultsTable = new JTable(searchResultsTableModel);
        JScrollPane scrollPane = new JScrollPane(searchResultsTable);
        searchResultTablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(5, 5, 5, 5);
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;

        JButton selectButton = new JButton("Select Flight");
        selectButton.setPreferredSize(new Dimension(150, 30));
        selectButton.setBackground(new Color(9, 13, 97));
        selectButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        selectButton.setForeground(Color.WHITE);
        buttonPanel.add(selectButton, gbcButton);

        gbcButton.gridy = 1;

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setBackground(new Color(146, 19, 19));
        backButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton, gbcButton);

        searchResultTablePanel.add(buttonPanel, BorderLayout.SOUTH);
        add(searchResultTablePanel, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departureCity = (String) sourceComboBox.getSelectedItem();
                String destinationCity = (String) destinationComboBox.getSelectedItem();
                searchFlights(departureCity, destinationCity);
            }
        });

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = searchResultsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String flightCode = (String) searchResultsTableModel.getValueAt(selectedRow, 0);
                    String departureCity = (String) searchResultsTableModel.getValueAt(selectedRow, 1);
                    String destinationCity = (String) searchResultsTableModel.getValueAt(selectedRow, 2);
                    String departureDateAndTime = (String) searchResultsTableModel.getValueAt(selectedRow, 3);
                    String airplaneModel = (String) searchResultsTableModel.getValueAt(selectedRow, 4);
                    int seats = (Integer) searchResultsTableModel.getValueAt(selectedRow, 5);

                    TicketBooking ticketBooking = new TicketBooking(flightCode, departureCity, destinationCity, departureDateAndTime, airplaneModel, seats);
                    ticketBooking.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a flight.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserHomePage userHomePage = new UserHomePage();
                userHomePage.setVisible(true);
                dispose();
            }
        });
    }


    private void searchFlights(String departureCity, String destinationCity) {
        searchResultsTableModel.setRowCount(0);
        boolean flightFound = false; // Flag to check if any flight is found

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(departureCity) && data[2].equals(destinationCity)) {
                    searchResultsTableModel.addRow(new Object[]{data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5])});
                    flightFound = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display message if no flight is found
        if (!flightFound) {
            JOptionPane.showMessageDialog(this, "No Flights Available", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlightSearching frame = new FlightSearching();
            frame.setVisible(true);
        });
    }
}
