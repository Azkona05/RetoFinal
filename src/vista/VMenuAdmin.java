
package vista;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import controlador.Principal;
import excepciones.DniException;
import excepciones.LoginException;
import modelo.Competicion;
import modelo.EnumPosicion;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

/**
 * Diálogo de administración para un sistema de futbol americano. Proporciona
 * funcionalidades para gestionar jugadores, equipos, competiciones y partidos.
 * 
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 * @version 1.0
 */
public class VMenuAdmin extends JDialog implements ActionListener {

	// Componentes de la interfaz
	private static final long serialVersionUID = 1L;

	/** Panel con pestañas que contiene todos los paneles de gestión */
	private JTabbedPane tabbedPane;

	/** Paneles de gestión */
	private JPanel panelJugador, panelCompeticion, panelEquipos, panelPartidos, panelCargar;

	// Botones de gestión.
	private JButton btnAltaJug, btnBajaJug, btnModificarJug, btnCargarJug, btnLimpiarDatosJug;
	private JButton btnAltaPart, btnBajaPart, btnModificarPart, btnCargarPart, btnLimpiarDatosPart;
	private JButton btnAltaComp, btnBajaComp, btnModificarComp, btnCargarComp, btnLimpiarDatosComp;
	private JButton btnAltaEq, btnBajaEq, btnModificarEq, btnCargarEq, btnLimpiarDatosEq;

	// Campos de texto
	private JTextField txtDni, txtNombre, txtApellido, txtDorsal, txtNombreEq, txtCodComp, txtNombreComp,
			txtCodEquipo_Equipo;

	// Grupo de botones
	private ButtonGroup grupoPosicion;

	// Radio botones
	private JRadioButton rdbtnGuard, rdbtnQuarterback, rdbtnRunning, rdbtnTackle;

	// Combo box
	private JComboBox<Competicion> cbLiga;
	private JComboBox<Equipo> cbLocal, cbVisitante, cbCodEqui_J;
	private JComboBox<String> cbGanador;
	private Competicion comp;
	private Equipo equi;
	private JTextField txtCodPar;
	private JLabel lblPosicion;
	private JLabel lblFecha;
	private JTextField txtFecha;

	// Labels
	private JLabel lblCodigo;

	/**
	 * Constructor que crea el diálogo de administración.
	 * 
	 * @param padre Ventana padre (login)
	 * @param modal Indica si el diálogo es modal
	 */
	public VMenuAdmin(VLogin padre, boolean modal) {
		super(padre);
		setTitle("Gestion Administrador");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icono.jpg")));
		setBounds(100, 100, 600, 450);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(modal);
		this.setResizable(false);

		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		// Panel Consultar
		panelCargar = new JPanel();
		panelCargar.setLayout(null);
		tabbedPane.addTab("Consultar", panelCargar);

		btnCargarJug = new JButton("Jugadores");
		btnCargarJug.setIcon(new ImageIcon(getClass().getResource("/resources/iconoJugadorEditado.png")));
		btnCargarJug.setBounds(25, 52, 242, 78);
		panelCargar.add(btnCargarJug);
		btnCargarJug.addActionListener(this);
		btnCargarJug.setBackground(Color.WHITE);

		btnCargarEq = new JButton("Equipos");
		btnCargarEq.setIcon(new ImageIcon(getClass().getResource("/resources/iconoEquipoEditado.png")));
		btnCargarEq.setBounds(292, 52, 242, 78);
		panelCargar.add(btnCargarEq);
		btnCargarEq.addActionListener(this);
		btnCargarEq.setBackground(Color.WHITE);

		btnCargarComp = new JButton("Competiciones");
		btnCargarComp.setIcon(new ImageIcon(getClass().getResource("/resources/iconoCompeticionEditado.jpg")));
		btnCargarComp.setBounds(25, 229, 242, 78);
		panelCargar.add(btnCargarComp);
		btnCargarComp.addActionListener(this);
		btnCargarComp.setBackground(Color.WHITE);

		btnCargarPart = new JButton("Partidos");
		btnCargarPart.setIcon(new ImageIcon(this.getClass().getResource("/resources/iconoPartidoEditado.png")));
		btnCargarPart.setBounds(292, 231, 242, 76);
		panelCargar.add(btnCargarPart);
		btnCargarPart.addActionListener(this);
		btnCargarPart.setBackground(Color.WHITE);

		// Panel Jugador
		panelJugador = new JPanel();
		panelJugador.setLayout(null);
		tabbedPane.addTab("Jugador", panelJugador);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(50, 30, 100, 30);
		panelJugador.add(lblDni);

		txtDni = new JTextField(10);
		txtDni.setBounds(200, 30, 150, 30);
		panelJugador.add(txtDni);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(50, 70, 100, 30);
		panelJugador.add(lblNombre);

		txtNombre = new JTextField(10);
		txtNombre.setBounds(200, 70, 150, 30);
		panelJugador.add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellido.setBounds(50, 110, 100, 30);
		panelJugador.add(lblApellido);

		txtApellido = new JTextField(10);
		txtApellido.setBounds(200, 110, 150, 30);
		panelJugador.add(txtApellido);

		JLabel lblDorsal = new JLabel("Dorsal:");
		lblDorsal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDorsal.setBounds(50, 151, 150, 30);
		panelJugador.add(lblDorsal);

		txtDorsal = new JTextField();
		txtDorsal.setBounds(200, 150, 150, 30);
		panelJugador.add(txtDorsal);

		JLabel lblCodEquipo = new JLabel("Codigo de Equipo:");
		lblCodEquipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodEquipo.setBounds(50, 253, 100, 30);
		panelJugador.add(lblCodEquipo);

		grupoPosicion = new ButtonGroup();

		rdbtnGuard = new JRadioButton("Guard");
		rdbtnGuard.setBounds(200, 186, 111, 23);
		grupoPosicion.add(rdbtnGuard);
		panelJugador.add(rdbtnGuard);

		rdbtnQuarterback = new JRadioButton("Quarterback");
		rdbtnQuarterback.setBounds(325, 186, 111, 23);
		grupoPosicion.add(rdbtnQuarterback);
		panelJugador.add(rdbtnQuarterback);

		rdbtnRunning = new JRadioButton("Running");
		rdbtnRunning.setBounds(200, 208, 111, 23);
		grupoPosicion.add(rdbtnRunning);
		panelJugador.add(rdbtnRunning);

		rdbtnTackle = new JRadioButton("Tackle");
		rdbtnTackle.setBounds(325, 208, 111, 23);
		grupoPosicion.add(rdbtnTackle);
		panelJugador.add(rdbtnTackle);

		btnAltaJug = new JButton("Alta Jugador");
		btnAltaJug.setBounds(50, 327, 134, 23);
		panelJugador.add(btnAltaJug);
		btnAltaJug.addActionListener(this);
		btnAltaJug.setBackground(Color.WHITE);

		btnBajaJug = new JButton("Baja jugador");
		btnBajaJug.setBounds(211, 327, 134, 23);
		panelJugador.add(btnBajaJug);
		btnBajaJug.addActionListener(this);
		btnBajaJug.setBackground(Color.WHITE);

		btnModificarJug = new JButton("Modificar Jugador");
		btnModificarJug.setBounds(380, 327, 134, 23);
		panelJugador.add(btnModificarJug);
		btnModificarJug.addActionListener(this);
		btnModificarJug.setBackground(Color.WHITE);

		btnLimpiarDatosJug = new JButton("Limpiar datos");
		btnLimpiarDatosJug.setBounds(380, 259, 134, 23);
		panelJugador.add(btnLimpiarDatosJug);
		btnLimpiarDatosJug.addActionListener(this);
		btnLimpiarDatosJug.setBackground(Color.WHITE);

		lblPosicion = new JLabel("Posicion:");
		lblPosicion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPosicion.setBounds(50, 190, 100, 30);
		panelJugador.add(lblPosicion);

		btnBajaJug.setEnabled(false);
		btnModificarJug.setEnabled(false);

		cbCodEqui_J = new JComboBox<Equipo>();
		cbCodEqui_J.setBounds(200, 260, 145, 21);
		panelJugador.add(cbCodEqui_J);
		List<Equipo> equipos = null;
		try {
			equipos = Principal.devolverEquipos();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		for (Equipo e : equipos) {
			cbCodEqui_J.addItem(e);
		}
		equi = (Equipo) cbCodEqui_J.getSelectedItem();
		cbCodEqui_J.setSelectedIndex(-1);
		cbCodEqui_J.addActionListener(this);

		// Panel Equipo
		panelEquipos = new JPanel();
		panelEquipos.setLayout(null);
		tabbedPane.addTab("Equipos", panelEquipos);

		JLabel lblCodEqu = new JLabel("Codigo de Equipo:");
		lblCodEqu.setBounds(29, 90, 149, 14);
		panelEquipos.add(lblCodEqu);

		JLabel lblNombreEq = new JLabel("Nombre del Equipo:");
		lblNombreEq.setBounds(29, 153, 149, 14);
		panelEquipos.add(lblNombreEq);

		txtCodEquipo_Equipo = new JTextField();
		txtCodEquipo_Equipo.setBounds(150, 90, 96, 20);
		panelEquipos.add(txtCodEquipo_Equipo);
		txtCodEquipo_Equipo.setColumns(10);

		txtNombreEq = new JTextField();
		txtNombreEq.setBounds(150, 153, 96, 20);
		panelEquipos.add(txtNombreEq);
		txtNombreEq.setColumns(10);

		btnAltaEq = new JButton("Alta Equipo");
		btnAltaEq.setBounds(50, 280, 140, 30);
		panelEquipos.add(btnAltaEq);
		btnAltaEq.addActionListener(this);
		btnAltaEq.setBackground(Color.WHITE);

		btnBajaEq = new JButton("Baja Equipo");
		btnBajaEq.setBounds(200, 280, 140, 30);
		panelEquipos.add(btnBajaEq);
		btnBajaEq.addActionListener(this);
		btnBajaEq.setBackground(Color.WHITE);

		btnModificarEq = new JButton("Modificar Equipo");
		btnModificarEq.setBounds(350, 280, 180, 30);
		panelEquipos.add(btnModificarEq);
		btnModificarEq.addActionListener(this);
		btnModificarEq.setBackground(Color.WHITE);

		btnLimpiarDatosEq = new JButton("Limpiar");
		btnLimpiarDatosEq.setBounds(350, 150, 180, 21);
		panelEquipos.add(btnLimpiarDatosEq);
		btnLimpiarDatosEq.addActionListener(this);
		btnLimpiarDatosEq.setBackground(Color.WHITE);

		btnBajaEq.setEnabled(false);
		btnModificarEq.setEnabled(false);

		// Panel Competiciones

		panelCompeticion = new JPanel();
		panelCompeticion.setLayout(null);
		tabbedPane.addTab("Competiciones", panelCompeticion);

		btnAltaComp = new JButton("Alta Competicion");
		btnAltaComp.setBounds(50, 350, 120, 30);
		panelCompeticion.add(btnAltaComp);
		btnAltaComp.addActionListener(this);
		btnAltaComp.setBackground(Color.WHITE);

		btnBajaComp = new JButton("Baja Competicion");
		btnBajaComp.setBounds(200, 350, 120, 30);
		panelCompeticion.add(btnBajaComp);
		btnBajaComp.addActionListener(this);
		btnBajaComp.setBackground(Color.WHITE);

		btnModificarComp = new JButton("Modificar Competicion");
		btnModificarComp.setBounds(350, 350, 140, 30);
		panelCompeticion.add(btnModificarComp);
		btnModificarComp.addActionListener(this);
		btnModificarComp.setBackground(Color.WHITE);

		JLabel lblCodComp = new JLabel("Codigo de Competicion:");
		lblCodComp.setBounds(29, 90, 149, 14);
		panelCompeticion.add(lblCodComp);

		JLabel lblNombreComp = new JLabel("Nombre de la Competicion:");
		lblNombreComp.setBounds(29, 153, 149, 14);
		panelCompeticion.add(lblNombreComp);

		txtCodComp = new JTextField();
		txtCodComp.setBounds(238, 87, 96, 20);
		panelCompeticion.add(txtCodComp);
		txtCodComp.setColumns(10);

		txtNombreComp = new JTextField();
		txtNombreComp.setBounds(238, 150, 96, 20);
		panelCompeticion.add(txtNombreComp);
		txtNombreComp.setColumns(10);

		btnLimpiarDatosComp = new JButton("Limpiar");
		btnLimpiarDatosComp.setBounds(200, 311, 120, 21);
		panelCompeticion.add(btnLimpiarDatosComp);
		btnLimpiarDatosComp.addActionListener(this);
		btnLimpiarDatosComp.setBackground(Color.WHITE);

		btnBajaComp.setEnabled(false);
		btnModificarComp.setEnabled(false);

		// Panel Partidos
		panelPartidos = new JPanel();
		panelPartidos.setLayout(null);
		tabbedPane.addTab("Partidos", panelPartidos);

		JLabel lblLiga = new JLabel("Liga: ");
		lblLiga.setBounds(35, 71, 85, 13);
		panelPartidos.add(lblLiga);

		cbLiga = new JComboBox<Competicion>();
		cbLiga.setBounds(130, 67, 130, 21);
		panelPartidos.add(cbLiga);
		List<Competicion> competiciones = null;
		try {
			competiciones = Principal.devolverCompeticiones();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		for (Competicion comp : competiciones) {
			cbLiga.addItem(comp);
		}
		comp = (Competicion) cbLiga.getSelectedItem();
		cbLiga.setSelectedIndex(-1);
		cbLiga.addActionListener(this);

		JLabel lblEquipoLocal = new JLabel("Local: ");
		lblEquipoLocal.setBounds(35, 113, 85, 13);
		panelPartidos.add(lblEquipoLocal);

		cbLocal = new JComboBox<Equipo>();
		cbLocal.setBounds(130, 109, 130, 21);
		panelPartidos.add(cbLocal);
		cbLocal.addActionListener(this);

		cbVisitante = new JComboBox<Equipo>();
		cbVisitante.setBounds(130, 147, 130, 21);
		panelPartidos.add(cbVisitante);
		cbVisitante.addActionListener(this);

		List<Equipo> equiposCods = null;
		try {
			equiposCods = Principal.buscarEquipos();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		for (Equipo equ : equiposCods) {
			cbLocal.addItem(equ);
			cbVisitante.addItem(equ);
		}
		cbLocal.setSelectedIndex(-1);
		cbVisitante.setSelectedIndex(-1);

		JLabel lblVisitante = new JLabel("Visitante: ");
		lblVisitante.setBounds(35, 151, 85, 13);
		panelPartidos.add(lblVisitante);

		JLabel lblGanador = new JLabel("Ganador: ");
		lblGanador.setBounds(35, 192, 85, 13);
		panelPartidos.add(lblGanador);

		cbGanador = new JComboBox<String>();
		cbGanador.setBounds(130, 188, 130, 21);
		panelPartidos.add(cbGanador);
		cbGanador.addItem("Local");
		cbGanador.addItem("Visitante");
		cbGanador.addItem("PSD");
		cbGanador.setSelectedIndex(-1);

		btnAltaPart = new JButton("Alta Partido");
		btnAltaPart.setBounds(50, 280, 140, 30);
		panelPartidos.add(btnAltaPart);
		btnAltaPart.addActionListener(this);
		btnAltaPart.setBackground(Color.WHITE);

		btnBajaPart = new JButton("Baja Partido");
		btnBajaPart.setBounds(200, 280, 140, 30);
		panelPartidos.add(btnBajaPart);
		btnBajaPart.addActionListener(this);
		btnBajaPart.setBackground(Color.WHITE);

		btnModificarPart = new JButton("Modificar Partido");
		btnModificarPart.setBounds(350, 280, 180, 30);
		panelPartidos.add(btnModificarPart);
		btnModificarPart.addActionListener(this);
		btnModificarPart.setBackground(Color.WHITE);

		btnLimpiarDatosPart = new JButton("Limpiar");
		btnLimpiarDatosPart.setBounds(432, 77, 85, 21);
		panelPartidos.add(btnLimpiarDatosPart);

		btnBajaPart.setEnabled(false);
		btnModificarPart.setEnabled(false);

		lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setVisible(false);
		lblCodigo.setBounds(35, 27, 85, 13);
		panelPartidos.add(lblCodigo);

		txtCodPar = new JTextField();
		txtCodPar.setVisible(false);
		txtCodPar.setEditable(false);
		txtCodPar.setBounds(130, 24, 130, 19);
		panelPartidos.add(txtCodPar);
		txtCodPar.setColumns(10);

		btnLimpiarDatosPart.addActionListener(this);
		btnLimpiarDatosPart.setBackground(Color.WHITE);

		lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(35, 233, 85, 13);
		panelPartidos.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(130, 230, 130, 19);
		panelPartidos.add(txtFecha);
	}

	/**
	 * Maneja los eventos de acción de los componentes.
	 * 
	 * @param e Evento de acción
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAltaJug)) {
			altaJug();
		} else if (e.getSource().equals(btnBajaJug)) {
			bajaJug();
		} else if (e.getSource().equals(btnModificarJug)) {
			modificarJug();
		} else if (e.getSource().equals(btnAltaEq)) {
			altaEq();
		} else if (e.getSource().equals(btnBajaEq)) {
			bajaEq();
		} else if (e.getSource().equals(btnModificarEq)) {
			modificarEq();
		} else if (e.getSource().equals(btnAltaPart)) {
			try {
				altaPart();
			} catch (LoginException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource().equals(btnBajaPart)) {
			bajaPart();
		} else if (e.getSource().equals(btnModificarPart)) {
			modPart();
		} else if (e.getSource().equals(btnAltaComp)) {
			altaComp();
		} else if (e.getSource().equals(btnBajaComp)) {
			bajaComp();
		} else if (e.getSource().equals(btnModificarComp)) {
			modComp();
		} else if (e.getSource().equals(btnCargarJug)) {
			cargarJug();
		} else if (e.getSource().equals(btnCargarEq)) {
			cargarEq();
		} else if (e.getSource().equals(btnCargarComp)) {
			cargarComp();
		} else if (e.getSource().equals(btnCargarPart)) {
			cargarPart();
		} else if (e.getSource().equals(btnLimpiarDatosEq)) {
			limpiarEq();
		} else if (e.getSource().equals(btnLimpiarDatosJug)) {
			limpiarJug();
		} else if (e.getSource().equals(btnLimpiarDatosPart)) {
			limpiarPart();
		} else if (e.getSource().equals(btnLimpiarDatosComp)) {
			limpiarComp();

		} else if (e.getSource().equals(cbLiga)) {

		}
	}

	// LIMPIAR DATOS

	/**
	 * Limpia los campos del formulario de competición.
	 */
	private void limpiarComp() {
		txtCodComp.setText("");
		txtCodComp.setEditable(true);
		txtNombreComp.setText("");

		btnAltaComp.setEnabled(true);
		btnBajaComp.setEnabled(false);
		btnModificarComp.setEnabled(false);

	}

	/**
	 * Limpia los campos del formulario de partido.
	 */
	private void limpiarPart() {
		txtCodPar.setText("");
		cbLiga.setSelectedIndex(-1);
		cbLocal.setSelectedIndex(-1);
		cbVisitante.setSelectedIndex(-1);
		cbGanador.setSelectedIndex(-1);
		txtFecha.setText("");

		btnAltaPart.setEnabled(true);
		btnBajaPart.setEnabled(false);
		btnModificarPart.setEnabled(false);
	}

	/**
	 * Limpia los campos del formulario de jugador.
	 */
	private void limpiarJug() {
		txtDni.setText("");
		txtDni.setEditable(true);
		txtNombre.setText("");
		txtApellido.setText("");
		txtDorsal.setText("");
		grupoPosicion.clearSelection();
		cbCodEqui_J.setSelectedIndex(-1);

		btnAltaJug.setEnabled(true);
		btnBajaJug.setEnabled(false);
		btnModificarJug.setEnabled(false);

	}

	/**
	 * Limpia los campos del formulario de equipo.
	 */
	private void limpiarEq() {
		txtCodEquipo_Equipo.setText("");
		txtCodEquipo_Equipo.setEditable(true);
		txtNombreEq.setText("");

		btnAltaEq.setEnabled(true);
		btnBajaEq.setEnabled(false);
		btnModificarEq.setEnabled(false);

	}

	// CARGAR VENTANAS CON LAS TABLAS RECARGADAS

	/**
	 * Abre la ventana para consultar partidos.
	 */
	private void cargarPart() {
		MostrarPartidos mP = new MostrarPartidos(this, true);
		dispose();
		mP.setVisible(true);

	}

	/**
	 * Abre la ventana para consultar competiciones.
	 */
	private void cargarComp() {
		MostrarCompeticiones mC = new MostrarCompeticiones(this, true);
		dispose();
		mC.setVisible(true);
	}

	/**
	 * Abre la ventana para consultar equipos.
	 */
	private void cargarEq() {
		MostrarEquipos mE = new MostrarEquipos(this, true);
		dispose();
		mE.setVisible(true);

	}

	/**
	 * Abre la ventana para consultar jugadores.
	 */
	private void cargarJug() {
		MostrarJugadores venJug = new MostrarJugadores(this, true);
		dispose();
		venJug.setVisible(true);

	}

	// GESTION COMPETICIONES

	/**
	 * Modifica los datos de una competición existente.
	 */
	private void modComp() {
		Competicion comp = new Competicion();
		comp.setCod_comp(txtCodComp.getText());
		comp.setNombre_competicion(txtNombreComp.getText());
		try {
			Principal.modificarCompeticion(comp);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "MODIFICACION CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * Da de baja una competición del sistema.
	 */
	private void bajaComp() {
		Competicion comp = new Competicion();
		comp.setCod_comp(txtCodComp.getText());
		try {
			Principal.eliminarCompeticion(comp);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "BAJA CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
		limpiarComp();

	}

	/**
	 * Da de alta una nueva competición en el sistema. Valida que el código tenga
	 * exactamente 3 letras, tambien hace la comprobacion de que no hay ninguna
	 * competicion con el mismo codigo.
	 */
	private void altaComp() {
		Competicion comp = new Competicion();
		String codComp = txtCodComp.getText().toUpperCase(); // por si alguien lo mete en minúsculas

		if (codComp.length() != 3 || !codComp.matches("^[A-Z]{3}$")) {
			JOptionPane.showMessageDialog(this, "ERROR! El código no es correcto, debe tener 3 letras.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			comp.setCod_comp(codComp);
			comp.setNombre_competicion(txtNombreComp.getText());

			try {
				List<Competicion> competiciones = new ArrayList<>();
				competiciones = Principal.devolverCompeticiones();
				if (txtCodComp != null) {
					for (Competicion competi : competiciones) {
						if (competi.getCod_comp().equalsIgnoreCase(comp.getCod_comp())) {
							JOptionPane.showMessageDialog(this, "El codigo de la liga ya ha sido introduciodo ",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				Principal.altaCompeticion(comp);
				cbLiga.removeAllItems();
				for (Competicion compe : competiciones) {
					cbLiga.addItem(compe);
				}
				JOptionPane.showMessageDialog(this, "ALTA CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
				limpiarComp();

			} catch (LoginException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al acceder al sistema: " + e.getMessage(), "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// GESTION PARTIDOS

	/**
	 * Modifica los datos de un partido existente.
	 */
	private void modPart() {
		this.dispose();
		Partido part = new Partido();
		part.setCod_part(Integer.valueOf(txtCodPar.getText()));
		part.setCod_comp(((Competicion) cbLiga.getSelectedItem()).getCod_comp());
		Equipo local = (Equipo) cbLocal.getSelectedItem();
		Equipo visi = (Equipo) cbVisitante.getSelectedItem();
		if (local.equals(visi)) {
			JOptionPane.showMessageDialog(this, "El equipo local y visitante no pueden ser el mismo.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			part.setEquipo_local(((Equipo) cbLocal.getSelectedItem()).getCod_equi());
			part.setEquipo_visitante(((Equipo) cbVisitante.getSelectedItem()).getCod_equi());
		}
		part.setFecha(Date.valueOf(txtFecha.getText()).toLocalDate());
		if (part.getFecha().isAfter(LocalDate.now())) {
			JOptionPane.showMessageDialog(this,
					"La fecha es superior a la fecha actual por lo tanto no se sabe quien es el ganador", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			part.setGanador("PSD");
		} else {
			if (cbGanador.getSelectedItem().equals("Local")) {
				part.setGanador(((Equipo) cbLocal.getSelectedItem()).getCod_equi());
			} else {
				part.setGanador(((Equipo) cbVisitante.getSelectedItem()).getCod_equi());
			}
		}

		try {
			Principal.modificarPartido(part);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "MODIFICACION CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
		limpiarPart();
	}

	/**
	 * Da de baja un partido del sistema.
	 */
	private void bajaPart() {
		Partido part = new Partido();
		part.setCod_part(Integer.parseInt(txtCodPar.getText()));
		try {
			Principal.bajaPartido(part);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "BAJA CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
		limpiarPart();
	}

	/**
	 * Da de alta un nuevo partido en el sistema.
	 * 
	 * @throws LoginException Si hay problemas de autenticación
	 */
	private void altaPart() throws LoginException {
		int i;
		Partido part = new Partido();
		i = Principal.cantidadPartidos() + 1;
		part.setCod_part(i);
		Equipo local = (Equipo) cbLocal.getSelectedItem();
		Equipo visi = (Equipo) cbVisitante.getSelectedItem();
		if (local.equals(visi)) {
			JOptionPane.showMessageDialog(this, "El equipo local y visitante no pueden ser el mismo.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			part.setEquipo_local(((Equipo) cbLocal.getSelectedItem()).getCod_equi());
			part.setEquipo_visitante(((Equipo) cbVisitante.getSelectedItem()).getCod_equi());
		}
		if (cbGanador.getSelectedItem().equals("Local")) {
			part.setGanador(((Equipo) cbLocal.getSelectedItem()).getCod_equi());
		} else if (cbGanador.getSelectedItem().equals("Visitante")) {
			part.setGanador(((Equipo) cbVisitante.getSelectedItem()).getCod_equi());
		} else {
			part.setGanador("PSD");
		}
		String input = txtFecha.getText();
		if (!input.matches("\\d{4}-\\d{2}-\\d{2}")) {
			JOptionPane.showMessageDialog(null, "Formato incorrecto. Use aaaa-MM-dd");
			return;
		} else {
			part.setFecha(Date.valueOf(txtFecha.getText()).toLocalDate());
			if (part.getFecha().isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(this,
						"La fecha es superior a la fecha actual por lo tanto no se sabe quien es el ganador", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
				part.setGanador("PSD");
			} else {
				if (cbGanador.getSelectedItem().equals("Local")) {
					part.setGanador(((Equipo) cbLocal.getSelectedItem()).getCod_equi());
				} else {
					part.setGanador(((Equipo) cbVisitante.getSelectedItem()).getCod_equi());
				}
			}
			part.setCod_comp(((Competicion) cbLiga.getSelectedItem()).getCod_comp().toUpperCase());
		}
		Principal.altaPartido(part);
		JOptionPane.showMessageDialog(this, "ALTA CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
		limpiarPart();
	}

	// GESTION EQUIPOS

	/**
	 * Modifica los datos de un equipo existente.
	 */
	private void modificarEq() {
		Equipo eq = new Equipo();
		eq.setCod_equi(txtCodEquipo_Equipo.getText());
		eq.setNombre_equipo(txtNombreEq.getText());
		try {
			Principal.modificarEquipo(eq);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiarEq();

	}

	/**
	 * Da de baja un equipo del sistema.
	 */
	private void bajaEq() {
		Equipo eq = new Equipo();
		eq.setCod_equi(txtCodEquipo_Equipo.getText());
		try {
			Principal.bajaEquipo(eq);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "BAJA CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
		limpiarEq();

	}

	/**
	 * Da de alta un nuevo equipo en el sistema. Valida que el código tenga
	 * exactamente 3 letras y que no se haya introducido con anterioridad o que
	 * exista.
	 */
	private void altaEq() {
		Equipo eq = new Equipo();
		String codEq = txtCodEquipo_Equipo.getText().toUpperCase();

		if (codEq.length() != 3 || !codEq.matches("^[A-Z]{3}$")) {
			JOptionPane.showMessageDialog(this, "ERROR! El código no es correcto, debe tener 3 letras.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {

		try {
			// Verificar si ya existe un equipo con ese código
			List<Equipo> equipos = Principal.buscarEquipos(); // Este método debe devolverte la lista de equipos
																// existentes
			for (Equipo existente : equipos) {
				if (existente.getCod_equi().equalsIgnoreCase(codEq)) {
					JOptionPane.showMessageDialog(this, "ERROR! Ya existe un equipo con ese código.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			// Si no existe, se puede crear
			eq.setCod_equi(codEq);
			eq.setNombre_equipo(txtNombreEq.getText());
			Principal.altaEquipo(eq);

			JOptionPane.showMessageDialog(this, "ALTA CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
			limpiarEq(); // Si tienes un método para limpiar los campos

		} catch (LoginException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al dar de alta el equipo: " + e.getMessage(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// GESTION JUGADORES

	/**
	 * Da de alta un nuevo jugador en el sistema. Valida el DNI antes de realizar el
	 * alta.
	 */
	private void altaJug() {
		String dni = txtDni.getText().toUpperCase().trim(); // Mejora: eliminar espacios y asegurar mayúsculas

		try {
			if (!validarDNI(dni)) {
				throw new DniException("ERROR! DNI no válido. Revíselo e inténtelo de nuevo.");
			}
			// Verificar si ya existe un jugador con el mismo DNI
			List<Jugador> jugadores = Principal.devolverJugadores(); // Este método debe existir en Principal
			for (Jugador existente : jugadores) {
				if (existente.getDni().equalsIgnoreCase(dni)) {
					JOptionPane.showMessageDialog(this, "ERROR! Ya existe un jugador con ese DNI.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			Jugador j = new Jugador();
			j.setDni(dni);
			j.setNombre(txtNombre.getText());
			j.setApellido(txtApellido.getText());

			try {
				j.setDorsal(Integer.parseInt(txtDorsal.getText()));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "ERROR! El dorsal debe ser un número entero.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (rdbtnQuarterback.isSelected()) {
				j.setPosicion(EnumPosicion.QUARTERBACK);
			} else if (rdbtnRunning.isSelected()) {
				j.setPosicion(EnumPosicion.RUNNING);
			} else if (rdbtnTackle.isSelected()) {
				j.setPosicion(EnumPosicion.TACKLE);
			} else if (rdbtnGuard.isSelected()) {
				j.setPosicion(EnumPosicion.GUARD);
			}
			j.setCod_equi(((Equipo) cbCodEqui_J.getSelectedItem()).getCod_equi());

			if (cbCodEqui_J.getSelectedItem() != null) {
				for (Jugador ju : jugadores) {
					if (ju.getCod_equi().equals(j.getCod_equi())) {
						if (ju.getDorsal() == j.getDorsal()) {
							JOptionPane.showMessageDialog(this,
									"ERROR! Ya existe un jugador con ese dorsal en el equipo.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
							txtDorsal.setText("");
							return;
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "ERROR! Debe seleccionar un equipo.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
			Principal.altaJugador(j);
			JOptionPane.showMessageDialog(this, "ALTA CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
			limpiarJug();

		} catch (DniException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR!", JOptionPane.ERROR_MESSAGE);
		} catch (LoginException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al registrar jugador: " + e.getMessage(), "ERROR!",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Da de baja un jugador del sistema.
	 */
	private void bajaJug() {
		Jugador j = new Jugador();
		j.setDni(txtDni.getText());
		try {
			Principal.eliminarJugador(j);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "BAJA CORRECTA!!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
		limpiarJug();
	}

	/**
	 * Modifica los datos de un jugador existente.
	 */
	private void modificarJug() {
		try {
			List<Jugador> jugadores = Principal.devolverJugadores();
			Jugador j = new Jugador();
			j.setDni(txtDni.getText());
			j.setNombre(txtNombre.getText());
			j.setApellido(txtApellido.getText());
			j.setDorsal(Integer.parseInt(txtDorsal.getText()));
			if (rdbtnQuarterback.isSelected()) {
				j.setPosicion(EnumPosicion.QUARTERBACK);
			} else if (rdbtnRunning.isSelected()) {
				j.setPosicion(EnumPosicion.RUNNING);
			} else if (rdbtnTackle.isSelected()) {
				j.setPosicion(EnumPosicion.TACKLE);
			} else if (rdbtnGuard.isSelected()) {
				j.setPosicion(EnumPosicion.GUARD);
			}
			j.setCod_equi(((Equipo) cbCodEqui_J.getSelectedItem()).getCod_equi());
			for (Jugador ju : jugadores) {
				if (ju.getCod_equi().equals(j.getCod_equi()) && ju.getDorsal() == j.getDorsal()) {
					JOptionPane.showMessageDialog(this, "ERROR! Ya existe un jugador con ese dorsal en el equipo.",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					txtDorsal.setText("");
					return;
				}
			}
			Principal.modificarJugador(j);
			JOptionPane.showMessageDialog(this, "¡MODIFICACIÓN CORRECTA!", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
			limpiarJug();
		} catch (LoginException e) {
			JOptionPane.showMessageDialog(this, "Error al modificar el jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Métodos auxiliares

	// CARGAR DATOS

	/**
	 * Carga los datos de un jugador en el formulario para su modificación.
	 * 
	 * @param dni      DNI del jugador
	 * @param nombre   Nombre del jugador
	 * @param apellido Apellido del jugador
	 * @param dorsal   Número de dorsal
	 * @param posicion Posición del jugador
	 * @param codEq    Código del equipo
	 */
	public void cargarDatosJug(String dni, String nombre, String apellido, int dorsal, EnumPosicion posicion,
			String codEq) {
		tabbedPane.setSelectedComponent(panelJugador);
		txtDni.setText(dni);
		txtDni.setEditable(false);
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtDorsal.setText(String.valueOf(dorsal));
		try {
			switch (posicion) {
			case TACKLE:
				rdbtnTackle.setSelected(true);
				break;
			case GUARD:
				rdbtnGuard.setSelected(true);
				break;
			case RUNNING:
				rdbtnRunning.setSelected(true);
				break;
			case QUARTERBACK:
				rdbtnQuarterback.setSelected(true);
				break;
			default:
				System.out.println("No se reconoce la posición.");
				break;
			}
		} catch (IllegalArgumentException e) {
			System.err.println("Error: La posición '" + posicion + "' no es válida.");
		}
		for (int i = 0; i < cbCodEqui_J.getItemCount(); i++) {
			equi = cbCodEqui_J.getItemAt(i);
			if (codEq.equalsIgnoreCase(equi.getCod_equi())) {
				cbCodEqui_J.setSelectedIndex(i);

			}
		}
		btnAltaJug.setEnabled(false);
		btnBajaJug.setEnabled(true);
		btnModificarJug.setEnabled(true);
	}

	/**
	 * Carga los datos de un partido en el formulario para su modificación.
	 * 
	 * @param codPart     Código de partido
	 * @param eqLocal     Equipo local
	 * @param eqVisitante Equipo visitante
	 * @param ganador     Equipo ganador
	 * @param fecha       Fecha del partido
	 * @param codComp     Código de competición
	 */
	public void cargarDatosPart(int codPart, String eqLocal, String eqVisitante, String ganador, LocalDate fecha,
			String codComp) {
		tabbedPane.setSelectedComponent(panelPartidos);
		txtCodPar.setText(String.valueOf(codPart));
		Equipo equiL = null, eq;
		Competicion comp;
		for (int i = 0; i < cbLiga.getItemCount(); i++) {
			comp = cbLiga.getItemAt(i);
			if (codComp.equalsIgnoreCase(comp.getCod_comp())) {
				cbLiga.setSelectedIndex(i);
			}
		}
		for (int i = 0; i < cbLocal.getItemCount(); i++) {
			eq = cbLocal.getItemAt(i);
			if (eq.getCod_equi().equals(eqLocal)) {
				cbLocal.setSelectedItem(eq);
				equiL = eq;
			}
		}
		for (int i = 0; i < cbVisitante.getItemCount(); i++) {
			eq = cbVisitante.getItemAt(i);
			if (eq.getCod_equi().equals(eqVisitante)) {
				cbVisitante.setSelectedItem(eq);
			}
		}

		if (ganador == null) {
			cbGanador.setSelectedIndex(2);
		} else if (ganador.equalsIgnoreCase(equiL.getCod_equi())) {
			cbGanador.setSelectedIndex(0);
		} else {
			cbGanador.setSelectedIndex(1);
		}

		txtFecha.setText(fecha.toString());

		btnAltaPart.setEnabled(false);
		lblCodigo.setVisible(true);
		txtCodPar.setVisible(true);
		btnBajaPart.setEnabled(true);
		btnModificarPart.setEnabled(true);
	}

	/**
	 * Carga los datos de una competición en el formulario para su modificación.
	 * 
	 * @param codCompeticion    Código de competición
	 * @param nombreCompeticion Nombre de competición
	 */
	public void cargarDatosComp(String codCompeticion, String nombreCompeticion) {
		tabbedPane.setSelectedComponent(panelCompeticion);
		txtCodComp.setText(codCompeticion);
		txtCodComp.setEditable(false);
		txtNombreComp.setText(nombreCompeticion);

		btnAltaComp.setEnabled(false);
		btnBajaComp.setEnabled(true);
		btnModificarComp.setEnabled(true);

	}

	/**
	 * Carga los datos de un equipo en el formulario para su modificación.
	 * 
	 * @param codEquipo    Código de equipo
	 * @param nombreEquipo Nombre de equipo
	 */
	public void cargarDatosEq(String codEquipo, String nombreEquipo) {
		tabbedPane.setSelectedComponent(panelEquipos);
		txtCodEquipo_Equipo.setText(codEquipo);
		txtCodEquipo_Equipo.setEditable(false);
		txtNombreEq.setText(nombreEquipo);

		btnAltaEq.setEnabled(false);
		btnBajaEq.setEnabled(true);
		btnModificarEq.setEnabled(true);

	}

	/**
	 * Valida que un DNI español tenga el formato correcto.
	 * 
	 * @param dni DNI a validar
	 * @return true si el DNI es válido, false en caso contrario
	 */
	private static boolean validarDNI(String dni) {
		String dniRegex = "\\d{8}[A-HJ-NP-TV-Z]";
		if (!dni.matches(dniRegex)) {
			return false;
		}
		String numeroDni = dni.substring(0, 8);
		char letraDni = dni.charAt(8);
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int numero = Integer.parseInt(numeroDni);
		char letraCalculada = letras.charAt(numero % 23);

		return letraDni == letraCalculada;

	}
}
