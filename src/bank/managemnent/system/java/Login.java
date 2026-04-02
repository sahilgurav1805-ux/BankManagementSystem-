package bank.managemnent.system.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    // Load DLL ONCE
    static {
        System.load("C:\\Users\\sahil\\Downloads\\opencv\\build\\java\\x64\\opencv_java4120.dll");
    }

    Login() {

        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);

        // Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(i2));
        label.setBounds(70, 10, 100, 100);
        add(label);

        // Title of the Page
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        // Card No to enter
        JLabel CardNo = new JLabel("Card No: ");
        CardNo.setFont(new Font("Raleway", Font.BOLD, 28));
        CardNo.setBounds(120, 150, 150, 30);
        add(CardNo);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 250, 30);
        add(cardTextField);

        // PIN to enter
        JLabel pin = new JLabel("PIN: ");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        add(pinTextField);

        // Buttons for next pages
        Login = new JButton("SIGN IN");
        Login.setBounds(300, 300, 100, 30);
        Login.addActionListener(this);
        add(Login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.addActionListener(this);
        add(clear);

        Signup = new JButton("SIGN UP");
        Signup.setBounds(300, 350, 230, 30);
        Signup.addActionListener(this);
        add(Signup);

        getContentPane().setBackground(Color.white);

        setSize(800, 500);
        setLocation(350, 200);
        setVisible(true);
    }

    //  Face detection capture code using open Cv
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

                    // Crop face with original frame
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

                    // ✅ Capture face BEFORE opening next screen
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