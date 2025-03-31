package vista;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Usuario;

public class VLogin extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JLabel lblUsuario, lblContra;
	private JPasswordField passwordField;
	private JButton btnComprobar;
	private JButton btnCancelar;
	public VLogin(VMenuPrincipal padre, boolean modal) {
		super(padre);
		this.setModal(modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\anazk\\3EBAL\\RetoFinal\\resources/icono.jpg"));
		setBounds(100, 100, 705, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(119, 109, 147, 29);
		contentPane.add(lblUsuario);

		lblContra = new JLabel("Contraseña: ");
		lblContra.setBounds(119, 174, 147, 29);
		contentPane.add(lblContra);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(this);
		btnComprobar.setBounds(159, 288, 112, 29);
		contentPane.add(btnComprobar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(413, 288, 122, 29);
		contentPane.add(btnCancelar);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(305, 109, 331, 29);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(305, 174, 331, 29);
		contentPane.add(passwordField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Crear objeto para manejar los datos del usuario
		if (e.getSource().equals(btnComprobar)) {
			comprobar();
		} else if (e.getSource().equals(btnCancelar)) {
			dispose();
		}
	}

	public void comprobar() {
		Usuario usuario = new Usuario();
		usuario.setNombre(txtUsuario.getText());
		usuario.setPassword(new String(passwordField.getPassword()));
		// Comprobar login correcto
		try {
			Principal.login(usuario);
			VMenuAdmin menu = new VMenuAdmin(this, true);
			menu.setVisible(true);
		} catch (LoginException e) {
			e.visualizarMsg();
		}
	}
}
