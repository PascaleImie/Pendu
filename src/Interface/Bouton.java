package Interface;

import Client.Joueur;
import Utilitaires.Message;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.*;
import java.io.IOException;

/**
 * Created by Pascale on 01/12/2016.
 */
public class Bouton extends JButton implements ActionListener {

    public Bouton(char c) {
        super(String.valueOf(c));
        this.setSize(new DimensionUIResource(50, 100));
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onButtonPress();
    }

    private void onButtonPress(){
        this.setEnabled(false);
        try {
            getJoueur().sendToServer(new Message("GestionTours", this.getText().charAt(0)));
            Thread.sleep(150);
            int etatPartie = ((JPanelJeu) this.getParent().getParent().getParent().getParent()).getPanScore().getPendu().getEtatPartie();
            if(etatPartie != 0){
                ((JPanelPendu)this.getParent().getParent().getParent()).getPanNorth().getSwingWorker().cancel(true);
                for (int i=0; i<26; i++) {
                    this.getParent().getComponent(i).setEnabled(false);
                }
            }
            ((JPanelMain) this.getParent().getParent().getParent().getParent().getParent()).setFocusable(true);
            ((JPanelMain) this.getParent().getParent().getParent().getParent().getParent()).requestFocus();
            ((JPanelMain) this.getParent().getParent().getParent().getParent().getParent()).requestFocusInWindow();

        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
    }
    private Joueur getJoueur() throws IOException {
        return ((JFenetre) this.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent()).getJoueur();
    }
}
