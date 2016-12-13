package Interface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelJeu extends JPanel {

    private final JPanelPendu panPendu;
    private final JPanelScore panScore;
    private BorderLayout borderLayout;

    public JPanelJeu(){
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        panPendu  = new JPanelPendu();
        panScore = new JPanelScore();

        this.add(panPendu, BorderLayout.CENTER);
        this.add(panScore, BorderLayout.EAST);



        int coupRestant = this.getPanScore().getPendu().getCoupRestant();

        //this.getPanPendu().getPanCenter().getResult().setText("Nombre de coup(s) restant(s) "+String.valueOf(etatPartie));
        /*if(etatPartie==1){
            this.getPanPendu().getPanCenter().getResult().setText("YOU WIN !!!");
        } else if(etatPartie==-1) {
            this.getPanPendu().getPanCenter().getResult().setText("YOU LOOSE !!!");
        }*/
    }


    public JPanelPendu getPanPendu() {
        return panPendu;
    }

    public JPanelScore getPanScore() {
        return panScore;
    }

}