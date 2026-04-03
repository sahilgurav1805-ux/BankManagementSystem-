package bank.managemnent.system.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

// OpenCV imports
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Login extends JFrame implements ActionListener {

    JButton Login, Signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    static {
        System.load("C:\\Users\\sahil\\Downloads\\opencv\\build\\java\\x64\\opencv_java4120.dll");
    }

    Login() {

        setTitle("ATM MACHINE");
        setLayout(null);
        setSize(800, 500);
        setLocation(350, 200);

        getContentPane().setBackground(new Color(230, 240, 255));

        // 🏦 Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(i2));
        label.setBounds(350, 20, 80, 80);
        add(label);

        // 🔷 Title
        JLabel text = new JLabel("BANK MANAGEMENT SYSTEM");
        text.setFont(new Font("Arial", Font.BOLD, 26));
        text.setForeground(new Color(0, 51, 102));
        text.setBounds(220, 100, 400, 30);
        add(text);

        // 🔹 Card Number
        JLabel CardNo = new JLabel("Card Number");
        CardNo.setFont(new Font("Tahoma", Font.BOLD, 16));
        CardNo.setBounds(200, 180, 150, 30);
        add(CardNo);

        cardTextField = new JTextField();
        cardTextField.setBounds(350, 180, 200, 35);
        cardTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        cardTextField.setBorder(BorderFactory.createLineBorder(new Color(30,144,255), 2));
        cardTextField.setBackground(new Color(245, 250, 255));
        add(cardTextField);

        // 🔹 PIN
        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("Tahoma", Font.BOLD, 16));
        pin.setBounds(200, 240, 150, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(350, 240, 200, 35);
        pinTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        pinTextField.setBorder(BorderFactory.createLineBorder(new Color(30,144,255), 2));
        pinTextField.setBackground(new Color(245, 250, 255));
        add(pinTextField);

        // 🔘 Buttons
        Login = createButton("SIGN IN", 220, 320);
        clear = createButton("CLEAR", 370, 320);
        Signup = createButton("SIGN UP", 220, 380);

        add(Login);
        add(clear);
        add(Signup);

        setVisible(true);
    }

    // 🔥 Modern Button Method (FIXED ERROR HERE)
    public JButton createButton(String text, int x, int y) {

        JButton button = new JButton(text);
        button.setBounds(x, y, 130, 40);

        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover Effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255));
            }
        });

        button.addActionListener(this);
        return button;
    }

    // 📷 Face Capture
    public void captureUserPhoto(String pinnumber) {

        CascadeClassifier faceDetector = new CascadeClassifier(
                "C:\\Users\\sahil\\Downloads\\opencv\\build\\java\\haarcascade_frontalface_default.xml"
        );

        if (faceDetector.empty()) {
            System.out.println("Error loading Haar Cascade!");
            return;
        }

        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            System.out.println("Camera not found!");
            return;
        }

        Mat frame = new Mat();
        boolean faceCaptured = false;

        System.out.println("Camera started...");

        while (!faceCaptured) {
            if (camera.read(frame)) {

                Mat gray = new Mat();
                Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

                MatOfRect faces = new MatOfRect();
                faceDetector.detectMultiScale(gray, faces);

                for (Rect rect : faces.toArray()) {

                    Mat faceColor = new Mat(frame, rect);
                    Imgcodecs.imwrite("user_" + pinnumber + ".jpg", faceColor);

                    System.out.println("Face captured!");
                    faceCaptured = true;
                    break;
                }
            }
        }

        camera.release();
    }

    public void actionPerformed(ActionEvent ac) {

        if (ac.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");

        } else if (ac.getSource() == Login) {

            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();

            String query = "select * from login where cardnumber = '" + cardnumber + "' and pin = '" + pinnumber + "'";

            try {
                ResultSet ra = conn.s.executeQuery(query);

                if (ra.next()) {

                    captureUserPhoto(pinnumber);

                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ac.getSource() == Signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}