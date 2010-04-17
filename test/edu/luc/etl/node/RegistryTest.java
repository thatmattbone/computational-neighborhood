package edu.luc.etl.node;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class RegistryTest {
	
	protected Registry registry;
	
	@Before
	public void setUp() {
		registry = new Registry();
	}
	
	@Test
	public void heartBeatFromNewNode() {
		INode node = new RemoteNodeProxy(UUID.randomUUID().toString(), "127.0.0.1");
		registry.heartbeatFromNode(node);
		
		List<INode> nodes = registry.getNodes();
		
        assertEquals(1, nodes.size());
		
		INode node1 = nodes.get(0);
		
		//I think for now it's fine to assume that these are just logically
		//equivalent and not identical in case there's some immutable/cloning
		//tricks going on later for easier multithreading
		assertEquals(node, node1);
	}
	
	@Test
	public void heartBeatFromExistingNode() {
		INode node = new RemoteNodeProxy(UUID.randomUUID().toString(), "127.0.0.1");
		long currentTime = System.currentTimeMillis();
		node.setLastHeartbeatTime(currentTime);
		
		registry.heartbeatFromNode(node);
		
		List<INode> nodes = registry.getNodes();
		assertEquals(node, nodes.get(0));
		
		//create new, logically equivalent node, with a later time
		currentTime = currentTime += 5000;
		node = new RemoteNodeProxy(node.getId().toString(), node.getIp().getHostAddress());
		
		registry.heartbeatFromNode(node);
		
        assertEquals(1, registry.getNodes().size());
		assertEquals(node, registry.getNodes().get(0));		
	}	
	
	@Test
	public void testReapDeadNodes() {
		INode node1 = new RemoteNodeProxy(UUID.randomUUID().toString(), "127.0.0.1");
		INode node2 = new RemoteNodeProxy(UUID.randomUUID().toString(), "127.0.0.1");
		
		node1.setLastHeartbeatTime(System.currentTimeMillis()-400000000); //set node 1 way in the past
		node2.setLastHeartbeatTime(System.currentTimeMillis()+400000000); //set node 2 way in the future
		
		registry.heartbeatFromNode(node1);
		registry.heartbeatFromNode(node2);
		
		//after reaping, only node 1 should survive
		registry.reapDeadNodes();
		
		List<INode> nodes = registry.getNodes();
		
		assertEquals(1, nodes.size());
		assertEquals(node2, nodes.get(0));		
	}
}
