package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import modelo.Competicion;

class TestCompeticion {
	Competicion comp;
	@BeforeEach
	void setUp() throws Exception {
		comp = new Competicion ("AME", "AMERICANA");
	}
	
	@Test
	public void testCompeticion() {
		Competicion compe;
		compe= new Competicion ();
		assertNull(compe.getCod_comp());
		assertNull(compe.getNombre_competicion());
	}
	
	@Test
	public void testCompeticionParametros() {
		assertEquals("AME",comp.getCod_comp());
		assertEquals("AMERICANA",comp.getNombre_competicion());
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
	
	@Test
	public void testGetNombreCompeticion() {
		assertEquals("AMERICANA",comp.getNombre_competicion());
	}
	
	@Test
	public void testSetNombreCompeticion() {
		comp.setNombre_competicion("NACIONAL");
		assertEquals("NACIONAL",comp.getNombre_competicion());
	}
	
	@Test
	public void testToString() {
		String compet;
		compet = comp.toString();
		assertEquals("Competicion [cod_comp=AME, nombre_competicion=AMERICANA]",comp.toString());
	}
	
	@AfterEach
	void tearDown() throws Exception {
		comp = null;
	}

	

}
