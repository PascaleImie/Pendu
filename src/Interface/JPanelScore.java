package Interface;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelScore extends JPanel {

    public JPanelScore(){
        this.setPreferredSize(new DimensionUIResource(400,600));

        Pendu pendu = new Pendu();
        this.add(pendu, BorderLayout.CENTER);
    }
}
