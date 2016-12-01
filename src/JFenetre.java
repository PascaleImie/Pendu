import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.awt.Font.*;

/**
 * Created by Pierre on 30/11/2016.
 */

public class JFenetre extends JFrame{

    // CARDLAYOUT
    private CardLayout cardLayout;

    public JFenetre(){

        // PARAMÉTRAGE DE LA JFRAME
        this.setTitle ("Jeu du pendu");
        this.setSize (new DimensionUIResource(1200,600));
        this.setLocationRelativeTo (null) ;
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        // CREATION DU CARD LAYOUT

        this.setContentPane(new JPanelMain());
        this.setVisible(true);
    }

}