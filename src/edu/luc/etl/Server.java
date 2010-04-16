package edu.luc.etl;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

import edu.luc.etl.discovery.MulticastBroadcaster;
import edu.luc.etl.discovery.MulticastReceiver;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		MulticastSocket socket;
		try {
			//TODO make some constants class somewhere
			socket = new MulticastSocket(9999);
		} catch (SocketException e) {
			//TODO pass this up and let the server deal with services failing to start?
			e.printStackTrace();
			return;
		}
		//InetAddress group = InetAddress.getByName("224.0.0.1");
		InetAddress group = InetAddress.getByName("230.0.0.1");
		socket.joinGroup(group);

		IService broadcaster, receiver;
		
		broadcaster = new MulticastBroadcaster(socket, group);
		receiver = new MulticastReceiver(socket);
		
		Thread broadcasterThread, receiverThread;
		
		broadcasterThread = new Thread(broadcaster);
		receiverThread = new Thread(receiver);
		
		broadcaster.start();
		broadcasterThread.start();
		
		receiver.start();
		receiverThread.start();
		System.out.println("receiver started");
		
		 /*String msg = "Hello";
		 InetAddress group = InetAddress.getByName("228.5.6.7");
		 MulticastSocket s = new MulticastSocket(6789);
		 s.joinGroup(group);
		 DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),
		                             group, 6789);
		 s.send(hi);
		 // get their responses!
		 byte[] buf = new byte[1000];
		 DatagramPacket recv = new DatagramPacket(buf, buf.length);
		 s.receive(recv);
		 System.out.println("got msg");
		 // OK, I'm done talking - leave the group...
		 s.leaveGroup(group);*/
		
		
		

	}

}
