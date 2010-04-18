package edu.luc.etl.node;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class RemoteNodeProxyTest {

	protected String uuid1 = "cf77e02b-708d-4069-991e-d2d600b06e17";
	protected String uuid2 = "4715943e-2c96-4207-8fde-8816366b52a1";
	protected InetAddress localhost;
	
	protected RemoteNodeProxy node1;
	protected RemoteNodeProxy node2;
	
	@Before
	public void setUp() throws Exception {
		localhost = InetAddress.getByName("127.0.0.1");
		
		node1 = new RemoteNodeProxy(uuid1, "127.0.0.1");
		node2 = new RemoteNodeProxy(uuid2, "127.0.0.1");
		
	}

	@Test
	public void testGetIp() {
		assertEquals(localhost, node1.getIp());
		assertEquals(localhost, node2.getIp());
	}

	@Test
	public void testGetId() {
		UUID uuidObj1 = UUID.fromString(uuid1);
		UUID uuidObj2 = UUID.fromString(uuid2);
		
		assertEquals(uuidObj1, node1.getId());
		assertEquals(uuidObj2, node2.getId());
	}
	
	@Test
	public void testEquals() {
		RemoteNodeProxy node1Equivalent = new RemoteNodeProxy(uuid1, "127.0.0.1");
		
		assertTrue(node1.equals(node1Equivalent));
		assertTrue(node1Equivalent.equals(node1));
	}

	@Test
	public void testSetGetHeartbeat() {
		long currentTime = System.currentTimeMillis();
		
		node1.setLastHeartbeatTime(currentTime);
		
		assertEquals(currentTime, node1.getLastHeartbeatTime());
	}
}
