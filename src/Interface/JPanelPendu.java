package Interface;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelPendu extends JPanel{

    private final JPanelPenduNorth panNorth;
    private final JPanelPenduCenter panSouth;

    public JPanelPendu(){

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new DimensionUIResource(800,600));
        this.setBorder(BorderFactory.createMatteBorder(0, 0,0, 2, Color.BLACK));

        panNorth = new JPanelPenduNorth();
        panNorth.setSize(new Dimension(1200,200));
        panSouth = new JPanelPenduCenter();
        panSouth.setSize(new Dimension(1200,400));

        this.add(panNorth, BorderLayout.NORTH);
        this.add(panSouth, BorderLayout.CENTER);
    }

    public JPanelPenduNorth getPanNorth() {
        return panNorth;
    }

    public JPanelPenduCenter getPanSouth() {
        return panSouth;
    }
}
