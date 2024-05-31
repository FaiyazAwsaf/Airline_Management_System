import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHomePage extends JFrame {
    public AdminHomePage() {
        setTitle("Admin Home Page");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Title panel stuff
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        GridBagConstraints tgbc = new GridBagConstraints();

        ImageIcon titleImage = new ImageIcon("src\\TitleImage.png");
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

        JButton flightAssignmentButton = new JButton("Flight Assignment");
        bgbc.gridx = 0;
        bgbc.gridy = 0;
        bgbc.fill = GridBagConstraints.HORIZONTAL;
        bgbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(flightAssignmentButton, bgbc);

        JButton flightCancellationButton = new JButton("Flight Cancellation");
        bgbc.gridx = 0;
        bgbc.gridy = 1;
        buttonPanel.add(flightCancellationButton, bgbc);

        JButton passengerListButton =  new JButton("Passenger List");
        bgbc.gridx = 0;
        bgbc.gridy = 2;
        buttonPanel.add(passengerListButton, bgbc);

        JButton backButton =  new JButton("Back Home");
        bgbc.gridx = 0;
        bgbc.gridy = 3;
        buttonPanel.add(backButton, bgbc);

        flightAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        flightCancellationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        passengerListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLoginFrame adminHomePage = new AdminLoginFrame();
                adminHomePage.setVisible(true);
                dispose();
            }
        });

        add(buttonPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminHomePage frame = new AdminHomePage();
            frame.setVisible(true);
        });
    }
}

