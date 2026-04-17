package bank.managemnent.system.java;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {
        setTitle("Mini Statement");
        setLayout(null);

        // Heading
        JLabel bank = new JLabel("INDIAN BANK");
        bank.setFont(new Font("Arial", Font.BOLD, 18));
        bank.setBounds(130, 20, 200, 30);
        add(bank);

        // Card Number Label
        JLabel card = new JLabel();
        card.setFont(new Font("Arial", Font.PLAIN, 14));
        card.setBounds(20, 70, 350, 25);
        add(card);

        // Mini Statement Area
        JTextArea mini = new JTextArea();
        mini.setEditable(false);
        mini.setFont(new Font("Monospaced", Font.PLAIN, 13));
        mini.setBackground(Color.WHITE);
        mini.setLineWrap(true);
        mini.setWrapStyleWord(true);

        JScrollPane sp = new JScrollPane(mini);
        sp.setBounds(20, 110, 350, 380);
        add(sp);

        // Balance Label
        JLabel balance = new JLabel();
        balance.setFont(new Font("Arial", Font.BOLD, 14));
        balance.setBounds(20, 510, 300, 25);
        add(balance);

        try {
            Conn conn = new Conn();

            // Fetch Card Number
            ResultSet rs = conn.s.executeQuery(
                    "select * from login where pin = '" + pinnumber + "'");

            while (rs.next()) {
                String cardno = rs.getString("cardnumber");
                card.setText("Card Number: " +
                        cardno.substring(0, 4) + "XXXXXXXX" + cardno.substring(12));
            }

            // Fetch Transactions
            rs = conn.s.executeQuery(
                    "select * from bank where pin = '" + pinnumber + "'");

            int bal = 0;

            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                mini.append(date + "   " + type + "   ₹" + amount + "\n\n");

                if (type.equals("Deposit")) {
                    bal += Integer.parseInt(amount);
                } else {
                    bal -= Integer.parseInt(amount);
                }
            }

            balance.setText("Available Balance: ₹ " + bal);

        } catch (Exception e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(Color.WHITE);
        setSize(400, 600);
        setLocation(450, 120);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("1234"); // test pin
    }
}