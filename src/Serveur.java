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
        char lettre;
        int coupRestant=10;


        objectOutputStream.writeUTF("Bienvenue dans le Pendu");
        objectOutputStream.flush();

    while(coupRestant<=10){
        coupRestant--;
        System.out.println("Il vous reste"+coupRestant+"à jouer");



    }
    }
    public static void main(String[] args) {
        System.out.println("Bienvenue");
    }

}
