package bank.managemnent.system.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    JTextField pan, aadhar;
    JButton next;
    JRadioButton syes, sno, eyes, eno;
    JComboBox religion, category, occupation, education, income;
    String formno;

    SignupTwo(String formno) {

        this.formno = formno;

        setTitle("Signup - Page 2");
        setSize(900, 780);
        setLocation(300, 20);
        setLayout(null);

        getContentPane().setBackground(new Color(18, 24, 38));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100, 20, 700, 700);
        panel.setBackground(new Color(30, 41, 59));
        add(panel);

        JLabel heading = new JLabel("Page 2 : Additional Details");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(Color.WHITE);
        heading.setBounds(180, 20, 400, 30);
        panel.add(heading);

        // Religion
        JLabel l1 = createLabel("Religion", 50, 90);
        panel.add(l1);

        String valReligion[] = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = createCombo(valReligion, 250, 90);
        panel.add(religion);

        // Category
        JLabel l2 = createLabel("Category", 50, 140);
        panel.add(l2);

        String valcategory[] = {"General", "OBC", "SC", "ST", "Other"};
        category = createCombo(valcategory, 250, 140);
        panel.add(category);

        // Income
        JLabel l3 = createLabel("Income", 50, 190);
        panel.add(l3);

        String incomecategory[] = {"< 1,50,000", "< 2,50,000", "< 5,00,000", "Upto 10,00,000"};
        income = createCombo(incomecategory, 250, 190);
        panel.add(income);

        // Education
        JLabel l4 = createLabel("Education", 50, 240);
        panel.add(l4);

        String educationalValues[] = {"Non Graduation", "Graduation", "Post Graduation", "Doctorate", "Other"};
        education = createCombo(educationalValues, 250, 240);
        panel.add(education);

        // Occupation
        JLabel l5 = createLabel("Occupation", 50, 290);
        panel.add(l5);

        String occupationValues[] = {"Salaried", "Self Employed", "Business", "Farmer", "Retired", "Other"};
        occupation = createCombo(occupationValues, 250, 290);
        panel.add(occupation);

        // PAN
        JLabel l6 = createLabel("PAN Number", 50, 340);
        panel.add(l6);

        pan = createTextField(250, 340);
        panel.add(pan);

        // Aadhar
        JLabel l7 = createLabel("Aadhar Number", 50, 390);
        panel.add(l7);

        aadhar = createTextField(250, 390);
        panel.add(aadhar);

        // Senior Citizen
        JLabel l8 = createLabel("Senior Citizen", 50, 440);
        panel.add(l8);

        syes = createRadio("Yes", 250, 440);
        sno = createRadio("No", 350, 440);

        ButtonGroup g1 = new ButtonGroup();
        g1.add(syes);
        g1.add(sno);

        panel.add(syes);
        panel.add(sno);

        // Existing Account
        JLabel l9 = createLabel("Existing Account", 50, 490);
        panel.add(l9);

        eyes = createRadio("Yes", 250, 490);
        eno = createRadio("No", 350, 490);

        ButtonGroup g2 = new ButtonGroup();
        g2.add(eyes);
        g2.add(eno);

        panel.add(eyes);
        panel.add(eno);

        // Button
        next = new JButton("NEXT");
        next.setBounds(270, 580, 150, 40);
        next.setBackground(new Color(59, 130, 246));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Segoe UI", Font.BOLD, 14));
        next.setFocusPainted(false);
        next.setBorderPainted(false);
        next.setCursor(new Cursor(Cursor.HAND_CURSOR));
        next.addActionListener(this);
        panel.add(next);

        setVisible(true);
    }

    public JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 180, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        return label;
    }

    public JTextField createTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 350, 35);
        tf.setBackground(new Color(51, 65, 85));
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return tf;
    }

    public JComboBox createCombo(String arr[], int x, int y) {
        JComboBox box = new JComboBox(arr);
        box.setBounds(x, y, 350, 35);
        box.setBackground(Color.WHITE);
        box.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return box;
    }

    public JRadioButton createRadio(String text, int x, int y) {
        JRadioButton rb = new JRadioButton(text);
        rb.setBounds(x, y, 80, 30);
        rb.setForeground(Color.WHITE);
        rb.setBackground(new Color(30, 41, 59));
        rb.setFocusPainted(false);
        return rb;
    }

    public void actionPerformed(ActionEvent ae) {

        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();

        String seniorcitizen = syes.isSelected() ? "Yes" : "No";
        String existingaccount = eyes.isSelected() ? "Yes" : "No";

        String span = pan.getText();
        String saadhar = aadhar.getText();

        try {
            Conn c = new Conn();

            String query = "insert into signupTwo values('" + formno + "','" + sreligion + "','" +
                    scategory + "','" + sincome + "','" + seducation + "','" + soccupation + "','" +
                    span + "','" + saadhar + "','" + seniorcitizen + "','" + existingaccount + "')";

            c.s.executeUpdate(query);

            setVisible(false);
            new SignupThree(formno).setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupTwo("1001");
    }
}