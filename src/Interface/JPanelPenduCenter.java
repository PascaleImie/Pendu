package Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.BOLD;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelPenduCenter extends JPanel {
    private JLabel result = new JLabel();
    private JPanel panEcran = new JPanel();
    private JLabel panImage = new JLabel();
    private BorderLayout borderLayout;
    public static Icon image;
    public static Icon imageLooser;




    JPanelPenduCenter(){
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        this.add(panEcran, BorderLayout.NORTH);
        for(char c = 'A'; c <= 'Z'; c++){
            Bouton buton = new Bouton(c);
            panEcran.add(buton, BorderLayout.NORTH);
        }
        panEcran.setPreferredSize(new Dimension(50,90));


        image = new ImageIcon(getClass().getClassLoader().getResource("feuxdartifice.gif"));
        imageLooser = new ImageIcon(getClass().getClassLoader().getResource("looser.gif"));
        //panImage.setIcon(image);
        panImage.setHorizontalAlignment(JLabel.CENTER);
        this.add(panImage, BorderLayout.CENTER);




        this.add(result, BorderLayout.SOUTH);
        result.setFont(new Font("Arial", BOLD, 30));
        result.setForeground(Color.RED);
        result.setText("Coup(s) restant(s) : 10");
        this.result.setHorizontalAlignment(JLabel.CENTER);
    }
        public JPanel getpanEcran(){
            return panEcran;
        }

        public JLabel getResult() {
            return result;
        }

        public JLabel getPanImage() {
        return panImage;
        }



    // public void paintComponent(Graphics g){
   //     try {
   //         super.paintComponent(g);
   //         Image img = ImageIO.read(new File("Resources/images.jpg"));
   //         g.drawImage(img, 0, 0, this);

   //     } catch (IOException e) {
   //         e.printStackTrace();
   //     }
    //}


}
