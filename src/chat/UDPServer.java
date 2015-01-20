package chat;
import java.net.*;


public class UDPServer implements Runnable {


	private DatagramSocket serverSocket;
	private byte[] receiveData;
	private DatagramPacket receivePacket;



	public UDPServer(int ListenPort) throws Exception{


		this.serverSocket = new DatagramSocket(ListenPort); // création du socket serveur sur le port d'écoute X
		this.receiveData= new byte[1024]; // création du tableau de byte pour réception
		this.receivePacket=null;
	}

	
	public void run ()
	{
			try
			{

				while(true)
				{

	                  receivePacket = new DatagramPacket(receiveData, receiveData.length);
	                  serverSocket.receive(receivePacket); // réception du packet

					//grace aux 2 paramètres j'évite le problème de résidue dans le buffer de réception  =>   AAAAAAAAAAAAAAAAAAAA
					// getOffset donne l'index du premier Byte et Length la longueur                     =>   coucouAAAAAAAA
	                  String sentence = new String( receivePacket.getData(), receivePacket.getOffset(),receivePacket.getLength()); // byte[] bytes reconverti en String

	                  System.out.println(sentence); // affichage client du texte reçu

				}

			} //try
			catch(Exception error)
			{
				error.printStackTrace();
			}

		
	}// run
	
}
