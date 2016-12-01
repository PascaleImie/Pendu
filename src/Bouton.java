import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pascale on 01/12/2016.
 */
public class Bouton extends JButton implements ActionListener {
    JButton[] tab_bouton;
    JPanel panelBouton = new JPanel();


    public Bouton(char c)

    {
        super(String.valueOf(c));
        this.setSize(new DimensionUIResource(500, 700));
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
