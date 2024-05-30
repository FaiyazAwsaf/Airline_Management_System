import javax.swing.*;

public class AdminHomePage extends JFrame {
    private JLabel title;

    public AdminHomePage(){
        setTitle("Admin Page");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        title = new JLabel("SUP!");
    }

}
