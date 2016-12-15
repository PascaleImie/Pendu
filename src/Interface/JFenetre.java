package Interface;

import Client.Joueur;
import Utilitaires.Bdd;
import Utilitaires.Message;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Pierre on 30/11/2016.
 */

public class JFenetre extends JFrame implements ActionListener, WindowListener{

    private JPanelMain panelMain;
    private Joueur joueur;

    private JMenuBar jMenuBar = new JMenuBar();
    private JMenu jMenuReglages = new JMenu("Menu");
    private JMenu jMenuNiveau = new JMenu("Niveau de difficulté");
    private JMenuItem jMenuItemBestScores = new JMenuItem ("Meilleurs scores");
    private JRadioButtonMenuItem jMenuItem1 = new JRadioButtonMenuItem("Easy");
    private JRadioButtonMenuItem jMenuItem2 = new JRadioButtonMenuItem("Medium");
    private JRadioButtonMenuItem jMenuItem3 = new JRadioButtonMenuItem("Hard");
    private JRadioButtonMenuItem jMenuItem4 = new JRadioButtonMenuItem("Impossible");
    private ButtonGroup bg = new ButtonGroup();
    private JOptionPane jOptionPane1 = new JOptionPane();
    private int[] bestsScores = new int[5];


    public JFenetre() throws IOException {

        jMenuReglages.add(jMenuNiveau);
        jMenuReglages.add(jMenuItemBestScores);
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
        jMenuItemBestScores.addActionListener(this);

        // PARAMÉTRAGE DE LA JFRAME
        this.setTitle ("Jeu du pendu");
        this.setSize (new DimensionUIResource(1200,600));
        this.setLocationRelativeTo (null) ;
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        // CREATION DU CARD LAYOUT
        panelMain = new JPanelMain();

        this.addWindowListener(this);

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
        if(e.getActionCommand().equals("Easy")){
            try {
                this.getJoueur().sendToServer(new Message("NiveauDeJeu", "easy"));
                recommencerUnePartie();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e.getActionCommand().equals("Medium")){
            try {
                this.getJoueur().sendToServer(new Message("NiveauDeJeu", "medium"));
                recommencerUnePartie();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e.getActionCommand().equals("Hard")){
            try {
                this.getJoueur().sendToServer(new Message("NiveauDeJeu", "hard"));
                recommencerUnePartie();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e.getActionCommand().equals("Impossible")){
            try {
                this.getJoueur().sendToServer(new Message("NiveauDeJeu", "impossible"));
                recommencerUnePartie();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e.getActionCommand().equals("Meilleurs scores")){
            try {
                this.getJoueur().sendToServer(new Message("Meilleurs scores", null));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

//            for(int i = 0; i<5; i++){
//                System.out.println(bestsScores[i]);
//            }

            jOptionPane1.showMessageDialog(null,
                    "" + bestsScores[0] + "\n" + bestsScores[1] + "\n" + bestsScores[2] + "\n" + bestsScores[3] + "\n" + bestsScores[4],
                    "Meilleurs scores", JOptionPane.INFORMATION_MESSAGE);

        }



    }

    private void recommencerUnePartie(){
        this.getPanelMain().getPanJeu().getPanPendu().recommencerUnePartie();
    }

    public void setBestsScores(int[] bestsScores) {
        this.bestsScores = bestsScores;
    }
}