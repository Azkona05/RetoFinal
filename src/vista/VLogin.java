package vista;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Usuario;
import javax.swing.ImageIcon;
import java.awt.Font;

public class VLogin extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JLabel lblUsuario, lblContra;
	private JPasswordField passwordField;
	private JButton btnComprobar;
	private JButton btnCancelar;

	/**
	 * Constructor de la ventana de login.
	 * Inicializa los componentes gráficos y define la configuración inicial de la ventana.
	 * 
	 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
	 * @param padre Ventana principal que invoca el login.
	 * @param modal Indica si la ventana será modal o no.
	 */
	public VLogin(VMenuPrincipal padre, boolean modal) {
		super(padre);
		this.setModal(modal);
		setTitle("Iniciar Sesion");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icono.jpg")));
		setBounds(100, 100, 705, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(119, 109, 147, 29);
		contentPane.add(lblUsuario);

		lblContra = new JLabel("Contraseña: ");
		lblContra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContra.setBounds(119, 174, 147, 29);
		contentPane.add(lblContra);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(this);
		btnComprobar.setBounds(413, 288, 122, 29);
		contentPane.add(btnComprobar);
		btnComprobar.setBackground(Color.WHITE);
		btnComprobar.setBorder(new LineBorder(Color.GREEN));

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(159, 288, 112, 29);
		contentPane.add(btnCancelar);
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBorder(new LineBorder(Color.RED));

		txtUsuario = new JTextField();
		txtUsuario.setBounds(360, 109, 175, 29);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(360, 174, 175, 29);
		contentPane.add(passwordField);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon("src\\resources\\iconoPersonaEditado.png"));
		lblImagen.setBounds(319, 11, 67, 87);
		contentPane.add(lblImagen);
	}

	/**
	 * Método que maneja los eventos de los botones en la ventana de login.
	 * 
	 * @param e El evento que se genera al hacer clic en uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Crear objeto para manejar los datos del usuario
		if (e.getSource().equals(btnComprobar)) {
			comprobar();
		} else if (e.getSource().equals(btnCancelar)) {
			dispose();
		}
	}

	/**
	 * Método para verificar las credenciales de inicio de sesión.
	 * Obtiene el nombre de usuario y la contraseña del formulario,
	 * y llama al método login de la clase Principal para verificar el usuario.
	 * 
	 * Si las credenciales son correctas, se muestra el menú de administración.
	 * Si hay un error, se muestra un mensaje de error.
	 */
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
