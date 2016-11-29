import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Pierre on 29/11/2016.
 */
public class Serveur {
    private ServerSocket socketServer;
    private Socket connection;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Scanner sc = new Scanner(System.in);


public Serveur() throws IOException {

    socketServer = new ServerSocket(8003);
    System.out.println("Le serveur est à l'écoute du port"+socketServer.getLocalPort());
    connection = socketServer.accept();
    System.out.println("Un joueur s'est connecté");
    objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
    objectOutputStream.flush();
    objectInputStream = new ObjectInputStream(connection.getInputStream());
    }

    public void run() throws IOException {
        String motSecret = "bateau";
        String motJoueur = motSecret.replaceAll(".", "*");
        char lettre;
        int coupRestant=10;

        objectOutputStream.writeUTF("Bienvenue dans le Pendu");
        objectOutputStream.flush();
        objectOutputStream.writeUTF(motJoueur.toString());
        objectOutputStream.flush();

        while (true) {
            objectOutputStream.writeUTF("Saisir une lettre : ");
            objectOutputStream.flush();

            lettre=objectInputStream.readChar();
            System.out.println(lettre);

            // Modification du motJoueur

            objectOutputStream.writeUTF(motJoueur.toString());
            objectOutputStream.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Bienvenue");
        Serveur s = new Serveur();
        s.run();
    }

}