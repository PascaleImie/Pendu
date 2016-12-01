import javax.swing.*;
import java.awt.*;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelJeu extends JPanel {

    private BorderLayout borderLayout;

    public JPanelJeu(){
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        JPanelPendu panPendu  = new JPanelPendu();
        JPanelScore panScore = new JPanelScore();

        this.add(panPendu, BorderLayout.WEST);
        this.add(panScore, BorderLayout.EAST);
    }

}