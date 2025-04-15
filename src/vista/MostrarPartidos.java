package vista;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Principal;
import excepciones.LoginException;
import modelo.Partido;

/**
 * Diálogo que muestra una lista de partidos y permite seleccionarlos para ver más detalles.
 * Permite la interacción mediante doble clic sobre un partido para abrir su vista detallada.
 * 
 * @author [Incluir autores]
 * @version 1.0
 */
public class MostrarPartidos extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;

    /**
     * Constructor que crea el diálogo para mostrar los partidos.
     * 
     * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
     * @param padre El JFrame padre de este diálogo
     * @param modal Indica si el diálogo debe ser modal (true) o no (false)
     */
    public MostrarPartidos(VMenuAdmin padre, boolean modal) {
        super(padre);
        this.setModal(modal);
        setTitle("Lista de Partidos");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icono.jpg")));
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout());

        String[] columnNames = { "Codigo", "Local", "Visitante", "Ganador", "Fecha", "Codigo de competicion" };
        model = new DefaultTableModel(columnNames, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Configura el listener para doble clic
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        int cod = (Integer) model.getValueAt(row, 0);
                        String eqLocal = (String) model.getValueAt(row, 1);
                        String eqVisitante = (String) model.getValueAt(row, 2);
                        String ganador = (String) model.getValueAt(row, 3);
                        java.sql.Date sqlDate = (java.sql.Date) model.getValueAt(row, 4);
                        LocalDate fecha = sqlDate.toLocalDate();
                        String codComp = (String) model.getValueAt(row, 5);
                        dispose();
                        padre.cargarDatosPart(cod, eqLocal, eqVisitante, ganador, fecha, codComp);
                        padre.setVisible(true);
                       // dispose();
                    }
                }
            }
        });

        try {
            cargarDatos();
        } catch (LoginException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Carga los datos de los partidos desde la capa de controlador.
     * 
     * @throws LoginException Si ocurre un error relacionado con la autenticación
     */
    private void cargarDatos() throws LoginException {
        Partido part = new Partido();
        Object[][] datos = Principal.devolverPartidos(part);
        actualizarDatos(datos);
    }

    /**
     * Actualiza la tabla con los datos proporcionados.
     * 
     * @param datos Matriz de objetos que contiene los datos de los partidos a mostrar
     */
    private void actualizarDatos(Object[][] datos) {
        model.setRowCount(0); // Limpiar tabla antes de agregar los nuevos datos
        for (Object[] fila : datos) {
            model.addRow(fila);
        }
    }
}