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
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Competicion;
import modelo.Equipo;
import modelo.Partido;

import java.awt.Color;

import javax.swing.JTable;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import java.awt.Toolkit;
import java.awt.Font;

/**
 * Clase que representa la ventana principal del menú de la aplicación de fútbol
 * americano. Muestra un calendario, tablas de clasificación y partidos, y
 * permite la navegación entre diferentes competiciones.
 * 
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 * @version 1.0
 */
public class VMenuPrincipal extends JFrame implements ActionListener, FocusListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JButton btnLogin, btnCalendario;
	private JCalendar calendario;
	private JTable tablaClasi, tablaPart;
	private JComboBox<Competicion> cbElegirLiga;
	private JScrollPane jscrollClasi, jscrollPartido;
	private LocalDate fecha;

	/**
	 * Constructor de la ventana principal. Inicializa los componentes y configura
	 * la interfaz gráfica.
	 * 
	 * @throws LoginException Si ocurre un error durante la inicialización
	 *                        relacionado con el login
	 */
	public VMenuPrincipal() throws LoginException {
		setResizable(false);
		setTitle("FUTBOL AMERICANO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		// ComboBox de Ligas
		cbElegirLiga = new JComboBox<Competicion>();
		cbElegirLiga.setBounds(10, 10, 400, 30);
		cbElegirLiga.addActionListener(this);
		getContentPane().add(cbElegirLiga);

		// Label de equipos
		JLabel lblEquipo = new JLabel("Equipos");
		lblEquipo.setFont(new Font("Stencil", Font.BOLD, 12));
		lblEquipo.setForeground(new Color(255, 255, 255));
		lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo.setBounds(500, 12, 201, 29);
		getContentPane().add(lblEquipo);

		// Botón Login
		btnLogin = new JButton("Login");
		btnLogin.setBounds(906, 10, 150, 30);
		btnLogin.addActionListener(this);
		btnLogin.setBackground(Color.WHITE);
		getContentPane().add(btnLogin);

		// Calendario
		calendario = new JCalendar();
		calendario.setDecorationBackgroundColor(new Color(255, 255, 255));
		calendario.getMonthChooser().getSpinner().setBackground(new Color(255, 255, 255));
		calendario.setNullDateButtonText("");
		calendario.getDayChooser().setWeekOfYearVisible(false);
		calendario.getDayChooser().getDayPanel().setBackground(new Color(255, 255, 255));
		calendario.getDayChooser().setForeground(new Color(0, 128, 128));
		calendario.getDayChooser().setWeekdayForeground(new Color(0, 128, 128));
		calendario.getDayChooser().setBackground(new Color(0, 128, 128));
		calendario.getDayChooser().setDecorationBackgroundColor(new Color(255, 255, 128));
		calendario.setBounds(500, 115, 400, 350);
		getContentPane().add(calendario);

		// Botón actualizar fecha
		btnCalendario = new JButton("Actualizar Fecha");
		btnCalendario.setBounds(906, 435, 150, 30);
		btnCalendario.addActionListener(this);
		btnCalendario.setBackground(Color.WHITE);
		getContentPane().add(btnCalendario);

		// Scroll de partidos
		jscrollPartido = new JScrollPane();
		jscrollPartido.setBounds(497, 509, 460, 101);
		getContentPane().add(jscrollPartido);
		if (fecha == null) {
			presentarTablaPartido(LocalDate.now());
		}

		// Scroll de clasificación
		jscrollClasi = new JScrollPane();
		jscrollClasi.setBounds(10, 60, 400, 550); // Izquierda
		getContentPane().add(jscrollClasi);

		// Cargar datos iniciales
		Map<String, Competicion> competiciones = Principal.leerCompeticiones();
		for (Competicion comp : competiciones.values()) {
			cbElegirLiga.addItem(comp);
		}
		presentarTabla((Competicion) cbElegirLiga.getSelectedItem());
				
		JLabel lblFondo = new JLabel();
		lblFondo.setIcon(new ImageIcon("src/resources/fondoIcono(re).jpg"));
		lblFondo.setBounds(0, 0, 1080, 720);
		getContentPane().add(lblFondo);
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
		tablaPart.setBounds(0, 0, 15, 15);
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
			fila[0] = part.getCod_comp().toUpperCase();
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
		tablaClasi.setBounds(0, 10, 75, 75);
		tablaClasi.addMouseListener(this);

		jscrollClasi.setViewportView(tablaClasi);
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
			if (e.getClickCount() == 2) {
				int row = tablaClasi.getSelectedRow();
				if (row != -1) {
					String nombreEquipo = (String) tablaClasi.getValueAt(row, 1);
					System.out.println(nombreEquipo);
					Equipo equi = Principal.devolverEquiNombre(nombreEquipo);
					
					System.out.println("Equipo seleccionado: " + equi);
					try {
						VEquipo ve;
						ve = new VEquipo(this, true, equi);
						ve.setVisible(true);
					} catch (LoginException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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