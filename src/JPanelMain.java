import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.BOLD;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelMain extends JPanel {

    private CardLayout cardLayout;
    public JPanelMain(){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        // CREATION DU PANEL ACCUEIL ET DU PANEL JEU
        JPanelAccueil panAccueil = new JPanelAccueil();
        JPanelJeu panJeu = new JPanelJeu();

        // AJOUT DES PANEL
        this.add(panAccueil);
        this.add(panJeu);
    }

    public void changeInterface() {
        this.cardLayout.next(this);
    }
}
