package vista;

import java.awt.BorderLayout;

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
	private JButton btnJugador; 
	private JButton btnCompeticion;
	private JButton btnEquipo;
	private JButton btnPartido;
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
			{
				btnJugador = new JButton("Jugador");
				btnJugador.addActionListener(this);
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 1;
				gbc_btnNewButton.gridy = 3;
				contentPanel.add(btnJugador, gbc_btnNewButton);
			}
		}
		{
			btnCompeticion = new JButton("Competicion");
			btnCompeticion.addActionListener(this);
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);

			gbc_btnNewButton_1.gridx = 6;
			gbc_btnNewButton_1.gridy = 3;
			contentPanel.add(btnCompeticion, gbc_btnNewButton_1);
		}
		{
			btnEquipo = new JButton("Equipo");
			btnEquipo.addActionListener(this);
			GridBagConstraints gbc_btnModificacion_1 = new GridBagConstraints();
			gbc_btnModificacion_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnModificacion_1.gridx = 9;
			gbc_btnModificacion_1.gridy = 3;
			contentPanel.add(btnEquipo, gbc_btnModificacion_1);
		}
		{
			btnPartido = new JButton("Partidos");
			btnPartido.addActionListener(this);
			GridBagConstraints gbc_btnModificacion_1 = new GridBagConstraints();
			gbc_btnModificacion_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnModificacion_1.gridx = 12;
			gbc_btnModificacion_1.gridy = 3;
			contentPanel.add(btnPartido, gbc_btnModificacion_1);
		}
		{
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(this);
			GridBagConstraints gbc_btnSalir = new GridBagConstraints();
			gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
			gbc_btnSalir.gridx = 15;
			gbc_btnSalir.gridy = 3;
			contentPanel.add(btnSalir, gbc_btnSalir);
		}

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnJugador)) {
			VGestionJugador va = new VGestionJugador(this, true);
			va.setVisible(true);
		}else if (e.getSource().equals(btnCompeticion)) {
			VGestionCompeticion vb = new VGestionCompeticion(this, true);
			vb.setVisible(true);
		}else if (e.getSource().equals(btnEquipo)) {
			VGestionEquipo vm = new VGestionEquipo(this, true);
			vm.setVisible(true);
		}else if (e.getSource().equals(btnSalir)) {
			VGestionPartidos vp = new VGestionPartidos (this, true);
			vp.setVisible(true);
		} else {
			dispose();
		}
		
	}

}
