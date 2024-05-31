import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame {

    public LandingPage() {
        setTitle("Airline Management System");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new BorderLayout());


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel titlePanel = new JPanel(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon AirlineImage = new ImageIcon("F:\\Programming\\Projects\\Airline_Management_System\\src\\AirlineLogo.png");
        JLabel titleImage =  new JLabel(AirlineImage, SwingConstants.CENTER);

        // Admin Login Button 
        JButton adminButton = new JButton("Admin Login");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLoginFrame adminLoginFrame = new AdminLoginFrame();
                adminLoginFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });

        // User Login Button
        JButton userButton = new JButton("User Login");
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLoginFrame userLoginFrame = new UserLoginFrame();
                userLoginFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });

        // Add buttons to the panel

        titlePanel.setBounds(0,0,1920,400);
        titlePanel.add(titleImage, BorderLayout.NORTH);
        titlePanel.setBackground(new Color(23, 10, 57));

        add(titlePanel);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(adminButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(userButton, gbc);

        add(buttonPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LandingPage frame = new LandingPage();
            frame.setVisible(true);
        });
    }
}

