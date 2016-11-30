import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

import static java.awt.Font.*;

/**
 * Created by Pierre on 30/11/2016.
 */
public class JFenetre extends JFrame {

    private JPanel panAccueil = new JPanel();
    private JLabel labelTitre = new JLabel();

    public JFenetre(){
        this.setTitle ("Jeu du pendu");
        this.setSize (new DimensionUIResource(1200,600));
        this.setLocationRelativeTo (null) ;
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panAccueil);

        labelTitre.setText("JEU DU PENDU");
        labelTitre.setFont(new Font("Arial", BOLD,30));

        panAccueil.add(labelTitre);
        this.setVisible(true);
    }

}