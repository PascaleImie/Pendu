package Interface;

import Client.Joueur;
import Utilitaires.Message;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 * Created by Pierre on 30/11/2016.
 */

public class JFenetre extends JFrame implements WindowListener{

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

        this.addWindowListener(this);
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            getJoueur().sendToServer(new Message("Deconnexion", null));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}