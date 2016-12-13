package Interface;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

import static java.awt.Font.BOLD;

/**
 * Created by Pierre on 01/12/2016.
 */

public class JPanelScore extends JPanel {

    Pendu pendu;
    BorderLayout borderLayout = new BorderLayout();
    private JLabel score = new JLabel("SCORE");

    public JPanelScore(){

        this.setLayout(borderLayout);

        JLabel labelScore = new JLabel();
        labelScore.setFont(new Font("Arial", BOLD,50));
        labelScore.setText("SCORE");
        this.add(labelScore, BorderLayout.NORTH);
        labelScore.setHorizontalAlignment(JLabel.CENTER);

        this.pendu = new Pendu();
        this.setPreferredSize(new DimensionUIResource(400,600));
        this.add(pendu, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public Pendu getPendu() {
        return pendu;
    }

}
