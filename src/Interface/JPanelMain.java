package Interface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelMain extends JPanel {

    private final JPanelAccueil panAccueil;
    private final JPanelJeu panJeu;
    private CardLayout cardLayout;

    public JPanelMain(){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        // CREATION DU PANEL ACCUEIL ET DU PANEL JEU
        panAccueil = new JPanelAccueil();
        panJeu = new JPanelJeu();

        // AJOUT DES PANEL
        this.add(panAccueil);
        this.add(panJeu);
    }

    public JPanelAccueil getPanAccueil() {
        return panAccueil;
    }

    public JPanelJeu getPanJeu() {
        return panJeu;
    }

    public void changeInterface() {
        this.cardLayout.next(this);
    }
}
