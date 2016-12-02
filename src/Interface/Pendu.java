package Interface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pascale on 30/11/2016.
 */
public class Pendu extends JPanel{

    int coupRestant = 10;
    public Pendu() {
        this.setSize(100,100);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //****************Potance**************
        //x, y,width, height
        if( coupRestant == 9){
            //socle
            g.fillRect(60,390,70,20);
        }else if (coupRestant == 8){
            //socle
            g.fillRect(60,390,70,20);
            //vertical
            g.fillRect(90,95,10,300);
        }else if (coupRestant == 7){
            //socle
            g.fillRect(60,390,70,20);
            //vertical
            g.fillRect(90,95,10,300);
            //horizontal
            g.fillRect(90,75,160,20);
        }else if (coupRestant==6){
            //socle
            g.fillRect(60,390,70,20);
            //vertical
            g.fillRect(90,95,10,300);
            //horizontal
            g.fillRect(90,75,160,20);
            //Noeud coulant
            g.fillRect(180,75,10,70);
        }else if (coupRestant==5){
            //socle
            g.fillRect(60,390,70,20);
            //vertical
            g.fillRect(90,95,10,300);
            //horizontal
            g.fillRect(90,75,160,20);
            //Noeud coulant
            g.fillRect(180,75,10,70);
            //******************Tête****************
            g.fillOval(162, 150, 50, 50);
        }else if (coupRestant==4){
            //socle
            g.fillRect(60,390,70,20);
            //vertical
            g.fillRect(90,95,10,300);
            //horizontal
            g.fillRect(90,75,160,20);
            //Noeud coulant
            g.fillRect(180,75,10,70);
            //******************Tête****************
            g.fillOval(162, 150, 50, 50);
            //******************Corps***************
            g.drawLine(188,200,188,275);
        }else if (coupRestant==3){
            //socle
            g.fillRect(60,390,70,20);
            //vertical
            g.fillRect(90,95,10,300);
            //horizontal
            g.fillRect(90,75,160,20);
            //Noeud coulant
            g.fillRect(180,75,10,70);
            //******************Tête****************
            g.fillOval(162, 150, 50, 50);
            //******************Corps***************
            g.drawLine(188,200,188,275);
            //******************bras gauche*********
            g.drawLine(148,235,188,215);
        }else if (coupRestant==2){
            //socle
            g.fillRect(60,390,70,20);
            //vertical
            g.fillRect(90,95,10,300);
            //horizontal
            g.fillRect(90,75,160,20);
            //Noeud coulant
            g.fillRect(180,75,10,70);
            //******************Tête****************
            g.fillOval(162, 150, 50, 50);
            //******************Corps***************
            g.drawLine(188,200,188,275);
            //******************bras gauche*********
            g.drawLine(148,235,188,215);
            //******************bras droit**********
            g.drawLine(188,215,228,235);
        }else if (coupRestant==1){
            //socle
            g.fillRect(60,390,70,20);
            //vertical
            g.fillRect(90,95,10,300);
            //horizontal
            g.fillRect(90,75,160,20);
            //Noeud coulant
            g.fillRect(180,75,10,70);
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
        }else if (coupRestant<=0){
            //socle
            g.fillRect(60,390,70,20);
            //vertical
            g.fillRect(90,95,10,300);
            //horizontal
            g.fillRect(90,75,160,20);
            //Noeud coulant
            g.fillRect(180,75,10,70);
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

    public void setCoupRestant(int x){
        this.coupRestant = x;
        this.repaint();
    }
}
