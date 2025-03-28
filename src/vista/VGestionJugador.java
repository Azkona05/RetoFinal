package vista;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Principal;
import modelo.EnumPosicion;
import modelo.Jugador;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class VGestionJugador extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//TEXT FIELDS
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDorsal;
	private JTextField txtCodEquipo;
	//JBUTTON
	private JButton btnBaja;
	private JButton btnAlta;
	private JButton btnModificar;
	private JButton btnLimpiar;
	//RADIO BUTTON
	private ButtonGroup grupoPosicion;
	private JRadioButton rdbtnGuard;
	private JRadioButton rdbtnQuarterback;
	private JRadioButton rdbtnRunning;
	private JRadioButton rdbtnTackle;

	/**
	 * Launch the application.
	 */

//	public static void main(String[] args) {
//		try {
//			VGestionJugador dialog = new VGestionJugador(null, false);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * 
	 * @param modall
	 * @param padre
	 */
	public VGestionJugador(VMenuAdmin padre, boolean modal) {
		super(padre);
		this.setModal(modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\anazk\\3EBAL\\RetoFinal\\resources/icono.jpg"));
		setBounds(100, 100, 564, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblGestionJugadores = new JLabel("GESTION DE JUGADORES:");
			GridBagConstraints gbc_lblGestionJugadores = new GridBagConstraints();
			gbc_lblGestionJugadores.insets = new Insets(0, 0, 5, 5);
			gbc_lblGestionJugadores.gridx = 1;
			gbc_lblGestionJugadores.gridy = 0;
			contentPanel.add(lblGestionJugadores, gbc_lblGestionJugadores);
		}
		{
			JLabel lblDni = new JLabel("DNI");
			GridBagConstraints gbc_lblDni = new GridBagConstraints();
			gbc_lblDni.insets = new Insets(0, 0, 5, 5);
			gbc_lblDni.gridx = 1;
			gbc_lblDni.gridy = 2;
			contentPanel.add(lblDni, gbc_lblDni);
		}
		{
			txtDNI = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 3;
			gbc_textField.gridy = 2;
			contentPanel.add(txtDNI, gbc_textField);
			txtDNI.setColumns(10);
		}
		{
			btnAlta = new JButton("ALTA");
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 8;
			gbc_btnNewButton.gridy = 3;
			contentPanel.add(btnAlta, gbc_btnNewButton);
			btnAlta.addActionListener(this);

		}
		{
			JLabel lblNombre = new JLabel("NOMBRE");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 1;
			gbc_lblNombre.gridy = 4;
			contentPanel.add(lblNombre, gbc_lblNombre);
		}
		{
			txtNombre = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 3;
			gbc_textField_1.gridy = 4;
			contentPanel.add(txtNombre, gbc_textField_1);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblApellido = new JLabel("APELLIDO");
			GridBagConstraints gbc_lblApellido = new GridBagConstraints();
			gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
			gbc_lblApellido.gridx = 1;
			gbc_lblApellido.gridy = 6;
			contentPanel.add(lblApellido, gbc_lblApellido);
		}
		{
			txtApellido = new JTextField();
			GridBagConstraints gbc_txtApellido = new GridBagConstraints();
			gbc_txtApellido.insets = new Insets(0, 0, 5, 5);
			gbc_txtApellido.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtApellido.gridx = 3;
			gbc_txtApellido.gridy = 6;
			contentPanel.add(txtApellido, gbc_txtApellido);
			txtApellido.setColumns(10);
		}
		{
			btnBaja = new JButton("BAJA");
			GridBagConstraints gbc_btnBaja = new GridBagConstraints();
			gbc_btnBaja.insets = new Insets(0, 0, 5, 5);
			gbc_btnBaja.gridx = 8;
			gbc_btnBaja.gridy = 7;
			contentPanel.add(btnBaja, gbc_btnBaja);
			btnBaja.addActionListener(this);
		}
		{
			JLabel lblDorsal = new JLabel("DORSAL");
			GridBagConstraints gbc_lblDorsal = new GridBagConstraints();
			gbc_lblDorsal.insets = new Insets(0, 0, 5, 5);
			gbc_lblDorsal.gridx = 1;
			gbc_lblDorsal.gridy = 8;
			contentPanel.add(lblDorsal, gbc_lblDorsal);
		}
		{
			txtDorsal = new JTextField();
			GridBagConstraints gbc_txtDorsal = new GridBagConstraints();
			gbc_txtDorsal.insets = new Insets(0, 0, 5, 5);
			gbc_txtDorsal.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDorsal.gridx = 3;
			gbc_txtDorsal.gridy = 8;
			contentPanel.add(txtDorsal, gbc_txtDorsal);
			txtDorsal.setColumns(10);
		}
		{
			JLabel lblPosicion = new JLabel("POSICION");
			GridBagConstraints gbc_lblPosicion = new GridBagConstraints();
			gbc_lblPosicion.insets = new Insets(0, 0, 5, 5);
			gbc_lblPosicion.gridx = 1;
			gbc_lblPosicion.gridy = 10;
			contentPanel.add(lblPosicion, gbc_lblPosicion);
		}
		grupoPosicion = new ButtonGroup();
		{
			rdbtnQuarterback = new JRadioButton("Quarterback");
			grupoPosicion.add(rdbtnQuarterback);
			GridBagConstraints gbc_rdbtnQuarterback = new GridBagConstraints();
			gbc_rdbtnQuarterback.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnQuarterback.gridx = 3;
			gbc_rdbtnQuarterback.gridy = 10;
			contentPanel.add(rdbtnQuarterback, gbc_rdbtnQuarterback);
		}
		{
			rdbtnTackle = new JRadioButton("Tackle");
			grupoPosicion.add(rdbtnTackle);
			GridBagConstraints gbc_rdbtnTackle = new GridBagConstraints();
			gbc_rdbtnTackle.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnTackle.gridx = 4;
			gbc_rdbtnTackle.gridy = 10;
			contentPanel.add(rdbtnTackle, gbc_rdbtnTackle);
		}
		{
			rdbtnRunning = new JRadioButton("Running");
			grupoPosicion.add(rdbtnRunning);
			GridBagConstraints gbc_rdbtnRunning = new GridBagConstraints();
			gbc_rdbtnRunning.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnRunning.gridx = 3;
			gbc_rdbtnRunning.gridy = 11;
			contentPanel.add(rdbtnRunning, gbc_rdbtnRunning);
		}
		{
			rdbtnGuard = new JRadioButton("Guard");
			grupoPosicion.add(rdbtnGuard);
			GridBagConstraints gbc_Guard = new GridBagConstraints();
			gbc_Guard.insets = new Insets(0, 0, 5, 5);
			gbc_Guard.gridx = 4;
			gbc_Guard.gridy = 11;
			contentPanel.add(rdbtnGuard, gbc_Guard);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			GridBagConstraints gbc_btnModificar = new GridBagConstraints();
			gbc_btnModificar.insets = new Insets(0, 0, 5, 5);
			gbc_btnModificar.gridx = 8;
			gbc_btnModificar.gridy = 11;
			contentPanel.add(btnModificar, gbc_btnModificar);
			btnModificar.addActionListener(this);
		}

		{
			JLabel lblCodEquipo = new JLabel("COD EQUIPO");
			GridBagConstraints gbc_lblCodEquipo = new GridBagConstraints();
			gbc_lblCodEquipo.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodEquipo.gridx = 1;
			gbc_lblCodEquipo.gridy = 13;
			contentPanel.add(lblCodEquipo, gbc_lblCodEquipo);
		}
		{
			txtCodEquipo = new JTextField();
			GridBagConstraints gbc_txtCodEquipo = new GridBagConstraints();
			gbc_txtCodEquipo.insets = new Insets(0, 0, 5, 5);
			gbc_txtCodEquipo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCodEquipo.gridx = 3;
			gbc_txtCodEquipo.gridy = 13;
			contentPanel.add(txtCodEquipo, gbc_txtCodEquipo);
			txtCodEquipo.setColumns(10);
		}
		{
			btnLimpiar = new JButton("LIMPIAR");
			GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
			gbc_btnLimpiar.insets = new Insets(0, 0, 5, 5);
			gbc_btnLimpiar.gridx = 8;
			gbc_btnLimpiar.gridy = 13;
			contentPanel.add(btnLimpiar, gbc_btnLimpiar);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAlta)) {
			altaJugador();
		} else if (e.getSource().equals(btnBaja)) {
			bajaJugador();
		} else if (e.getSource().equals(btnModificar)) {
			modJugador();
		} else if (e.getSource().equals(btnLimpiar)) {
			limipiar();
		}

	}

	private void limipiar() {
		txtDNI.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtDorsal.setText("");
		rdbtnGuard.setSelected(false);
		rdbtnQuarterback.setSelected(false);
		rdbtnRunning.setSelected(false);
		rdbtnTackle.setSelected(false);
		txtCodEquipo.setText("");
	}

	private void bajaJugador() {
		Jugador j = new Jugador();
		j.setDni(txtDNI.getText());
		Principal.EliminarJugador(j);
		dispose();
	}

	private void modJugador() {
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
		j.setCod_equi(txtCodEquipo.getText());
		Principal.modificarJugador(j);
		dispose();

	}

	private void altaJugador() {
		Jugador j = new Jugador();
		j.setDni(txtDNI.getText());
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
		j.setCod_equi(txtCodEquipo.getText());
		Principal.altaJugador(j);
		dispose();

	}
	
	

}
