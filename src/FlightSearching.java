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
    private static final String FILE_PATH = "flights_data.csv";

    public FlightSearching() {
        setTitle("Flight Booking");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());


        JPanel titlePanel = new JPanel();
        ImageIcon AirlineImage = new ImageIcon("src\\FlightImage.png");
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

        sourceComboBox = new JComboBox<>(new String[]{"Dhaka", "Chittagong", "Barisal", "London", "Chennai", "Kolkata", "Toronto", "Dubai"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        searchPanel.add(sourceComboBox, gbc);

        JLabel destinationLabel = new JLabel("Destination");
        destinationLabel.setFont(new Font("Hind", Font.BOLD, 15));
        gbc.gridx = 2;
        gbc.gridy = 0;
        searchPanel.add(destinationLabel, gbc);

        destinationComboBox = new JComboBox<>(new String[]{"Dhaka", "Chittagong", "Barisal", "London", "Chennai", "Kolkata", "Toronto", "Dubai"});
        gbc.gridx = 3;
        gbc.gridy = 0;
        searchPanel.add(destinationComboBox, gbc);

        JButton searchButton = new JButton("Search");
        gbc.gridx = 4;
        gbc.gridy = 0;
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setBackground(new Color(9, 13, 97));
        searchButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        searchButton.setMargin(new Insets(10, 5, 10, 5));
        searchButton.setForeground(Color.WHITE);
        searchPanel.add(searchButton, gbc);

        add(searchPanel, BorderLayout.CENTER);

        JPanel searchResultTablePanel = new JPanel(new BorderLayout());
        searchResultsTableModel = new DefaultTableModel(new Object[]{"Flight Code", "Departure City", "Destination", "Departure Date and Time", "Airplane Model", "Seats"}, 0);
        searchResultsTable = new JTable(searchResultsTableModel);
        JScrollPane scrollPane = new JScrollPane(searchResultsTable);
        searchResultTablePanel.add(scrollPane, BorderLayout.CENTER);

        JButton selectButton = new JButton("Select Flight");
        selectButton.setPreferredSize(new Dimension(100, 30));
        selectButton.setBackground(new Color(9, 13, 97));
        selectButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        selectButton.setMargin(new Insets(10, 5, 10, 5));
        selectButton.setForeground(Color.WHITE);

        searchResultTablePanel.add(selectButton, BorderLayout.SOUTH);

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
                String departureCity = (String) sourceComboBox.getSelectedItem();
                String destinationCity = (String) destinationComboBox.getSelectedItem();
                // Open a new page for booking ticket.
                // Make the departing and arriving city constant
            }
        });
    }

    private void searchFlights(String departureCity, String destinationCity) {
        searchResultsTableModel.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(departureCity) && data[2].equals(destinationCity)) {
                    searchResultsTableModel.addRow(new Object[]{data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5])});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlightSearching().setVisible(true);
            }
        });
    }
}
