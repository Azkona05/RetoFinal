package test;


import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import modelo.Partido;

class TestPartido {

	Partido part;
	@BeforeEach
	void setUp() throws Exception {
		part=new Partido();
		part.setCod_part(0);
		part.setEquipo_local("PAW");
		part.setEquipo_visitante("JET");
		part.setGanador("JET");
		part.setFecha(LocalDate.parse("2025-01-12"));
		part.setCod_comp("NAC");
	}

	@Test
	public void testGetCodPart() {
		assertEquals(0, part.getCod_part());
	}
	
	@Test
	public void testSetCodPart() {
		part.setCod_part(1);
		assertEquals(1, part.getCod_part());
	}
	
	@Test
	public void testGetEquipoLocal() {
		assertEquals("PAW", part.getEquipo_local());
	}
	
	@Test
	public void testSetEquipoLocal() {
		part.setEquipo_local("Madrid");
		assertEquals("Madrid", part.getEquipo_local());
	}
	
	@Test
	public void testGetEquipoVisitante() {
		assertEquals("JET", part.getEquipo_visitante());
	}
	
	@Test
	public void testSetEquipoVisitante() {
		part.setEquipo_visitante("Barca");
		assertEquals("Barca", part.getEquipo_visitante());
	}
	
	@Test
	public void testGetGanador() {
		assertEquals("JET", part.getGanador());
	}
	
	@Test
	public void testGetFecha() {
		assertEquals(LocalDate.parse("2025-01-12"), part.getFecha());
	}
	
	@Test
	public void testSetFecha() {
		part.setFecha(LocalDate.parse("2024-01-12"));
		assertEquals(LocalDate.parse("2024-01-12"), part.getFecha());
	}
	
	@Test
	public void testGetCodComp() {
		assertEquals("NAC", part.getCod_comp());
	}
	
	@Test
	public void testSetCodComp() {
		part.setCod_comp("AME");
		assertEquals("AME", part.getCod_comp());
	}
	
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		String parti;
		parti = part.toString();
		assertEquals("Partido [cod_part=0, equipo_local=PAW, equipo_visitante=JET, ganador=VISITANTE, fecha=2025-01-12]",part.toString());
	}

}
