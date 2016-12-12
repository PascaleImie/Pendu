package Interface;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelPenduCenter extends JPanel{
    private JLabel result = new JLabel("You win");
    private JPanel panEcran = new JPanel();
    private BorderLayout borderLayout;



    JPanelPenduCenter(){
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        this.add(panEcran);
        for(char c = 'A'; c <= 'Z'; c++){
            Bouton buton = new Bouton(c);
            panEcran.add(buton, BorderLayout.NORTH);
        }

        this.add(result, BorderLayout.SOUTH);
        result.setFont(new Font("Arial", BOLD,50));
        this.result.setHorizontalAlignment(JLabel.CENTER);

        //Recupérer le nb coup restant
        //int coupRestant = ((JPanelJeu) this.getParent().getParent().getParent()).getPanScore().getPendu().getCoupRestant();
        //result.setText(String.valueOf(coupRestant));

    }



}
