package vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Equipo;

import javax.swing.JLabel;


public class VEquipo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;

	public VEquipo(VMenuPrincipal padre, boolean modal, String equi) {
		super (padre);
		setModal(modal);
		getContentPane().setLayout(null);
		
		JLabel lblNombreEquipo = new JLabel("");
		lblNombreEquipo.setBounds(23, 31, 136, 26);
		getContentPane().add(lblNombreEquipo);
		

	}

}
