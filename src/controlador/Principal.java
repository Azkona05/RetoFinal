package controlador;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.toedter.calendar.JDayChooser;

import excepciones.LoginException;
import modelo.Competicion;
import modelo.Partido;
import modelo.Usuario;
import vista.VMenuPrincipal;

public class Principal {

	private static InterfazDao dao = new DaoImplementacion();

	public static void main(String[] args) throws LoginException {
		VMenuPrincipal vmp = new VMenuPrincipal();
		vmp.setVisible(true);
	}

	public static void login(Usuario usuario) throws LoginException {
		dao.login(usuario);
		}
	public static List<Partido> buscarEquiLiga (Competicion liga) {
		return dao.buscarEquiLiga(liga);
	}

	public static Map<String, Competicion> leerCompeticiones() {
		return dao.listarCompeticiones();
	}
	public static List<String> devolverEquipos(Competicion liga) {
		return dao.buscarDifEquipo(liga);
	}
	public static List<Partido> devolverPartidos(LocalDate fecha) {
		return dao.devolverPartidos(fecha);
	}
	
}
