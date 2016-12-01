import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.BOLD;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelAccueil extends JPanel implements ActionListener {

    public JPanelAccueil(){

        // JLABEL
        JLabel labelTitre = new JLabel();
        JLabel labelDescription = new JLabel();
        // JBUTTON
        JButton btnJouer = new JButton("JOUER !");

        labelTitre.setText("JEU DU PENDU");
        labelTitre.setFont(new Font("Arial", BOLD,30));
        labelDescription.setText("Vous devez trouver le mot proposé par le serveur. Pour cela vous avez le droit de vous tromper 11 fois de lettre. Passé cette limite, vous aurez perdu. BON JEU !");
        btnJouer.setPreferredSize(new Dimension(200,50));

        this.add(labelTitre);
        this.add(labelDescription);
        this.add(btnJouer);
        btnJouer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((JPanelMain) this.getParent()).changeInterface();
    }

}
