package Interface;

import sun.java2d.loops.FillRect;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pascale on 30/11/2016.
 */

class Forme {
    private String type;
    private int[]tab;

    public Forme(String type, int[] tab) {
        this.type = type;
        this.tab = tab;
    }

    public String getType() {
        return type;
    }

    public int[] getTab() {
        return tab;
    }
}

public class Pendu extends JPanel{

    int coupRestant = 10;
    int etatPartie = 0;
    private Pendu dessin;
    private ArrayList<Forme>listDessin = new ArrayList<>();


    public Pendu() {
        this.setSize(100,100);
        this.setVisible(true);
        //**************jambe gauche************
        listDessin.add(new Forme("Ligne", new int[]{148, 335, 188, 275}));
        //**************jambe droite************
        listDessin.add(new Forme("Ligne", new int[]{188, 275, 228, 335}));
        //**************bras gauche************
        listDessin.add(new Forme("Ligne", new int[]{148, 235, 188, 215}));
        //**************bras droit************
        listDessin.add(new Forme("Ligne", new int[]{188, 215, 228, 235}));
        //**************corps***********
        listDessin.add(new Forme("Ligne", new int[]{188, 200, 188, 275}));
        //**************tête***********
        listDessin.add(new Forme("Ovale", new int[]{162, 150, 50, 50}));
        //**************noeud coulant***********
        listDessin.add(new Forme("Rectangle", new int[]{180, 75, 10, 70}));
        //**************barre horizontale***********
        listDessin.add(new Forme("Rectangle", new int[]{90, 75, 160, 20}));
        //**************barre verticale***********
        listDessin.add(new Forme("Rectangle", new int[]{90, 95, 10, 300}));
        //**************socle***********
        listDessin.add(new Forme("Rectangle", new int[]{60, 390, 70, 20}));
        }

    private void draw(Graphics g) {
        for(int i = 9; i >= coupRestant; i--){
            Forme f = listDessin.get(i);
            if(f.getType().equals("Ligne")){
                g.drawLine(f.getTab()[0],f.getTab()[1],f.getTab()[2],f.getTab()[3]);
            } else if(f.getType().equals("Ovale")){
                g.fillOval(f.getTab()[0],f.getTab()[1],f.getTab()[2],f.getTab()[3]);
            } else if (f.getType().equals("Rectangle")){
                g.fillRect(f.getTab()[0],f.getTab()[1],f.getTab()[2],f.getTab()[3]);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void setCoupRestant(int x){
        this.coupRestant = x;
        this.repaint();
    }

    public void setEtatPartie(int x){
        this.etatPartie = x;
    }

    public int getEtatPartie(){
        return etatPartie;
    }

    public int getCoupRestant() {
        return coupRestant;
    }
}
