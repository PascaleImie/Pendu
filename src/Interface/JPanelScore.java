package Interface;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelScore extends JPanel {

    Pendu pendu = new Pendu();

    public JPanelScore(){

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new DimensionUIResource(400,600));

        JLabel labelCoupRestant = new JLabel();
        this.add(labelCoupRestant);


        this.add(pendu);
    }

    public Pendu getPendu() {
        return pendu;
    }
}
