package edu.luc.etl.node;

import java.net.InetAddress;
import java.util.UUID;

public interface INode {

	public UUID getId();
	
	public InetAddress getIp();
	
	public long getLastHeartbeatTime();
	
	public void setLastHeartbeatTime(long lastHeartbeatTime);
	
}
