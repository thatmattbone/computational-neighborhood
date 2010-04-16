package edu.luc.etl.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import edu.luc.etl.IService;

public class MulticastReceiver implements IService {
	
	protected DatagramSocket socket = null;
	
	protected boolean keepRunning = true;

	public MulticastReceiver(DatagramSocket socket) {
		this.setSocket(socket);
		
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	@Override
	public void start() { }

	@Override
	public void stop() {
		this.keepRunning  = false; 
	}

	@Override
	public void run() {
		while(keepRunning) {
			try {
				byte[] buf = new byte[256];

				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				System.out.println("receiver going to block: ");
				socket.receive(packet); //TODO set timeout

				// send the response to the client at "address" and "port"
				InetAddress address = packet.getAddress();
				System.out.println("Received multicast package from: " + address);
			} catch(IOException e) {
				e.printStackTrace();			
			}
		}
		socket.close();

	}

}
