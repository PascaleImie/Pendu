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
    System.out.println("Le serveur est à l'écoute du port "+socketServer.getLocalPort());
    connection = socketServer.accept();
    System.out.println("Un joueur s'est connecté");
    objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
    objectOutputStream.flush();
    objectInputStream = new ObjectInputStream(connection.getInputStream());
    }

    public void run() throws IOException {
        String motSecret = "anticonstitutionellement";
        StringBuilder motSecretCopy = new StringBuilder(motSecret);
        StringBuilder motJoueur = new StringBuilder(motSecret.replaceAll(".", "*"));
        char lettre;
        int coupRestant=10;


        objectOutputStream.writeUTF("Bienvenue dans le Pendu");
        objectOutputStream.flush();
        objectOutputStream.writeUTF(motJoueur.toString());
        objectOutputStream.flush();

        while (true) {
            objectOutputStream.writeUTF("Saisir une lettre : ");
            objectOutputStream.flush();
            //le serveur récupère la lettre saisie par le joueur
            lettre = objectInputStream.readChar();
            System.out.println(lettre);

            int index = motSecretCopy.indexOf(String.valueOf(lettre));
            System.out.println(index);
            while (index >= 0) {
                motJoueur.setCharAt(index, lettre);
                motSecretCopy.setCharAt(index, '*');
                index = motSecretCopy.indexOf(String.valueOf(lettre));
            }
            if (!motSecret.contains(String.valueOf(lettre))) {

                objectOutputStream.writeUTF("Le mot à deviner ne contient pas la lettre " + lettre);
                coupRestant--;
                objectOutputStream.writeUTF("Il vous reste " + coupRestant + " essai(s)");
                objectOutputStream.flush();
            } else {

                objectOutputStream.writeUTF(motJoueur.toString());
                objectOutputStream.flush();
            }


        }

    }

    public static void main(String[] args) throws IOException {
        Serveur s = new Serveur();
        s.run();
    }


}