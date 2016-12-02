package Interface;

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
            ((JFenetre) this.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).getJoueur()
                    .sendToServer(new Message("Decrypt", this.getText().charAt(0)));
            ((JFenetre) this.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).getJoueur()
                    .sendToServer(new Message("GestionTours", this.getText().charAt(0)));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
