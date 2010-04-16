package edu.luc.etl.heartbeat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import edu.luc.etl.IService;

public class UdpResponderService implements IResponderService {

	protected boolean keepRunning = true;
	protected DatagramSocket socket = null;
	
	@Override
	public void start() { 
		try {
			//TODO make some constants class somewhere
			socket = new DatagramSocket(9999);
		} catch (SocketException e) {
			//TODO pass this up and let the server deal with services failing to start?
			e.printStackTrace();
		} 
	}

	@Override
	public void stop() {
		keepRunning = false;
	}

	@Override
	public void run() {
		while(keepRunning) {
			try {
				byte[] buf = new byte[256];

				// receive request
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet); //TODO set timeout

				// figure out response
				/*String dString = null;
				if (in == null)
					dString = new Date().toString();
				else
					dString = getNextQuote();
				buf = dString.getBytes();*/

				// send the response to the client at "address" and "port"
				InetAddress address = packet.getAddress();
				System.out.println("Received udp package from: " + address);
				//int port = packet.getPort();
				//packet = new DatagramPacket(buf, buf.length, address, port);
				//socket.send(packet);
			} catch(IOException e) {
				e.printStackTrace();			
			}
		}
		socket.close();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World");
		
		//start heartbeat responder service
		//start discovery service

		IService udpResponder = new UdpResponderService();
		udpResponder.start();
		
		Thread udpThread = new Thread(udpResponder);
		udpThread.start();
		
		Thread.sleep(5); //makes sure the thread is started...very bad

		//send the responder a msg
        DatagramSocket socket = new DatagramSocket();

        // send request
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 9999);
        udpResponder.stop();//stop before we do the send because right now there is no timeout
        socket.send(packet);
        System.out.println("sent the message");

	}

}
