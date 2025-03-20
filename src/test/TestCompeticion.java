package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Competicion;

class TestCompeticion {
	Competicion comp;
	@BeforeEach
	void setUp() throws Exception {
		comp = new Competicion ("AME", "AMERICANA");
	}
	
	@Test
	public void testGetCodCompeticion() {
		assertEquals("AME", comp.getCod_comp());
	}
	
	@Test
	public void testSetCodCompeticion() {
		comp.setCod_comp("ABC");
		assertEquals("ABC", comp.getCod_comp());
	}

	@AfterEach
	void tearDown() throws Exception {
		comp = null;
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
