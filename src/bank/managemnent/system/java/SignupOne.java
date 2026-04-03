package bank.managemnent.system.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class SignupOne extends JFrame implements ActionListener {

    long random;
    JTextField nameTextField, fnameTextField, emailTextField,
            addressTextField, cityTextField, stateTextField, pinTextField;
    JButton next;
    JRadioButton male, female, other, married, unmarried;
    JDateChooser dateChooser;

    SignupOne() {

        // ✅ FIX 1: System Look & Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Signup - Page 1");
        setSize(900, 750);
        setLocation(300, 20);
        setLayout(null);

        getContentPane().setBackground(new Color(18, 24, 38));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100, 20, 700, 680);
        panel.setBackground(new Color(30, 41, 59));
        add(panel);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("APPLICATION NO. " + random);
        formno.setFont(new Font("Arial", Font.BOLD, 22));
        formno.setForeground(Color.WHITE);
        formno.setBounds(180, 20, 400, 30);
        panel.add(formno);

        JLabel heading = new JLabel("Personal Details");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setForeground(Color.LIGHT_GRAY);
        heading.setBounds(260, 60, 200, 30);
        panel.add(heading);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);

        // Name
        JLabel name = new JLabel("Name");
        styleLabel(name, 50, 120);
        panel.add(name);

        nameTextField = createTextField(200, 120);
        panel.add(nameTextField);

        // Father's Name
        JLabel fname = new JLabel("Father's Name");
        styleLabel(fname, 50, 170);
        panel.add(fname);

        fnameTextField = createTextField(200, 170);
        panel.add(fnameTextField);

        // DOB
        JLabel dob = new JLabel("Date of Birth");
        styleLabel(dob, 50, 220);
        panel.add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(200, 220, 400, 40);
        panel.add(dateChooser);

        // Gender
        JLabel gender = new JLabel("Gender");
        styleLabel(gender, 50, 270);
        panel.add(gender);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        styleRadio(male, 200, 270);
        styleRadio(female, 340, 270);

        // ✅ FIX 2: FORCE ICON
        male.setIcon(UIManager.getIcon("RadioButton.icon"));
        female.setIcon(UIManager.getIcon("RadioButton.icon"));

        ButtonGroup g = new ButtonGroup();
        g.add(male);
        g.add(female);

        panel.add(male);
        panel.add(female);

        // Email
        JLabel email = new JLabel("Email");
        styleLabel(email, 50, 320);
        panel.add(email);

        emailTextField = createTextField(200, 320);
        panel.add(emailTextField);

        // Marital
        JLabel marital = new JLabel("Marital Status");
        styleLabel(marital, 50, 370);
        panel.add(marital);

        married = new JRadioButton("Married");
        unmarried = new JRadioButton("Unmarried");
        other = new JRadioButton("Other");

        styleRadio(married, 200, 370);
        styleRadio(unmarried, 340, 370);
        styleRadio(other, 500, 370);

        // ✅ FIX 2: FORCE ICON
        married.setIcon(UIManager.getIcon("RadioButton.icon"));
        unmarried.setIcon(UIManager.getIcon("RadioButton.icon"));
        other.setIcon(UIManager.getIcon("RadioButton.icon"));

        ButtonGroup m = new ButtonGroup();
        m.add(married);
        m.add(unmarried);
        m.add(other);

        panel.add(married);
        panel.add(unmarried);
        panel.add(other);

        // Address
        JLabel address = new JLabel("Address");
        styleLabel(address, 50, 420);
        panel.add(address);

        addressTextField = createTextField(200, 420);
        panel.add(addressTextField);

        // City
        JLabel city = new JLabel("City");
        styleLabel(city, 50, 470);
        panel.add(city);

        cityTextField = createTextField(200, 470);
        panel.add(cityTextField);

        // State
        JLabel state = new JLabel("State");
        styleLabel(state, 50, 520);
        panel.add(state);

        stateTextField = createTextField(200, 520);
        panel.add(stateTextField);

        // Pin
        JLabel pin = new JLabel("Pin Code");
        styleLabel(pin, 50, 570);
        panel.add(pin);

        pinTextField = createTextField(200, 570);
        panel.add(pinTextField);

        next = createButton("NEXT", 250, 620);
        panel.add(next);

        setVisible(true);
    }

    // 🔥 Label Style
    public void styleLabel(JLabel label, int x, int y) {
        label.setBounds(x, y, 150, 25);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    public JTextField createTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 400, 40);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tf.setBackground(new Color(51, 65, 85));
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        return tf;
    }

    public JButton createButton(String text, int x, int y) {

        JButton btn = new JButton(text);
        btn.setBounds(x, y, 150, 40);

        btn.setBackground(new Color(59, 130, 246));
        btn.setForeground(Color.WHITE);

        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));


        btn.setOpaque(true);
        btn.setBorderPainted(false);

        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(37, 99, 235));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(59, 130, 246));
            }
        });

        btn.addActionListener(this);
        return btn;
    }

    public void styleRadio(JRadioButton rb, int x, int y) {
        rb.setBounds(x, y, 130, 30);
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rb.setForeground(Color.WHITE);
        rb.setBackground(new Color(30, 41, 59));

        rb.setOpaque(false); // ✅ FIX
        rb.setFocusPainted(false);
        rb.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showMessageDialog(null, "Next Page Logic Same 👍");
    }

    public static void main(String[] args) {
        new SignupOne();
    }
}