package Serveur;

import Moteur.Moteur;
import Utilitaires.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Pascale on 29/11/2016.
 */
public class Serveur {

    private static Serveur serveur;

    private ServerSocket socketServer;
    private Socket connection;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private boolean etatConnexion = false;

    public static Serveur getServeur() throws IOException {
        if (serveur == null)
            serveur = new Serveur();

        return serveur;
    }

    private Serveur() throws IOException {
        socketServer = new ServerSocket(8003);
        System.out.println("Le serveur est à l'écoute du port " + socketServer.getLocalPort());
    }

    private void initConnexion() throws IOException {
        etatConnexion = true;
        connection = socketServer.accept();
        System.out.println("Un joueur s'est connecté");
        objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(connection.getInputStream());
    }

    public void run() throws IOException, SQLException, ClassNotFoundException {

        do {

            this.initConnexion();
            this.initialiserPartie();

            while (true) {
                Message message = (Message) objectInputStream.readObject();
                this.traiterMessage(message);

                if (message.getCle().equals("Deconnexion"))
                    break;
            }
        } while (true) ;
    }

    public void traiterMessage(Message message) throws SQLException, ClassNotFoundException, IOException {
        if (message.getCle().equals("GestionTours")) {

            String mot = (String) Moteur.getMoteur().getRequest(new Message("Decrypt", message.getValue())).getValue();
            sendMessageToClient(new Message("Decrypt", mot));
            int[] gestionTours = (int[]) Moteur.getMoteur().getRequest(new Message("GestionTours", message.getValue())).getValue();
            sendMessageToClientWithReset(new Message("GestionTours", gestionTours));
            int score = (int) Moteur.getMoteur().getRequest(new Message("GetScore", message.getValue())).getValue();
            sendMessageToClient(new Message("GetScore", score));

            //Si l'état de la partie est à -1
            if(gestionTours[1]==-1) {
                //On récupère le motSecret dans le Moteur et on l'envoit au client
                String motDecrypt = (String) Moteur.getMoteur().getRequest(new Message("GetMot", message.getValue())).getValue();
                sendMessageToClient(new Message("GetMot", motDecrypt));

            }
        } else if (message.getCle().equals("RecommencerUnePartie")) {

            this.initialiserPartie();
            sendMessageToClient(new Message("GestionTours", new int[]{10, 0}));
            int score = (int) Moteur.getMoteur().getRequest(new Message("GetScore", message.getValue())).getValue();
            sendMessageToClient(new Message("GetScore", score));


        } else if (message.getCle().equals("TempsEcoule")) {

            int score = (int) Moteur.getMoteur().getRequest(new Message("ScoreTempsEcoule", null)).getValue();
            int[] gestionTours = (int[]) Moteur.getMoteur().getRequest(new Message("GestionToursTempsEcoule", message.getValue())).getValue();

            sendMessageToClientWithReset(new Message("GestionTours", gestionTours));
            sendMessageToClient(new Message("GetScore", score));

            if(gestionTours[1]==-1) {
                //On récupère le motSecret dans le Moteur et on l'envoit au client
                String motDecrypt = (String) Moteur.getMoteur().getRequest(new Message("GetMot", message.getValue())).getValue();
                sendMessageToClient(new Message("GetMot", motDecrypt));
            }


        } else if (message.getCle().equals("Deconnexion")) {

            objectInputStream.close();
            objectOutputStream.close();

        }
    }

    public void sendMessageToClient(Message message) throws IOException {
        //envoi le message au client avec objectOutputStream
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }

    public void sendMessageToClientWithReset(Message message) throws IOException {
        objectOutputStream.reset();
        sendMessageToClient(message);
    }

    public void initialiserPartie() throws IOException, SQLException, ClassNotFoundException {
        //On appelle la méthode getRequest du moteur avec le mot clé Initialiser
        String mot = (String) Moteur.getMoteur().getRequest(new Message("Initialiser", null)).getValue().toString();
        sendMessageToClient(new Utilitaires.Message("MotJoueur", mot));

    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Serveur s = getServeur();
        s.run();
    }
}