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
 * Created by Pierre on 29/11/2016.
 */
public class Serveur {

    private static Serveur serveur;

    private ServerSocket socketServer;
    private Socket connection;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public static Serveur getServeur() throws IOException {
        if (serveur == null)
            serveur = new Serveur();

        return serveur;
    }

    private Serveur() throws IOException {
        socketServer = new ServerSocket(8003);
        System.out.println("Le serveur est à l'écoute du port " + socketServer.getLocalPort());
        connection = socketServer.accept();
        System.out.println("Un joueur s'est connecté");
        objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(connection.getInputStream());
    }

    public void run() throws IOException, SQLException, ClassNotFoundException {

        this.initialiserPartie();

        while (true) {
            Message message = (Message) objectInputStream.readObject();
            this.traiterMessage(message);
        }
    }

    public void traiterMessage(Message message) throws SQLException, ClassNotFoundException, IOException {
        if (message.getCle().equals("GestionTours")) {
            String mot = (String) Moteur.getMoteur().getRequest(new Message("Decrypt", message.getValue())).getValue();
            sendMessageToClient(new Message("Decrypt", mot));
            int coupRestant = (int) Moteur.getMoteur().getRequest(new Message("GestionTours", message.getValue())).getValue();
            sendMessageToClient(new Message("GestionTours", coupRestant));

        }else if (message.getCle().equals("RecommencerUnePartie")) {
            this.initialiserPartie();
            sendMessageToClient(new Message("GestionTours", 10));
        }
    }

    public void sendMessageToClient(Message message) throws IOException {
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }

    public void initialiserPartie() throws IOException, SQLException, ClassNotFoundException {
        String mot = (String) Moteur.getMoteur().getRequest(new Message("Initialiser", null)).getValue().toString();
        sendMessageToClient(new Utilitaires.Message("MotJoueur", mot));
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Serveur s = getServeur();
        s.run();
    }
}