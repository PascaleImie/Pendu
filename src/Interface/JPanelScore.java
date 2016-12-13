package Interface;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created by Pierre on 01/12/2016.
 */

public class JPanelScore extends JPanel {

    Pendu pendu;

    public JPanelScore(){
        this.pendu = new Pendu();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new DimensionUIResource(400,600));
        this.add(pendu, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public Pendu getPendu() {
        return pendu;
    }

}
