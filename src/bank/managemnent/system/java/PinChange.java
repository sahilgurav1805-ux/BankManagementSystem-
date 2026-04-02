package bank.managemnent.system.java;

import javax.swing.*;
import java.awt.*;

public class PinChange extends JFrame {

    PinChange(String pinchange) {
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg")) ;
        Image i2 = i1.getImage().getScaledInstance(900 ,900 ,Image.SCALE_DEFAULT) ;
        ImageIcon i3 = new ImageIcon(i2) ;
        JLabel image = new JLabel(i3) ;
        image.setBounds(0,0,900,900);
        add(image) ;

        setSize(900 , 900);


    }


    public static void main(String[] args) {
        new PinChange("").setVisible(true)  ;
    }



}
