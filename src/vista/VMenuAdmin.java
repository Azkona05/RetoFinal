package vista;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import controlador.Principal;
import modelo.Competicion;
import modelo.EnumPosicion;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class VMenuAdmin extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel panelJugador, panelCompeticion, panelEquipos, panelPartidos;
	private JButton btnAltaJug, btnBajaJug, btnModificarJug, btnCargarJug, btnLimpiarDatosJug;
	private JButton btnAltaPart, btnBajaPart, btnModificarPart, btnCargarPart, btnLimpiarDatosPart;
	private JButton btnAltaComp, btnBajaComp, btnModificarComp, btnCargarComp, btnLimpiarDatosComp;
	private JButton btnAltaEq, btnBajaEq, btnModificarEq, btnCargarEq, btnLimpiarDatosEq;
	private JTextField txtDni, txtNombre, txtApellido, txtDorsal, txtCodEq, txtNombreEq, txtCodComp, txtNombreComp,
			txtCodEquipo_Jugador, txtCodEquipo_Equipo;
	private ButtonGroup grupoPosicion;
	private JRadioButton rdbtnGuard, rdbtnQuarterback, rdbtnRunning, rdbtnTackle, rdbtnLocalNuevo, rdbtnVisitanteNuevo;
	private JComboBox<Competicion> cbLiga;
	private JComboBox<Equipo> cbLocal, cbVisitante;
	private JComboBox<String> cbGanador;
	private Competicion comp;

	public VMenuAdmin(VLogin padre, boolean modal) {
		super(padre);
		setTitle("Gestion Administrador");
		setBounds(100, 100, 600, 450);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(modal);

		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

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

		JLabel lblDorsal = new JLabel("Posicion");
		lblDorsal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDorsal.setBounds(50, 180, 150, 30);
		panelJugador.add(lblDorsal);

		txtDorsal = new JTextField();
		txtDorsal.setBounds(200, 150, 150, 30);

		JLabel lblCodEquipo = new JLabel("Codigo de Equipo:");
		lblCodEquipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodEquipo.setBounds(50, 253, 100, 30);
		panelJugador.add(lblCodEquipo);

		txtCodEquipo_Jugador = new JTextField(10);
		txtCodEquipo_Jugador.setBounds(200, 255, 150, 30);
		panelJugador.add(txtCodEquipo_Jugador);

		grupoPosicion = new ButtonGroup();

		rdbtnGuard = new JRadioButton("Guard");
		rdbtnGuard.setBounds(200, 171, 111, 23);
		grupoPosicion.add(rdbtnGuard);
		panelJugador.add(rdbtnGuard);

		rdbtnQuarterback = new JRadioButton("Quarterback");
		rdbtnQuarterback.setBounds(325, 171, 111, 23);
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

		btnCargarJug = new JButton("Cargar Jugadores");
		btnCargarJug.setBounds(432, 37, 85, 21);
		panelJugador.add(btnCargarJug);
		btnCargarJug.addActionListener(this);

		btnAltaJug = new JButton("Alta Jugador");
		btnAltaJug.setBounds(432, 114, 89, 23);
		panelJugador.add(btnAltaJug);
		btnAltaJug.addActionListener(this);

		btnBajaJug = new JButton("Baja jugador");
		btnBajaJug.setBounds(432, 186, 89, 23);
		panelJugador.add(btnBajaJug);
		btnBajaJug.addActionListener(this);

		btnModificarJug = new JButton("Modificar Jugador");
		btnModificarJug.setBounds(432, 245, 89, 23);
		panelJugador.add(btnModificarJug);
		btnModificarJug.addActionListener(this);

		btnLimpiarDatosJug = new JButton("Limpiar datos");
		btnLimpiarDatosJug.setBounds(432, 327, 89, 23);
		panelJugador.add(btnLimpiarDatosJug);
		btnCargarJug.addActionListener(this);

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

		btnBajaEq = new JButton("Baja Equipo");
		btnBajaEq.setBounds(200, 280, 140, 30);
		panelEquipos.add(btnBajaEq);
		btnBajaEq.addActionListener(this);

		btnModificarEq = new JButton("Modificar Equipo");
		btnModificarEq.setBounds(350, 280, 180, 30);
		panelEquipos.add(btnModificarEq);
		btnModificarEq.addActionListener(this);

		btnCargarEq = new JButton("Cargar Equipos");
		btnCargarEq.setBounds(432, 37, 85, 21);
		panelEquipos.add(btnCargarEq);
		btnCargarEq.addActionListener(this);

		btnLimpiarDatosEq = new JButton("Limpiar");
		btnLimpiarDatosEq.setBounds(432, 77, 85, 21);
		panelEquipos.add(btnLimpiarDatosEq);
		btnLimpiarDatosEq.addActionListener(this);

		// Panel Competiciones
		panelCompeticion = new JPanel();
		panelCompeticion.setLayout(null);
		tabbedPane.addTab("Competiciones", panelCompeticion);

		btnAltaComp = new JButton("Alta Competicion");
		btnAltaComp.setBounds(50, 350, 120, 30);
		panelCompeticion.add(btnAltaComp);
		btnAltaComp.addActionListener(this);

		btnBajaComp = new JButton("Baja Competicion");
		btnBajaComp.setBounds(200, 350, 120, 30);
		panelCompeticion.add(btnBajaComp);
		btnBajaComp.addActionListener(this);

		btnModificarComp = new JButton("Modificar Competicion");
		btnModificarComp.setBounds(350, 350, 140, 30);
		panelCompeticion.add(btnModificarComp);
		btnModificarComp.addActionListener(this);

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
		btnLimpiarDatosComp.setBounds(432, 77, 85, 21);
		panelEquipos.add(btnLimpiarDatosComp);
		btnLimpiarDatosComp.addActionListener(this);

		btnCargarComp = new JButton("Cargar Competiciones");
		btnCargarComp.setBounds(432, 37, 85, 21);
		panelCompeticion.add(btnCargarComp);
		btnCargarComp.addActionListener(this);

		// Panel Partidos
		panelPartidos = new JPanel();
		panelPartidos.setLayout(null);
		tabbedPane.addTab("Partidos", panelPartidos);

		JLabel lblLiga = new JLabel("Liga: ");
		lblLiga.setBounds(33, 31, 85, 13);
		panelPartidos.add(lblLiga);

		cbLiga = new JComboBox();
		cbLiga.setBounds(128, 27, 130, 21);
		panelPartidos.add(cbLiga);
		List<Competicion> competiciones = Principal.devolverCompeticiones();
		for (Competicion comp : competiciones) {
			cbLiga.addItem(comp);
		}
		comp = (Competicion) cbLiga.getSelectedItem();
		cbLiga.setSelectedIndex(-1);
		cbLiga.addActionListener(this);

		JLabel lblEquipoLocal = new JLabel("Local: ");
		lblEquipoLocal.setBounds(33, 73, 85, 13);
		panelPartidos.add(lblEquipoLocal);

		cbLocal = new JComboBox<Equipo>();
		cbLocal.setBounds(128, 69, 130, 21);
		panelPartidos.add(cbLocal);
		cbLocal.addActionListener(this);

		JLabel lblVisitante = new JLabel("Visitante: ");
		lblVisitante.setBounds(33, 111, 85, 13);
		panelPartidos.add(lblVisitante);

		cbVisitante = new JComboBox();
		cbVisitante.setBounds(128, 107, 130, 21);
		panelPartidos.add(cbVisitante);
		cbVisitante.addActionListener(this);

		JLabel lblGanador = new JLabel("Ganador: ");
		lblGanador.setBounds(33, 152, 85, 13);
		panelPartidos.add(lblGanador);

		cbGanador = new JComboBox<String>();
		cbGanador.setBounds(128, 148, 130, 21);
		panelPartidos.add(cbGanador);
		cbGanador.addItem("Local");
		cbGanador.addItem("Visitante");
		cbGanador.setSelectedIndex(-1);

		btnAltaPart = new JButton("Alta Partido");
		btnAltaPart.setBounds(50, 280, 140, 30);
		panelPartidos.add(btnAltaPart);
		btnAltaPart.addActionListener(this);

		btnBajaPart = new JButton("Baja Partido");
		btnBajaPart.setBounds(200, 280, 140, 30);
		panelPartidos.add(btnBajaPart);
		btnBajaPart.addActionListener(this);

		btnModificarPart = new JButton("Modificar Partido");
		btnModificarPart.setBounds(350, 280, 180, 30);
		panelPartidos.add(btnModificarPart);
		btnModificarPart.addActionListener(this);

		btnCargarPart = new JButton("Cargar Partidos");
		btnCargarPart.setBounds(432, 37, 85, 21);
		panelPartidos.add(btnCargarPart);
		btnCargarPart.addActionListener(this);

		btnLimpiarDatosEq = new JButton("Limpiar");
		btnLimpiarDatosEq.setBounds(432, 77, 85, 21);
		panelPartidos.add(btnLimpiarDatosEq);

		rdbtnLocalNuevo = new JRadioButton("Local Nuevo");
		rdbtnLocalNuevo.setBounds(271, 69, 103, 21);
		panelPartidos.add(rdbtnLocalNuevo);
		rdbtnLocalNuevo.addActionListener(this);

		rdbtnVisitanteNuevo = new JRadioButton("Visitante Nuevo");
		rdbtnVisitanteNuevo.setBounds(271, 107, 123, 21);
		panelPartidos.add(rdbtnVisitanteNuevo);
		rdbtnVisitanteNuevo.addActionListener(this);
		
		btnLimpiarDatosEq.addActionListener(this);
	}

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
			altaPart();
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
			// actualizar equipos a la liga
//			System.out.println(cbLiga);
//			cbLocal.removeAllItems();
//			
//			comp = (Competicion) cbLiga.getSelectedItem();
//			System.out.println(comp);
//			if (rdbtnVisitanteNuevo.isSelected()) {
//				System.out.println("jaja");
//			} else {
//				List<Equipo> equipos = Principal.devolverEquipos(comp);
//				System.out.println(equipos);
//				System.out.println(comp);
//				for (Equipo equ : equipos) {
//					System.out.println(equ);
//					cbLocal.addItem(equ);
//				}
//			}
//		}
			cbLocal.removeAllItems();
			cbVisitante.removeAllItems();
			comp = (Competicion) cbLiga.getSelectedItem();
			List<Equipo> equipos = Principal.devolverEquipos(comp);
			for (Equipo equ : equipos) {
				cbLocal.addItem(equ);
			}
			cbLocal.setSelectedIndex(-1);

			equipos = Principal.devolverEquipos(comp);
			for (Equipo equ : equipos) {
				cbVisitante.addItem(equ);
			}
			cbVisitante.setSelectedIndex(-1);
			
		} else if (e.getSource().equals(rdbtnLocalNuevo)) {
			if (rdbtnLocalNuevo.isSelected()) {
				cbLocal.removeAllItems();
				comp = (Competicion) cbLiga.getSelectedItem();
				List<Equipo> equipos = Principal.nuevosEquipos(comp);
				for (Equipo equ : equipos) {
					cbLocal.addItem(equ);
				}
				cbLocal.setSelectedIndex(-1);
			} else {
				cbLocal.removeAllItems();
				comp = (Competicion) cbLiga.getSelectedItem();
				List<Equipo> equipos = Principal.devolverEquipos(comp);
				for (Equipo equ : equipos) {
					cbLocal.addItem(equ);
				}
				cbLocal.setSelectedIndex(-1);
			}
		}
		else if (e.getSource().equals(rdbtnVisitanteNuevo)) {
			if (rdbtnVisitanteNuevo.isSelected()) {
				cbVisitante.removeAllItems();
				comp = (Competicion) cbLiga.getSelectedItem();
				List<Equipo> equipos = Principal.nuevosEquipos(comp);
				for (Equipo equ : equipos) {
					cbVisitante.addItem(equ);
				}
				cbVisitante.setSelectedIndex(-1);
			} else {
				cbVisitante.removeAllItems();
				comp = (Competicion) cbLiga.getSelectedItem();
				List<Equipo> equipos = Principal.devolverEquipos(comp);
				for (Equipo equ : equipos) {
					cbVisitante.addItem(equ);
				}
				cbVisitante.setSelectedIndex(-1);
			}
		}
	}

	private void limpiarComp() {
		txtCodComp.setText("");
		txtNombreComp.setText("");

	}

	private void limpiarPart() {
		// TODO Auto-generated method stub

	}

	private void limpiarJug() {
		txtDni.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtDorsal.setText("");
		rdbtnGuard.setSelected(false);
		rdbtnQuarterback.setSelected(false);
		rdbtnRunning.setSelected(false);
		rdbtnTackle.setSelected(false);
		txtCodEq.setText("");

	}

	private void limpiarEq() {
		txtCodEq.setText("");
		txtNombreEq.setText("");

	}

	private void cargarPart() {
		MostrarPartidos mP = new MostrarPartidos(this, true);
		mP.setVisible(true);

	}

	private void cargarComp() {
		MostrarCompeticiones mC = new MostrarCompeticiones(this, true);
		mC.setVisible(true);

	}

	private void cargarEq() {
		MostrarEquipos mE = new MostrarEquipos(this, true);
		mE.setVisible(true);

	}

	private void modComp() {
		Competicion comp = new Competicion();
		comp.setNombre_competicion(txtNombreComp.getText());
		Principal.modificarCompeticion(comp);

	}

	private void bajaComp() {
		Competicion comp = new Competicion();
		comp.setCod_comp(txtCodComp.getText());
		Principal.eliminarCompeticion(comp);

	}

	private void altaComp() {
		Competicion comp = new Competicion();
		comp.setCod_comp(txtCodComp.getText());
		comp.setNombre_competicion(txtNombreComp.getText());
		Principal.altaCompeticion(comp);

	}

	private void modPart() {
		Partido part = new Partido();
		part.setCod_comp(getName());
	}

	private void bajaPart() {
		// TODO Auto-generated method stub

	}

	private void altaPart() {
		Partido part = new Partido ();
		

	}

	private void modificarEq() {
		Equipo eq = new Equipo();
		eq.setNombre_equipo(txtNombreEq.getText());
		Principal.modificarEquipo(eq);

	}

	private void bajaEq() {
		Equipo eq = new Equipo();
		eq.setCod_equi(txtCodEq.getText());
		Principal.bajaEquipo(eq);

	}

	private void altaEq() {
		Equipo eq = new Equipo();
		eq.setCod_equi(txtCodEq.getText());
		eq.setNombre_equipo(txtNombreEq.getText());
		Principal.altaEquipo(eq);
	}

	private void cargarJug() {
		MostrarJugadores venJug = new MostrarJugadores(this, true);
		venJug.setVisible(true);
	}

	private void altaJug() {
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
		j.setCod_equi(txtCodEq.getText());
		Principal.altaJugador(j);
		dispose();

	}

	private void bajaJug() {
		Jugador j = new Jugador();
		j.setDni(txtDni.getText());
		Principal.EliminarJugador(j);
		dispose();
	}

	private void modificarJug() {
		Jugador j = new Jugador();
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
		j.setCod_equi(txtCodEq.getText());
		Principal.modificarJugador(j);
		dispose();
	}

	private void salir() {
		dispose();
	}

	public void cargarDatosJug(String dni, String nombre, String apellido, int dorsal, EnumPosicion posicion,
			String codEq) {
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
		txtCodEquipo_Jugador.setText(codEq);

	}

	public void cargarDatosPart(int codPart, String eqLocal, String eqVisitante, String ganador, LocalDate fecha,
			String codComp) {
		// TODO Auto-generated method stub

	}

	public void cargarDatosComp(String codCompeticion, String nombreCompeticion) {
		tabbedPane.setSelectedComponent(panelCompeticion);
		txtCodComp.setText(codCompeticion);
		txtNombreComp.setText(nombreCompeticion);

	}

	public void cargarDatosEq(String codEquipo, String nombreEquipo) {
		tabbedPane.setSelectedComponent(panelEquipos);
		txtCodEquipo_Equipo.setText(codEquipo);
		txtNombreEq.setText(nombreEquipo);

	}
}
