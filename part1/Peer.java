
import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;

public class Peer {

	byte[] peerID; //the id of this peer
	
	private String IPAddress; //the IP address of this peer as a String
	
	private int portNum; //the port number of this peer
	
	final byte[] clientID; //the peer ID of the local client
	
	private final byte[] info_hash; //the info hash of the torrent that this peer is using to communicate
	
	private Socket peerSocket; //the socket connecting the local client to this peer
	
	private DataOutputStream outputStream;
	
	private DataInputStream inputStream;
	
	private Message peerMessage = null;
	
	/**
	 * Creates a new peer object. Actual parameters are yet to be determined
	 * @param args
	 */
	public Peer (byte[] id, String ip, int port, byte[] cid, byte[] info_hash) throws UnknownHostException, IOException{
		this.peerID = id;
		this.portNum = port;
		this.IPAddress = ip;	
		this.peerSocket = null;
		this.info_hash = info_hash;
		this.clientID = cid;
		
	}/*end of Peer class constructor*/
	
	public String getIP(){
		return this.IPAddress;
	}
	
	public int getPort(){
		return this.portNum;
	}
	
	/*
	 * Open a TCP socket on the local machine and contact the peer using the BT peer protocol
	 */
	public boolean connectToPeer(){
		try {
			this.peerSocket = new Socket(this.IPAddress, this.portNum);
			this.outputStream = new DataOutputStream(this.peerSocket.getOutputStream()); // open output stream for outgoing data
			System.out.println("Data output stream for " + RUBTClient.escape(this.peerID) + " opened...");
			this.inputStream = new DataInputStream(this.peerSocket.getInputStream()); //open input stream for incoming data
			System.out.println("Data input stream for " + RUBTClient.escape(this.peerID) + " opened...");
			return true;
		}catch (UnknownHostException e) { //catch error for incorrect host name and exit program
	            System.out.println("Peer " + peerID + " is unknown");
	            return false;
	    } catch (IOException e) { //catch error for invalid port and exit program
	            System.out.println("Connection to " + peerID + " failed");
	            return false;
	    }  
	}/*end of connectToPeer method*/
	
	/*
	 * @params
	 */
	public void requestPiecesFromPeer() throws EOFException, IOException{
		return;
	}
	
	public int shakeHand(){
		return -1;
	}
}
