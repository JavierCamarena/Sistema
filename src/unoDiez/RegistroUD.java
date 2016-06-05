/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoDiez;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import sist.Colonia;
import sist.LimiteDeCaracteres;

/**
 *
 * @author xaovs
 */
public class RegistroUD extends javax.swing.JFrame {

    private ClsResponsables Responsable;
    private ArrayList<Colonia> listaColonias;
    private ArrayList<Colonia> listaTemColonias;
    private ArrayList<Colonia> listaMovilizadores;
    private String colonia;
    private String coloniaCiu;
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
    ResultSet rs   = null;
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
    public RegistroUD(String[] conf) throws SQLException{
       Configuracion = conf;
       listaColonias = new ArrayList<>();
       listaMovilizadores = new ArrayList<>(); 
       initComponents();
        AsignaTamanios();
        Responsable = new ClsResponsables(Configuracion);
        LimpiaCampos();
        LimpiaCamposCiudadano();
        setLocationRelativeTo(null);
        
        cargaColonias(); 
        cargaMovilizadores();
        showCargo(true);
    }

    private RegistroUD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void showCargo(boolean show){
        
            jLabelMoviliza.setVisible(show);
            
            jComboMovil.setVisible(show);
    }
    
     private void cargaColonias() throws SQLException 
    {
        Connection con = DriverManager.getConnection(Configuracion[1],Configuracion[2],Configuracion[3]); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
        Statement stat = con.createStatement();    
        String SQL = "SELECT  * FROM colonias";
        ResultSet rs = stat.executeQuery(SQL);
        while(rs.next())
        {
            if(rs.getString("tipo").equals(""))
            {
              listaColonias.add(new Colonia(rs.getString("Nombre"),rs.getString("Tipo"),rs.getString("ClaveCp")));
            }
            else
            {
              listaColonias.add(new Colonia(rs.getString("Tipo")+" - " + rs.getString("Nombre"),rs.getString("Tipo"),rs.getString("ClaveCp")));
            }
            
        }
    }
    
    private void cargaMovilizadores() throws SQLException{
        Connection con = DriverManager.getConnection(Configuracion[1],Configuracion[2],Configuracion[3]); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
        Statement stat = con.createStatement();    
        String SQL = "SELECT  * FROM responsables WHERE idSuperior=0";
        ResultSet rs = stat.executeQuery(SQL);
        listaMovilizadores = new ArrayList<>();
        while(rs.next()){
            listaMovilizadores.add(new Colonia(rs.getString("Nombre")+" "+rs.getString("Apellido"),"", rs.getString("idResponsables")));
            //System.out.println("Añadiendo a "+rs.getString("Nombre"));
        }
        
    } 
     
    private void AsignaTamanios()
    {
        jTextNombre   .setDocument(new LimiteDeCaracteres(45));
        jTextApellidos.setDocument(new LimiteDeCaracteres(45)); 
        jTextINE      .setDocument(new LimiteDeCaracteres(45));
        jTextCorreo   .setDocument(new LimiteDeCaracteres(45));
        jTextTelefono .setDocument(new LimiteDeCaracteres(45));
        jTextSeccion.setDocument(new LimiteDeCaracteres(45));
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
        jTextSeccion.setText("");
        colonia = "";
        jTextColonia.setText("");
        jComboResulColonias.removeAllItems();
        jComboMovil.removeAllItems();
        cargoMenu     .setSelectedIndex(0);
        ///////////////////////////////////////Quedan pendientes 3 campos que no se si el responsable tambien los llevara
        Responsable.Limpia();
        BtnEditar  .setEnabled(false);
        btnRemover .setEnabled(false);
        btnAniadir .setEnabled(false);
        showCargo(true);
        
        modelo = new DefaultTableModel();
        jTableCiudadano.setModel(modelo);
    }
    
    public void PresentaDatos() throws SQLException 
    {
        jTextNombre.setText(Responsable.Nombre);
        jTextApellidos.setText(Responsable.Apellido);
        jTextTelefono.setText(Responsable.NumTelefono);
        jTextINE.setText(Responsable.ClaveElectorIne);
        jTextCorreo.setText(Responsable.Email);
        jTextSeccion.setText(Responsable.Seccion);
        //jTextCargo.setText(Responsable.Cargo);
        cargoMenu.setSelectedItem(Responsable.Cargo);
        colonia = Responsable.Colonia;
        jTextColonia.setText(colonia);
        if(Responsable.Cargo.equalsIgnoreCase("MOVILIZADOR")){
            btnAniadir.setEnabled(false);
        }else{
            btnAniadir.setEnabled(true);
            cargaMovilizadores();
            for(Colonia mov : listaMovilizadores){
                if(mov.getClave().equalsIgnoreCase(Responsable.idSuperior+""))
                    jComboMovil.setSelectedItem(mov.getNombre());
            }
            
        }
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
        Responsable.Seccion       = jTextSeccion.getText();
        Responsable.Cargo           = cargoMenu.getSelectedItem().toString();
        Responsable.Colonia         = colonia;
        if(Responsable.Cargo.equalsIgnoreCase("MOVILIZADOR")){
            Responsable.idSuperior = 0;
        }else{
            Responsable.idSuperior = Integer.parseInt(listaMovilizadores.get(jComboMovil.getSelectedIndex()).getClave());
        }
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
        if(jTextINE.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Clave es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            jTextINE.requestFocusInWindow(); 
            return false;
        } 
        
        if("".equals(jTextSeccion.getText())){
            JOptionPane.showMessageDialog(null, "Seccion es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            jTextSeccion.requestFocusInWindow(); 
            return false;
        }

        try{
            if( Integer.parseInt(jTextSeccion.getText())<470 || Integer.parseInt(jTextSeccion.getText()) > 616 ){
                JOptionPane.showMessageDialog(null, "Seccion debe estar entre [470-616].", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                jTextSeccion.requestFocusInWindow(); 
                return false;
            }
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Sólo se admiten números en el campo Seccion","Cuidado",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(cargoMenu.getSelectedIndex()!=MOVILIZADOR){
            if(listaMovilizadores.isEmpty() || jComboMovil.getSelectedItem().toString().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Necesitas un Movilizador","Cuidado",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            
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
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextColonia1 = new javax.swing.JTextField();
        jComboResulColonias1 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jComboCasilla = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jDialogBuscaCiudadano = new javax.swing.JDialog();
        BtnCancelarCiudadano = new javax.swing.JButton();
        BtnAceptarCiudadano = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableBuscaCiudadano = new javax.swing.JTable();
        jButtonBuscarCiudadano = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextSeccionCiudadano = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextNombreCiudadano = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextApellidoCiudadano = new javax.swing.JTextField();
        CheckNoFolio = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jTextApellidos = new javax.swing.JTextField();
        jTextINE = new javax.swing.JTextField();
        jTextSeccion = new javax.swing.JTextField();
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
        jLabel17 = new javax.swing.JLabel();
        jTextColonia = new javax.swing.JTextField();
        jComboResulColonias = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboMovil = new javax.swing.JComboBox<>();
        jLabelMoviliza = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
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
                        .addComponent(BtnCancelar)
                        .addContainerGap(246, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogBuscaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
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

        jButton2.setText("Nuevo");
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

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Registro de Ciudadano");

        jLabel21.setText("Busca Colonia:");

        jTextColonia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextColonia1ActionPerformed(evt);
            }
        });
        jTextColonia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextColonia1KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextColonia1KeyReleased(evt);
            }
        });

        jComboResulColonias1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboResulColonias1ActionPerformed(evt);
            }
        });

        jLabel22.setText("Selecciona colonia:");

        jComboCasilla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONA", "BASICA", "CONTIGUA 1", "CONTIGUA 2", "CONTIGUA 3", "CONTIGUA 4", "CONTIGUA 5", "CONTIGUA 6", "CONTIGUA 7", "CONTIGUA 8", "CONTIGUA 9", "CONTIGUA 10", "CONTIGUA 11", "CONTIGUA 12", "CONTIGUA 13", "CONTIGUA 14", "CONTIGUA 15", "CONTIGUA 16", "CONTIGUA 17", "CONTIGUA 18", "CONTIGUA 19", "CONTIGUA 20", "CONTIGUA 21", "CONTIGUA 22", "CONTIGUA 23", "CONTIGUA 24", "CONTIGUA 25", "CONTIGUA 26", "CONTIGUA 27", "CONTIGUA 28", "CONTIGUA 29", "CONTIGUA 30" }));

        jLabel23.setText("Casilla:");

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
                            .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                .addComponent(folioField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(26, 26, 26)
                                        .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jComboCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addComponent(jLabel18)
                    .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                        .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialogCiudadanoLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextColonia1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)))
                        .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jComboResulColonias1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jDialogCiudadanoLayout.setVerticalGroup(
            jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogCiudadanoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
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
                        .addComponent(jLabel15)
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(seccionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(folioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboResulColonias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextColonia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jDialogCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        BtnCancelarCiudadano.setText("Cancelar");
        BtnCancelarCiudadano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarCiudadanoActionPerformed(evt);
            }
        });

        BtnAceptarCiudadano.setText("Editar");
        BtnAceptarCiudadano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAceptarCiudadanoActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jTableBuscaCiudadano);

        jButtonBuscarCiudadano.setText("Buscar");
        jButtonBuscarCiudadano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarCiudadanoActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setText("Buscar Ciudadano");

        jLabel26.setText("Seccion ");

        jTextSeccionCiudadano.setText("jTextField1");

        jLabel27.setText("Nombre");

        jTextNombreCiudadano.setText("jTextField1");

        jLabel28.setText("Apellido");

        jTextApellidoCiudadano.setText("jTextField1");

        CheckNoFolio.setText("Sin Folio");

        javax.swing.GroupLayout jDialogBuscaCiudadanoLayout = new javax.swing.GroupLayout(jDialogBuscaCiudadano.getContentPane());
        jDialogBuscaCiudadano.getContentPane().setLayout(jDialogBuscaCiudadanoLayout);
        jDialogBuscaCiudadanoLayout.setHorizontalGroup(
            jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogBuscaCiudadanoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogBuscaCiudadanoLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDialogBuscaCiudadanoLayout.createSequentialGroup()
                        .addComponent(jTextSeccionCiudadano, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CheckNoFolio)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogBuscaCiudadanoLayout.createSequentialGroup()
                        .addGroup(jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDialogBuscaCiudadanoLayout.createSequentialGroup()
                                .addGroup(jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextNombreCiudadano, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel24))
                                .addGap(18, 18, 18)
                                .addGroup(jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDialogBuscaCiudadanoLayout.createSequentialGroup()
                                        .addComponent(jTextApellidoCiudadano, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonBuscarCiudadano))
                                    .addComponent(jLabel28)))
                            .addGroup(jDialogBuscaCiudadanoLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BtnCancelarCiudadano, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BtnAceptarCiudadano, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(20, 20, 20))))
        );
        jDialogBuscaCiudadanoLayout.setVerticalGroup(
            jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogBuscaCiudadanoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGroup(jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogBuscaCiudadanoLayout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(BtnAceptarCiudadano)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnCancelarCiudadano)
                        .addContainerGap(253, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogBuscaCiudadanoLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextNombreCiudadano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextApellidoCiudadano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBuscarCiudadano))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialogBuscaCiudadanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextSeccionCiudadano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CheckNoFolio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombres:");

        jLabel2.setText("Apellidos:");

        jLabel3.setText("Clave INE:");

        jLabel4.setText("Sección:");

        jLabel5.setText("Teléfono:");

        jLabel6.setText("Correo:");

        jTextNombre.setText("jTextField1");

        jTextApellidos.setText("jTextField2");

        jTextINE.setText("jTextField3");

        jTextSeccion.setText("jTextField4");

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

        btnAniadir.setText("Añadir Ciudadano");
        btnAniadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAniadirActionPerformed(evt);
            }
        });

        BtnEditar.setText("Editar Ciudadano");
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed1(evt);
            }
        });

        BtnEliminar.setText("Eliminar Responsable");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnGuardar.setText("Guardar Responsable");
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

        btnLimpiar.setText("Nuevo Responsable");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar Responsable");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover Ciudadano");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        cargoMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OTRO", "LIDER", "MOVILIZADOR", "ENLACE" }));
        cargoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargoMenuActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Registro de Responsables");

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

        jComboResulColonias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboResulColoniasActionPerformed(evt);
            }
        });

        jLabel19.setText("Selecciona colonia:");

        jLabel20.setText("Busca Colonia:");

        jLabelMoviliza.setText("Movilizador:");

        jButton3.setText("Buscar Ciudadano");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextNombre))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jTextSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextINE, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboResulColonias, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(20, 20, 20))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(jTextApellidos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(9, 9, 9)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelMoviliza)
                                        .addGap(0, 257, Short.MAX_VALUE))
                                    .addComponent(jComboMovil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cargoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel17)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextINE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cargoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabelMoviliza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboResulColonias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(BtnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAniadir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemover)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                    if(cargoMenu.getSelectedIndex()!=MOVILIZADOR)btnAniadir.setEnabled(true);
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
                    if(cargoMenu.getSelectedIndex()!=MOVILIZADOR)btnAniadir.setEnabled(true);
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
        modelo = new DefaultTableModel();
        //BtnAceptar.setEnabled(false);
        jComboBox1.setSelectedIndex(2);
        jComboResulColonias1.removeAllItems();
        jComboResulColonias1.setEnabled(false);
        jTextColonia1.setText("");
        coloniaCiu = "";
    }
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BtnAceptar.setEnabled(false);
        jDialogBusca.setVisible(true);
        jDialogBusca.pack();
        jDialogBusca.setLocationRelativeTo(this);
        jDialogBusca.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        LimpiaDialog();
        //jDialogBusca.size(200,200);
        //rellenaTabla();
        
        // TODO add your handling code here:
        busqueda();
    }//GEN-LAST:event_btnBuscarActionPerformed

    
    public void rellenaTabla(){
        try{    
            Class.forName(Configuracion[0]);
            con = DriverManager.getConnection(Configuracion[1],Configuracion[2],Configuracion[3]); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
            stat = con.createStatement();
            rs = stat.executeQuery("select * from ciudadanos where idResponsable="+Responsable.idResponsable+" Order by Apellidos");
            System.out.println("Datos obtenidos configurando tabla");
            //conversorTable.rellena(rs, modelo);
            String [] titulos = {"id","Apellidos", "Nombres", "Clave INE", "Correo", "Telefono", "Folio Padron","Seccion","Colonia"}; 
            String [] registros = new String[9];
            
            modelo = new DefaultTableModel(null, titulos);
            while(rs.next())
                {
                    registros[0]= rs.getString("idciudadanos");
                    registros[1]= rs.getString("Apellidos");
                    registros[2]= rs.getString("Nombres");
                    registros[3]= rs.getString("ClaveIne");
                    registros[4]= rs.getString("Email");
                    registros[5]= rs.getString("Telefono");
                    registros[6]= rs.getString("FolioPadron");
                    registros[7]= rs.getString("Seccion");
                    registros[8]= rs.getString("Colonia");
                    modelo.addRow(registros);
                }
            jTableCiudadano.setModel(modelo);
            jTableCiudadano.setAutoCreateRowSorter(true);
            rs.close();
                }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
           
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            try { if (stat != null) stat.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            try { if (con != null) con.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            
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
            rs = stat.executeQuery("select * from responsables "+sqlcode+" Order by Apellido");
            System.out.println("Datos obtenidos configurando tabla");
          
            String [] titulos = {"id","Apellido", "Nombre", "Clave Ine", "Numero", "Seccion", "Cargo", "Email","Colonia"}; 
            String [] registros = new String[9];
            
            modelo = new DefaultTableModel(null, titulos);
            while(rs.next())
                {
                    registros[0]= rs.getString("idResponsables");
                    registros[1]= rs.getString("Apellido");
                    registros[2]= rs.getString("Nombre");
                    registros[3]= rs.getString("ClaveElectorIne");
                    registros[4]= rs.getString("NumTelefono");
                    registros[5]= rs.getString("Seccion");
                    registros[6]= rs.getString("Cargo");
                    registros[7]= rs.getString("Email");
                    registros[8]= rs.getString("Colonia");
                    modelo.addRow(registros);
                }
            jTable1.setModel(modelo);
            jTable1.setAutoCreateRowSorter(true);
            rs.close();
            
            //asignaSql();
                }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
           
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            try { if (stat != null) stat.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            try { if (con != null) con.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            
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
        try {
            PresentaDatos();
        } catch (SQLException ex) {
            Logger.getLogger(RegistroUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        rellenaTabla();
        BtnAceptar.setEnabled(false);
        //btnAniadir.setEnabled(true);
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
        jButton2.setEnabled(true);
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
        ciudadano.Colonia     =    coloniaCiu;
        ciudadano.Casilla     = jComboCasilla.getSelectedIndex();
        // si selectedIndex es 0 no hay seleccionado, 1 es Basica 2 es Contigua1 3 Contigua2
        System.out.println("Casilla "+ciudadano.Casilla);
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
        jTextColonia1.setText(ciudadano.Colonia);
        coloniaCiu = ciudadano.Colonia;
        jComboCasilla.setSelectedIndex(ciudadano.Casilla);
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
        jTextColonia1.setText("");
        jComboResulColonias1.removeAllItems();
        coloniaCiu = "";
        jComboCasilla.setSelectedIndex(0);
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
         if(claveIneField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Clave es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            claveIneField.requestFocusInWindow(); 
            return false;
        }
        if("".equals(seccionField.getText())){
            JOptionPane.showMessageDialog(null, "Seccion es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            seccionField.requestFocusInWindow(); 
            return false;
        }

        try{
            if( Integer.parseInt(seccionField.getText())<470 || Integer.parseInt(seccionField.getText()) > 616 ){
                JOptionPane.showMessageDialog(null, "Seccion debe estar entre [470-616].", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
                seccionField.requestFocusInWindow(); 
                return false;
            }
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Sólo se admiten números en el campo Seccion","Cuidado",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
         /*
        if(folioField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Folio es un campo obligatorio.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            folioField.requestFocusInWindow(); 
            return false; 
        } 
         */
        if(!folioField.getText().equals("")){ 
            try {
                if(Long.parseLong(folioField.getText())<1 || Long.parseLong(folioField.getText())>750 ){
                    JOptionPane.showMessageDialog(null, "Folio debe estar entre [1-750]","Cuidado",JOptionPane.INFORMATION_MESSAGE);
                        folioField.requestFocus();
                        return false;
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Folio debe ser número","Cuidado",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
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
                try {
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
                } catch (SQLException ex) {
                    Logger.getLogger(RegistroUD.class.getName()).log(Level.SEVERE, null, ex);
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

    private void jTextColoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextColoniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextColoniaActionPerformed

    private void jTextColoniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextColoniaKeyTyped

    }//GEN-LAST:event_jTextColoniaKeyTyped

    private void jTextColoniaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextColoniaKeyReleased
        // TODO add your handling code here:
        // busqueda interactiva del texto actual
        buscaColonia(jTextColonia.getText().toUpperCase());
    }//GEN-LAST:event_jTextColoniaKeyReleased

    private void jComboResulColoniasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboResulColoniasActionPerformed
        // TODO add your handling code here:

        if(jComboResulColonias.getSelectedItem()!=null){
            colonia=jComboResulColonias.getSelectedItem().toString();
            System.out.println("seleccionado: "+colonia);

            int id =jComboResulColonias.getSelectedIndex();
            
        }
    }//GEN-LAST:event_jComboResulColoniasActionPerformed

    private void jTextColonia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextColonia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextColonia1ActionPerformed

    private void jTextColonia1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextColonia1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextColonia1KeyTyped

    private void jTextColonia1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextColonia1KeyReleased
        // TODO add your handling code here:
        buscaColoniaCiu(jTextColonia1.getText().toUpperCase());
    }//GEN-LAST:event_jTextColonia1KeyReleased

    private void jComboResulColonias1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboResulColonias1ActionPerformed
        // TODO add your handling code here:
        if(jComboResulColonias1.getSelectedItem()!=null){
            coloniaCiu=jComboResulColonias1.getSelectedItem().toString();
            System.out.println("seleccionado: "+coloniaCiu);

            int id =jComboResulColonias1.getSelectedIndex();
            
        }
    }//GEN-LAST:event_jComboResulColonias1ActionPerformed

    private void cargoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargoMenuActionPerformed
       
        if(cargoMenu.getSelectedIndex()!=MOVILIZADOR){
            try {
                // TODO add your handling code here:
                cargaMovilizadores();
                jComboMovil.removeAllItems();
                for(Colonia mov : listaMovilizadores){
                    jComboMovil.addItem(mov.getNombre());
                }
                showCargo(true);
            } catch (SQLException ex) {
                Logger.getLogger(RegistroUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            showCargo(false);
        }
    }//GEN-LAST:event_cargoMenuActionPerformed

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////Ciudadano
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jDialogBuscaCiudadano.setVisible(true);
        jDialogBuscaCiudadano.pack();
        jDialogBuscaCiudadano.setLocationRelativeTo(this);
        jDialogBuscaCiudadano.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        LimpiaCiudadanoBusqueda();       
        BtnAceptarCiudadano.setEnabled(false);
        
        modelo = new DefaultTableModel();
        jTableBuscaCiudadano.setModel(modelo);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BtnCancelarCiudadanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarCiudadanoActionPerformed
        // TODO add your handling code here:
            jDialogBuscaCiudadano.dispose();
    }//GEN-LAST:event_BtnCancelarCiudadanoActionPerformed

    private void BtnAceptarCiudadanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAceptarCiudadanoActionPerformed
        // TODO add your handling code here:
        int a = jTableBuscaCiudadano.getSelectedRow();
        ciudadano = new ClsCiudadano(Configuracion);
        ciudadano.idCiudadano = Integer.parseInt(jTableBuscaCiudadano.getValueAt(a , 0).toString() );
        jDialogBusca.dispose();
        jDialogCiudadano.setVisible(true);
        jDialogCiudadano.pack();
        jDialogCiudadano.setLocationRelativeTo(this);
        jDialogCiudadano.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ciudadano.Busca(ciudadano.idCiudadano);
        PresentaDatosCiudadano();
        jButton2.setEnabled(false);
        
    }//GEN-LAST:event_BtnAceptarCiudadanoActionPerformed

    private void jButtonBuscarCiudadanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarCiudadanoActionPerformed
        // TODO add your handling code here:
        BuscaCiudadano();
    }//GEN-LAST:event_jButtonBuscarCiudadanoActionPerformed
    
    public void actualizaFromFile() throws IOException{
        ClsCiudadano temp = new ClsCiudadano(Configuracion);
        try{
        String ruta = "actualizar.txt";
        FileReader fr = new FileReader( ruta );
        BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(ruta), "ISO-8859-1") );
 
            String linea;
            int positivos=0;
            int fail=0;
            //Obtenemos el contenido del archivo linea por linea
            while( ( linea = br.readLine() ) != null ){
                //System.out.println(linea);
               
                 String diaArray[] = linea.split(",");
                 temp = new ClsCiudadano(Configuracion);
                 if(temp.actualizaCasilla(Integer.parseInt(diaArray[0]),diaArray[1], 
                         diaArray[2], diaArray[3],Integer.parseInt(diaArray[4]))){
                     positivos++;
                 }else{fail++;}
                 
            }   
            System.out.println("Finalizo con "+positivos+" pos"+fail+" fails");
        }catch(FileNotFoundException | UnsupportedEncodingException e){
            System.out.println("Error al abrir el archivo "+e.getMessage());
        }
    }
    
    public void AsignaSqlCiudadano() 
    {        
        sqlcode = " WHERE Nombres LIKE '%" + jTextNombreCiudadano.getText() + "%' AND Apellidos LIKE '%" + jTextApellidoCiudadano.getText() + "%' AND Seccion LIKE '%" + jTextSeccionCiudadano.getText() + "%'";
        if(CheckNoFolio.isSelected())
        {
            sqlcode = sqlcode + " AND FolioPadron = ''";
        }
    }
    
    public void BuscaCiudadano()
    {
        AsignaSqlCiudadano();
        System.out.println("Buscando y llenando tabla");
        try{    
            Class.forName(Configuracion[0]);
            
            con = DriverManager.getConnection(Configuracion[1],Configuracion[2],Configuracion[3]); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
            stat = con.createStatement();
            System.out.println("preparando statement :"+sqlcode);
            rs = stat.executeQuery("select * from ciudadanos "+sqlcode+" Order by Apellidos");
            System.out.println("Datos obtenidos configurando tabla");
          
            String [] titulos = {"id","Apellido", "Nombre", "Clave Ine", "Email", "FolioPadron", "Seccion", "Colonia","Casilla"}; 
            String [] registros = new String[9];
            int cont = 0;
            modelo = new DefaultTableModel(null, titulos);
            while(rs.next())
                {
                    registros[0]= rs.getString("idciudadanos");
                    registros[1]= rs.getString("Apellidos");
                    registros[2]= rs.getString("Nombres");
                    registros[3]= rs.getString("ClaveIne");
                    registros[4]= rs.getString("Email");
                    registros[5]= rs.getString("FolioPadron");
                    registros[6]= rs.getString("Seccion");
                    registros[7]= rs.getString("Colonia");
                    registros[8]= rs.getString("Casilla");
                    modelo.addRow(registros);
                    cont ++;
                }
            jTableBuscaCiudadano.setModel(modelo);
            jTableBuscaCiudadano.setAutoCreateRowSorter(true);
            
            //setColumnWidths(jTableBuscaCiudadano, new int[]{15,200,200} );  ajustar tamaño de columna
            System.out.println("TOTAL:"+cont);
            
            //asignaSql();
                }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
           
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            try { if (stat != null) stat.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            try { if (con != null) con.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            
             jTableBuscaCiudadano.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                   if (!e.getValueIsAdjusting())
                        {
                                boolean rowsAreSelected = jTableBuscaCiudadano.getSelectedRowCount() > 0;
                                 BtnAceptarCiudadano.setEnabled(rowsAreSelected);
                        }
                    
                }
            } );
            jTableBuscaCiudadano.setDefaultEditor(Object.class, null);
            
        }
    }
    
    public void LimpiaCiudadanoBusqueda()
    {
          jTextSeccionCiudadano.setText("");
          jTextNombreCiudadano.setText("");
          jTextApellidoCiudadano.setText("");
    }
    
    public static void setColumnWidths(JTable table, int [] widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMinWidth(widths[i]);
            }
            else break;
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
     public void buscaColoniaCiu(String text){
        Colonia c = new Colonia("", "", "");
        jComboResulColonias1.removeAllItems();
        jComboResulColonias1.setEnabled(true);
        listaTemColonias = c.buscaColonias(listaColonias, text);
        if(listaTemColonias != null){
            for(Colonia col : listaTemColonias){
                jComboResulColonias1.addItem(col.getNombre());
                System.out.println(col.getNombre() + "-" + text);
            }
            
            
        }
        
    }
    
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
    private javax.swing.JButton BtnAceptarCiudadano;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnCancelarCiudadano;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JCheckBox CheckNoFolio;
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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonBuscarCiudadano;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboCasilla;
    private javax.swing.JComboBox<String> jComboMovil;
    private javax.swing.JComboBox<String> jComboResulColonias;
    private javax.swing.JComboBox<String> jComboResulColonias1;
    private javax.swing.JDialog jDialogBusca;
    private javax.swing.JDialog jDialogBuscaCiudadano;
    private javax.swing.JDialog jDialogCiudadano;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelMoviliza;
    private javax.swing.JLabel jLabelParam;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableBuscaCiudadano;
    private javax.swing.JTable jTableCiudadano;
    private javax.swing.JTextField jTextApellidoCiudadano;
    private javax.swing.JTextField jTextApellidos;
    private javax.swing.JTextField jTextBusquedaDialog;
    private javax.swing.JTextField jTextColonia;
    private javax.swing.JTextField jTextColonia1;
    private javax.swing.JTextField jTextCorreo;
    private javax.swing.JTextField jTextINE;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextNombreCiudadano;
    private javax.swing.JTextField jTextSeccion;
    private javax.swing.JTextField jTextSeccionCiudadano;
    private javax.swing.JTextField jTextTelefono;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField seccionField;
    private javax.swing.JTextField telField;
    // End of variables declaration//GEN-END:variables
}
