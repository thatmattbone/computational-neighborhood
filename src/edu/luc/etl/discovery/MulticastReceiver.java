package edu.luc.etl.discovery;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import edu.luc.etl.IService;
import edu.luc.etl.messages.Messages.NodeBroadcast;
import edu.luc.etl.node.INode;
import edu.luc.etl.node.IRegistry;
import edu.luc.etl.node.RemoteNodeProxy;

public class MulticastReceiver implements IService {
	
	protected DatagramSocket socket = null;
	
	protected boolean keepRunning = true;

	protected IRegistry registry;

	public MulticastReceiver(DatagramSocket socket) {
		this.setSocket(socket);
		
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}
	
	public void setRegistry(IRegistry registry) {
		this.registry = registry;
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
				
				byte[] buf = new byte[38]; //TODO hardcoded the size of this msg in here...always 38 bytes. BAD
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				
				socket.receive(packet); //TODO set timeout
				
				NodeBroadcast msg = NodeBroadcast.parseFrom(packet.getData());
				
				InetAddress address = packet.getAddress();

				INode node = new RemoteNodeProxy(msg.getUuid(), address);
				
				registry.heartbeatFromNode(node);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		socket.close();

	}

}
