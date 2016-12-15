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
        //Au lancement de la partie, le nb de coup restant est à 10, l'état de la partie à 0 et le score à 0
        coupRestant = 10;
        if(etatPartie==0){
            score=0;
        }
        etatPartie = 0;
        //Au lancement de la partie, la méthode generateMotAlea() genère un mot aléatoire et le stocke
        // dans le string mot Secret
        motSecret = generateMotAlea();
        //Instanciation d'un objet StringBuilder motSecretCopy qui est construit avec motSecret
        motSecretCopy = new StringBuilder(motSecret);
        //Instanciation d'un objet StringBuilder motJoueur qui est construit avec le motSecret dont les caractères
        //sont remplacés par *
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

    private String getMot (){
        return motSecret;
    }

    //Méthode gestion de tours qui récupère dans un tableau coup restant et état de la partie.
    private int[] gestionTours(char lettre) {
        //Si le mot caché par * ne contient pas la lettre cliquée par le joueur
        if (!motSecret.contains(String.valueOf(lettre))) {
            //On décrémente coup restant
            coupRestant--;
            //Si il n'y a plus de coup restant, l'état de la partie est à -1 soit perdu et le score est à 0
            if(coupRestant==0){
                etatPartie = -1;
                score=0;
            }
        //Sinon si le mot caché ne contient pas de *, l'état de la partie est à 1 soit gagné et le score est à 1
        }else if(!motJoueur.toString().contains("*")){
            etatPartie = 1;
            score++;
        }

        //Coup Restant et etat de la partie sont stockés dans un tableau
        gestionTours[0] = coupRestant;
        gestionTours[1] = etatPartie;
        return gestionTours;
    }

    private void tempsEcoule() {
        coupRestant=0;
        score=0;
        etatPartie=-1;
        gestionTours[0] = coupRestant;
        gestionTours[1] = etatPartie;
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
        } else if(message.getCle().equals("GetMot")){
            return new Message("GetMot", motSecret);
        } else if (message.getCle().equals("ScoreTempsEcoule")) {
            this.tempsEcoule();
            return new Message("GetScore", getScore());
        } else if (message.getCle().equals("GestionToursTempsEcoule")){
            return new Message("GestionTours", gestionTours);
        }
        return null;
    }
}
