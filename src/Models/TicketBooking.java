package Models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.*;

public class TicketBooking extends JFrame {
    private String flightCode, departureCity, destinationCity, departureDateAndTime, airplaneModel, seatNumber;

    public TicketBooking(String flightCode, String departureCity, String destinationCity, String departureDateAndTime, String airplaneModel, int seats) {
        this.flightCode = flightCode;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureDateAndTime = departureDateAndTime;
        this.airplaneModel = airplaneModel;
        this.seatNumber = generateRandomNumberWithLetter(seats);

        setTitle("User Page");
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

        JPanel formPanel = new JPanel(new GridLayout(14, 4, 10, 10));

        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField passportIDField = new JTextField();
        JComboBox<String> genderField = new JComboBox<>(new String[]{"Male", "Female"});
        JComboBox<String> nationalityField = new JComboBox<>(new String[]{"Bangladeshi", "Indian", "American", "Emirati", "Canadian"});

        JTextField flightCodeField = new JTextField();
        flightCodeField.setText(flightCode);
        flightCodeField.setEditable(false);

        JTextField aircraftModelField = new JTextField();
        aircraftModelField.setText(airplaneModel);
        aircraftModelField.setEditable(false);

        JTextField seatsField = new JTextField();
        seatsField.setText(seatNumber);
        seatsField.setEditable(false);

        JTextField departureField = new JTextField();
        departureField.setText(departureCity);
        departureField.setEditable(false);

        JTextField arrivalField = new JTextField();
        arrivalField.setText(destinationCity);
        arrivalField.setEditable(false);

        JTextField dateField = new JTextField();
        dateField.setText(departureDateAndTime);
        dateField.setEditable(false);

        JButton bookButton = new JButton("Book Ticket");
        bookButton.setPreferredSize(new Dimension(150, 30));
        bookButton.setBackground(new Color(9, 13, 97));
        bookButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        bookButton.setForeground(Color.WHITE);

        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(150, 30));
        resetButton.setBackground(new Color(9, 13, 97));
        resetButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        resetButton.setForeground(Color.WHITE);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 30));
        backButton.setBackground(new Color(149, 16, 16));
        backButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        backButton.setForeground(Color.WHITE);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Gender:"));
        formPanel.add(genderField);
        formPanel.add(new JLabel("Nationality:"));
        formPanel.add(nationalityField);
        formPanel.add(new JLabel("Passport Number:"));
        formPanel.add(passportIDField);
        formPanel.add(new JLabel("Contact Number:"));
        formPanel.add(contactField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Flight Code:"));
        formPanel.add(flightCodeField);
        formPanel.add(new JLabel("Departing City:"));
        formPanel.add(departureField);
        formPanel.add(new JLabel("Arriving City:"));
        formPanel.add(arrivalField);
        formPanel.add(new JLabel("Departure Date:"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Aircraft Model:"));
        formPanel.add(aircraftModelField);
        formPanel.add(new JLabel("Seat Number:"));
        formPanel.add(seatsField);

        formPanel.add(bookButton);
        formPanel.add(resetButton);
        formPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightSearching flightSearching = new FlightSearching();
                flightSearching.setVisible(true);
                dispose();
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isEmpty() || contactField.getText().isEmpty() || emailField.getText().isEmpty()
                        || departureField.getText().isEmpty() || arrivalField.getText().isEmpty() || dateField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    displayTicket(nameField.getText(), contactField.getText(), emailField.getText(),
                            departureField.getText(), arrivalField.getText(), dateField.getText(),
                            flightCodeField.getText(), aircraftModelField.getText(), seatsField.getText());
                    storePassengerInfo(nameField.getText(), contactField.getText(), emailField.getText(),passportIDField.getText(), (String) genderField.getSelectedItem(),(String) nationalityField.getSelectedItem(),
                            flightCodeField.getText(),departureField.getText(), arrivalField.getText(), dateField.getText(),
                            aircraftModelField.getText(), seatsField.getText());
                }

                nameField.setText("");
                contactField.setText("");
                emailField.setText("");
                passportIDField.setText("");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                contactField.setText("");
                emailField.setText("");
                passportIDField.setText("");
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Book Your Ticket");
        headerLabel.setSize(20, 20);
        headerPanel.add(headerLabel);

        add(topPanel, BorderLayout.NORTH);
        add(headerPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
    }

    private void displayTicket(String name, String contact, String email, String source, String destination, String date,
                               String flightCode, String airplaneModel, String seatNumber) {
        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        ticketPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ticketPanel.add(new JLabel("***** Astra Airline Ticket *****"));
        ticketPanel.add(new JLabel("Passenger Name: " + name));
        ticketPanel.add(new JLabel("Contact Number: " + contact));
        ticketPanel.add(new JLabel("Email: " + email));
        ticketPanel.add(new JLabel("Departure City: " + source));
        ticketPanel.add(new JLabel("Arrival City: " + destination));
        ticketPanel.add(new JLabel("Date and Time: " + date));
        ticketPanel.add(new JLabel("Flight Code: " + flightCode));
        ticketPanel.add(new JLabel("Aircraft Model: " + airplaneModel));
        ticketPanel.add(new JLabel("Seat Number: " + seatNumber));
        ticketPanel.add(new JLabel("**************************"));

        JOptionPane.showMessageDialog(this, ticketPanel, "Ticket Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String generateRandomNumberWithLetter(int x) {
        Random random = new Random();
        return (random.nextInt(x) + 1) + "" + (char) ('A' + random.nextInt(3));
    }

    private void storePassengerInfo(String name, String contact, String email, String passportID, String gender, String nationality, String flightCode, String departureCity, String destinationCity, String departureDateAndTime, String airplaneModel, String seatNumber) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Data_Storage\\Passengers.txt", true))) {
            writer.write(name + "," + contact + "," + email + "," + passportID + "," + gender + "," + nationality + "," + flightCode + "," + departureCity + "," + destinationCity + "," + departureDateAndTime + "," + airplaneModel + "," + seatNumber);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // public static void main(String[] args) {
    //     TicketBooking frame = new TicketBooking();
    //     frame.setVisible(true);
    // }
}
