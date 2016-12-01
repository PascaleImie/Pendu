package Interface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.awt.Font.BOLD;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelPenduNorth extends JPanel {

    JPanelPenduNorth(){
        JLabel labelMot = new JLabel();
        labelMot.setFont(new Font("Arial", BOLD,50));
        this.add(labelMot);
    }

}
