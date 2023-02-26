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
//        setVisible(true); this is moved to underneath the dispose method
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
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //method will be executed when click the cancel button, we want to close the app
                dispose();
            }
        });

        setVisible(true);
    }

    private void registerUser() {
        //implementing the method by registering the fields:
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        //if fields are empty, there is an error message:
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //if password & confirmPassword are equal:
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords do not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Creating a new user with a new method below:
        user = addUserToDatabase(name, email, phone, address, password);

        //If the user is an existing user, we close the registration form.
        //If the user is null, then we display an error message.
        if (user != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public User user; //global variable and is public. is initialized from line 74.

    //implementing addUserToDatabase from above.
    //this method will return the user object, if method does succeed in creating a new method
    private User addUserToDatabase(String name, String email, String phone, String address, String password) {
        User user = null; //fail = return null
        return user; //success = valid user
    }

    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
