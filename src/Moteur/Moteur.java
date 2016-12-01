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
    private int coupRestant = 10;
    private String messageToServer="";

    private Moteur(){

    }
    public static Moteur getMoteur() {
        if (moteur == null)
            moteur = new Moteur();
            return moteur;
    }


    private void lancerPartie() throws SQLException, ClassNotFoundException {
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
    private String gestionTours(char lettre) {
        if (!motSecret.contains(String.valueOf(lettre))) {
            coupRestant--;
            if (coupRestant == 0) {
                return messageToServer = "Vous avez perdu !";
            } else {
                return messageToServer = "Le mot à deviner ne contient pas la lettre " + lettre +
                        " Il vous reste " + coupRestant + " essai(s)";
            }
        } else if (!motJoueur.toString().contains("*")) {
            return messageToServer = "C'est gagné !";
        } else {
            return messageToServer = motJoueur.toString();
        }
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
            return new Message("GestionTours", messageToServer);
        }

        return null;

    }
}
