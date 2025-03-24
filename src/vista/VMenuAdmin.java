package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMenuAdmin extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnAlta; 
	private JButton btnBaja;
	private JButton btnModificacion;
	private JButton btnNewButton;
	private JButton btnSalir;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			VMenuAdmin dialog = new VMenuAdmin();
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
	public VMenuAdmin(VLogin padre, boolean modal) {
		super(padre);
		this.setModal(modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\anazk\\3EBAL\\RetoFinal\\resources/icono.jpg"));
		setBounds(100, 100, 605, 416);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			btnAlta = new JButton("Alta");
			btnAlta.addActionListener(this);
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 3;
			gbc_btnNewButton.gridy = 3;
			contentPanel.add(btnAlta, gbc_btnNewButton);
		}
		{
			btnBaja = new JButton("Baja");
			btnBaja.addActionListener(this);
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_1.gridx = 10;
			gbc_btnNewButton_1.gridy = 3;
			contentPanel.add(btnBaja, gbc_btnNewButton_1);
		}
		{
			btnModificacion = new JButton("Modificacion");
			btnModificacion.addActionListener(this);
			GridBagConstraints gbc_btnModificacion_1 = new GridBagConstraints();
			gbc_btnModificacion_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnModificacion_1.gridx = 6;
			gbc_btnModificacion_1.gridy = 7;
			contentPanel.add(btnModificacion, gbc_btnModificacion_1);
		}
		{
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(this);
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.gridx = 14;
			gbc_btnNewButton.gridy = 11;
			contentPanel.add(btnSalir, gbc_btnNewButton);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAlta)) {
			VAlta va = new VAlta(this, true);
			va.setVisible(true);
		}else if (e.getSource().equals(btnBaja)) {
			VBaja vb = new VBaja(this, true);
			vb.setVisible(true);
		}else if (e.getSource().equals(btnModificacion)) {
			VModificar vm = new VModificar(this, true);
			vm.setVisible(true);
		}else if (e.getSource().equals(btnSalir)) {
			dispose();
		}
		
	}

}
