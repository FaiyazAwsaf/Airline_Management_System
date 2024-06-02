package Models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserLoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton, returnButton;

    public UserLoginFrame() {
        setTitle("User Login");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,2));

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints bgbc = new GridBagConstraints();
        bgbc.insets = new Insets(10, 10, 10, 10);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints tgbc =  new GridBagConstraints();

        bgbc.gridx = 0;
        bgbc.gridy = 0;
        bgbc.gridwidth = 2;
        bgbc.anchor = GridBagConstraints.CENTER;
        JLabel heading = new JLabel("Welcome to Astra Airlines");
        heading.setFont(new Font("Freeman", Font.BOLD, 25));
        heading.setForeground(new Color(33, 15, 74));
        buttonPanel.add(heading, bgbc);

        bgbc.gridx = 0;
        bgbc.gridy = 1;
        bgbc.gridwidth = 1;
        bgbc.anchor = GridBagConstraints.EAST;
        buttonPanel.add(new JLabel("Username:"), bgbc);

        bgbc.gridx = 1;
        bgbc.gridy = 1;
        bgbc.anchor = GridBagConstraints.WEST;
        userField = new JTextField(20);
        buttonPanel.add(userField, bgbc);

        bgbc.gridx = 0;
        bgbc.gridy = 2;
        bgbc.anchor = GridBagConstraints.EAST;
        buttonPanel.add(new JLabel("Password:"), bgbc);

        bgbc.gridx = 1;
        bgbc.gridy = 2;
        bgbc.anchor = GridBagConstraints.WEST;
        passField = new JPasswordField(20);
        buttonPanel.add(passField, bgbc);

        bgbc.gridx = 0;
        bgbc.gridy = 3;
        bgbc.gridwidth = 2;
        bgbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateCredentials(userField.getText(), new String(passField.getPassword()))) {
                    UserHomePage userHomePage = new UserHomePage();
                    userHomePage.setVisible(true);
                    dispose();
                }
            }
        });
        buttonPanel.add(loginButton, bgbc);

        bgbc.gridx = 0;
        bgbc.gridy = 4;
        bgbc.gridwidth = 2;
        bgbc.anchor = GridBagConstraints.CENTER;
        returnButton = new JButton("Return to Landing Page");
        returnButton.setPreferredSize(new Dimension(200, 15));
        returnButton.setBackground(new Color(146, 19, 19));
        returnButton.setForeground(Color.WHITE);


        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LandingPage landingPage = new LandingPage();
                landingPage.setVisible(true);
                dispose();
            }
        });
        buttonPanel.add(returnButton, bgbc);


        add(buttonPanel);

        ImageIcon AirlineImage = new ImageIcon("src\\Resources\\AdminPageImage.png");
        JLabel titleImage =  new JLabel(AirlineImage, SwingConstants.CENTER);

        tgbc.gridx = 0;
        tgbc.gridy = 0;
        tgbc.anchor = GridBagConstraints.CENTER;

        titlePanel.add(titleImage);
        titlePanel.setBackground(new Color(23, 10, 57));
        add(titlePanel);
    }
    private boolean validateCredentials(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\Data_Storage\\user_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    String fileUsername = credentials[0];
                    String filePassword = credentials[1];
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserLoginFrame frame = new UserLoginFrame();
            frame.setVisible(true);
        });
    }
}


