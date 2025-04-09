package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JCheckBox;

public class VMenuPrincipal extends JFrame implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;
	private JButton btnLogin, btnCalendario;
	private JCalendar calendario;
	private JTable tablaClasi, tablaPart;
	private JPanel panel_Derecho, panel_Izquierdo;
	private JComboBox<Competicion> cbElegirLiga;
	private JScrollPane jscroll, jscrollPartido;
	private LocalDate fecha;

	public VMenuPrincipal() throws LoginException {
		getContentPane().setForeground(new Color(0, 0, 160));
		setForeground(new Color(0, 0, 64));
		setTitle("FUTBOL AMERICANO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 524);

		// Panel Superior
		JPanel panel_Superior = new JPanel();
		panel_Superior.setToolTipText("");
		panel_Superior.setForeground(new Color(255, 255, 255));
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
		if (fecha==null) {
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
		jscrollPartido.setViewportView(tablaPart);
	}

	private JTable cargarTablaPart(LocalDate fecha) throws LoginException {
		String[] columnasNombre = { "Liga", "Local", "Visitante", "Ganador" };
		DefaultTableModel model = new DefaultTableModel(null, columnasNombre);
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

	private void presentarTabla(Competicion liga) throws LoginException {
		// cargarTabla (prop);
		// jscroll = new JScrollPane();
		tablaClasi = this.cargarTabla(liga);
		jscroll.setViewportView(tablaClasi);
		// panel_2.add(jscroll);
		// jscroll.setBounds(5, 5, 150, 150);
	}

	private JTable cargarTabla(Competicion liga) throws LoginException {
		String[] coulumnasNombre = { "Posicion", "Nombre", "Victorias" };
		String[] colum = new String[3];
		DefaultTableModel model = new DefaultTableModel(null, coulumnasNombre);
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
//	private JTable cargarTabla(Competicion liga) {
//		String[] coulumnasNombre = { "Posicion", "Nombre", "Victorias" };
//		String[] colum = new String[3];
//		DefaultTableModel model = new DefaultTableModel(null, coulumnasNombre);
//		List<Partido> partidos = Principal.buscarEquiLiga(liga);
//		List<String> diferentesEquipos = Principal.devolverEquipos(liga);
//		Map<String, String> orden = new TreeMap<>(Comparator.reverseOrder());
//		int cont = 0;
//		for (String equipo : diferentesEquipos) {
//			cont = 0;
//			for (Partido p : partidos) {
//				if (equipo.equals(p.getGanador())) {
//					cont++;
//				}
//			}
//			orden.put(cont + "-" + equipo, equipo);
//		}
//
//		cont = 1;
//		for (Map.Entry<String, String> entry : orden.entrySet()) {
//			colum[0] = String.valueOf(cont);
//			colum[2] = entry.getKey().split("-")[0];
//			colum[1] = entry.getValue();
//			model.addRow(colum);
//			cont++;
//		}
//
//		return new JTable(model);
//	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {

	}
}