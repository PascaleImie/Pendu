import javax.swing.*;

/**
 * Created by Pascale on 30/11/2016.
 */
public class Fenetre extends JFrame {
    private Pendu pendu = new Pendu();
   // private Bouton bouton = new Bouton('A');

    public Fenetre(){
        this.setTitle("Le jeu du pendu");
        this.setSize(500, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pendu);


        this.setVisible(true);
    }

}
