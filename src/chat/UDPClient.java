package chat;

import java.io.*;
import java.net.*;

public class UDPClient implements Runnable {


    private DatagramSocket clientSocket;  // client socket
    private BufferedReader sourceUserKeyboard;  // buffer de récupération de l'entrée clavier
    private byte[] sendData; // data envoyé au format byte vers la destination
    private String IPadresse; // ip du pc de destionation (avant résolution via InetAddress)
    private int portDestination = 0; // port du pc de destination
    private String sentence;// contiendra le texte (avant envoi)
    private InetAddress IPadressRes; // ip du pc de destionation (résolu via InetAddress)


    public UDPClient(String IPadresse, int port) throws Exception {
        this.clientSocket = new DatagramSocket(); //création du socket UDP (réception/envoie) pour le client
        this.sourceUserKeyboard = new BufferedReader(new InputStreamReader(System.in)); // buffer pour la récupération clavier du client
        this.sendData = new byte[1024]; // création d'un tableau de byte pour l'envoi des données
        this.IPadresse = IPadresse; // adresse IP du client opposé
        this.portDestination = port; // port du client opposé
    }


    public void run() {


        while (true) {

            try {


                IPadressRes = InetAddress.getByName(IPadresse);

                sentence = null; // remise à zéro
                //je récupère l'adresse IP de la machine(ici) pour un visuel de chat
                // IPclientChat : contenu récupéré au clavier  via BufferReader
                sentence =Inet4Address.getLocalHost().getHostAddress()+" : "+sourceUserKeyboard.readLine();

                sendData =  sentence.getBytes(); //encodage string en Bytes (préparation à l'envoi)

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPadressRes, portDestination); //création d'un packet (data, longueur, IP + port)
                clientSocket.send(sendPacket); // envoi du sendPacket

               System.gc(); // lance un garbage collector


            } catch (Exception error) {
                error.printStackTrace();
            }


        }// while


    }// run


}
