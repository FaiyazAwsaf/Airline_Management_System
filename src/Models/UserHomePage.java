package Models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserHomePage extends JFrame {
    public UserHomePage() {
        setTitle("User Home Page");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Title panel stuff
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        GridBagConstraints tgbc = new GridBagConstraints();

        ImageIcon titleImage = new ImageIcon("src\\Resources\\TitleImage.png");
        JLabel titleImageLabel = new JLabel(titleImage, SwingConstants.CENTER);

        tgbc.gridx = 0;
        tgbc.gridy = 0;
        tgbc.anchor = GridBagConstraints.CENTER;
        titlePanel.add(titleImageLabel, tgbc);

        add(titlePanel, BorderLayout.NORTH);


        //Button panel stuff
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints bgbc = new GridBagConstraints();
        bgbc.insets = new Insets(20,0,20,0);
        buttonPanel.setBackground(new Color(211, 231, 239));

        JButton bookTicketButton = new JButton("Book Your Ticket");
        bgbc.gridx = 0;
        bgbc.gridy = 0;
        bgbc.fill = GridBagConstraints.HORIZONTAL;
        bgbc.anchor = GridBagConstraints.CENTER;
        bookTicketButton.setPreferredSize(new Dimension(200,40));
        bookTicketButton.setBackground(new Color(9, 13, 97));
        bookTicketButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        bookTicketButton.setMargin(new Insets(10, 5, 10, 5));
        bookTicketButton.setForeground(Color.WHITE);
        buttonPanel.add(bookTicketButton, bgbc);

        JButton flightCancellationButton = new JButton("Cancel Your Ticket");
        bgbc.gridx = 0;
        bgbc.gridy = 1;
        flightCancellationButton.setPreferredSize(new Dimension(200,40));
        flightCancellationButton.setBackground(new Color(9, 13, 97));
        flightCancellationButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        flightCancellationButton.setMargin(new Insets(10, 5, 10, 5));
        flightCancellationButton.setForeground(Color.WHITE);
        buttonPanel.add(flightCancellationButton, bgbc);


        JButton backButton =  new JButton("Log Out");
        bgbc.gridx = 0;
        bgbc.gridy = 2;
        backButton.setPreferredSize(new Dimension(200,40));
        backButton.setBackground(new Color(146, 19, 19));
        backButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        backButton.setMargin(new Insets(10, 5, 10, 5));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton, bgbc);

        bookTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightSearching flightSearching = new FlightSearching();
                flightSearching.setVisible(true);
                dispose();
            }
        });

        flightCancellationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightCancellation page = new FlightCancellation();
                page.setVisible(true);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLoginFrame userLoginFrame = new UserLoginFrame();
                userLoginFrame.setVisible(true);
                dispose();
            }
        });

        add(buttonPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserHomePage frame = new UserHomePage();
            frame.setVisible(true);
        });
    }
}
