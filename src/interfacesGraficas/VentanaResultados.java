package interfacesGraficas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import controlRiegoAutomatizado.Cultivo;
import controlRiegoAutomatizado.EstrategiaRiego;

public class VentanaResultados extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes
    private JPanel jPanel1;
    private JLabel LabelTitulo, LabelSombraTitulo, jLabelFondo;
    private JTable jTable1;
    private JScrollPane scrollPaneTabla;
    private JButton BotonVolver, BotonRegarSeleccionado, BotonRegarTodo;
    private DefaultTableModel modeloTabla;

    // Colores
    private final Color COLOR_VERDE = new Color(106, 153, 43);
    private final Color COLOR_TEXTO_OSCURO = new Color(60, 60, 60);
    private final Color COLOR_AZUL_RIEGO = new Color(0, 120, 215);
    private final Color COLOR_AZUL_TODO = new Color(0, 80, 180);

    // Variables de datos
    private int idAgricultor; // Usamos ID en vez de nombre para ser consistentes
    private List<Cultivo> listaCultivosRecibida;

    // --- CONSTRUCTOR ---
    public VentanaResultados(int idAgricultor, List<Cultivo> cultivosProcesados) {
        this.idAgricultor = idAgricultor;
        this.listaCultivosRecibida = cultivosProcesados;

        initComponents();
        this.setLocationRelativeTo(null);
        
        // Cargamos los datos que nos pasaron desde la ventana anterior
        cargarDatosEnTabla(); 
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema Agrícola - Resultados del Riego");
        setResizable(false);

        jPanel1 = new JPanel();
        jPanel1.setLayout(null);

        // TÍTULOS
        LabelSombraTitulo = new JLabel("RESULTADOS DE RIEGO", SwingConstants.CENTER);
        LabelSombraTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        LabelSombraTitulo.setForeground(new Color(99, 58, 39));
        LabelSombraTitulo.setBounds(52, 52, 880, 60);
        jPanel1.add(LabelSombraTitulo);

        LabelTitulo = new JLabel("RESULTADOS DE RIEGO", SwingConstants.CENTER);
        LabelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        LabelTitulo.setForeground(new Color(225, 205, 170));
        LabelTitulo.setBounds(50, 50, 880, 60);
        jPanel1.add(LabelTitulo);

        // TABLA
        String[] columnas = {
            "ID Agri.", 
            "Cultivo", 
            "Región", 
            "Temp. (°C)", 
            "Humedad (%)", 
            "Estrategia Aplicada", 
            "Hora Riego"
        };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        jTable1 = new JTable(modeloTabla);
        estilizarTabla(jTable1);

        scrollPaneTabla = new JScrollPane(jTable1);
        scrollPaneTabla.setBorder(new LineBorder(COLOR_VERDE, 2));
        scrollPaneTabla.setBounds(50, 130, 880, 350);
        jPanel1.add(scrollPaneTabla);

        // BOTONES
        BotonRegarSeleccionado = crearBotonModerno("Ver Informe Detallado", COLOR_AZUL_RIEGO);
        BotonRegarSeleccionado.setBounds(90, 500, 220, 45);
        BotonRegarSeleccionado.addActionListener(this::BotonRegarSeleccionadoActionPerformed);
        jPanel1.add(BotonRegarSeleccionado);

        BotonRegarTodo = crearBotonModerno("Finalizar Riego", COLOR_AZUL_TODO);
        BotonRegarTodo.setBounds(330, 500, 180, 45);
        BotonRegarTodo.addActionListener(this::BotonRegarTodoActionPerformed);
        jPanel1.add(BotonRegarTodo);

        BotonVolver = crearBotonModerno("Cerrar Sesión", new Color(180, 60, 60));
        BotonVolver.setBounds(750, 500, 160, 45);
        BotonVolver.addActionListener(this::BotonVolverActionPerformed);
        jPanel1.add(BotonVolver);

        // FONDOS
        jLabelFondo = new JLabel();
        cargarImagen(jLabelFondo, "/imagenesParaGUI/fondo.JPG");
        jLabelFondo.setBounds(0, 0, 1000, 600);
        jPanel1.add(jLabelFondo);

        setPreferredSize(new Dimension(980, 600));
        getContentPane().add(jPanel1);
        pack();
    }

    // --- CARGAR DATOS ---
    private void cargarDatosEnTabla() {
        modeloTabla.setRowCount(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaActual = LocalDateTime.now().format(dtf);

        for (Cultivo c : listaCultivosRecibida) {
            
            double temp = c.getTemperaturaActual();
            double hum = c.getHumedadActual();
            

            String estrategiaReal = c.getNombreEstrategia(); 
            
            Object[] fila = {
                idAgricultor,
                c.getTipo(),
                c.getRegion(),
                String.format("%.1f", temp),
                String.format("%.1f", hum),
                estrategiaReal,
                horaActual
            };
            modeloTabla.addRow(fila);
        }
    }

    // --- DISEÑO ---
    private void estilizarTabla(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.setForeground(COLOR_TEXTO_OSCURO);
        table.setSelectionBackground(new Color(180, 220, 255));
        table.setSelectionForeground(Color.BLACK);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(COLOR_VERDE);
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    private JButton crearBotonModerno(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void cargarImagen(JLabel label, String ruta) {
        try { label.setIcon(new ImageIcon(getClass().getResource(ruta))); } catch (Exception e) {}
    }

    // --- ACCIONES ---
    private void BotonRegarSeleccionadoActionPerformed(ActionEvent evt) {
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila para ver el informe.");
            return;
        }
        Cultivo cultivo = listaCultivosRecibida.get(fila);
        JOptionPane.showMessageDialog(this, cultivo.obtenerInformeRiego(), "Informe Técnico", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void BotonRegarTodoActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Todos los datos han sido guardados en la Base de Datos Histórica.\nProceso Finalizado.");
        System.exit(0); // Cierra todo
    }

    private void BotonVolverActionPerformed(ActionEvent evt) {
        // Volver al Login
        new registro().setVisible(true);
        this.dispose();
    }
}