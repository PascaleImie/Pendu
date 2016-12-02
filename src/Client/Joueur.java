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
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }

    public void run() throws ClassNotFoundException, IOException {
        while (true) {
            Message message = (Message) objectInputStream.readObject();
            this.traiterMessage(message);
        }
    }

    public void traiterMessage(Message message) throws ClassNotFoundException, IOException {

        if (message.getCle().equals("Decrypt")) {
            String mot = message.getValue().toString();
            ((JLabel) jfenetre.getPanelMain().getPanJeu().getPanPendu().getPanNorth().getComponent(0)).setText(mot);
        }else if (message.getCle().equals("MotJoueur")) {
            String mot = message.getValue().toString();
            ((JLabel) jfenetre.getPanelMain().getPanJeu().getPanPendu().getPanNorth().getComponent(0)).setText(mot);
        }else if (message.getCle().equals("GestionTours")) {
            int coupRestant = (int) message.getValue();
            jfenetre.getPanelMain().getPanJeu().getPanScore().getPendu().setCoupRestant(coupRestant);
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
