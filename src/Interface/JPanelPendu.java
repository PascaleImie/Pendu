package Interface;

import Utilitaires.Message;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Pierre on 01/12/2016.
 */

public class JPanelPendu extends JPanel implements ActionListener{

    private final JPanelPenduNorth panNorth;
    private final JPanelPenduCenter panCenter;
    private JButton recommencer;

    public JPanelPendu(){

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new DimensionUIResource(800,600));
        this.setBorder(BorderFactory.createMatteBorder(0, 0,0, 2, Color.BLACK));

        panNorth = new JPanelPenduNorth();
        panNorth.setSize(new Dimension(1200,200));
        panCenter = new JPanelPenduCenter();
        panCenter.setSize(new Dimension(1200,400));
        recommencer = new JButton();
        recommencer.setText("Recommencer une partie");

        this.add(panNorth, BorderLayout.NORTH);
        this.add(panCenter, BorderLayout.CENTER);
        this.add(recommencer, BorderLayout.SOUTH);
        recommencer.addActionListener(this);

    }

    public JPanelPenduNorth getPanNorth() {
        return panNorth;
    }

    public JPanelPenduCenter getPanCenter() {
        return panCenter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        okcancel("Voulez vous vrailent recommencer une nouvelle partie ?");
    }

    public void recommencerUnePartie(){
        try {
            ((JFenetre) this.getParent().getParent().getParent().getParent().getParent()).getJoueur()
                    .sendToServer(new Message("RecommencerUnePartie", null));
            for (int i=0; i<26; i++){
                (this.getPanCenter()).getComponent(i).setEnabled(true);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void okcancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
                "Nouvelle partie", JOptionPane.YES_NO_OPTION);
        System.out.println(result);
        if (result == 0){
            recommencerUnePartie();
        }
    }
}
