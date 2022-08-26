import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

/*
 * created UserRegistration class and its constructer contains
 * all the textField, labels and database connection;
 * MySQL workbench is used in this for SQL
 * jar file library is used to connect to SQL database;
 */

public class UserRegistration extends JFrame {
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField firstname;
  private JTextField lastname;
  private JTextField email;
  private JTextField username;
  private JTextField mob;
  private JPasswordField passwordField;
  private JButton btnNewButton;

  /*
   * to lauch the applicaton
   */

  public static void main(String[] args) {
    EventQueue.invokeLater(
      new Runnable() {

        public void run() {
          /*
           * lauch the application
           * if no error occured!
           */
          try {
            UserRegistration Jframe = new UserRegistration();
            Jframe.setVisible(true);
          } /*
           * if any exception occured
           * print that exception.
           */catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    );
  }

  UserRegistration() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(450, 190, 1014, 597);
    setResizable(false);

    /*
     * JPanel, a part of the Java Swing package, is a
     * container that can store a group of components.
     * The main task of JPanel is to organize components,
     * various layouts can be set in JPanel which provide
     * better organization of components, however, it
     * does not have a title bar.
     */
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    /*
     * creating Header of the Page.
     */
    JLabel title = new JLabel("New User Registration");
    title.setFont(new Font("Times New Roman", Font.PLAIN, 30));
    title.setBounds(362, 52, 320, 50);
    contentPane.add(title);

    JLabel lblFirstName = new JLabel("First Name");
    lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblFirstName.setBounds(58, 152, 99, 43);
    contentPane.add(lblFirstName);

    JLabel lblLastname = new JLabel("Last Name");
    lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblLastname.setBounds(58, 243, 110, 29);
    contentPane.add(lblLastname);

    JLabel lblEmailAddress = new JLabel("Email");
    lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblEmailAddress.setBounds(58, 324, 110, 36);
    contentPane.add(lblEmailAddress);

    //now TextFields
    firstname = new JTextField();
    firstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
    firstname.setBounds(214, 151, 228, 50);
    firstname.setColumns(20);
    contentPane.add(firstname);

    /*
     * just changing Y axis for accurate position
     */
    lastname = new JTextField();
    lastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
    lastname.setBounds(214, 235, 228, 50);
    lastname.setColumns(20);
    contentPane.add(lastname);

    /*
     * just changing Y axis for accurate position
     */
    email = new JTextField();
    email.setFont(new Font("Tahoma", Font.PLAIN, 32));
    email.setBounds(214, 320, 228, 50);
    email.setColumns(20);
    contentPane.add(email);

    /*
     * now right side content;
     */
    JLabel lblUsername = new JLabel("User Name");
    lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblUsername.setBounds(542, 159, 99, 29);
    contentPane.add(lblUsername);

    JLabel lblPassword = new JLabel("Password");
    lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblPassword.setBounds(542, 245, 99, 24);
    contentPane.add(lblPassword);

    JLabel lblMobileNumber = new JLabel("Mobile number");
    lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblMobileNumber.setBounds(542, 329, 139, 26);
    contentPane.add(lblMobileNumber);

    /*
     * text Fields
     * Postions are according to the x and y values
     * here x is fixed and y is varied;
     */
    username = new JTextField();
    username.setFont(new Font("Tahoma", Font.PLAIN, 32));
    username.setBounds(707, 151, 228, 50);
    contentPane.add(username);
    username.setColumns(10);

    mob = new JTextField();
    mob.setFont(new Font("Tahoma", Font.PLAIN, 32));
    mob.setBounds(707, 320, 228, 50);
    contentPane.add(mob);
    mob.setColumns(10);

    /*
     * password had its own field
     */
    passwordField = new JPasswordField();
    passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
    passwordField.setBounds(707, 235, 228, 50);
    contentPane.add(passwordField);

    btnNewButton = new JButton("Register");
    /*
     * for action event import java.awt.event.*;
     */
    btnNewButton.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          String firstName = firstname.getText();
          String lastName = lastname.getText();
          String emailID = email.getText();
          String userName = username.getText();
          String passWord = passwordField.getText();
          String mobile = mob.getText();
          int len = mobile.length();

          String name = "";
          name += firstName;
          name += "\n";
          if (len != 10) {
            JOptionPane.showMessageDialog(
              btnNewButton,
              "Enter a valid mobile number"
            );
          }

          /*
           * connecting to the backEND
           * using MySQL workBench to create a database
           * connect to that database;
           */
          try {
            String url = "jdbc:mysql://127.0.0.1:3306/userData";
            String user = "root";
            String pass = "root";
            Connection connection = DriverManager.getConnection(
              url,
              user,
              pass
            );

            String query =
              "INSERT INTO account values('" +
              firstName +
              "','" +
              lastName +
              "','" +
              emailID +
              "','" +
              userName +
              " ','" +
              passWord +
              " ','" +
              mobile +
              " ' )";

            Statement sta = connection.createStatement();
            int val = sta.executeUpdate(query);
            if (val == 0) {
              JOptionPane.showMessageDialog(
                btnNewButton,
                "This is alredy exist"
              );
            } else {
              JOptionPane.showMessageDialog(
                btnNewButton,
                "Welcome" + name + "Your account is sucessfully created"
              );
            }
            connection.close();
          } catch (Exception e1) {
            /*
             * exception name should be different always
             */
            e1.printStackTrace();
          }
        }
      }
    );

    btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
    btnNewButton.setBounds(399, 447, 259, 74);
    contentPane.add(btnNewButton);
  }
}
