import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.jar.JarEntry;

public class TicketBooking extends JFrame{
    private JTextField flightIdField;
    private JTextField departureField;
    private JTextField arrivalField;
    private JComboBox<String> classType;
    private JSpinner flightDateSpinner;
    private JComboBox<String> flightCodes;

    public TicketBooking() {
        setTitle("User Page");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        ImageIcon AirlineImage = new ImageIcon("src\\FlightImage.png");
        JLabel titleImage = new JLabel(AirlineImage, SwingConstants.CENTER);
        titlePanel.add(titleImage);
        titlePanel.setBackground(new Color(0, 0, 0));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 10, 10);
        formPanel.setBackground(new Color(225, 217, 203));

        JLabel ticketBookingLabel = new JLabel("Book Your Ticket");
        ticketBookingLabel.setSize(15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        formPanel.add(ticketBookingLabel, gbc);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints igbc = new GridBagConstraints();
        igbc.insets = new Insets(5, 5, 10, 5);

        JLabel departureCityLabel = new JLabel("Departing City: ");
        igbc.gridx = 0;
        igbc.gridy = 0;
        infoPanel.add(departureCityLabel, igbc);

        departureField = new JTextField(12);
        igbc.gridx = 1;
        igbc.gridy = 0;
        infoPanel.add(departureField, igbc);

        JLabel arrivalCityLabel = new JLabel("Arriving City: ");
        igbc.gridx = 0;
        igbc.gridy = 1;
        infoPanel.add(arrivalCityLabel, igbc);

        arrivalField = new JTextField(12);
        igbc.gridx = 1;
        igbc.gridy = 1;
        infoPanel.add(arrivalField, igbc);

        JLabel dateOfFlightLabel = new JLabel("Date of Flight: ");
        igbc.gridx = 0;
        igbc.gridy = 2;
        infoPanel.add(dateOfFlightLabel, igbc);

        flightDateSpinner = new JSpinner(new SpinnerDateModel());
        flightDateSpinner.setEditor(new JSpinner.DateEditor(flightDateSpinner, "EEE MMM dd HH:mm yyyy"));
        igbc.gridx = 1;
        igbc.gridy = 2;
        infoPanel.add(flightDateSpinner, igbc);

        JLabel flightIdLabel = new JLabel("Flight ID: ");
        igbc.gridx = 4;
        igbc.gridy = 0;
        infoPanel.add(flightIdLabel, igbc);

        String[] codes = new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader("flights_data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (Objects.equals(values[1], departureField.getText()) && Objects.equals(values[2], arrivalField.getText()) && Objects.equals(values[3], flightDateSpinner.getValue().toString())) {
                    codes = new String[]{values[0]};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        flightCodes = new JComboBox<>(codes);
        igbc.gridx = 5;
        igbc.gridy = 0;
        infoPanel.add(flightCodes, igbc);


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(infoPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
    }

//    public void setFlightCodes(){
//        try(BufferedReader br = new BufferedReader(new FileReader("flights_data.csv"))){
//            String line;
//            String[] codes;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                if(Objects.equals(values[1], departureField.getText()) && Objects.equals(values[2], arrivalField.getText()) && Objects.equals(values[3], flightDateSpinner.toString())){
//                    codes = new String[]{values[0]};
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }    
//    }

    public static void main(String[] args) {
        TicketBooking frame = new TicketBooking();
        frame.setVisible(true);
    }
}
