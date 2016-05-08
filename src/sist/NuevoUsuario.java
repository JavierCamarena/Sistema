/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author xaovs
 */
public class NuevoUsuario extends javax.swing.JFrame {
    private final clsAspirantesResponsables Aspirante = new clsAspirantesResponsables(); // Esta clase se va a usar para todo
    private ArrayList<Colonia> listaColonias;
    private ArrayList<Colonia> listaTemColonias;
    private String nombre;
    private String apellidos;
    private String calle;
    private String numero;
    private String colonia;
    private String agencia;
    private String seccion;
    private String senias;
    private String telefono;
    private String email;
    private boolean barda, lona, banderin, reunion, gestion;
    private String pSocial,pInfra;
    private String fechaReunion;
    private String observaciones;
    private String claveCp;
    /**
     * Creates new form NuevoUsuario
     */
    public NuevoUsuario() throws SQLException {
        initComponents();
        listaColonias = new ArrayList<>();
        setLocationRelativeTo(null);
        limpiar(); 
        AjustaTamanios();    // Se ajustan los tamanios de los textbox
        cargaColonias();     // Se cargan las colonias en el combobox
    }
    
    private void AjustaTamanios()
    {
           jTextNombre.setDocument(new LimiteDeCaracteres(45));
        jTextApellido.setDocument(new LimiteDeCaracteres(45));
        jTextCalle.setDocument(new LimiteDeCaracteres(45));
        jTextNumero.setDocument(new LimiteDeCaracteres(45));
        jTextColonia.setDocument(new LimiteDeCaracteres(45));
        jTextAgencia.setDocument(new LimiteDeCaracteres(45));
        jTextSeccion.setDocument(new LimiteDeCaracteres(45));
        jTextSenias.setDocument(new LimiteDeCaracteres(100));
        jTextTelefono.setDocument(new LimiteDeCaracteres(45));
        jTextCorreo.setDocument(new LimiteDeCaracteres(45));
        jTextPSocial.setDocument(new LimiteDeCaracteres(100));
        jTextPInfra.setDocument(new LimiteDeCaracteres(100));
        jTextObs.setDocument(new LimiteDeCaracteres(50));
    }
    
    private void cargaColonias() throws SQLException 
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemamonitoreo","root", "1234"); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
        Statement stat = con.createStatement();    
        String SQL = "SELECT  * FROM colonias";
        ResultSet rs = stat.executeQuery(SQL);
        while(rs.next())
        {
            listaColonias.add(new Colonia(rs.getString("Nombre")+" - " + rs.getString("Tipo"),rs.getString("Tipo"),rs.getString("ClaveCp")));
        }
    }
    
    public NuevoUsuario(int id) throws SQLException
    {
        System.out.println("Resultado de una busqueda " + id);
        initComponents();
        listaColonias = new ArrayList<>();
        setLocationRelativeTo(null);
        limpiar();
        Aspirante.Busca(id);
        AjustaTamanios();    // Se ajustan los tamanios de los textbox
        cargaColonias();     // Se cargan las colonias en el combobox
        PresentaDatos();
        BtnElimina.setEnabled(true);
    }
    
    public boolean validaDatos() // Aqui se validan (?
    {
        if(colonia.equals("")){
            JOptionPane.showMessageDialog(null, "Colonia es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            jTextColonia.requestFocusInWindow(); 
            return false;
        }
        
        if ("".equals(jTextNombre.getText()))
        {
            JOptionPane.showMessageDialog(null, "El campo Nombre es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            jTextNombre.requestFocusInWindow(); 
            return false;
        }
        if (jDateCalendario.getDate() == null)
        {
            JOptionPane.showMessageDialog(null, "Elija una fecha.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            jDateCalendario.requestFocusInWindow(); 
            return false;
        }
        
        
        return true;
    }
    
    public void PresentaDatos() 
    {
        jTextNombre.setText(Aspirante.Nombre);
        jTextApellido.setText(Aspirante.Apellido);
        jTextCalle.setText(Aspirante.Calle);
        jTextNumero.setText(Aspirante.Numero);
        jTextColonia.setText(Aspirante.Colonia);
        colonia=Aspirante.Colonia;
        jTextAgencia.setText(Aspirante.Agencia);
        jTextSeccion.setText(Aspirante.Seccion);
        jTextSenias.setText(Aspirante.SenasParticulares);
        jTextTelefono.setText(Aspirante.Telefono);
        jTextCorreo.setText(Aspirante.Email);   
        jCheckBarda.setSelected(Aspirante.barda);
        jCheckLona.setSelected(Aspirante.lona); 
        jCheckBanderin.setSelected(Aspirante.banderin);
        jCheckReunion.setSelected(Aspirante.reunion);
        jCheckGestion.setSelected(Aspirante.gestion);
        jTextPSocial.setText(Aspirante.PSocial);
        jTextPInfra.setText(Aspirante.PInfraestructura); 
        jDateCalendario.setDate(Aspirante.FechaReunion);
        jTextObs.setText(Aspirante.Observaciones);
        
        System.out.println("Presentando datos");
    }
    
    public void AsignaDatos() // Se le asignan los valores de los campos para una insercion o modificacion
    {
        Aspirante.Nombre = jTextNombre.getText();
        Aspirante.Apellido = jTextApellido.getText();
        Aspirante.Calle = jTextCalle.getText();
        Aspirante.Numero = jTextNumero.getText();
        Aspirante.Colonia = colonia; 
        Aspirante.Agencia = jTextAgencia.getText();
        Aspirante.Seccion = jTextSeccion.getText(); 
        Aspirante.SenasParticulares = jTextSenias.getText(); 
        Aspirante.Telefono = jTextTelefono.getText(); 
        Aspirante.Email = jTextCorreo.getText();
        Aspirante.barda = jCheckBarda.isSelected(); 
        Aspirante.lona = jCheckLona.isSelected();
        Aspirante.banderin = jCheckBanderin.isSelected(); 
        Aspirante.reunion = jCheckReunion.isSelected();
        Aspirante.gestion = jCheckGestion.isSelected(); 
        Aspirante.PSocial = jTextPSocial.getText(); 
        Aspirante.PInfraestructura = jTextPInfra.getText(); 
        Aspirante.FechaReunion = new java.sql.Date(jDateCalendario.getDate().getTime());
        Aspirante.Observaciones = jTextObs.getText();
        Aspirante.Clave = claveCp;
    }
    
    public void limpiar(){
        jTextAgencia.setText("");
        jTextApellido.setText("");
        jTextCalle.setText("");
        jTextColonia.setText("");
        jTextCorreo.setText("");
        jTextNombre.setText("");
        jTextNumero.setText("");
        jTextObs.setText("");
        jTextPInfra.setText("");
        jTextPSocial.setText("");
        jTextSeccion.setText("");
        jTextSenias.setText("");
        jTextTelefono.setText("");
        jCheckBanderin.setSelected(false);
        jCheckBarda.setSelected(false);
        jCheckGestion.setSelected(false);
        jCheckLona.setSelected(false);
        jCheckReunion.setSelected(false);
        claveCp = "";
        colonia = "";
        jComboResulColonias.removeAllItems();
        jComboResulColonias.setEnabled(false);
        Aspirante.Limpia();
        BtnElimina.setEnabled(false);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jTextCalle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextNumero = new javax.swing.JTextField();
        jTextColonia = new javax.swing.JTextField();
        jTextAgencia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextSeccion = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextSenias = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jTextApellido = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextTelefono = new javax.swing.JTextField();
        jTextCorreo = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPSocial = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPInfra = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jCheckBarda = new javax.swing.JCheckBox();
        jCheckLona = new javax.swing.JCheckBox();
        jCheckBanderin = new javax.swing.JCheckBox();
        jCheckReunion = new javax.swing.JCheckBox();
        jCheckGestion = new javax.swing.JCheckBox();
        jSeparator5 = new javax.swing.JSeparator();
        jDateCalendario = new org.jdesktop.swingx.JXDatePicker();
        jLabel15 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jButtonGuardar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextObs = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jButtonBuscar = new javax.swing.JButton();
        BtnElimina = new javax.swing.JButton();
        jComboResulColonias = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CEDULA DE REGISTRO");

        jLabel2.setText("Calle");

        jLabel3.setText("Número");

        jTextColonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextColoniaActionPerformed(evt);
            }
        });
        jTextColonia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextColoniaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextColoniaKeyTyped(evt);
            }
        });

        jLabel4.setText("Busca Colonia");

        jLabel5.setText("Agencia");

        jLabel6.setText("Sección");

        jLabel7.setText("Señas particulares:");

        jTextSenias.setColumns(20);
        jTextSenias.setLineWrap(true);
        jTextSenias.setRows(5);
        jScrollPane1.setViewportView(jTextSenias);

        jLabel8.setText("Nombres");

        jTextNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreActionPerformed(evt);
            }
        });

        jLabel9.setText("Apellidos");

        jLabel10.setText("Teléfono");

        jLabel11.setText("Correo Electrónico");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Prioridades");

        jTextPSocial.setColumns(20);
        jTextPSocial.setLineWrap(true);
        jTextPSocial.setRows(5);
        jScrollPane3.setViewportView(jTextPSocial);

        jTextPInfra.setColumns(20);
        jTextPInfra.setLineWrap(true);
        jTextPInfra.setRows(5);
        jScrollPane4.setViewportView(jTextPInfra);

        jLabel13.setText("Social");

        jLabel14.setText("Infraestructura");

        jCheckBarda.setText("Barda");

        jCheckLona.setText("Lona");

        jCheckBanderin.setText("Banderin");

        jCheckReunion.setText("Reunión");

        jCheckGestion.setText("Gestión");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jDateCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDateCalendarioActionPerformed(evt);
            }
        });

        jLabel15.setText("Fecha de reunión");

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jTextObs.setColumns(20);
        jTextObs.setLineWrap(true);
        jTextObs.setRows(5);
        jScrollPane2.setViewportView(jTextObs);

        jLabel16.setText("Observaciones");

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        BtnElimina.setText("Elimina");
        BtnElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminaActionPerformed(evt);
            }
        });

        jComboResulColonias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboResulColoniasActionPerformed(evt);
            }
        });

        jLabel18.setText("Selecciona colonia:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBarda)
                                        .addGap(8, 8, 8)
                                        .addComponent(jCheckReunion))
                                    .addComponent(jCheckBanderin)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckLona)
                                        .addGap(14, 14, 14)
                                        .addComponent(jCheckGestion)))
                                .addGap(97, 97, 97)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonGuardar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonLimpiar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonBuscar)
                                .addGap(18, 18, 18)
                                .addComponent(BtnElimina)))
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jDateCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 261, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextNumero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jComboResulColonias, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboResulColonias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1)
                            .addComponent(jSeparator5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBarda)
                            .addComponent(jCheckReunion)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckLona)
                                    .addComponent(jCheckGestion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBanderin))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonLimpiar)
                    .addComponent(jButtonBuscar)
                    .addComponent(BtnElimina))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDateCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDateCalendarioActionPerformed
        // TODO add your handling code here:
        
        //System.out.println(jXDatePicker1.getDate());
    }//GEN-LAST:event_jDateCalendarioActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if(validaDatos())  // Primero validamos los datos
        {
            AsignaDatos();
            if(Aspirante.idaspirante == 0 ) // Si el id es 0, es una insercion
            {
                    int aux =Aspirante.Nuevo() ;
                    if (aux == -1)
                    {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Alerta.", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Se ha agregado con exito.", "Exito.", JOptionPane.INFORMATION_MESSAGE);
                        Aspirante.idaspirante = aux;
                        //System.out.println( Aspirante.idaspirante);
                        limpiar();
                    }                
            }
            else // Si el id no es 0, significa que es despues de hacer una busqueda, y es una modificacion
            {
                if (Aspirante.Modifica())
                {
                     JOptionPane.showMessageDialog(null, "Se ha modificado con exito.", "Exito.", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Alerta.", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        JFrame vBusqueda = new VentanaBusqueda();
        vBusqueda.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void BtnEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminaActionPerformed
        // TODO add your handling code here:
        
        int reply = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar este elemento?", "Eliminar", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
          
       
        if (Aspirante.Elimina())
        {
            JOptionPane.showMessageDialog(null, "Se ha eliminado con exito.", "Exito.", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Alerta.", JOptionPane.ERROR_MESSAGE);
        }    
         }
       
    }//GEN-LAST:event_BtnEliminaActionPerformed

    private void jTextNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreActionPerformed

    private void jTextColoniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextColoniaKeyTyped
       
        
    }//GEN-LAST:event_jTextColoniaKeyTyped

    
    private void jComboResulColoniasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboResulColoniasActionPerformed
        // TODO add your handling code here:
        
        if(jComboResulColonias.getSelectedItem()!=null){
            colonia=jComboResulColonias.getSelectedItem().toString();
            System.out.println("seleccionado: "+colonia);
            
            int id =jComboResulColonias.getSelectedIndex();
            claveCp = listaTemColonias.get(id).getClave();
            System.out.println("ClaveCp:"+claveCp);
        }
    }//GEN-LAST:event_jComboResulColoniasActionPerformed

    private void jTextColoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextColoniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextColoniaActionPerformed

    private void jTextColoniaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextColoniaKeyReleased
        // TODO add your handling code here:
         // busqueda interactiva del texto actual
        buscaColonia(jTextColonia.getText().toUpperCase());
    }//GEN-LAST:event_jTextColoniaKeyReleased

    public void buscaColonia(String text){
        Colonia c = new Colonia("", "", "");
        jComboResulColonias.removeAllItems();
        jComboResulColonias.setEnabled(true);
        listaTemColonias = c.buscaColonias(listaColonias, text);
        if(listaTemColonias != null){
            for(Colonia col : listaTemColonias){
                jComboResulColonias.addItem(col.getNombre());
                System.out.println(col.getNombre() + "-" + text);
            }
            
            
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NuevoUsuario().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnElimina;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JCheckBox jCheckBanderin;
    private javax.swing.JCheckBox jCheckBarda;
    private javax.swing.JCheckBox jCheckGestion;
    private javax.swing.JCheckBox jCheckLona;
    private javax.swing.JCheckBox jCheckReunion;
    private javax.swing.JComboBox<String> jComboResulColonias;
    private org.jdesktop.swingx.JXDatePicker jDateCalendario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextAgencia;
    private javax.swing.JTextField jTextApellido;
    private javax.swing.JTextField jTextCalle;
    private javax.swing.JTextField jTextColonia;
    private javax.swing.JTextField jTextCorreo;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextNumero;
    private javax.swing.JTextArea jTextObs;
    private javax.swing.JTextArea jTextPInfra;
    private javax.swing.JTextArea jTextPSocial;
    private javax.swing.JTextField jTextSeccion;
    private javax.swing.JTextArea jTextSenias;
    private javax.swing.JTextField jTextTelefono;
    // End of variables declaration//GEN-END:variables
}
