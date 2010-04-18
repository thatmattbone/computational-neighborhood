package edu.luc.etl.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * Keeps track of active nodes.
 * 
 * @author mbone
 */
public class Registry implements IRegistry {

	protected long timeout = 2000; //default to two seconds
	
	protected HashMap<UUID, INode> nodeMap = new HashMap<UUID, INode>();
	
	/* (non-Javadoc)
	 * @see edu.luc.etl.node.IRegistry#heartbeatFromNode(edu.luc.etl.node.INode)
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
	
	/* (non-Javadoc)
	 * @see edu.luc.etl.node.IRegistry#getNodes()
	 */
	public synchronized List<INode> getNodes() {
		ArrayList<INode> nodes = new ArrayList<INode>();
		nodes.addAll(nodeMap.values());
		
		return nodes;
	}
	
	/* (non-Javadoc)
	 * @see edu.luc.etl.node.IRegistry#reapDeadNodes()
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
	
	/* (non-Javadoc)
	 * @see edu.luc.etl.node.IRegistry#setTimeout(int)
	 */
	public void setTimeout(int timeout) { this.timeout = timeout; }
	
}
