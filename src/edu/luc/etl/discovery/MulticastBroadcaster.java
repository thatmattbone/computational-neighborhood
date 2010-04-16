package edu.luc.etl.discovery;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import edu.luc.etl.IService;

public class MulticastBroadcaster implements IService {

	protected InetAddress multicastAddress = null;
	protected DatagramSocket socket = null;
	
	protected boolean keepRunning = true;
	
	protected int sleepValue = 500; //amount to sleep between broadcasts

	public MulticastBroadcaster(DatagramSocket socket, InetAddress multicastAddress) {
		this.setSocket(socket);
		this.multicastAddress = multicastAddress;
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
			byte[] buf  = new byte[256];
			
			String broadcast = "broadcast";
			buf = broadcast.getBytes();
			
			DatagramPacket packet;
			packet = new DatagramPacket(buf, buf.length, this.multicastAddress, 9999);
			
			try {
				socket.send(packet);
				System.out.println("packet sent");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(sleepValue);
			} catch (InterruptedException e) { /*Ignore and keep broadcasting */ }
		}
		
		socket.close();
	}

}
