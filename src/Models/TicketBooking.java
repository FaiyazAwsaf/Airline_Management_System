package Models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketBooking extends JFrame{
    public TicketBooking() {
        setTitle("User Page");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        ImageIcon AirlineImage = new ImageIcon("Rescources/FlightImage.png");
        JLabel titleImage = new JLabel(AirlineImage, SwingConstants.CENTER);
        titlePanel.add(titleImage);
        titlePanel.setBackground(new Color(0, 0, 0));

        JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField emailField = new JTextField();

        JTextField departureField = new JTextField();
        JTextField arrivalField = new JTextField();
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "EEE MMM dd HH:mm yyyy"));

        JButton searchButton = new JButton("Search Flights");
        JButton bookButton = new JButton("Book Ticket");
        JButton resetButton = new JButton("Reset");

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Contact:"));
        formPanel.add(contactField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Departing City:"));
        formPanel.add(departureField);
        formPanel.add(new JLabel("Arriving City:"));
        formPanel.add(arrivalField);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        formPanel.add(dateSpinner);

        formPanel.add(searchButton);
        formPanel.add(bookButton);
        formPanel.add(resetButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Searching for flights...");
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to book ticket based on user input
                if (nameField.getText().isEmpty() || contactField.getText().isEmpty() || emailField.getText().isEmpty()
                        || departureField.getText().isEmpty() || arrivalField.getText().isEmpty() || dateSpinner.toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Display ticket in a dialog box
                    displayTicket(nameField.getText(), contactField.getText(), emailField.getText(),
                            departureField.getText(), arrivalField.getText(), dateSpinner.toString());
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                contactField.setText("");
                emailField.setText("");
                departureField.setText("");
                arrivalField.setText("");
            }
        });


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Book Your Ticket");
        headerLabel.setSize(20,20);
        headerPanel.add(headerLabel);

        add(topPanel, BorderLayout.NORTH);
        add(headerPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
    }

    private void displayTicket(String name, String contact, String email, String source, String destination, String date) {
        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        ticketPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ticketPanel.add(new JLabel("***** Airline Ticket *****"));
        ticketPanel.add(new JLabel("Name: " + name));
        ticketPanel.add(new JLabel("Contact: " + contact));
        ticketPanel.add(new JLabel("Email: " + email));
        ticketPanel.add(new JLabel("From: " + source));
        ticketPanel.add(new JLabel("To: " + destination));
        ticketPanel.add(new JLabel("Date: " + date));
        ticketPanel.add(new JLabel("**************************"));

        JOptionPane.showMessageDialog(this, ticketPanel, "Ticket Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        TicketBooking frame = new TicketBooking();
        frame.setVisible(true);
    }
}
