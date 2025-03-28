package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.EnumPosicion;
import modelo.Jugador;

class TestJugador {

	Jugador juga;
	@BeforeEach
	void setUp() throws Exception {
		juga = new Jugador();
		juga.setDni("16093711J");
		juga.setNombre("Nora");
		juga.setApellido("Yakoubi");
		juga.setDorsal(0);
		juga.setPosicion(EnumPosicion.QUARTERBACK);
		juga.setCod_equi("PAT");
	}
	
	@Test
	public void testGetDni() {
		assertEquals("16093711J", juga.getDni());
	}
	
	@Test
	public void testSetDni() {
		juga.setDni("11111111J");
		assertEquals("11111111J", juga.getDni());
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Nora", juga.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		juga.setNombre("Sara");
		assertEquals("Sara", juga.getNombre());
	}
	
	@Test
	public void testGetApellido() {
		assertEquals("Yakoubi", juga.getApellido());
	}
	
	@Test
	public void testSetApellido() {
		juga.setApellido("Martinez");
		assertEquals("Martinez", juga.getApellido());
	}
	
	@Test
	public void testGetDorsal() {
		assertEquals(0, juga.getDorsal());
	}
	
	@Test
	public void testSetDorsal() {
		juga.setDorsal(1);
		assertEquals(1, juga.getDorsal());
	}
	
	@Test
	public void testGetPosicion() {
		assertEquals(EnumPosicion.QUARTERBACK, juga.getPosicion());
	}
	
	@Test
	public void testSetPosicion() {
		juga.setPosicion(EnumPosicion.GUARD);
		assertEquals(EnumPosicion.GUARD, juga.getPosicion());
	}
	
	@Test
	public void testGetCodEquipo() {
		assertEquals("PAT", juga.getCod_equi());
	}
	
	@Test
	public void testSetCodEquipo() {
		juga.setCod_equi("JET");
		assertEquals("JET", juga.getCod_equi());
	}
	
	@Test
	public void testToString() {
		String jugad;
		jugad = juga.toString();
		assertEquals("Jugador [dni=16093711J, nombre=Nora, apellido=Yakoubi, dorsal=0, posicion=QUARTERBACK]",juga.toString());
	}
	
	@AfterEach
	void tearDown() throws Exception {
		juga=null;
	}

	

}
