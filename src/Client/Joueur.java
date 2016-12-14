package Client;

import Interface.JFenetre;
import Utilitaires.Message;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Pierre on 29/11/2016.
 */

public class Joueur {

    private Socket socketClient;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Scanner sc = new Scanner(System.in);

    private JFenetre jfenetre;

    public Joueur() throws IOException {
        socketClient = new Socket(InetAddress.getLocalHost(),8003);
        System.out.println("Demande de connexion au serveur");

        //lire
        objectInputStream = new ObjectInputStream(socketClient.getInputStream());

        //ecrire
        objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
        objectOutputStream.flush();
    }

    public void sendToServer(Message message) throws IOException {
        //Envoi message au serveur avec l'objet outputStream
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }

    public void run() throws ClassNotFoundException, IOException {
        while (true) {
            //récupère le message envoyé par le serveur
            Message message = (Message) objectInputStream.readObject();
            //Appelle la méthode traiterMessage
            this.traiterMessage(message);
        }
    }

    public void traiterMessage(Message message) throws ClassNotFoundException, IOException {
        //Constructeur de l'objet Message prend en paramètre String cle, Object value.
        // Si la clé du message est Decrypt ...
        if (message.getCle().equals("Decrypt")) {
            //On récupère la valeur de l'objet dans la variable mot
            String mot = message.getValue().toString();
            //On met à jour le labelMot(getComponent) contenu dans le panel JPanelPenduNorth
            ((JLabel) jfenetre.getPanelMain().getPanJeu().getPanPendu().getPanNorth().getComponent(0)).setText(mot);
        }else if (message.getCle().equals("MotJoueur")) {
            String mot = message.getValue().toString();
            ((JLabel) jfenetre.getPanelMain().getPanJeu().getPanPendu().getPanNorth().getComponent(0)).setText(mot);
        }else if (message.getCle().equals("GestionTours")) {

            int coupRestant = ((int[]) message.getValue())[0];
            jfenetre.getPanelMain().getPanJeu().getPanScore().getPendu().setCoupRestant(coupRestant);
            //Récupère le nb de coups restants et le transmet au label Result

            int etatPartie = ((int[]) message.getValue())[1];
            jfenetre.getPanelMain().getPanJeu().getPanScore().getPendu().setEtatPartie(etatPartie);

            jfenetre.getPanelMain().getPanJeu().getPanPendu().getPanCenter().getResult().setText("Coup(s) restant(s): "
                    + String.valueOf(coupRestant));

            if(etatPartie == 1){
                //Récupère l'état de la partie (1 ou -1) et le transmet au label Result
                jfenetre.getPanelMain().getPanJeu().getPanPendu().getPanCenter().getResult().setText("YOU WIN !");
            }

            jfenetre.getPanelMain().getPanJeu().getPanScore().getPendu().setEtatPartie(etatPartie);

        }else if (message.getCle().equals("GetScore")) {
            int score = ((int)message.getValue());
            ((JLabel) jfenetre.getPanelMain().getPanJeu().getPanScore().getComponent(0)).setText("SCORE : " + String.valueOf(score));
        } else if(message.getCle().equals("GetMot")){
            String motDecrypt = (String) message.getValue();
            System.out.println(motDecrypt);
            jfenetre.getPanelMain().getPanJeu().getPanPendu().getPanCenter().getResult().setText("YOU LOOSE! ... Le mot était " + motDecrypt);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Joueur joueur = new Joueur();
        JFenetre jFenetre = new JFenetre();
        joueur.setUI(jFenetre);
        jFenetre.setEngine(joueur);
        joueur.run();
    }


    public void setUI(JFenetre jfenetre) {
        this.jfenetre = jfenetre;
    }
}
