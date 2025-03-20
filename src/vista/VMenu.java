package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.LoginException;

public class VMenu extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnAlta;
	private JButton btnBaja;
	private JButton btnConsulta;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			VMenu dialog = new VMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 * @param b 
	 * @param vLogin 
	 */
	public VMenu(VLogin padre, boolean modal) {
		super(padre);
		this.setModal(modal);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnAlta = new JButton("ALTA");
		btnAlta.addActionListener(this);
		btnAlta.setBounds(35, 28, 159, 95);
		contentPanel.add(btnAlta);

		btnBaja = new JButton("BAJA");
		btnBaja.addActionListener(this);
		btnBaja.setBounds(35, 133, 159, 95);
		contentPanel.add(btnBaja);

		btnConsulta = new JButton("CONSULTA");
		btnConsulta.addActionListener(this);
		btnConsulta.setBounds(212, 30, 179, 93);
		contentPanel.add(btnConsulta);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(222, 133, 169, 95);
		contentPanel.add(btnModificar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAlta)) {
			VAlta alta;
			alta = new VAlta(this, true);
			alta.setVisible(true);
		} else if (e.getSource().equals(btnBaja)) {
			VBaja baja = new VBaja(this, true);
			baja.setVisible(true);
		} else if (e.getSource().equals(btnConsulta)) {
			VConsulta consulta = new VConsulta(this, true);
			consulta.setVisible(true);
		} else if (e.getSource().equals(btnModificar)) {
			VModificar modificar = new VModificar(this, true);
			modificar.setVisible(true);
		}

	}
}

