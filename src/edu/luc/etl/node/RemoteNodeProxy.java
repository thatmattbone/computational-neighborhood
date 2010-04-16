package edu.luc.etl.node;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class RemoteNodeProxy implements INode {

	protected InetAddress ip = null;
	protected UUID id;
	
	public RemoteNodeProxy(String id, String ip) {
		try {
			this.ip = InetAddress.getByName(ip);
		} catch (UnknownHostException e) { /* Just continue with it set to null. */}
		
		this.id = UUID.fromString(id);
	}

	@Override
	public InetAddress getIp() {
		return ip;
	}

	@Override
	public UUID getId() {
		return id;
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}
}
