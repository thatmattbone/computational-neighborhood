package edu.luc.etl.node;

import java.util.List;

/**
 * Keeps track of active nodes.
 * 
 * @author mbone
 */
public class Registry {

	protected int timeout = 2; //default to two seconds
	
	/**
	 * 
	 * @param node
	 */
	public void heartbeatFromNode(INode node) {
		
	}
	
	/**
	 * Return the list of active nodes.
	 * @return
	 */
	public List<INode> getNodes() {
		return null;
	}
	
	/**
	 * Remove dead nodes from the registry.  
	 * Dead nodes are defined as nodes those that have not been heard from
	 * in <this.timeout> seconds. 
	 * 
	 */
	public void reapDeadNodes() {
		
	}
	
	public void setTimeout(int timeout) { this.timeout = timeout; }
	
}
