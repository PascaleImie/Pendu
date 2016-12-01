package Interface;

import Client.Joueur;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created by Pierre on 30/11/2016.
 */

public class JFenetre extends JFrame{

    private final JPanelMain panelMain;
    private CardLayout cardLayout;
    private Joueur joueur;

    public JFenetre(){

        // PARAMÉTRAGE DE LA JFRAME
        this.setTitle ("Jeu du pendu");
        this.setSize (new DimensionUIResource(1200,600));
        this.setLocationRelativeTo (null) ;
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        // CREATION DU CARD LAYOUT
        panelMain = new JPanelMain();
        this.setContentPane(panelMain);
        this.setVisible(true);
    }

    public void setEngine(Joueur joueur) {
        this.joueur = joueur;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public JPanelMain getPanelMain() {
        return panelMain;
    }
}