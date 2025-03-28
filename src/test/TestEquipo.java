package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Equipo;

class TestEquipo {

	Equipo equi;
	@BeforeEach
	void setUp() throws Exception {
		equi = new Equipo ();
		equi.setCod_equi("PAT");
		equi.setNombre_equipo("PATRIOTS");
	}

	@Test
	public void testGetCodEquipo() {
		assertEquals("PAT", equi.getCod_equi());
	}
	
	@Test
	public void testSetCodEquipo() {
		equi.setCod_equi("JET");
		assertEquals("JET", equi.getCod_equi());
	}
	
	@Test
	public void testGetNombreEquipo() {
		assertEquals("PATRIOTS",equi.getNombre_equipo());
	}
	
	@Test
	public void testSetNombreEquipo() {
		equi.setNombre_equipo("JETS");
		assertEquals("JETS",equi.getNombre_equipo());
	}
	
	@Test
	public void testToString() {
		String equip;
		equip = equi.toString();
		assertEquals("Equipo [cod_equi=PAT, nombre_equipo=PATRIOTS]",equi.toString());
	}
	
	@AfterEach
	void tearDown() throws Exception {
		equi=null;
	}
	

	

}
