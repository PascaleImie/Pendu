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
    }

    public JPanelPendu getPanPendu() {
        return panPendu;
    }

    public JPanelScore getPanScore() {
        return panScore;
    }
}