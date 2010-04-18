package edu.luc.etl.node;

import java.util.List;

//for now this is mostly an interface for mocking purposes
public interface IRegistry {

	/**
	 * Node has sent a notification.
	 * 
	 * Either add the new node, or update the existing node's last heartbeat time.
	 * 
	 * @param node
	 */
	public void heartbeatFromNode(INode node);

	
	/**
	 * Return the list of active nodes.
	 * @return
	 */
	public List<INode> getNodes();

	
	/**
	 * Remove dead nodes from the registry.  
	 * Dead nodes are defined as nodes those that have not been heard from
	 * in <this.timeout> seconds. 
	 * 
	 */
	public void reapDeadNodes();

	
	/**
	 * Set the "don't hear from node" timeout.
	 * 
	 * If we don't hear from a node in the specified amount of time, then it is
	 * removed from the registry as it asssumed to be dead
	 * 
	 * @param timeout Milliseconds
	 */
	public void setTimeout(int timeout);

}