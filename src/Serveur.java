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

            lettre=objectInputStream.readChar();
            System.out.println(lettre);
//            System.out.println(lettre);

//            for(int i = 0; i < motSecret.length(); i++){
//                if(motSecret.indexOf(i) == lettre){
//                    motJoueur.rep
//                }
//            }

            int index = motSecretCopy.indexOf(String.valueOf(lettre));
            System.out.println(index);
            while (index >= 0) {
                motJoueur.setCharAt(index, lettre);
                motSecretCopy.setCharAt(index, '*');
                index = motSecretCopy.indexOf(String.valueOf(lettre));
            }

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

    public static String replaceCharAt(String s, int pos, char c) {
        return s.substring(0,pos) + c + s.substring(pos+1);
    }

}