package Interface;

import Client.Joueur;
import Utilitaires.Message;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.setEnabled(false);
        try {
            getJoueur().sendToServer(new Message("GestionTours", this.getText().charAt(0)));
            Thread.sleep(100);
            int coupRestant = ((JPanelJeu) this.getParent().getParent().getParent()).getPanScore().getPendu().getCoupRestant();

            System.out.println(coupRestant);
            if(coupRestant == 0 || coupRestant == -1){
                for (int i=0; i<26; i++){
                    this.getParent().getComponent(i).setEnabled(false);
                }
            }
            System.out.println("TEST" + coupRestant);

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
    private Joueur getJoueur() throws IOException {
        return ((JFenetre) this.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).getJoueur();
    }
}
