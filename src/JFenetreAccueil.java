import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.*;

/**
 * Created by Pierre on 30/11/2016.
 */
public class JFenetreAccueil extends JFrame {

    private JPanel panAccueil = new JPanel();
    private JPanel panJeu = new JPanel();
    private JLabel labelTitre = new JLabel();
    private JLabel labelDescription = new JLabel();
    private JButton btnJouer = new JButton("JOUER !");
    private JLabel copyright = new JLabel();
    private final CardLayout cardLayout;

    public JFenetreAccueil(){

        cardLayout = new CardLayout();
        this.setTitle ("Jeu du pendu");
        this.setSize (new DimensionUIResource(1200,600));
        this.setLocationRelativeTo (null) ;
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panAccueil);
//        this.getContentPane().add(panJeu);

        labelTitre.setText("JEU DU PENDU");
        labelTitre.setFont(new Font("Arial", BOLD,30));
        labelDescription.setText("Vous devez trouver le mot proposé par le serveur. Pour cela vous avez le droit de vous tromper 11 fois de lettre. Passé cette limite, vous aurez perdu. BON JEU !");
        btnJouer.setPreferredSize(new Dimension(200,50));
        copyright.setText("Programme développé par Pascale et Pierre @2016");
        copyright.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        this.add(copyright, BorderLayout.SOUTH);
        panAccueil.add(labelTitre);
        panAccueil.add(labelDescription);
        panAccueil.add(btnJouer);

        btnJouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.setVisible(true);
    }

}