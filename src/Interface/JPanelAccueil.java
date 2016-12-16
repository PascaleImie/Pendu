package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.BOLD;
import static java.awt.Label.CENTER;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelAccueil extends JPanel implements ActionListener {

    public final JButton btnJouer;
    JPanel panbtnJouer = new JPanel();
    private Icon imageAccueil;

    public JPanelAccueil(){

        // JLABEL
        //JLabel labelTitre = new JLabel();
        JLabel labelDescription = new JLabel();
        // JBUTTON
        btnJouer = new JButton("JOUER !");

        //labelTitre.setText("JEU DU PENDU");
        labelDescription.setFont(new Font("Arial",BOLD,20));
        labelDescription.setText("<html>Vous devez trouver le mot proposé par le serveur.<br/>" +
                " Pour cela vous avez le droit de vous tromper 11 fois de lettre.</n>" +
                " Passée cette limite, vous aurez perdu.<br><center><h1> BON JEU !</h1></center></html>");
        labelDescription.setHorizontalAlignment(JLabel.CENTER);

        btnJouer.setPreferredSize(new Dimension(200,50));
        //JLABELIMAGE
        JLabel jLabImage = new JLabel();
        jLabImage.setBackground(Color.BLUE);
        imageAccueil = new ImageIcon(getClass().getClassLoader().getResource("pendu.gif"));
        jLabImage.setIcon(imageAccueil);
        jLabImage.setHorizontalAlignment(JLabel.CENTER);

        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);


        //this.add(labelTitre);
        this.add(labelDescription, BorderLayout.NORTH);
        this.add(jLabImage, BorderLayout.CENTER);
        this.add(panbtnJouer, BorderLayout.SOUTH);

        panbtnJouer.add(btnJouer);


        btnJouer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((JPanelMain) this.getParent()).changeInterface();
        ((JPanelMain) this.getParent()).getPanJeu().getPanPendu().getPanNorth().getSwingWorker().execute();
    }

}
