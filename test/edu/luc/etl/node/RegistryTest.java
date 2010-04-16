package edu.luc.etl.node;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RegistryTest {
	
	protected Registry registry;
	
	@Before
	public void setUp() {
		registry = new Registry();
	}
	
	@Test
	public void heartBeatFromNode() {
		assertTrue(true);
	}
}
