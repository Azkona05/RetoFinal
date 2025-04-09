package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Competicion;
import modelo.Equipo;
import modelo.Partido;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTable;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

/**
 * Clase que representa la ventana principal del menú de la aplicación de fútbol
 * americano. Muestra un calendario, tablas de clasificación y partidos, y
 * permite la navegación entre diferentes competiciones.
 * 
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 * @version 1.0
 */
public class VMenuPrincipal extends JFrame implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;
	private JButton btnLogin, btnCalendario;
	private JCalendar calendario;
	private JTable tablaClasi, tablaPart;
	private JPanel panel_Derecho, panel_Izquierdo;
	private JComboBox<Competicion> cbElegirLiga;
	private JScrollPane jscroll, jscrollPartido;
	private LocalDate fecha;

	/**
	 * Constructor de la ventana principal. Inicializa los componentes y configura
	 * la interfaz gráfica.
	 * 
	 * @throws LoginException Si ocurre un error durante la inicialización
	 *                        relacionado con el login
	 */
	public VMenuPrincipal() throws LoginException {
		setTitle("FUTBOL AMERICANO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 428);

		// Panel Superior
		JPanel panel_Superior = new JPanel();
		getContentPane().add(panel_Superior, BorderLayout.NORTH);
		panel_Superior.setLayout(new GridLayout(1, 0, 0, 0));

		cbElegirLiga = new JComboBox<Competicion>();
		panel_Superior.add(cbElegirLiga);
		cbElegirLiga.addActionListener(this);

		JLabel lblEquipo = new JLabel("Equipos");
		lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Superior.add(lblEquipo);

		btnLogin = new JButton("Login");
		panel_Superior.add(btnLogin);
		btnLogin.addActionListener(this);
		btnLogin.setBackground(Color.WHITE);

		// Panel Izqu.
		panel_Izquierdo = new JPanel();
		getContentPane().add(panel_Izquierdo, BorderLayout.EAST);
		jscrollPartido = new JScrollPane();
		panel_Izquierdo.add(jscrollPartido);
		if (fecha == null) {
			presentarTablaPartido(LocalDate.now());
		}

		// Panel Central
		JPanel panel_Central = new JPanel();
		getContentPane().add(panel_Central, BorderLayout.CENTER);
		calendario = new JCalendar();
		panel_Central.add(calendario);

		btnCalendario = new JButton();
		btnCalendario.setText("Actualizar Fecha");
		panel_Central.add(btnCalendario);
		btnCalendario.addActionListener(this);
		btnCalendario.setBackground(Color.WHITE);

		// Panel Derecho
		panel_Derecho = new JPanel();
		getContentPane().add(panel_Derecho, BorderLayout.WEST);
		jscroll = new JScrollPane();
		panel_Derecho.add(jscroll);

		Map<String, Competicion> competiciones;
		competiciones = Principal.leerCompeticiones();
		for (Competicion comp : competiciones.values()) {
			cbElegirLiga.addItem(comp);
		}
		presentarTabla((Competicion) cbElegirLiga.getSelectedItem());

	}

	/**
	 * Maneja los eventos de acción de los componentes de la interfaz.
	 * 
	 * @param e El evento de acción que se ha producido
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(calendario)) {

		} else if (e.getSource().equals(btnLogin)) {
			VLogin vL = new VLogin(this, true);
			vL.setVisible(true);
		} else if (e.getSource().equals(cbElegirLiga)) {
			try {
				presentarTabla((Competicion) cbElegirLiga.getSelectedItem());
			} catch (LoginException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(btnCalendario)) {
			Calendar calendar = calendario.getCalendar();
			LocalDate fecha;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int month = calendar.get(Calendar.MONTH) + 1;
			int year = calendar.get(Calendar.YEAR);
			fecha = LocalDate.of(year, month, day);
			try {
				presentarTablaPartido(fecha);
			} catch (LoginException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Muestra la tabla de partidos para una fecha específica.
	 * 
	 * @param fecha La fecha para la que se mostrarán los partidos
	 * @throws LoginException Si ocurre un error relacionado con el login
	 */
	private void presentarTablaPartido(LocalDate fecha) throws LoginException {
		tablaPart = this.cargarTablaPart(fecha);
		jscrollPartido.setViewportView(tablaPart);
	}

	/**
	 * Carga y devuelve una tabla con los partidos de una fecha específica.
	 * 
	 * @param fecha La fecha de los partidos a cargar
	 * @return JTable con los partidos de la fecha especificada
	 * @throws LoginException Si ocurre un error relacionado con el login
	 */
	private JTable cargarTablaPart(LocalDate fecha) throws LoginException {
		String[] columnasNombre = { "Liga", "Local", "Visitante", "Ganador" };
		DefaultTableModel model = new DefaultTableModel(null, columnasNombre) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Bloquea la edición de todas las celdas
			}
		};
		List<Partido> partidos = Principal.devolverPartidos(fecha);
		for (Partido part : partidos) {
			String[] fila = new String[4];
			fila[0] = part.getCod_comp();
			fila[1] = part.getEquipo_local();
			fila[2] = part.getEquipo_visitante();
			if (part.getGanador() == null) {
				part.setGanador("PSD");
				fila[3] = part.getGanador();
			} else {
				fila[3] = part.getGanador();
			}
			model.addRow(fila);
		}
		return new JTable(model);
	}

	/**
	 * Muestra la tabla de clasificación para una competición específica.
	 * 
	 * @param liga La competición para la que se mostrará la clasificación
	 * @throws LoginException Si ocurre un error relacionado con el login
	 */
	private void presentarTabla(Competicion liga) throws LoginException {
		tablaClasi = this.cargarTabla(liga);

		tablaClasi.addMouseListener(this);

		jscroll.setViewportView(tablaClasi);
	}

	/**
	 * Carga y devuelve una tabla con la clasificación de una competición.
	 * 
	 * @param liga La competición para la que se cargará la clasificación
	 * @return JTable con la clasificación de la competición especificada
	 * @throws LoginException Si ocurre un error relacionado con el login
	 */
	private JTable cargarTabla(Competicion liga) throws LoginException {
		String[] coulumnasNombre = { "Posicion", "Nombre", "Victorias" };
		String[] colum = new String[3];
		DefaultTableModel model = new DefaultTableModel(null, coulumnasNombre) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Bloquea la edición de todas las celdas
			}
		};
		List<Partido> partidos = Principal.buscarEquiLiga(liga);
		List<Equipo> diferentesEquipos = Principal.devolverEquipos(liga);
		Map<String, Equipo> orden = new TreeMap<>(Comparator.reverseOrder());
		int cont = 0;
		for (Equipo equipo : diferentesEquipos) {
			cont = 0;
			for (Partido p : partidos) {
				if (equipo.getCod_equi().equals(p.getGanador())) {
					cont++;
				}
			}
			orden.put(cont + "-" + equipo, equipo);
		}

		cont = 1;
		for (Map.Entry<String, Equipo> entry : orden.entrySet()) {
			colum[0] = String.valueOf(cont);
			colum[1] = entry.getValue().getNombre_equipo();
			colum[2] = entry.getKey().split("-")[0];
			model.addRow(colum);
			cont++;
		}

		return new JTable(model);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tablaClasi)) {
			if (e.MOUSE_CLICKED == 2) {
				int row = tablaClasi.getSelectedRow();
				if (row != -1) {
					String nombreEquipo = (String) tablaClasi.getValueAt(row, 1); // Columna del nombre
					System.out.println("Equipo seleccionado: " + nombreEquipo);

					// Suponiendo que VEquipo tiene constructor (Frame, boolean, String)
					VEquipo ve = new VEquipo(this, true, nombreEquipo);
					ve.setVisible(true);
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public boolean celdaEditable(int row, int colum) {
		return false;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}