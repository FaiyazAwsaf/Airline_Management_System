package Models;

import javax.print.event.PrintJobAttributeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FlightCancellation extends JFrame{
    private JTextField flightCodeField;

    public FlightCancellation(){
        setTitle("User Page");
        setSize(800, 600);
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

        JPanel searchPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        searchPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        searchPanel.setBackground(new Color(225, 217, 203));

        JLabel flightCodeLabel = new JLabel("Enter Flight Code: ");
        flightCodeLabel.setSize(15, 15);
        flightCodeLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
        searchPanel.add(flightCodeLabel);

        flightCodeField = new JTextField(15);
        searchPanel.add(flightCodeField);

        flightCodeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(BufferedReader br = new BufferedReader(new FileReader("src\\Data_Storage\\flights_data.csv"))){
                    String line;
                    while ((line = br.readLine()) != null){
                        String[] data = line.split(",");
                        if (data[0].equals(flightCodeField.getText())){
                            JOptionPane.showMessageDialog(null, "sdsd", "Your Ticket", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (IOException exception){
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

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.setBackground(new Color(9, 13, 97));
        cancelButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        cancelButton.setForeground(Color.WHITE);
        buttonPanel.add(cancelButton, gbcButton);

        gbcButton.gridy = 1;

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setBackground(new Color(9, 13, 97));
        backButton.setFont(new Font("Rowdies", Font.BOLD, 15));
        backButton.setForeground(Color.WHITE);
        buttonPanel.add(backButton, gbcButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        FlightCancellation frame = new FlightCancellation();
        frame.setVisible(true);
    }
}
