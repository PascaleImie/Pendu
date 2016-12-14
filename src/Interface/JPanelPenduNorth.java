package Interface;

import Utilitaires.Message;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

import static java.awt.Font.BOLD;

/**
 * Created by Pierre on 01/12/2016.
 */
public class JPanelPenduNorth extends JPanel {

    private BorderLayout borderLayout;
    private JProgressBar jProgressBarTime = new JProgressBar();
    private int etatPartie;
    private SwingWorker swingWorker;
    private int time;

    JPanelPenduNorth(){

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        JLabel labelMot = new JLabel();
        labelMot.setFont(new Font("Arial", BOLD,60));
        labelMot.setHorizontalAlignment(JLabel.CENTER);
        this.add(labelMot, BorderLayout.NORTH);

        jProgressBarTime.setMaximum(100);
        jProgressBarTime.setMinimum(0);
        jProgressBarTime.setStringPainted(false);

        this.add(jProgressBarTime);
        this.updateProgressBar();
    }

    public SwingWorker getSwingWorker() {
        return swingWorker;
    }

    public void updateProgressBar() {
        swingWorker = new SwingWorker<Integer,Integer>() {

            protected Integer doInBackground() throws Exception {
                for (int i = 100; i >= 0; i--) {
                    Thread.sleep(time);
                    publish(i);
                }
                return null;
            }

            public void done() {

                if (!this.isCancelled()){
                    try {
                        ( (JFenetre) jProgressBarTime.getParent().getParent().getParent().getParent().getParent().getParent().getParent()).getJoueur().sendToServer(new Message("TempsEcoule", null));
                        for (int i=0; i<26; i++){
                            ((JPanelPendu)jProgressBarTime.getParent().getParent()).getPanCenter().getpanEcran().getComponent(i).setEnabled(false);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    jProgressBarTime.setValue(0);
                }


            }

            public void process(java.util.List<Integer> list) {

                int somme = 0;
                for (Integer note : list) {
                    somme += note;
                }
                jProgressBarTime.setValue(somme / list.size());
            }
        };
    }

    public void setTime(int time) {
        this.time = time;
    }
}