package interfacesGraficas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import controlRiegoAutomatizado.*;
import baseDeDatos.DatosRiego;

public class GestionCultivos extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private DefaultListModel<String> modeloLista;

    private String regionAgricultor;
    private int idAgricultor;

    // Componentes
    private JPanel jPanel1;
    private JButton BotonSiguiente, BotonLimpiar, BotonAñadir, BotonBorrarTodo;
    private JList<String> jList1;
    private JScrollPane scrollLista;
    private JLabel LabelCultivo, LabelRegistro, LabelSubtituloLista, jLabelFondo, jLabelPanelMarron, jLabelLogo;
    private JTextField TextoCultivo;
    
    // Colores
    private final Color COLOR_VERDE = new Color(106, 153, 43);
    private final Color COLOR_ROJO = new Color(180, 60, 60);
    private final Color COLOR_TEXTO_OSCURO = new Color(60, 60, 60);

    // --- CONSTRUCTOR: PIDE ID Y REGIÓN ---
    public GestionCultivos(String region, int id) {
        this.regionAgricultor = region;
        this.idAgricultor = id; // Guardamos el ID para usarlo luego
        
        initComponents();
        this.setLocationRelativeTo(null);
        
        // Ponemos el dato en el título para verificar que llegó bien
        this.setTitle("Gestión de Cultivos - Región: " + region + " (ID: " + id + ")");
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        modeloLista = new DefaultListModel<>();

        jPanel1 = new JPanel();
        jPanel1.setLayout(null);

        // --- TÍTULOS ---
        JLabel sombraTitulo = new JLabel("ANÁLISIS DE CULTIVOS", SwingConstants.CENTER);
        sombraTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        sombraTitulo.setForeground(new Color(99, 58, 39));
        sombraTitulo.setBounds(103, 23, 780, 60);
        jPanel1.add(sombraTitulo);
        LabelRegistro = new JLabel("ANÁLISIS DE CULTIVOS", SwingConstants.CENTER);
        LabelRegistro.setFont(new Font("Segoe UI", Font.BOLD, 42));
        LabelRegistro.setForeground(new Color(225, 205, 170));
        LabelRegistro.setBounds(100, 20, 780, 60);
        jPanel1.add(LabelRegistro);

        // --- CAMPO CULTIVO ---
        LabelCultivo = new JLabel("CULTIVO:", SwingConstants.RIGHT);
        LabelCultivo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        LabelCultivo.setForeground(Color.WHITE);
        LabelCultivo.setBounds(180, 150, 150, 40);
        jPanel1.add(LabelCultivo);

        TextoCultivo = new JTextField();
        TextoCultivo.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        TextoCultivo.setForeground(COLOR_TEXTO_OSCURO);
        TextoCultivo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_VERDE, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        TextoCultivo.setBounds(350, 150, 300, 45);
        TextoCultivo.addActionListener(this::TextoCultivoActionPerformed);
        jPanel1.add(TextoCultivo);

        // --- BOTONES ---
        BotonAñadir = crearBotonModerno("Añadir", COLOR_VERDE);
        BotonAñadir.setBounds(350, 220, 140, 45);
        BotonAñadir.addActionListener(this::BotonAñadirActionPerformed);
        jPanel1.add(BotonAñadir);

        BotonLimpiar = crearBotonModerno("Limpiar", COLOR_ROJO);
        BotonLimpiar.setBounds(510, 220, 140, 45);
        BotonLimpiar.addActionListener(this::BotonLimpiarActionPerformed);
        jPanel1.add(BotonLimpiar);

        // --- LISTA ---
        LabelSubtituloLista = new JLabel("Lista de cultivos a analizar (Selecciona para borrar):");
        LabelSubtituloLista.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        LabelSubtituloLista.setForeground(Color.WHITE);
        LabelSubtituloLista.setBounds(350, 275, 350, 20);
        jPanel1.add(LabelSubtituloLista);

        jList1 = new JList<>();
        jList1.setModel(modeloLista);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jList1.setSelectionBackground(new Color(220, 240, 200));
        jList1.setSelectionForeground(COLOR_TEXTO_OSCURO);
        
        scrollLista = new JScrollPane(jList1);
        scrollLista.setBorder(new LineBorder(COLOR_VERDE, 1));
        scrollLista.setBounds(350, 295, 300, 130); 
        jPanel1.add(scrollLista);

        // --- BOTONES FINALES ---
        BotonBorrarTodo = crearBotonModerno("Borrar seleccionado", COLOR_ROJO);
        BotonBorrarTodo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        BotonBorrarTodo.setBounds(700, 370, 160, 45);
        BotonBorrarTodo.addActionListener(this::BotonBorrarTodoActionPerformed);
        jPanel1.add(BotonBorrarTodo);

        BotonSiguiente = crearBotonModerno("Siguiente >", COLOR_VERDE);
        BotonSiguiente.setFont(new Font("Segoe UI", Font.BOLD, 16));
        BotonSiguiente.setBounds(700, 430, 160, 50);
        BotonSiguiente.addActionListener(this::BotonSiguienteActionPerformed);
        jPanel1.add(BotonSiguiente);

        // --- IMÁGENES ---
        jLabelLogo = new JLabel();
        cargarImagen(jLabelLogo, "/imagenesParaGUI/planta.JPG");
        jLabelLogo.setBounds(40, 30, 170, 140);
        jPanel1.add(jLabelLogo);

        jLabelPanelMarron = new JLabel();
        cargarImagen(jLabelPanelMarron, "/imagenesParaGUI/fonfomarron.JPG");
        jLabelPanelMarron.setBounds(100, 30, 780, 470); 
        jPanel1.add(jLabelPanelMarron);

        jLabelFondo = new JLabel();
        cargarImagen(jLabelFondo, "/imagenesParaGUI/fondo.JPG");
        jLabelFondo.setBounds(0, 0, 1000, 600);
        jPanel1.add(jLabelFondo);

        setPreferredSize(new Dimension(980, 570));
        getContentPane().add(jPanel1);
        pack();
    }

    // --- MÉTODOS AUXILIARES ---
    private JButton crearBotonModerno(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void cargarImagen(JLabel label, String ruta) {
        try { label.setIcon(new ImageIcon(getClass().getResource(ruta))); } 
        catch (Exception e) { label.setText("Error IMG"); }
    }

    // --- ACCIONES ---
    private void TextoCultivoActionPerformed(ActionEvent evt) { BotonAñadir.doClick(); }

    private void BotonAñadirActionPerformed(ActionEvent evt) {
        String cultivo = TextoCultivo.getText().trim();
        if (!cultivo.isEmpty()) {
            modeloLista.addElement(cultivo);
            TextoCultivo.setText("");
            TextoCultivo.requestFocus();
        } else { Toolkit.getDefaultToolkit().beep(); }
    }

    private void BotonLimpiarActionPerformed(ActionEvent evt) {
        TextoCultivo.setText("");
        TextoCultivo.requestFocus();
    }

    private void BotonBorrarTodoActionPerformed(ActionEvent evt) {
        int indiceSeleccionado = jList1.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            modeloLista.remove(indiceSeleccionado);
        } else {
            if (!modeloLista.isEmpty()) JOptionPane.showMessageDialog(this, "Selecciona un cultivo para borrar.");
        }
        TextoCultivo.requestFocus();
    }

    private void BotonSiguienteActionPerformed(ActionEvent evt) {
        if (modeloLista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Añade al menos un cultivo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Lista para guardar los objetos Cultivo YA procesados y pasarlos a la otra ventana
        ArrayList<Cultivo> listaProcesada = new ArrayList<>();

        GeneradorDeEstrategias generador = new GeneradorDeEstrategias();
        DatosRiego daoRiego = new DatosRiego();
        
        // Procesamos
        for (int i = 0; i < modeloLista.getSize(); i++) {
            String nombreCultivo = modeloLista.getElementAt(i);
            
            // Creamos
            Cultivo miCultivo = new Cultivo(nombreCultivo, regionAgricultor, "Verano");
            
            // Analizamos
            EstrategiaRiego estrategia = generador.definirEstrategia(miCultivo);
            miCultivo.setEstrategia(estrategia);
            miCultivo.ejecutarRiego(); // Sensores generan datos AQUÍ
            
            // Guardamos en BD
            double temp = miCultivo.getTemperaturaActual();
            double hum = miCultivo.getHumedadActual();
            String nomEst = estrategia.getNombreEstrategia();
            
            daoRiego.guardarRiego(idAgricultor, nombreCultivo, temp, hum, nomEst);
            
            // AGREGAMOS A LA LISTA PARA PASARLA A LA OTRA VENTANA
            listaProcesada.add(miCultivo);
        }

        // Abrimos la ventana de resultados
        VentanaResultados ventanaFinal = new VentanaResultados(idAgricultor, listaProcesada);
        ventanaFinal.setVisible(true);
        this.dispose(); // Cerramos esta
    }

    public static void main(String args[]) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ex) {}
        // Probamos con un ID ficticio (1) y una región
        EventQueue.invokeLater(() -> new GestionCultivos("Prueba", 1).setVisible(true));
    }
}