package Interface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pascale on 30/11/2016.
 */
public class Pendu extends JPanel{


    public Pendu() {
        this.setVisible(true);
        this.setBackground(Color.black);
    }

    public void paintComponent(Graphics g){

        //****************Potance**************
        //x, y,width, height
        //vertical
        g.fillRect(90,95,10,300);
        //horizontal
        g.fillRect(90,75,160,20);
        //Noeud coulant
        g.fillRect(180,75,10,70);
        //socle
        g.fillRect(60,390,70,20);

        //******************Tête****************
        g.fillOval(162, 150, 50, 50);
        //******************Corps***************
        g.drawLine(188,200,188,275);
        //******************bras gauche*********
        g.drawLine(148,235,188,215);
        //******************bras droit**********
        g.drawLine(188,215,228,235);
        //******************jambe droite********
        g.drawLine(188,275,228,335);
        //**************jambe gauche************
        g.drawLine(148,335,188,275);

    }





}
