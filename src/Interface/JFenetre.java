package Interface;

import Client.Joueur;
import Utilitaires.Message;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 * Created by Pierre on 30/11/2016.
 */

public class JFenetre extends JFrame implements ActionListener, WindowListener{

    private final JPanelMain panelMain;
    private CardLayout cardLayout;
    private Joueur joueur;

    private JMenuBar jMenuBar = new JMenuBar();
    private JMenu jMenuReglages = new JMenu("Réglages");
    private JMenu jMenuNiveau = new JMenu("Niveau de difficulté");
    private JRadioButtonMenuItem jMenuItem1 = new JRadioButtonMenuItem("easy");
    private JRadioButtonMenuItem jMenuItem2 = new JRadioButtonMenuItem("medium");
    private JRadioButtonMenuItem jMenuItem3 = new JRadioButtonMenuItem("hard");
    private ButtonGroup bg = new ButtonGroup();

    public JFenetre(){

        jMenuReglages.add(jMenuNiveau);
        jMenuNiveau.add(jMenuItem1);
        jMenuNiveau.add(jMenuItem2);
        jMenuNiveau.add(jMenuItem3);



        bg.add(jMenuItem1);
        bg.add(jMenuItem2);
        bg.add(jMenuItem3);

        jMenuItem1.setSelected(true);

        this.jMenuBar.add(jMenuReglages);
        this.setJMenuBar(jMenuBar);

        jMenuItem1.addActionListener(this);
        jMenuItem2.addActionListener(this);
        jMenuItem3.addActionListener(this);

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
        this.getPanelMain().getPanJeu().getPanPendu().getPanNorth().setTime(500);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("easy")){
            this.getPanelMain().getPanJeu().getPanPendu().getPanNorth().setTime(500);
        }else if(e.getActionCommand().equals("medium")){
            this.getPanelMain().getPanJeu().getPanPendu().getPanNorth().setTime(300);
        }else if(e.getActionCommand().equals("hard")){
            this.getPanelMain().getPanJeu().getPanPendu().getPanNorth().setTime(100);
        }
    }
}