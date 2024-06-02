package Models;

import javax.print.event.PrintJobAttributeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FlightCancellation extends JFrame{
    private JTextField flightCodeField;
    private static String FILE_PATH = "src\\Data_Storage\\Passengers.txt";

    public FlightCancellation(){
        setTitle("User Page");
        setSize(500, 400);
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

        JPanel searchPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        searchPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        searchPanel.setBackground(new Color(225, 217, 203));

        JLabel passportIDLabel = new JLabel("Enter Passport Number: ");
        passportIDLabel.setSize(7, 7);
        passportIDLabel.setFont(new Font("Open Sans", Font.BOLD, 15));
        searchPanel.add(passportIDLabel);

        flightCodeField = new JTextField(15);
        searchPanel.add(flightCodeField);

        flightCodeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedReader br = new BufferedReader(new FileReader("src\\Data_Storage\\Passengers.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(",");
                        if (data[3].equals(flightCodeField.getText())) {
                            Passenger passenger = new Passenger(data[0], data[1], data[2], data[3], data[6], data[10], data[7], data[8], data[11]);
                            JOptionPane.showMessageDialog(null, passenger.toString(), "Ticket Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        add(searchPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(5, 5, 5, 5);
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;

        JButton cancelButton = new JButton("Cancel Flight");
        cancelButton.setPreferredSize(new Dimension(150, 30));
        cancelButton.setBackground(new Color(9, 13, 97));
        cancelButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        cancelButton.setForeground(Color.WHITE);
        buttonPanel.add(cancelButton, gbcButton);

        gbcButton.gridy = 1;

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setBackground(new Color(146, 19, 19));
        backButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton, gbcButton);

        add(buttonPanel, BorderLayout.SOUTH);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(",");
                        if (data[3].equals(flightCodeField.getText())) {
                            Passenger passenger = new Passenger(data[0], data[1], data[2], data[3], data[6], data[9], data[7], data[8], data[11]);
                            storePassengerInfo(data[0], data[1], data[2], data[3], data[6], data[9], data[7], data[8], data[10], data[11]);
                            JOptionPane.showMessageDialog(null,"Your cancellation request is under processing","Request Accepted", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserHomePage page = new UserHomePage();
                page.setVisible(true);
                dispose();
            }
        });
    }

    private void storePassengerInfo(String name, String contact, String email, String passportID, String flightCode, String departureDateAndTime, String departureCity, String destinationCity, String airplaneModel, String seatNumber) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Data_Storage\\cancellation_requests.txt", true))) {
            writer.write(name + "," + contact + "," + email + "," + passportID + "," + flightCode + "," + departureCity + "," + destinationCity + "," + departureDateAndTime + "," + airplaneModel + "," + seatNumber);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FlightCancellation frame = new FlightCancellation();
        frame.setVisible(true);
    }
}
