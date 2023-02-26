import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

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

        //necessary to restart application when clicking on the close button:
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

        //define variables to connect to database:
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        //connect to database:
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                //Connected to database successfully..
                //create an sql statement to add a new user:
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (name, email, phone, address, password) " + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                //values added to the sql:
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, password);

            //execute the sql: by inserting rows into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;
                user.address = address;
                user.password = password;
            }

            //close the connection:
            stmt.close();
            conn.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return user; //success = valid user
    }

    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
        //Checking if the registration is completed:
        User user = myForm.user;
        if (user != null) {
            System.out.println("Successful registration of: " + user.name);
        } else {
            System.out.println("Registration canceled.");
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
