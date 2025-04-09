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

import javax.swing.ImageIcon;
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
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JCheckBox;

public class VMenuPrincipal extends JFrame implements ActionListener, FocusListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JButton btnLogin, btnCalendario;
	private JCalendar calendario;
	private JTable tablaClasi, tablaPart;
	private JPanel panel_Derecho, panel_Izquierdo, panel_Central;
	private JComboBox<Competicion> cbElegirLiga;
	private JScrollPane jscroll, jscrollPartido;
	private LocalDate fecha;


	public VMenuPrincipal() throws LoginException {
		setTitle("FUTBOL AMERICANO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1140, 462);
		getContentPane().setLayout(null);
		

		// ComboBox de Ligas
		cbElegirLiga = new JComboBox<Competicion>();
		cbElegirLiga.setBounds(10, 10, 400, 30);
		cbElegirLiga.addActionListener(this);
		getContentPane().add(cbElegirLiga);

		// Label de equipos
		JLabel lblEquipo = new JLabel("Equipos");
		lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo.setBounds(418, 10, 400, 30);
		getContentPane().add(lblEquipo);

		// Botón Login
		btnLogin = new JButton("Login");
		btnLogin.setBounds(828, 10, 288, 30);
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
		calendario.setBounds(500, 60, 400, 225);
		getContentPane().add(calendario);

		// Botón actualizar fecha
		btnCalendario = new JButton("Actualizar Fecha");
		btnCalendario.setBounds(910, 255, 203, 30);
		btnCalendario.addActionListener(this);
		btnCalendario.setBackground(Color.WHITE);
		getContentPane().add(btnCalendario);

		// Scroll de partidos
		jscrollPartido = new JScrollPane();
		jscrollPartido.setBounds(497, 306, 460, 101); // Derecha
		getContentPane().add(jscrollPartido);
		if (fecha == null) {
			presentarTablaPartido(LocalDate.now());
		}

		// Scroll de clasificación
		jscroll = new JScrollPane();
		jscroll.setBounds(10, 60, 400, 347); // Izquierda
		getContentPane().add(jscroll);

		// Cargar datos iniciales
		Map<String, Competicion> competiciones = Principal.leerCompeticiones();
		for (Competicion comp : competiciones.values()) {
			cbElegirLiga.addItem(comp);
		}
		presentarTabla((Competicion) cbElegirLiga.getSelectedItem());
	}

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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {

		}
	}

	private void presentarTablaPartido(LocalDate fecha) throws LoginException {
		tablaPart = this.cargarTablaPart(fecha);
		tablaPart.setBounds(0, 0, 15, 15);
		jscrollPartido.setViewportView(tablaPart);
	}

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

	private void presentarTabla(Competicion liga) throws LoginException {
		tablaClasi = this.cargarTabla(liga);
		tablaClasi.setBounds(0, 10, 75, 75);
		tablaClasi.addMouseListener(this);

		jscroll.setViewportView(tablaClasi);
	}

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

	}

	@Override
	public void focusLost(FocusEvent e) {

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