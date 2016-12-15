package Moteur;

import Utilitaires.Bdd;
import Utilitaires.Message;

import java.net.Inet4Address;
import java.net.UnknownHostException;
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
    private int bestScore;
    private int [] bestsScores = new int[5];
    private int [] gestionTours = new int[2];
    private String niveauDeJeu = "";


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
        System.out.println(mot);
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
    private int[] gestionTours(char lettre) throws SQLException, ClassNotFoundException, UnknownHostException {

        if (!motSecret.contains(String.valueOf(lettre))) {
            coupRestant--;
            if(coupRestant==0){
                etatPartie = -1;
                score=0;
            }
        }else if(!motJoueur.toString().contains("*")){

            etatPartie = 1;

            if(niveauDeJeu.equals("easy")){
                score++;
            }else if(niveauDeJeu.equals("medium")){
                score=score+2;
            }else if(niveauDeJeu.equals("hard")){
                score=score+3;
            }else if(niveauDeJeu.equals("impossible")){
                score=score+4;
            }

            if(score>bestScore){
                Bdd bdd = Bdd.getBdd();
                bdd.setBestScore(Inet4Address.getLocalHost().getHostAddress(), score);
                bestScore = bdd.getBestScore(Inet4Address.getLocalHost().getHostAddress());
            }

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

    public int getTime(String niveauDeJeu) {
        int time = 0;
        if(niveauDeJeu.equals("easy")){
            time = 500;
        }else if(niveauDeJeu.equals("medium")){
            time=300;
        }else if(niveauDeJeu.equals("hard")){
            time=100;
        }else if(niveauDeJeu.equals("impossible")){
            time=33;
        }

        return time;

    }

    private int getScore() throws SQLException, ClassNotFoundException {
        return score;
    }

    public int[] getBestsScores() throws SQLException, ClassNotFoundException {
        Bdd bdd = Bdd.getBdd();
        bestsScores = bdd.getTenBestsScores();
        return bestsScores;
    }

    public void createPlayer() throws UnknownHostException, SQLException, ClassNotFoundException {
        Bdd bdd = Bdd.getBdd();
        try {
            bdd.insertPlayerScore(Inet4Address.getLocalHost().getHostAddress(), 0);
        }catch(SQLException e){
            bestScore = bdd.getBestScore(Inet4Address.getLocalHost().getHostAddress());
        }

    }

    public Message getRequest(Message message) throws SQLException, ClassNotFoundException, UnknownHostException {
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
        }else if (message.getCle().equals("NiveauDeJeu")){
            niveauDeJeu = ((String) message.getValue());
            return new Message("NiveauDeJeu", getTime((String)message.getValue()));
        } else if (message.getCle().equals("CreatePlayer")){
            this.createPlayer();
        } else if (message.getCle().equals("Meilleurs scores")){
            return new Message("Meilleurs scores",getBestsScores());
        }
        return null;
    }
}
