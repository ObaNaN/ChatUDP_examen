package chat;
public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		int port = 65432;
		String adresseIP = "192.168.0.16";



		Thread serv = new Thread(new UDPServer(port));
		serv.start();
		
		Thread client = new Thread(new UDPClient(adresseIP,port)); // je lui passe le parametre adresse IP
		client.start();
		
		
	}

}
