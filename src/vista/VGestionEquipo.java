package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Principal;
import modelo.Competicion;
import modelo.Equipo;

public class VGestionEquipo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodEq;
	private JTextField txtNombreEq;
	private JButton btnAlta;
	private JButton btnBaja;
	private JButton btnModificar;
	private JButton btnLimpiar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			VGestionEquipo dialog = new VGestionEquipo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 * @param modal 
	 * @param padre 
	 */
	public VGestionEquipo(VMenuAdmin padre, boolean modal) {
		super(padre);
		this.setModal(modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\anazk\\3EBAL\\RetoFinal\\resources/icono.jpg"));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	
			JLabel lblCodEq = new JLabel("Codigo de Equipo:");
			lblCodEq.setBounds(29, 90, 149, 14);
			contentPanel.add(lblCodEq);
			
			JLabel lblNombreEq = new JLabel("Nombre del Equipo:");
			lblNombreEq.setBounds(29, 153, 149, 14);
			contentPanel.add(lblNombreEq);
			
			txtCodEq = new JTextField();
			txtCodEq.setBounds(238, 87, 96, 20);
			contentPanel.add(txtCodEq);
			txtCodEq.setColumns(10);
			
			txtNombreEq = new JTextField();
			txtNombreEq.setBounds(238, 150, 96, 20);
			contentPanel.add(txtNombreEq);
			txtNombreEq.setColumns(10);
			
			btnAlta = new JButton("ALTA");
			btnAlta.addActionListener(this);
			btnAlta.setBounds(29, 213, 89, 23);
			contentPanel.add(btnAlta);
			
			btnBaja = new JButton("BAJA");
			btnBaja.addActionListener(this);
			btnBaja.setBounds(137, 213, 89, 23);
			contentPanel.add(btnBaja);
			
			btnModificar = new JButton("MODIFICAR");
			btnModificar.addActionListener(this);
			btnModificar.setBounds(238, 213, 89, 23);
			contentPanel.add(btnModificar);
			
			btnLimpiar = new JButton("LIMPIAR");
			btnLimpiar.addActionListener(this);
			btnLimpiar.setBounds(337, 213, 89, 23);
			contentPanel.add(btnLimpiar);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnAlta)) {
				altaEquipo();
			} else if(e.getSource().equals(btnBaja)) {
				bajaEquipo();
			}else if (e.getSource().equals(btnModificar)) {
				modificarEquipo();
			}else if(e.getSource().equals(btnLimpiar)) {
				limpiarDatos();
			}
			
		}

		private void limpiarDatos() {
			txtCodEq.setText("");
			txtNombreEq.setText("");
		}

		private void modificarEquipo() {
			Equipo eq = new Equipo();
			eq.setNombre_equipo(txtNombreEq.getText());
			Principal.modificarEquipo(eq);
		}

		private void bajaEquipo() {
			Equipo eq = new Equipo();
			eq.setCod_equi(txtCodEq.getText());
			Principal.bajaEquipo(eq);
		}

		private void altaEquipo() {
			Equipo eq = new Equipo();
			eq.setCod_equi(txtCodEq.getText());
			eq.setNombre_equipo(txtNombreEq.getText());
			Principal.altaEquipo(eq);
		}
}
