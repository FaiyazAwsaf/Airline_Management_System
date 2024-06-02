package Models;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CancellationRequestFrame extends JFrame {
    private JTable requestsTable;
    private DefaultTableModel tableModel;
    public CancellationRequestFrame(){
        setTitle("Admin Page");
        setSize(900, 650);
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

        JPanel listPanel = new JPanel(new BorderLayout());

        JLabel headerLabel = new JLabel("Cancellation Requests", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        listPanel.add(headerLabel, BorderLayout.NORTH);

        String[] columnNames = {"Name", "Phone", "Email", "Passport ID",
                "Flight Code", "Date", "Departure", "Arrival",
                "Aircraft", "Seat"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        requestsTable = new JTable(tableModel);
        requestsTable.setFont(new Font("Open Sans", Font.BOLD, 12));
        requestsTable.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(requestsTable);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        loadRequestsList();

        add(listPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(225, 217, 203));
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints bgbc = new GridBagConstraints();
        bgbc.insets = new Insets(20, 20, 20, 20);

        JButton approveButton = new JButton("Approve");
        bgbc.gridx = 0;
        bgbc.gridy = 0;
        approveButton.setPreferredSize(new Dimension(100, 30));
        approveButton.setBackground(new Color(9, 13, 97));
        approveButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        approveButton.setMargin(new Insets(10, 5, 10, 5));
        approveButton.setForeground(Color.WHITE);
        buttonPanel.add(approveButton, bgbc);

        JButton rejectButton = new JButton("Reject");
        bgbc.gridx = 1;
        bgbc.gridy = 0;
        rejectButton.setPreferredSize(new Dimension(100, 30));
        rejectButton.setBackground(new Color(9, 13, 97));
        rejectButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        rejectButton.setMargin(new Insets(10, 5, 10, 5));
        rejectButton.setForeground(Color.WHITE);
        buttonPanel.add(rejectButton, bgbc);

        JButton backButton = new JButton("Back");
        bgbc.gridx = 2;
        bgbc.gridy = 0;
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setBackground(new Color(9, 13, 97));
        backButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        backButton.setMargin(new Insets(10, 5, 10, 5));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(rejectButton, bgbc);

        add(buttonPanel, BorderLayout.SOUTH);

        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveFlightCancellation();
            }
        });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rejectFlightCancellation();
            }
        });
    }

    private void approveFlightCancellation() {
        int selectedRow = requestsTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            saveRequestsToFile();
            saveFlightsToFile();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a flight.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFlightsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\Data_Storage\\Passengers.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    bw.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rejectFlightCancellation() {
        int selectedRow = requestsTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            saveRequestsToFile();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a flight.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadRequestsList() {
        CancellationRequests requests = new CancellationRequests();
        List<Passenger> passengers = requests.getPassengers();
        for (Passenger passenger : passengers) {
            tableModel.addRow(new Object[]{
                    passenger.getName(), passenger.getContact(), passenger.getEmail(), passenger.getPassportID(),
                    passenger.getFlightCode(), passenger.getDepartureDateAndTime(), passenger.getDepartureCity(),
                    passenger.getDestinationCity(), passenger.getAirplaneModel(), passenger.getSeatNumber()
            });
        }
    }

    private void saveRequestsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\Data_Storage\\cancellation_requests.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    bw.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
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
        CancellationRequestFrame frame = new CancellationRequestFrame();
        frame.setVisible(true);
    }
}
