import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.tools.corba.se.idl.constExpr.BooleanNot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by Pierre on 30/11/2016.
 */
public class Moteur {

    private static Moteur moteur;

    private String motSecret;
    private StringBuilder motSecretCopy;
    private StringBuilder motJoueur;
    private int coupRestant = 11;
    private String messageToServer = "";

    private Moteur() {

    }

    public static Moteur getMoteur() {
        if (moteur == null)
            moteur = new Moteur();

        return moteur;
    }

    private void lancerPartie() throws SQLException, ClassNotFoundException {
        motSecret = generateMotAleat();
        System.out.println(motSecret);
        motSecretCopy = new StringBuilder(motSecret);
        motJoueur = new StringBuilder(motSecret.replaceAll(".", "*"));
    }

    private String generateMotAleat() throws SQLException, ClassNotFoundException {
        Bdd bdd = Bdd.getBdd();
        Random r = new Random();
        int index = r.nextInt(bdd.getNbMots());
        String mot = bdd.getMot(index);
        return mot;
    }

    private void decryptMot(char lettre){
        int index = motSecretCopy.indexOf(String.valueOf(lettre));
        while (index >= 0) {
            motJoueur.setCharAt(index, lettre);
            motSecretCopy.setCharAt(index, '*');
            index = motSecretCopy.indexOf(String.valueOf(lettre));
        }

    }

    private String gestionTours(char lettre){
        if (!motSecret.contains(String.valueOf(lettre))) {
            coupRestant--;
            if (coupRestant == 0){
                return messageToServer = "Vous avez perdu, vous êtes mauvais... :'(";
            }else{
                return messageToServer = motJoueur.toString() + "Le mot à deviner ne contient pas la lettre " + lettre + ". Il vous reste " + coupRestant + " essai(s)";
            }

        } else if(!motJoueur.toString().contains("*")){
            return messageToServer = "VICTOIRE !! VOUS AVEZ TROUVÉ LE MOT " + motSecret;
        }else{
            return messageToServer = motJoueur.toString();
        }
    }

    public Message getRequest(Message message) throws SQLException, ClassNotFoundException, IOException {
        if (message.getCle().equals("MotAleatoire")) {
            return new Message("MotAleatoire", generateMotAleat());

        } else if (message.getCle().equals("Decrypt")) {
            this.decryptMot((Character) message.getValue());
            return new Message("Decrypt", motJoueur);

        } else if (message.getCle().equals("Initialiser")) {
            this.lancerPartie();
            return new Message("Initialiser", motJoueur);

        } else if (message.getCle().equals("GestionTours")) {
            this.gestionTours((Character) message.getValue());
            return new Message("GestionTours", messageToServer);

        }
        return null;
    }

}