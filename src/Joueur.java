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

    public Joueur() throws IOException {
        socketClient = new Socket(InetAddress.getLocalHost(),8003);
        System.out.println("Demande de connexion au serveur");

        //lire
        objectInputStream = new ObjectInputStream(socketClient.getInputStream());

        //ecrire
        objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
        objectOutputStream.flush();

    }

    public void run() throws IOException {
        //System.out.println("***hello Serveur***");
        String message ="";
        while (true) {
            //Recupère le message du serveur
            message=objectInputStream.readUTF();
            //lis un string dans un format UTF
            System.out.println("Le serveur dit :" + message);
            System.out.println("Saisir une lettre :");
            objectOutputStream.writeUTF(sc.nextLine());
            objectOutputStream.flush();
        }

    }

    public static void main(String[] args) throws IOException {
        Joueur  j = new Joueur();
        j.run();
    }

}
