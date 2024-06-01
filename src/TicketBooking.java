import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

public class TicketBooking extends JFrame{
    private JTextField flightID;
    private JTextField departure;
    private JTextField arrival;
    private JComboBox<String> classType;

    public TicketBooking() {setTitle("User Page");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        ImageIcon AirlineImage = new ImageIcon("src\\FlightImage.png");
        JLabel titleImage =  new JLabel(AirlineImage, SwingConstants.CENTER);
        titlePanel.add(titleImage);
        titlePanel.setBackground(new Color(0, 0, 0));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,10,10,10);
        formPanel.setBackground(new Color(225, 217, 203));

        JLabel ticketBookingLabel = new JLabel("Book Your Ticket");
        ticketBookingLabel.setSize(15,15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        formPanel.add(ticketBookingLabel, gbc);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints igbc = new GridBagConstraints();
        igbc.insets = new Insets(5,10,10,10);

        JLabel departureCityLabel = new JLabel("Departing City: ");
        igbc.gridx = 0;
        igbc.gridy = 0;
        infoPanel.add(departureCityLabel, igbc);

        JLabel arrivalCityLabel = new JLabel("Arriving City: ");
        igbc.gridx = 0;
        igbc.gridy = 1;
        infoPanel.add(arrivalCityLabel, igbc);

        JLabel dateOfFlightLabel = new JLabel("Date of Flight: ");
        igbc.gridx = 0;
        igbc.gridy = 2;
        infoPanel.add(dateOfFlightLabel, igbc);

        JLabel flightIdLabel = new JLabel();


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(infoPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        TicketBooking frame = new TicketBooking();
        frame.setVisible(true);
    }

    //Great job Saddy!!

}
