package Interface;

import Client.Joueur;
import Utilitaires.Message;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.*;
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
    private JRadioButtonMenuItem jMenuItem4 = new JRadioButtonMenuItem("impossible");
    private ButtonGroup bg = new ButtonGroup();

    public JFenetre() {

        jMenuReglages.add(jMenuNiveau);
        jMenuNiveau.add(jMenuItem1);
        jMenuNiveau.add(jMenuItem2);
        jMenuNiveau.add(jMenuItem3);
        jMenuNiveau.add(jMenuItem4);


        bg.add(jMenuItem1);
        bg.add(jMenuItem2);
        bg.add(jMenuItem3);
        bg.add(jMenuItem4);

        jMenuItem1.setSelected(true);

        this.jMenuBar.add(jMenuReglages);
        this.setJMenuBar(jMenuBar);

        jMenuItem1.addActionListener(this);
        jMenuItem2.addActionListener(this);
        jMenuItem3.addActionListener(this);
        jMenuItem4.addActionListener(this);

        // PARAMÉTRAGE DE LA JFRAME
        this.setTitle ("Jeu du pendu");
        this.setSize (new DimensionUIResource(1200,600));
        this.setLocationRelativeTo (null) ;
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        // CREATION DU CARD LAYOUT
        panelMain = new JPanelMain();


        this.addWindowListener(this);
        this.getPanelMain().getPanJeu().getPanPendu().getPanNorth().setTime(500);


        this.getPanelMain().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (getPanelMain().getPanAccueil().isVisible()) {
                        getPanelMain().getPanAccueil().btnJouer.doClick();
                    }else{
                        getPanelMain().getPanJeu().getPanPendu().getRecommencer().doClick();
                    }
                } else {
                    for (int i =0; i<26; i++){
                        if(((Bouton) getPanelMain().getPanJeu().getPanPendu().getPanCenter().getpanEcran().getComponent(i)).getText().equals((String.valueOf(e.getKeyChar()).toUpperCase()))){
                            ((Bouton)getPanelMain().getPanJeu().getPanPendu().getPanCenter().getpanEcran().getComponent(i)).doClick();
                        }
                    }
                }
            }
        });

        this.getPanelMain().setFocusable(true);
        this.getPanelMain().requestFocus();
        this.getPanelMain().requestFocusInWindow();

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
        }else if(e.getActionCommand().equals("impossible")){
            this.getPanelMain().getPanJeu().getPanPendu().getPanNorth().setTime(33);
        }
    }
}