package Moteur;

import Utilitaires.Bdd;
import Utilitaires.Message;

import java.sql.SQLException;
import java.util.Random;

/**
 * Created by Pascale on 30/11/2016.
 */
public class Moteur {
    private static Moteur moteur;
    private String motSecret;
    private StringBuilder motSecretCopy;
    private StringBuilder motJoueur;
    private int coupRestant;
    private int etatPartie;
    private int score;
    private int [] gestionTours = new int[2];


    private Moteur(){

    }
    public static Moteur getMoteur() {
        if (moteur == null)
            moteur = new Moteur();
            return moteur;
    }


    private void lancerPartie() throws SQLException, ClassNotFoundException {
        coupRestant = 10;
        if(etatPartie==0){
            score=0;
        }
        etatPartie = 0;
        motSecret = generateMotAlea();
        motSecretCopy = new StringBuilder(motSecret);
        motJoueur = new StringBuilder(motSecret.replaceAll(".","*"));
    }

    private String generateMotAlea() throws SQLException, ClassNotFoundException {
        Bdd bdd = Bdd.getBdd();
        Random r = new Random();
        int index = r.nextInt(bdd.getNbMots());
        String mot = bdd.getMot(index);
        return mot;
    }

    private void decryptMot(char lettre){
        int index = motSecretCopy.indexOf(String.valueOf(lettre));
        while(index>=0){
            motJoueur.setCharAt(index, lettre);
            motSecretCopy.setCharAt(index,'*');
            index = motSecretCopy.indexOf(String.valueOf(lettre));
        }
    }
    private int[] gestionTours(char lettre) {
        if (!motSecret.contains(String.valueOf(lettre))) {
            coupRestant--;
            if(coupRestant==0){
                etatPartie = -1;
                score=0;
            }
        }else if(!motJoueur.toString().contains("*")){
            etatPartie = 1;
            score++;
        }

        gestionTours[0] = coupRestant;
        gestionTours[1] = etatPartie;
        return gestionTours;
    }

    private int getScore(){
        return score;
    }

    public Message getRequest(Message message) throws SQLException, ClassNotFoundException {
        if (message.getCle().equals("MotAleatoire")) {
            return new Message("MotAleatoire", generateMotAlea());
        } else if (message.getCle().equals("Decrypt")) {
            this.decryptMot((Character) message.getValue());
            return new Message("Decrypt", motJoueur.toString());
        } else if (message.getCle().equals("Initialiser")) {
            this.lancerPartie();
            return new Message("Initialiser", motJoueur.toString());
        } else if (message.getCle().equals("GestionTours")) {
            this.gestionTours((Character)message.getValue());
            return new Message("GestionTours", gestionTours);
        } else if (message.getCle().equals("GetScore")) {
            return new Message("GetScore", getScore());
        }
        return null;
    }
}
