package Interface;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelPenduCenter extends JPanel{

    JPanelPenduCenter(){
        for(char c = 'A'; c <= 'Z'; c++){
            Bouton buton = new Bouton(c);
            this.add(buton);
        }
    }
}
