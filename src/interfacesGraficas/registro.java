package interfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import baseDeDatos.DatosAgricultor;

public class registro extends JFrame {

    // Componentes
    private JPanel jPanel1;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel5;
    private JButton BotonLimpiar, BotonRegistrar, BotonIniciarSesion; 
    private JComboBox<String> ComboRegion; 
    private JLabel LabelNombre, LabelApellido, LabelRegion, LabelDNI, LabelRegistro;
    private JTextField TextoDNI, TextoApellido, TextoNombre;

    public registro() {
        initComponents();
        setLocationRelativeTo(null); // Centrar ventana
    }

    private void initComponents() {
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registro de Agricultor");
        setResizable(false);

        jPanel1 = new JPanel();
        jPanel1.setLayout(null); 
        
        // ELEMENTOS DEL FORMULARIO

        // Título Principal
        LabelRegistro = new JLabel("REGISTRO DE AGRICULTOR");
        LabelRegistro.setFont(new Font("Roboto", Font.BOLD, 48));
        LabelRegistro.setForeground(new Color(99, 58, 39));
        LabelRegistro.setBounds(290, 40, 700, 60);
        jPanel1.add(LabelRegistro);

        // --- ETIQUETAS (LABELS) ---
        LabelDNI = new JLabel("DNI");
        LabelDNI.setFont(new Font("Roboto", Font.BOLD, 36));
        LabelDNI.setForeground(Color.WHITE);
        LabelDNI.setBounds(180, 190, 100, 40);
        jPanel1.add(LabelDNI);

        LabelNombre = new JLabel("NOMBRE");
        LabelNombre.setFont(new Font("Roboto", Font.BOLD, 36));
        LabelNombre.setForeground(Color.WHITE);
        LabelNombre.setBounds(180, 260, 200, 40);
        jPanel1.add(LabelNombre);

        LabelApellido = new JLabel("APELLIDO");
        LabelApellido.setFont(new Font("Roboto", Font.BOLD, 36));
        LabelApellido.setForeground(Color.WHITE);
        LabelApellido.setBounds(180, 330, 200, 40);
        jPanel1.add(LabelApellido);

        LabelRegion = new JLabel("REGIÓN");
        LabelRegion.setFont(new Font("Roboto", Font.BOLD, 36));
        LabelRegion.setForeground(Color.WHITE);
        LabelRegion.setBounds(180, 400, 200, 40);
        jPanel1.add(LabelRegion);

        // --- ESTILOS COMUNES ---
        Font fuenteTexto = new Font("Roboto", Font.PLAIN, 28); 
        javax.swing.border.Border paddingInterno = BorderFactory.createEmptyBorder(0, 15, 0, 0);

        // --- CAMPOS DE TEXTO ---
        TextoDNI = new JTextField("");
        TextoDNI.setFont(fuenteTexto);
        TextoDNI.setForeground(Color.GRAY);
        TextoDNI.setBorder(paddingInterno);
        TextoDNI.setBounds(390, 190, 230, 40);
        jPanel1.add(TextoDNI);

        TextoNombre = new JTextField("");
        TextoNombre.setFont(fuenteTexto);
        TextoNombre.setForeground(Color.GRAY);
        TextoNombre.setBorder(paddingInterno);
        TextoNombre.setBounds(390, 260, 300, 40);
        jPanel1.add(TextoNombre);

        TextoApellido = new JTextField("");
        TextoApellido.setFont(fuenteTexto);
        TextoApellido.setForeground(Color.GRAY);
        TextoApellido.setBorder(paddingInterno);
        TextoApellido.setBounds(390, 330, 300, 40);
        jPanel1.add(TextoApellido);

        // --- COMBOBOX ---
        String[] regiones = { "Costa o Chala", "Yunga", "Quechua", "Suni", "Puna", "Janca", "Rupa Rupa", "Omagua" };
        ComboRegion = new JComboBox<>(regiones);
        ComboRegion.setFont(fuenteTexto);
        ComboRegion.setForeground(Color.GRAY);
        ComboRegion.setBackground(Color.WHITE);
        ComboRegion.setBounds(390, 400, 240, 40); 
        jPanel1.add(ComboRegion);

        // --- BOTONES ---
        // Variable de color unificado
        Color colorBotones = new Color(85, 45, 45); 

        // 1. Botón Registrar
        BotonRegistrar = new JButton("Registrar");
        BotonRegistrar.setBackground(colorBotones);
        BotonRegistrar.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        BotonRegistrar.setForeground(Color.WHITE);
        BotonRegistrar.setBounds(230, 510, 180, 50);
        BotonRegistrar.addActionListener(this::BotonRegistrarActionPerformed);
        jPanel1.add(BotonRegistrar);

        // 2. Botón Limpiar 
        BotonLimpiar = new JButton("Limpiar");
        BotonLimpiar.setBackground(colorBotones);
        BotonLimpiar.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        BotonLimpiar.setForeground(Color.WHITE);
        BotonLimpiar.setBounds(450, 510, 160, 50);
        BotonLimpiar.addActionListener(this::BotonLimpiarActionPerformed);
        jPanel1.add(BotonLimpiar);

        // BOTÓN: Iniciar Sesión
        BotonIniciarSesion = new JButton("Iniciar Sesión");
        BotonIniciarSesion.setBackground(colorBotones);
        BotonIniciarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        BotonIniciarSesion.setForeground(Color.WHITE);
        BotonIniciarSesion.setBounds(650, 510, 250, 50); 
        BotonIniciarSesion.addActionListener(this::BotonIniciarSesionActionPerformed);
        jPanel1.add(BotonIniciarSesion);

        // --- IMÁGENES DECORATIVAS ---
        jLabel2 = new JLabel();
        try { jLabel2.setIcon(new ImageIcon(getClass().getResource("/imagenesParaGUI/planta.JPG"))); } 
        catch (Exception e) {}
        jLabel2.setBounds(90, 30, 170, 150);
        jPanel1.add(jLabel2);

        jLabel5 = new JLabel();
        try { jLabel5.setIcon(new ImageIcon(getClass().getResource("/imagenesParaGUI/ecorregioness.JPG"))); } 
        catch (Exception e) {}
        jLabel5.setBounds(720, 170, 320, 300);
        jPanel1.add(jLabel5);

        // FONDOS
        
        jLabel3 = new JLabel();
        try { jLabel3.setIcon(new ImageIcon(getClass().getResource("/imagenesParaGUI/fonfomarron.JPG"))); } 
        catch (Exception e) {}
        jLabel3.setBounds(160, 130, 730, 370);
        jPanel1.add(jLabel3);

        jLabel1 = new JLabel();
        try { jLabel1.setIcon(new ImageIcon(getClass().getResource("/imagenesParaGUI/fondo.JPG"))); } 
        catch (Exception e) {}
        jLabel1.setBounds(0, 0, 1090, 580);
        jPanel1.add(jLabel1);

        setPreferredSize(new Dimension(1100, 620));
        getContentPane().add(jPanel1);
        pack();
    }


    private void BotonRegistrarActionPerformed(ActionEvent evt) {
        String dni = TextoDNI.getText();
        String nombre = TextoNombre.getText();
        String apellido = TextoApellido.getText();
        String region = (String) ComboRegion.getSelectedItem();

        if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || dni.equals("Ingrese su DNI")) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        DatosAgricultor dao = new DatosAgricultor();
        boolean exito = dao.registrarAgricultor(dni, nombre, apellido, region);

        if (exito) {
            JOptionPane.showMessageDialog(this, "¡Registro Exitoso!");
            
            // Usamos el login para obtener el ID recién creado
            String[] datosNuevos = dao.loginAgricultor(dni);
            int idNuevo = Integer.parseInt(datosNuevos[0]);
            
            // Pasamos ID y Región a la siguiente ventana
            GestionCultivos ventanaSiguiente = new GestionCultivos(region, idNuevo);
            ventanaSiguiente.setVisible(true);
            this.dispose(); 
        }
    }

    private void BotonLimpiarActionPerformed(ActionEvent evt) {
        TextoDNI.setText("");
        TextoNombre.setText("");
        TextoApellido.setText("");
        ComboRegion.setSelectedIndex(0);
    }
    
    private void BotonIniciarSesionActionPerformed(ActionEvent evt) {
        String dniIngresado = JOptionPane.showInputDialog(this, "Ingrese su DNI para entrar:");
        
        if (dniIngresado != null && !dniIngresado.trim().isEmpty()) {
            
            DatosAgricultor dao = new DatosAgricultor();
            String[] datosUsuario = dao.loginAgricultor(dniIngresado);
            
            if (datosUsuario != null) {
                // Recuperamos ID y Región
                int idRecuperado = Integer.parseInt(datosUsuario[0]); // Convertimos String a int
                String regionRecuperada = datosUsuario[2];
                
                // --- PASAMOS LOS DOS DATOS ---
                GestionCultivos ventanaSiguiente = new GestionCultivos(regionRecuperada, idRecuperado);
                ventanaSiguiente.setVisible(true);
                this.dispose(); 
                
            } else {
                JOptionPane.showMessageDialog(this, "Error: DNI no encontrado.\nPor favor regístrese primero.");
            }
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) { ex.printStackTrace(); }

        EventQueue.invokeLater(() -> new registro().setVisible(true));
    }
}