package edu.luc.etl.node;

import java.util.Collection;
import java.util.List;
import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;


/**
 * Keeps track of active nodes.
 * 
 * @author mbone
 */
public class Registry {

	protected long timeout = 2000; //default to two seconds
	
	protected HashMap<UUID, INode> nodeMap = new HashMap<UUID, INode>();
	
	/**
	 * Node has sent a notification.
	 * 
	 * Either add the new node, or update the existing node's last heartbeat time.
	 * 
	 * @param node
	 */
	public synchronized void heartbeatFromNode(INode node) {
		UUID nodeId = node.getId();
		
		if(nodeMap.containsKey(nodeId)) { 
			INode updateNode = nodeMap.get(nodeId);
			updateNode.setLastHeartbeatTime(node.getLastHeartbeatTime());
		} else {
			nodeMap.put(nodeId, node);
		}
	}
	
	/**
	 * Return the list of active nodes.
	 * @return
	 */
	public synchronized List<INode> getNodes() {
		ArrayList<INode> nodes = new ArrayList<INode>();
		nodes.addAll(nodeMap.values());
		
		return nodes;
	}
	
	/**
	 * Remove dead nodes from the registry.  
	 * Dead nodes are defined as nodes those that have not been heard from
	 * in <this.timeout> seconds. 
	 * 
	 */
	public synchronized void reapDeadNodes() {
		long currentTime = System.currentTimeMillis();
		
		for(INode node: this.getNodes()) { //use getNodes() to avoid ConcurrentModificationException
			long diff = (currentTime - node.getLastHeartbeatTime());
			System.out.println(diff);
			if(diff > timeout) {				
				nodeMap.remove(node.getId());
			}
		}
	}
	
	public void setTimeout(int timeout) { this.timeout = timeout; }
	
}
