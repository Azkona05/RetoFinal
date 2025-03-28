package vista;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Principal;
import modelo.Competicion;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class VGestionCompeticion extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodComp;
	private JTextField txtNombreComp;
	private JButton btnAlta;
	private JButton btnBaja;
	private JButton btnModificar;
	private JButton btnLimpiar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			VGestionCompeticion dialog = new VGestionCompeticion();
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
	public VGestionCompeticion(VMenuAdmin padre, boolean modal) {
		super(padre);
		this.setModal(modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\anazk\\3EBAL\\RetoFinal\\resources/icono.jpg"));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCodComp = new JLabel("Codigo de Competicion:");
		lblCodComp.setBounds(29, 90, 149, 14);
		contentPanel.add(lblCodComp);
		
		JLabel lblNombreComp = new JLabel("Nombre de la Competicion:");
		lblNombreComp.setBounds(29, 153, 149, 14);
		contentPanel.add(lblNombreComp);
		
		txtCodComp = new JTextField();
		txtCodComp.setBounds(238, 87, 96, 20);
		contentPanel.add(txtCodComp);
		txtCodComp.setColumns(10);
		
		txtNombreComp = new JTextField();
		txtNombreComp.setBounds(238, 150, 96, 20);
		contentPanel.add(txtNombreComp);
		txtNombreComp.setColumns(10);
		
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
			altaCompeticion();
		} else if(e.getSource().equals(btnBaja)) {
			bajaCompeticion();
		}else if (e.getSource().equals(btnModificar)) {
			modificarCompeticion();
		}else if(e.getSource().equals(btnLimpiar)) {
			limpiarDatos();
		}
		
	}

	private void limpiarDatos() {
		txtCodComp.setText("");
		txtNombreComp.setText("");
	}

	private void modificarCompeticion() {
		Competicion comp = new Competicion();
		comp.setNombre_competicion(txtNombreComp.getText());
		Principal.modificarCompeticion(comp);
	}

	private void bajaCompeticion() {
		Competicion comp = new Competicion();
		comp.setCod_comp(txtCodComp.getText());
		Principal.eliminarCompeticion(comp);
	}

	private void altaCompeticion() {
		Competicion comp = new Competicion();
		comp.setCod_comp(txtCodComp.getText());
		comp.setNombre_competicion(txtNombreComp.getText());
		Principal.altaCompeticion(comp);
	}
}
