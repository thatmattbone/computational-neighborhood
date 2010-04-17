package edu.luc.etl.discovery;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import edu.luc.etl.IService;
import edu.luc.etl.Util;
import edu.luc.etl.messages.Messages.NodeBroadcst;
import edu.luc.etl.node.INode;

public class MulticastBroadcaster implements IService {

	protected InetAddress multicastAddress = null;
	protected DatagramSocket socket = null;
	protected INode myNode;
	
	protected boolean keepRunning = true;
	
	protected int sleepValue = 5000; //amount to sleep between broadcasts

	public MulticastBroadcaster(DatagramSocket socket, InetAddress multicastAddress, INode myNode) {
		this.setSocket(socket);
		this.multicastAddress = multicastAddress;
		this.myNode = myNode;
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
			
			NodeBroadcst msg = NodeBroadcst.newBuilder()
				.setUuid(myNode.getId().toString())
				.build();
			
			try {
				System.out.println(Util.getHexString(msg.toByteArray()));
			} catch (Exception e1) {
			}
			
			DatagramPacket packet = new DatagramPacket(msg.toByteArray(), 
													   msg.getSerializedSize(), 
													   this.multicastAddress, 
													   9999);
			
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
