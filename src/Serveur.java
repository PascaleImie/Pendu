import com.mysql.fabric.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Random;
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
    private Scanner sc = new Scanner(System.in);

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

        String mot = (String) Moteur.getMoteur().getRequest(new Message("Initialiser", null)).getValue().toString();

        int coupRestant=10;

        sendMessageToClient("Bienvenue dans le Pendu");
        sendMessageToClient(mot);

        while (true) {

            sendMessageToClient("Saisir une lettre : ");

            // LE SERVEUR RECUPERE LA LETTRE SAISIE PAR LE JOUEUR
            char lettre = objectInputStream.readChar();

            // MODIFICATION DU MOT
            mot = String.valueOf(Moteur.getMoteur().getRequest(new Message("Decrypt", lettre)).getValue());

            // GESTION DES TOURS
            sendMessageToClient(String.valueOf(Moteur.getMoteur().getRequest(new Message("GestionTours", lettre)).getValue()));

        }
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Serveur s = getServeur();
        s.run();
    }

    public void sendMessageToClient(String message) throws IOException {
        objectOutputStream.writeUTF(message);
        objectOutputStream.flush();
    }
}