package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Usuario;

class TestUsuario {

	Usuario usu;
	@BeforeEach
	void setUp() throws Exception {
		usu= new Usuario();
		usu.setNombre("noyakoubi@gmail.com");
		usu.setPassword("Nora123");
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("noyakoubi@gmail.com", usu.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		usu.setNombre("norayakoubi@hotmail.com");
	}

	@Test
	public void testGetPassword() {
		assertEquals("Nora123", usu.getPassword());
	}
	
	@Test
	public void testSetPassword() {
		usu.setPassword("Nora456");
		assertEquals("Nora456", usu.getPassword());
	}
	
	@Test
	public void TestToString() {
		String usua;
		usua= usu.toString();
		assertEquals("USUARIO: Nombre=noyakoubi@gmail.com, Password=Nora123", usu.toString());
		
	}
	
	
	@AfterEach
	void tearDown() throws Exception {
	}

	
}
