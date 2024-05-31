import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame {

    public LandingPage() {
        setTitle("Airline Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);
        setLayout(new BorderLayout());


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel titlePanel = new JPanel(new GridBagLayout());

        GridBagConstraints tgbc = new GridBagConstraints();

        GridBagConstraints bgbc = new GridBagConstraints();
        bgbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon AirlineImage = new ImageIcon("src\\AirlineLogo.png");
        JLabel titleImage =  new JLabel(AirlineImage, SwingConstants.CENTER);

        JButton adminButton = new JButton("Admin Login");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLoginFrame adminLoginFrame = new AdminLoginFrame();
                adminLoginFrame.setVisible(true);
                dispose();
            }
        });

        // User Login Button
        JButton userButton = new JButton("User Login");
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLoginFrame userLoginFrame = new UserLoginFrame();
                userLoginFrame.setVisible(true);
                dispose();
            }
        });


        tgbc.gridx = 0;
        tgbc.gridy = 0;
        tgbc.anchor = GridBagConstraints.CENTER;

        titlePanel.add(titleImage);
        titlePanel.setBackground(new Color(23, 10, 57));

        add(titlePanel, BorderLayout.NORTH);

        bgbc.gridx = 0;
        bgbc.gridy = 4;
        bgbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(adminButton, bgbc);

        bgbc.gridx = 1;
        bgbc.gridy = 4;
        bgbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(userButton, bgbc);

        add(buttonPanel,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LandingPage frame = new LandingPage();
            frame.setVisible(true);
        });
    }
}