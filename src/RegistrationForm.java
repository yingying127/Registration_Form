import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;

    public RegistrationForm(JFrame parent) {
        super(parent);
        setTitle("Create an account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //click on button, create listener, action listener, ok
                //^this method will be executed when we click on register button.
                //^ when clicking, we want to register a new user
                registerUser();
                //^ can be created by on the left, crete method, and click the bottom option, then it is created underneath
            }
        });
    }

    private void registerUser() {
    }

    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
