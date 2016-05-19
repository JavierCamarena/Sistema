/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoDiez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import sist.LimiteDeCaracteres;

/**
 *
 * @author xaovs
 */
public class RegistroUD extends javax.swing.JFrame {

    private ClsResponsables Responsable;
    
    private String jTextnombre= "Ingresa el nombre:";
    private String jTextapellido= "Ingresa el apellido:";
    private final int nomb = 0;
    private final int ape  = 1;
    private final int todo = 2;
    private String sqlcode=" WHERE Colonia =";
    private int op = 0 ; 
    String Configuracion[];
    Connection con = null;
    Statement stat = null;
    public DefaultTableModel modelo;
    public final int OTRO  = 0;
    public final int LIDER = 1;
    public final int MOVILIZADOR = 2;
    public final int ENLACE =3;
    //////////////////////////////////////////////Variables Ciudadano
    public ClsCiudadano ciudadano;
    /**
     * Creates new form RegistroUD
     */
    public RegistroUD(String[] conf) {
       Configuracion = conf;
        initComponents();
        AsignaTamanios();
        Responsable = new ClsResponsables(Configuracion);
        LimpiaCampos();
        setLocationRelativeTo(null);
    }

    private RegistroUD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void AsignaTamanios()
    {
        jTextNombre   .setDocument(new LimiteDeCaracteres(45));
        jTextApellidos.setDocument(new LimiteDeCaracteres(45)); 
        jTextINE      .setDocument(new LimiteDeCaracteres(45));
        jTextCorreo   .setDocument(new LimiteDeCaracteres(45));
        jTextTelefono .setDocument(new LimiteDeCaracteres(45));
        jTextZonaGrupo.setDocument(new LimiteDeCaracteres(45));
        nombreField   .setDocument(new LimiteDeCaracteres(45));
        apellidoField .setDocument(new LimiteDeCaracteres(45));
        claveIneField .setDocument(new LimiteDeCaracteres(45));
        correoField   .setDocument(new LimiteDeCaracteres(45));
        telField      .setDocument(new LimiteDeCaracteres(45));
        seccionField  .setDocument(new LimiteDeCaracteres(45));
        folioField    .setDocument(new LimiteDeCaracteres(45));
    }
    
    public void LimpiaCampos()
    {
        jTextNombre   .setText("");
        jTextApellidos.setText("");
        jTextTelefono .setText("");
        jTextINE      .setText("");
        jTextCorreo   .setText("");
        jTextZonaGrupo.setText("");
        cargoMenu     .setSelectedIndex(0);
        ///////////////////////////////////////Quedan pendientes 3 campos que no se si el responsable tambien los llevara
        Responsable.Limpia();
        BtnEditar  .setEnabled(false);
        btnRemover .setEnabled(false);
        btnAniadir .setEnabled(false);
        
    }
    
    public void PresentaDatos() 
    {
        jTextNombre   .setText(Responsable.         Nombre);
        jTextApellidos.setText(Responsable.       Apellido);
        jTextTelefono .setText(Responsable.    NumTelefono);
        jTextINE      .setText(Responsable.ClaveElectorIne);
        jTextCorreo   .setText(Responsable.          Email);
        jTextZonaGrupo.setText(Responsable.      ZonaGrupo);
        //jTextCargo.setText(Responsable.Cargo);
        cargoMenu       .setSelectedItem(Responsable.Cargo);
    }
    
    public void CargaDatos()
    {
        Responsable.Busca();
    }
    
    public void AsignaDatos()
    {
        Responsable.Nombre          = jTextNombre.getText();
        Responsable.Apellido        = jTextApellidos.getText(); 
        Responsable.NumTelefono     = jTextTelefono.getText();
        Responsable.ClaveElectorIne = jTextINE.getText();
        Responsable.Email           = jTextCorreo.getText();
        Responsable.ZonaGrupo       = jTextZonaGrupo.getText();
        Responsable.Cargo           = cargoMenu.getSelectedItem().toString();
    }

    public boolean ValidaDatos() 
    {
        if(jTextNombre.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Nombre es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            jTextNombre.requestFocusInWindow(); 
            return false;
        }
         if(jTextApellidos.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Apellidos es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            jTextApellidos.requestFocusInWindow(); 
            return false;
        }
        return true; 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogBusca = new javax.swing.JDialog();
        jButtonBuscar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextBusquedaDialog = new javax.swing.JTextField();
        jLabelParam = new javax.swing.JLabel();
        BtnCancelar = new javax.swing.JButton();
        BtnAceptar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDialogCiudadano = new javax.swing.JDialog();
        claveIneField = new javax.swing.JTextField();
        correoField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        telField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        seccionField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        folioField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        apellidoField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jTextApellidos = new javax.swing.JTextField();
        jTextINE = new javax.swing.JTextField();
        jTextZonaGrupo = new javax.swing.JTextField();
        jTextTelefono = new javax.swing.JTextField();
        jTextCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCiudadano = new javax.swing.JTable();
        btnAniadir = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        cargoMenu = new javax.swing.JComboBox<>();

        jDialogBusca.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Buscar Responsable");

        jLabel9.setText("Selecciona una opción:");

        jTextBusquedaDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextBusquedaDialogActionPerformed(evt);
            }
        });

        jLabelParam.setText("Parámetro:");

        BtnCancelar.setText("Cancelar");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnAceptar.setText("Editar");
        BtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAceptarActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "Todos" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jDialogBuscaLayout = new javax.swing.GroupLayout(jDialogBusca.getContentPane());
        jDialogBusca.getContentPane().setLayout(jDialogBuscaLayout);
        jDialogBuscaLayout.setHorizontalGroup(
            jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogBuscaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))
                    .addGroup(jDialogBuscaLayout.createSequentialGroup()
                        .addGroup(jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialogBuscaLayout.createSequentialGroup()
                                .addGroup(jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(85, 85, 85)
                                .addGroup(jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelParam)
                                    .addGroup(jDialogBuscaLayout.createSequentialGroup()
                                        .addComponent(jTextBusquedaDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonBuscar))))
                            .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jDialogBuscaLayout.setVerticalGroup(
            jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelParam))
                .addGap(18, 18, 18)
                .addGroup(jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jTextBusquedaDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jDialogBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogBuscaLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(BtnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnCancelar))
                    .addGroup(jDialogBuscaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        claveIneField.setText("jTextField3");

        correoField.setText("jTextField4");

        jLabel10.setText("Nombres:");

        telField.setText("jTextField5");

        jLabel11.setText("Apellidos:");

        seccionField.setText("jTextField6");

        jLabel12.setText("Clave INE:");

        folioField.setText("jTextField7");

        jLabel13.setText("Correo:");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Teléfono:");

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setText("Sección:");

        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel16.setText("Folio Padron:");

        nombreField.setText("jTextField1");

        apellidoField.setText("jTextField2");

        javax.swing.GroupLayout jDialogCiudadanoLayout = new javax.swing.GroupLayout(jDialogCiudadano.getContentPane());
        jDialogCiudadano.getContentPane().setLayout(jDialogCiudadanoLayout);
        jDialogCiudadanoLayout.setHorizontalGroup(
            jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                        .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13)
                            .addComponent(nombreField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addComponent(correoField))
                        .addGap(18, 18, 18)
                        .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(apellidoField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(telField))
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(claveIneField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                                        .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(seccionField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel16))
                                            .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(folioField))))
                                    .addComponent(jButton4)))))
                    .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogCiudadanoLayout.setVerticalGroup(
            jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveIneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14))
                    .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(seccionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(folioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombres:");

        jLabel2.setText("Apellidos:");

        jLabel3.setText("Clave INE:");

        jLabel4.setText("Zona/Grupo:");

        jLabel5.setText("Teléfono:");

        jLabel6.setText("Correo:");

        jTextNombre.setText("jTextField1");

        jTextApellidos.setText("jTextField2");

        jTextINE.setText("jTextField3");

        jTextZonaGrupo.setText("jTextField4");

        jTextTelefono.setText("jTextField5");

        jTextCorreo.setText("jTextField6");

        jLabel7.setText("Cargo:");

        jTableCiudadano.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableCiudadano);

        btnAniadir.setText("Añadir");
        btnAniadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAniadirActionPerformed(evt);
            }
        });

        BtnEditar.setText("Editar");
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed1(evt);
            }
        });

        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnGuardar.setText("Guardar");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnSalir.setText("Salir");
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        cargoMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OTRO", "LIDER", "MOVILIZADOR", "ENLACE" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextZonaGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextINE, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextCorreo)
                            .addComponent(cargoMenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAniadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextINE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jTextZonaGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnEliminar)
                        .addGap(59, 59, 59)
                        .addComponent(BtnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAniadir)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemover)
                        .addGap(94, 94, 94)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        LimpiaCampos();
        rellenaTabla();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        if (ValidaDatos())
        {
            AsignaDatos();
            int aux;
            if(Responsable.idResponsable == 0 )
            {
                aux =Responsable.Nuevo() ;
                if (aux!= -1)
                {
                    JOptionPane.showMessageDialog(null, "Se ha agregado con exito.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                    Responsable.idResponsable = aux;
                    btnAniadir.setEnabled(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE); 
                }
            }
            else
            {
                if( Responsable.Actualiza()) 
                {
                     JOptionPane.showMessageDialog(null, "Se ha actualizado con exito.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                    
                }
                else
                {
                     JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        if(Responsable.idResponsable != 0)
        {
            int reply = JOptionPane.showConfirmDialog(null, "Deseas eliminar el responsable junto con sus ciudadanos asignados?", "Eliminar", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) 
            {
                if (Responsable.Elimina())
                {
                    JOptionPane.showMessageDialog(null, "Se ha eliminado con exito.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                    LimpiaCampos();
                    rellenaTabla();
                }
            }
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void LimpiaDialog() 
    {
        jTextBusquedaDialog.setText("");
        //BtnAceptar.setEnabled(false);
        
    }
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
      
        jDialogBusca.setVisible(true);
        jDialogBusca.pack();
        jDialogBusca.setLocationRelativeTo(this);
        jDialogBusca.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        LimpiaDialog();
        //jDialogBusca.size(200,200);
        //rellenaTabla();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    
    public void rellenaTabla(){
        try{    
            Class.forName(Configuracion[0]);
            con = DriverManager.getConnection(Configuracion[1],Configuracion[2],Configuracion[3]); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
            stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from ciudadanos where idResponsable="+Responsable.idResponsable);
            System.out.println("Datos obtenidos configurando tabla");
            //conversorTable.rellena(rs, modelo);
            String [] titulos = {"id","Nombre", "Apellidos", "Clave INE", "Correo", "Telefono", "Folio Padron"}; 
            String [] registros = new String[7];
            
            modelo = new DefaultTableModel(null, titulos);
            while(rs.next())
                {
                    registros[0]= rs.getString("idciudadanos");
                    registros[1]= rs.getString("Nombres");
                    registros[2]= rs.getString("Apellidos");
                    registros[3]= rs.getString("ClaveIne");
                    registros[4]= rs.getString("Email");
                    registros[5]= rs.getString("Telefono");
                    registros[6]= rs.getString("FolioPadron");
                    modelo.addRow(registros);
                }
            jTableCiudadano.setModel(modelo);
            rs.close();
                }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
           
        } finally {
            
            jTableCiudadano.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                   if (!e.getValueIsAdjusting())
                        {
                                boolean rowsAreSelected = jTableCiudadano.getSelectedRowCount() > 0;
                                 btnRemover.setEnabled(rowsAreSelected);
                                 BtnEditar.setEnabled(rowsAreSelected);
                        }
                    
                }
            } );
            jTableCiudadano.setDefaultEditor(Object.class, null);
            
        }
    }
    
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        busqueda();
    }//GEN-LAST:event_jButtonBuscarActionPerformed

        public void asignaSql(){
        op = jComboBox1.getSelectedIndex();
        jTextBusquedaDialog.setEnabled(true);
        switch (op) {
            case nomb : jLabelParam.setText(jTextnombre);sqlcode=" WHERE Nombre LIKE";  break;
            case ape: jLabelParam.setText(jTextapellido);sqlcode=" WHERE Apellido LIKE";break;
            case todo: jTextBusquedaDialog.setEnabled(false); jLabelParam.setText("Se muestran todos");sqlcode="";break;
            default: jLabelParam.setText("Parámetro:");sqlcode="";break;
        }
    }
    
     public void busqueda(){
         asignaSql();
        if(op < 2 && jTextBusquedaDialog.getText().equalsIgnoreCase("") ){
            JOptionPane.showMessageDialog(null, "Ingresa una opcion en la busqueda.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }else {
            if(op < 2) {
                sqlcode+="'%"+ jTextBusquedaDialog.getText()+"%'";
            }
        }
        System.out.println("Buscando y llenando tabla");
        try{    
            Class.forName(Configuracion[0]);
            con = DriverManager.getConnection(Configuracion[1],Configuracion[2],Configuracion[3]); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
            stat = con.createStatement();
            System.out.println("preparando statement :"+sqlcode);
            ResultSet rs = stat.executeQuery("select * from responsables "+sqlcode);
            System.out.println("Datos obtenidos configurando tabla");
          
            String [] titulos = {"id","Nombre", "Apellido", "Clave Ine", "Numero", "Zona", "Cargo", "Email"}; 
            String [] registros = new String[8];
            
            modelo = new DefaultTableModel(null, titulos);
            while(rs.next())
                {
                    registros[0]= rs.getString("idResponsables");
                    registros[1]= rs.getString("Nombre");
                    registros[2]= rs.getString("Apellido");
                    registros[3]= rs.getString("ClaveElectorIne");
                    registros[4]= rs.getString("NumTelefono");
                    registros[5]= rs.getString("ZonaGrupo");
                    registros[6]= rs.getString("Cargo");
                    registros[7]= rs.getString("Email");
                    modelo.addRow(registros);
                }
            jTable1.setModel(modelo);
            rs.close();
            //asignaSql();
                }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
           
        } finally {
            
            jTable1.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                   if (!e.getValueIsAdjusting())
                        {
                                boolean rowsAreSelected = jTable1.getSelectedRowCount() > 0;
                                 BtnAceptar.setEnabled(rowsAreSelected);
                        }
                    
                }
            } );
            jTable1.setDefaultEditor(Object.class, null);
            
        }
    }
    
    private void jTextBusquedaDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextBusquedaDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBusquedaDialogActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        jDialogBusca.dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAceptarActionPerformed
         int a = jTable1.getSelectedRow();
        Responsable.idResponsable = Integer.parseInt(jTable1.getValueAt(a , 0).toString() );
        jDialogBusca.dispose();
        CargaDatos();
        PresentaDatos();
        rellenaTabla();
        btnAniadir.setEnabled(true);
    }//GEN-LAST:event_BtnAceptarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        asignaSql();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniadirActionPerformed
        // TODO add your handling code here:
        //RegistroCiudadano rc = new RegistroCiudadano(Configuracion,Responsable.idResponsable);
        //rc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //rc.setLocationRelativeTo(null);
        jDialogCiudadano.setVisible(true);
        jDialogCiudadano.pack();
        jDialogCiudadano.setLocationRelativeTo(this);
        jDialogCiudadano.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ciudadano = new ClsCiudadano(Configuracion);
        ciudadano.idResponsable= Responsable.idResponsable;
        LimpiaCamposCiudadano();
    }//GEN-LAST:event_btnAniadirActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        
            int a = jTableCiudadano.getSelectedRow();
            
            ClsCiudadano temp = new ClsCiudadano(Configuracion);
            temp.idCiudadano = Integer.parseInt(jTableCiudadano.getValueAt(a , 0).toString() );
            temp.Elimina();
            rellenaTabla();
        
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void BtnEditarActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed1
        ciudadano = new ClsCiudadano(Configuracion);
        int sel = jTableCiudadano.getSelectedRow();
        int id =  Integer.parseInt(jTableCiudadano.getValueAt(sel,0).toString());
        
        if(ciudadano.Busca(id))
        {
            PresentaDatosCiudadano();
            jDialogCiudadano.setVisible(true);
            jDialogCiudadano.pack();
            jDialogCiudadano.setLocationRelativeTo(this);
            jDialogCiudadano.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        else 
        {
            System.out.println("Error al encontrar al usuario");
        
        }
    }//GEN-LAST:event_BtnEditarActionPerformed1
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////// Funciones de los Ciudadanos
    
    private void AsignaDatosCiudadano() 
    {
        ciudadano.Nombres     =    nombreField.getText();
        ciudadano.Apellidos   =  apellidoField.getText(); 
        ciudadano.ClaveIne    =  claveIneField.getText();
        ciudadano.Mail        =    correoField.getText(); 
        ciudadano.Telefono    =       telField.getText();
        ciudadano.Seccion     =   seccionField.getText(); 
        ciudadano.FolioPadron =     folioField.getText();
    }
    
    private void PresentaDatosCiudadano() 
    {
   
        apellidoField.setText(ciudadano.Apellidos);
        claveIneField.setText(ciudadano.ClaveIne);
        folioField.   setText(ciudadano.FolioPadron);
        correoField.  setText(ciudadano.Mail);
        nombreField.  setText(ciudadano.Nombres);
        seccionField. setText(ciudadano.Seccion);
        telField.     setText(ciudadano.Telefono);
    }
    
    private void LimpiaCamposCiudadano() 
    {
        apellidoField.setText("");
        nombreField.  setText("");
        correoField.  setText("");
        claveIneField.setText("");
        seccionField. setText("");
        telField.     setText("");
        folioField.   setText("");
    }
    
    private boolean ValidaDatosCiudadano()
    {
           if(nombreField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Nombre es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            nombreField.requestFocusInWindow(); 
            return false;
        }
         if(apellidoField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Apellidos es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            apellidoField.requestFocusInWindow(); 
            return false;
        }
        return true; 
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (ValidaDatosCiudadano())
        {
            AsignaDatosCiudadano();
            int aux;
            if(ciudadano.idCiudadano == 0 )
            {
                aux =ciudadano.Nuevo() ;
                if (aux!= -1)
                {
                    JOptionPane.showMessageDialog(null, "Se ha agregado con exito.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                    ciudadano.idCiudadano = aux;
                    LimpiaCamposCiudadano();
                    ciudadano.Limpia();
                    rellenaTabla();

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                if( ciudadano.Actualiza())
                {
                    JOptionPane.showMessageDialog(null, "Se ha actualizado con exito.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                    rellenaTabla();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        LimpiaCamposCiudadano();
        ciudadano.Limpia();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jDialogCiudadano.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                     

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
            java.util.logging.Logger.getLogger(RegistroUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAceptar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JButton btnAniadir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cargoMenu;
    private javax.swing.JTextField claveIneField;
    private javax.swing.JTextField correoField;
    private javax.swing.JTextField folioField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialogBusca;
    private javax.swing.JDialog jDialogCiudadano;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelParam;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableCiudadano;
    private javax.swing.JTextField jTextApellidos;
    private javax.swing.JTextField jTextBusquedaDialog;
    private javax.swing.JTextField jTextCorreo;
    private javax.swing.JTextField jTextINE;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextTelefono;
    private javax.swing.JTextField jTextZonaGrupo;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField seccionField;
    private javax.swing.JTextField telField;
    // End of variables declaration//GEN-END:variables
}
