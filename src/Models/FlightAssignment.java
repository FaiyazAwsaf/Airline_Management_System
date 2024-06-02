package Models;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class FlightAssignment extends JFrame {
    private JTextField flightCode;
    private JTextField seatsField;
    private JComboBox<String> sourceComboBox;
    private JComboBox<String> destinationComboBox;
    private JSpinner takeoffDateSpinner;
    private JTable flightsTable;
    private DefaultTableModel flightsTableModel;
    private JComboBox<String> airplaneModelComboBox;
    private Map<String, Integer> airplaneModels;
    private static final String FILE_PATH = "src\\Data_Storage\\flights_data.csv";

    public FlightAssignment() {
        setTitle("Admin Page");
        setSize(1000, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        ImageIcon AirlineImage = new ImageIcon("src\\Resources\\FlightImage.png");
        JLabel titleImage = new JLabel(AirlineImage, SwingConstants.CENTER);
        titlePanel.add(titleImage);
        titlePanel.setBackground(new Color(0, 0, 0));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.setBackground(new Color(225, 217, 203));

        JLabel flightAssignLabel = new JLabel("Flight Assignment");
        flightAssignLabel.setSize(15, 15);
        flightAssignLabel.setFont(new Font("Open Sans", Font.BOLD, 25));
        flightAssignLabel.setForeground(new Color(4,5,2));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        formPanel.add(flightAssignLabel, gbc);

        JLabel airplaneModelLabel = new JLabel("Airplane Model");
        airplaneModelLabel.setFont(new Font("Hind", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(airplaneModelLabel, gbc);

        airplaneModels = new HashMap<>();
        airplaneModels.put("Boeing 737", 160);
        airplaneModels.put("Boeing 787", 220);
        airplaneModels.put("ATR 72-600", 70);
        airplaneModels.put("Airbus A380", 555);

        airplaneModelComboBox = new JComboBox<>(airplaneModels.keySet().toArray(new String[0]));
        airplaneModelComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedModel = (String) airplaneModelComboBox.getSelectedItem();
                seatsField.setText(airplaneModels.get(selectedModel).toString());
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(airplaneModelComboBox, gbc);

        JLabel seatsLabel = new JLabel("Number Of Seats");
        seatsLabel.setFont(new Font("Hind", Font.BOLD, 15));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(seatsLabel, gbc);

        seatsField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(seatsField, gbc);

        JLabel flightCodeLabel = new JLabel("Flight Code");
        flightCodeLabel.setFont(new Font("Hind", Font.BOLD, 15));

        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(flightCodeLabel, gbc);

        flightCode = new JTextField(7);
        gbc.gridx = 5;
        gbc.gridy = 2;
        formPanel.add(flightCode, gbc);

        JLabel sourceLabel = new JLabel("Departure City");
        sourceLabel.setFont(new Font("Hind", Font.BOLD, 15));

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(sourceLabel, gbc);

        sourceComboBox = new JComboBox<>(new String[]{"Dhaka","Sylhet", "Chittagong", "Barisal", "London", "Chennai", "Kolkata", "Toronto", "Dubai"});
        gbc.gridx = 2;
        gbc.gridy = 2;
        formPanel.add(sourceComboBox, gbc);

        JLabel destinationLabel = new JLabel("Destination");
        destinationLabel.setFont(new Font("Hind", Font.BOLD, 15));
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(destinationLabel, gbc);

        destinationComboBox = new JComboBox<>(new String[]{"Dhaka", "Sylhet", "Chittagong", "Barisal", "London", "Chennai", "Kolkata", "Toronto", "Dubai"});
        gbc.gridx = 3;
        gbc.gridy = 2;
        formPanel.add(destinationComboBox, gbc);

        JLabel takeoffDateLabel = new JLabel("Departure Date");
        takeoffDateLabel.setFont(new Font("Hind", Font.BOLD, 15));
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(takeoffDateLabel, gbc);

        takeoffDateSpinner = new JSpinner(new SpinnerDateModel());
        takeoffDateSpinner.setEditor(new JSpinner.DateEditor(takeoffDateSpinner, "dd MMM yyyy; HH:mm"));
        gbc.gridx = 4;
        gbc.gridy = 2;
        formPanel.add(takeoffDateSpinner, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(225, 217, 203));
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints bgbc = new GridBagConstraints();
        bgbc.insets = new Insets(20, 20, 20, 20);

        JButton saveButton = new JButton("Save");
        bgbc.gridx = 0;
        bgbc.gridy = 0;
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.setBackground(new Color(9, 13, 97));
        saveButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        saveButton.setMargin(new Insets(10, 5, 10, 5));
        saveButton.setForeground(Color.WHITE);
        buttonPanel.add(saveButton, bgbc);

        JButton editButton = new JButton("Edit");
        bgbc.gridx = 1;
        bgbc.gridy = 0;
        editButton.setPreferredSize(new Dimension(100, 30));
        editButton.setBackground(new Color(9, 13, 97));
        editButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        editButton.setMargin(new Insets(10, 5, 10, 5));
        editButton.setForeground(Color.WHITE);
        buttonPanel.add(editButton, bgbc);

        JButton deleteButton = new JButton("Delete");
        bgbc.gridx = 2;
        bgbc.gridy = 0;
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setBackground(new Color(9, 13, 97));
        deleteButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        deleteButton.setMargin(new Insets(10, 5, 10, 5));
        deleteButton.setForeground(Color.WHITE);
        buttonPanel.add(deleteButton, bgbc);

        JButton backButton = new JButton("Back");
        bgbc.gridx = 3;
        bgbc.gridy = 0;
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setBackground(new Color(146, 19, 19));
        backButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        backButton.setMargin(new Insets(10, 5, 10, 5));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton, bgbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFlight();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFlight();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFlight();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminHomePage adminHomePage = new AdminHomePage();
                adminHomePage.setVisible(true);
                dispose();
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        String[] columnNames = {"Flight Code", "Departure City", "Destination City", "Departure Date", "Aircraft", "Number of Seats"};
        flightsTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        flightsTable = new JTable(flightsTableModel);
        JScrollPane tableScrollPane = new JScrollPane(flightsTable);

        add(topPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);

        loadFlightsFromFile();
    }

    private void saveFlight() {
        String flightCode = this.flightCode.getText();
        String source = (String) sourceComboBox.getSelectedItem();
        String destination = (String) destinationComboBox.getSelectedItem();
        String takeoffDate = new SimpleDateFormat("dd MMM yyyy; HH:mm").format(takeoffDateSpinner.getValue());
        String aircraft = (String) airplaneModelComboBox.getSelectedItem();
        String seats = seatsField.getText();

        flightsTableModel.addRow(new Object[]{flightCode, source, destination, takeoffDate, aircraft, seats});
        saveFlightsToFile();

        this.flightCode.setText("");
        sourceComboBox.setSelectedIndex(0);
        destinationComboBox.setSelectedIndex(0);
        airplaneModelComboBox.setSelectedIndex(0);
        seatsField.setText("");
    }

    private void editFlight() {
        int selectedRow = flightsTable.getSelectedRow();
        if (selectedRow != -1) {
            String flightCode = this.flightCode.getText();
            String source = (String) sourceComboBox.getSelectedItem();
            String destination = (String) destinationComboBox.getSelectedItem();
            String takeoffDate = new SimpleDateFormat("dd MMM yyyy; HH:mm").format(takeoffDateSpinner.getValue());
            String aircraft = (String) airplaneModelComboBox.getSelectedItem();
            String seats = seatsField.getText();

            flightsTableModel.setValueAt(flightCode, selectedRow, 0);
            flightsTableModel.setValueAt(source, selectedRow, 1);
            flightsTableModel.setValueAt(destination, selectedRow, 2);
            flightsTableModel.setValueAt(takeoffDate, selectedRow, 3);
            flightsTableModel.setValueAt(aircraft, selectedRow, 4);
            flightsTableModel.setValueAt(seats, selectedRow, 5);

            saveFlightsToFile();

            this.flightCode.setText("");
            sourceComboBox.setSelectedIndex(0);
            destinationComboBox.setSelectedIndex(0);
            airplaneModelComboBox.setSelectedIndex(0);
            seatsField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a flight to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteFlight() {
        int selectedRow = flightsTable.getSelectedRow();
        if (selectedRow >= 0) {
            flightsTableModel.removeRow(selectedRow);
            saveFlightsToFile();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a flight to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFlightsFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    flightsTableModel.addRow(values);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFlightsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < flightsTableModel.getRowCount(); i++) {
                for (int j = 0; j < flightsTableModel.getColumnCount(); j++) {
                    bw.write(flightsTableModel.getValueAt(i, j).toString());
                    if (j < flightsTableModel.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlightAssignment frame = new FlightAssignment();
            frame.setVisible(true);
        });
    }
}
