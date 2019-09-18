/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.vista;

import ar.com.lujan.asistencia.database.AsistenciaClaseDAO;
import ar.com.lujan.asistencia.database.ClaseDAO;
import ar.com.lujan.asistencia.modelo.AsistenciaClase;
import ar.com.lujan.asistencia.modelo.AsistenciaClaseTableModel;
import ar.com.lujan.asistencia.modelo.Carrera;
import ar.com.lujan.asistencia.modelo.CarreraMateria;
import ar.com.lujan.asistencia.modelo.Clase;
import ar.com.lujan.asistencia.modelo.ClaseTableModel;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.modelo.Materia;
//
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Papa
 */
public class AsistenciaClaseDialog extends javax.swing.JDialog {

    /**
     * Creates new form AsistenciaDialog
     */
    // El estado se crea fuera de cargar grilla de asistencia para poder acceder al
    // isGrabar desde cualquier lugar del JDialog. Dicho método se utiliza para 
    // saber si se realizó alguna modificación en la tabla de Asistencias
    private AsistenciaClaseTableModel asistenciaClaseTM;
    private String fechaAsistencia;


//    public AsistenciaClaseDialog(java.awt.Frame parent, boolean modal) {
    public AsistenciaClaseDialog(java.awt.Frame parent, boolean modal, Curso cu) {

        super(parent, modal);
        initComponents();
        this.cu = cu;
        // Se controla el cierre con la x
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });
        //
        this.lblDescripcionCarrera.setText((cu.getCarreraMaterias().getCarrera().getDescripcion().toString()));
        this.lblDescripcionMateria.setText((cu.getCarreraMaterias().getMateria().getDescripcion().toString()));
        this.lblAnioLectivo.setText(Integer.toString(cu.getAnioLectivo()));
        this.lblCuatrimestre.setText((cu.getCuatrimestre()));
        this.lblComision.setText((cu.getComision()));
        
        this.jdcFechaDesde.setDate(new Date());
        this.jdcFechaHasta.setDate(cu.getHasta());
        
        // Selecciona todo el campo Desde a modificar
        jdcFechaDesde.getDateEditor().getUiComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                ((JTextFieldDateEditor)evt.getSource()).selectAll();
            }
        });
        
        // Selecciona todo el campo Hasta a modificar
        jdcFechaHasta.getDateEditor().getUiComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                ((JTextFieldDateEditor)evt.getSource()).selectAll();
            }
        });

        //Se inhabilita el ingreso manual de fecha
//        ((JTextField) this.jdcFechaDesde.getDateEditor()).setEditable(false);
//        ((JTextField) this.jdcFechaHasta.getDateEditor()).setEditable(false);

        //fechaSqlDesde = new java.sql.Date( jdcFechaDesde.getDate().getTime()); 
        fechaSqlDesde = new java.sql.Date( jdcFechaDesde.getDate().getTime()); 
        fechaSqlHasta = new java.sql.Date( jdcFechaHasta.getDate().getTime()); 

        // Se carga la Grilla de Clase
        cargarGrillaClaseByCurso(cu);
       
    }

    private void cargarGrillaClaseByCurso(Curso cu){
//        tblClase.setModel(new ClaseTableModel((new ClaseDAO()).getClaseByFilters(null, cu, null, null, 0)));
//        tblClase.setModel(new ClaseTableModel((new ClaseDAO()).getClaseByFilters(null, cu, fechaSqlDesde, fechaSqlHasta, 0)));
//        tblClase.setModel(new ClaseTableModel((new ClaseDAO()).getClaseByCurso(cu)));
        tblClase.setModel(new ClaseTableModel((new ClaseDAO()).getClaseByFilters(null,cu,fechaSqlDesde,fechaSqlHasta,0)));    
        // Se ocultan las columnas que contienen ID
        tblClase.getColumnModel().getColumn(0).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(0).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tblClase.getColumnModel().getColumn(1).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(1).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        tblClase.getColumnModel().getColumn(3).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(3).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        tblClase.getColumnModel().getColumn(4).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(4).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        tblClase.getColumnModel().getColumn(5).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(5).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        tblClase.getColumnModel().getColumn(6).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(6).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        tblClase.getColumnModel().getColumn(7).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(7).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        tblClase.getColumnModel().getColumn(8).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(8).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
        tblClase.getColumnModel().getColumn(9).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(9).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
        tblClase.getColumnModel().getColumn(10).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(10).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);
        tblClase.getColumnModel().getColumn(11).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(11).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(11).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(11).setMinWidth(0);
        tblClase.getColumnModel().getColumn(12).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(12).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);
        tblClase.getColumnModel().getColumn(13).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(13).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
        tblClase.getColumnModel().getColumn(14).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(14).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(14).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(14).setMinWidth(0);
        tblClase.getColumnModel().getColumn(15).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(15).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(15).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(15).setMinWidth(0);
        tblClase.getColumnModel().getColumn(16).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(16).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(16).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(16).setMinWidth(0);
        tblClase.getColumnModel().getColumn(17).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(17).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(17).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(17).setMinWidth(0);
        tblClase.getColumnModel().getColumn(18).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(18).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(18).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(18).setMinWidth(0);
        tblClase.getColumnModel().getColumn(19).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(19).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(19).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(19).setMinWidth(0);
        tblClase.getColumnModel().getColumn(20).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(20).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(20).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(20).setMinWidth(0);
        tblClase.getColumnModel().getColumn(21).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(21).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(21).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(21).setMinWidth(0);
        tblClase.getColumnModel().getColumn(22).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(22).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(22).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(22).setMinWidth(0);
        tblClase.getColumnModel().getColumn(23).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(23).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(23).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(23).setMinWidth(0);
        tblClase.getColumnModel().getColumn(24).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(24).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(24).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(24).setMinWidth(0);
        tblClase.getColumnModel().getColumn(25).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(25).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(25).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(25).setMinWidth(0);
        tblClase.getColumnModel().getColumn(26).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(26).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(26).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(26).setMinWidth(0);
        tblClase.getColumnModel().getColumn(27).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(27).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(27).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(27).setMinWidth(0);
        tblClase.getColumnModel().getColumn(28).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(28).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(28).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(28).setMinWidth(0);
        tblClase.getColumnModel().getColumn(29).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(29).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(29).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(29).setMinWidth(0);
        tblClase.getColumnModel().getColumn(30).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(30).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(30).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(30).setMinWidth(0);
        tblClase.getColumnModel().getColumn(31).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(31).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(31).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(31).setMinWidth(0);
        tblClase.getColumnModel().getColumn(32).setMaxWidth(0);
        tblClase.getColumnModel().getColumn(32).setMinWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(32).setMaxWidth(0);
        tblClase.getTableHeader().getColumnModel().getColumn(32).setMinWidth(0);
        
        // Columnas Visibles 
        tblClase.getColumnModel().getColumn(2).setMinWidth(75);

        // Evita que se pueda cambiar de posición las columnas 
        tblClase.getTableHeader().setReorderingAllowed(false);
        
        // Se inhabilita el boton 
        this.btnGrabar.setEnabled(false);
        // Se selecciona primer registro si la tabla tiene registros
        if(tblClase.getRowCount() > 0){
          tblClase.setRowSelectionInterval(0, 0);
          this.btnGrabar.setEnabled(true);
        }  
        this.cargarGrillaAsisteciaByClase();
    }

//    private void cargarGrillaAsisteciaByClase(Clase cl){
    private void cargarGrillaAsisteciaByClase(){
        int row = tblClase.getSelectedRow();
        if (row != -1){
            //Se guarda la fecha de la Clase para mensaje de grabar
            fechaAsistencia = (String) tblClase.getModel().getValueAt(row, 2).toString();
            
            Clase c = new Clase();
            c.setIdClase((int)tblClase.getModel().getValueAt(row, 0));
            c.setCurso(null);//c.setCurso((Curso)tblClase.getModel().getValueAt(row, 1));
            c.setFecha(null);//c.setFecha((Date)tblClase.getModel().getValueAt(row, 2));
            c.setTema((String)tblClase.getModel().getValueAt(row, 3));
            c.setEstado((String)tblClase.getModel().getValueAt(row, 4));

            // Cargar grilla Con los Alumnos de la Clase seleccionada
            // tblAsistencia.setModel(new AsistenciaClaseTableModel((new AsistenciaClaseDAO()).getAsistenciasByClase(c)));
            asistenciaClaseTM = new AsistenciaClaseTableModel((new AsistenciaClaseDAO()).getAsistenciasByClase(c));
            tblAsistencia.setModel(asistenciaClaseTM);

            // Se ocultan las columnas que contienen ID
            tblAsistencia.getColumnModel().getColumn(0).setMaxWidth(0);
            tblAsistencia.getColumnModel().getColumn(0).setMinWidth(0);
            tblAsistencia.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            tblAsistencia.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
            tblAsistencia.getColumnModel().getColumn(1).setMaxWidth(0);
            tblAsistencia.getColumnModel().getColumn(1).setMinWidth(0);
            tblAsistencia.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
            tblAsistencia.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
            tblAsistencia.getColumnModel().getColumn(2).setMaxWidth(0);
            tblAsistencia.getColumnModel().getColumn(2).setMinWidth(0);
            tblAsistencia.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
            tblAsistencia.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);

            // Columnas Visibles 
            tblAsistencia.getColumnModel().getColumn(3).setMinWidth(450);
            tblAsistencia.getColumnModel().getColumn(4).setMinWidth(5);

            // Evita que se pueda cambiar de posición las columnas 
            tblAsistencia.getTableHeader().setReorderingAllowed(false);

            // Se inhabilita el boton 
            this.btnGrabar.setEnabled(false);
            // Se selecciona primer registro si la tabla tiene registros
            if(tblAsistencia.getRowCount() > 0){
              this.btnGrabar.setEnabled(true);
            }  
         }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblClase = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAsistencia = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblCarrera = new javax.swing.JLabel();
        lblDescripcionCarrera = new javax.swing.JLabel();
        lblDescripcionMateria = new javax.swing.JLabel();
        lblMateria = new javax.swing.JLabel();
        lblTituloAnioLectivo = new javax.swing.JLabel();
        lblAnioLectivo = new javax.swing.JLabel();
        lblTituloCuatrimestre = new javax.swing.JLabel();
        lblCuatrimestre = new javax.swing.JLabel();
        lblTituloComision = new javax.swing.JLabel();
        lblComision = new javax.swing.JLabel();
        btnGrabar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblFechaDesde = new javax.swing.JLabel();
        lblFechaHasta = new javax.swing.JLabel();
        jdcFechaDesde = new com.toedter.calendar.JDateChooser();
        jdcFechaHasta = new com.toedter.calendar.JDateChooser();
        btnFiltrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Asistencia");

        tblClase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblClase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClaseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClase);

        tblAsistencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblAsistencia);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCarrera.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblCarrera.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCarrera.setText("Carrera: ");

        lblDescripcionCarrera.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblDescripcionMateria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblMateria.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblMateria.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMateria.setText("Materia: ");

        lblTituloAnioLectivo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTituloAnioLectivo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTituloAnioLectivo.setText("Año Lectivo: ");

        lblAnioLectivo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblTituloCuatrimestre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTituloCuatrimestre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTituloCuatrimestre.setText("Cuatrimestre: ");

        lblCuatrimestre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblTituloComision.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTituloComision.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTituloComision.setText("Comisión: ");

        lblComision.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblComision.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCarrera, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMateria, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcionCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDescripcionMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloAnioLectivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTituloCuatrimestre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTituloComision, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnioLectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDescripcionCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTituloAnioLectivo)
                        .addComponent(lblAnioLectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMateria)
                    .addComponent(lblDescripcionMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTituloCuatrimestre)
                        .addComponent(lblCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblComision, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloComision))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        lblFechaDesde.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblFechaDesde.setText("Fecha Desde:");

        lblFechaHasta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblFechaHasta.setText("Fecha Hasta:");
        lblFechaHasta.setToolTipText("");

        jdcFechaDesde.setDateFormatString("dd-MM-yyyy");
        jdcFechaDesde.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jdcFechaDesde.setNextFocusableComponent(jdcFechaHasta);

        jdcFechaHasta.setDateFormatString("dd-MM-yyyy");
        jdcFechaHasta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jdcFechaHasta.setNextFocusableComponent(btnFiltrar);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblFechaDesde)
                        .addGap(18, 18, 18)
                        .addComponent(jdcFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblFechaHasta)
                        .addGap(21, 21, 21)
                        .addComponent(jdcFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(btnFiltrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaDesde)
                            .addComponent(jdcFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaHasta)
                            .addComponent(jdcFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnFiltrar)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGrabar)
                .addGap(17, 17, 17))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ccntrolarActualizacionAsistencia(){
        if (asistenciaClaseTM != null && asistenciaClaseTM.isGrabar()){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (this, "¿Desea guardar los cambios de asistencia realizados en la clase del día " + fechaAsistencia + "?\n Sino lo hace los mismos se perederán!"  ,"Asistencia",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                btnGrabarActionPerformed(null);
//                JOptionPane.showMessageDialog(this,"Se guardaron los cambios");
            }
        }        
    }

    private void tblClaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClaseMouseClicked
        // Se controla si se realizó alguna modificación en la tabla Asistencia por Clase
        ccntrolarActualizacionAsistencia();
        
        cargarGrillaAsisteciaByClase();
        
    }//GEN-LAST:event_tblClaseMouseClicked

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        // TODO add your handling code here:
        int rows = tblAsistencia.getRowCount();

        for(int i=0;i<rows;i++){
            if (rows != -1){
                AsistenciaClase a = new AsistenciaClase();
                AsistenciaClaseDAO acd = new AsistenciaClaseDAO();
                
                a.setIdAsistencia((int)tblAsistencia.getModel().getValueAt(i, 0));
                a.setClase(null);//c.setCurso((Curso)tblClase.getModel().getValueAt(row, 1));
                a.setCursoAlumno(null);
                a.setAsistencia((Boolean)tblAsistencia.getModel().getValueAt(i, 4));
                
                acd.ActualizarAsistencia(a);
                
                asistenciaClaseTM.setGrabar(false);
            }
        }
        
        JOptionPane.showMessageDialog(this,"Se guardaron los cambios");
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:

        if (jdcFechaDesde.getDate()==null){
            JOptionPane.showMessageDialog(this, "Debe ingresar una Fecha Desde válida");
            jdcFechaDesde.getDateEditor().getUiComponent().requestFocusInWindow();
        }
        else{
            if (jdcFechaHasta.getDate()==null){
                JOptionPane.showMessageDialog(this, "Debe ingresar una Fecha Hasta válida");
                jdcFechaHasta.getDateEditor().getUiComponent().requestFocusInWindow();
            }
            else{
                fechaSqlDesde = sqlFecha(jdcFechaDesde); 
                fechaSqlHasta = sqlFecha(jdcFechaHasta); 
                if (fechaSqlDesde.after(fechaSqlHasta)== true ){
                    JOptionPane.showMessageDialog(this, "La Fecha Desde debe ser anterior o igual a la Fecha Hasta");
                    jdcFechaDesde.getDateEditor().getUiComponent().requestFocusInWindow();
                }
                else {

                    cargarGrillaClaseByCurso(cu);
                }
            }
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private java.sql.Date sqlFecha(JDateChooser fecha){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date fechaSql;
            java.util.Date dateDesde;
            int año = fecha.getCalendar().get(Calendar.YEAR);
            int mes = fecha.getCalendar().get(Calendar.MONTH) + 1;
            int dia = fecha.getCalendar().get(Calendar.DAY_OF_MONTH);
            String fechaDesde = año + "-" + mes + "-" + dia;
        try {
            dateDesde = formatter.parse(fechaDesde);
            fechaSql = new java.sql.Date( dateDesde.getTime());
            return fechaSql;
        } catch (ParseException ex) {
            Logger.getLogger(AsistenciaClaseDialog.class.getName()).log(Level.SEVERE, null, ex);
            return null;    
        }
        
    }
    
    private void close(){
        ccntrolarActualizacionAsistencia();
        this.dispose();
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
            java.util.logging.Logger.getLogger(AsistenciaClaseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AsistenciaClaseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AsistenciaClaseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AsistenciaClaseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AsistenciaClaseDialog dialog = new AsistenciaClaseDialog(new javax.swing.JFrame(), true, null);
//                AsistenciaClaseDialog dialog = new AsistenciaClaseDialog(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcFechaDesde;
    private com.toedter.calendar.JDateChooser jdcFechaHasta;
    private javax.swing.JLabel lblAnioLectivo;
    private javax.swing.JLabel lblCarrera;
    private javax.swing.JLabel lblComision;
    private javax.swing.JLabel lblCuatrimestre;
    private javax.swing.JLabel lblDescripcionCarrera;
    private javax.swing.JLabel lblDescripcionMateria;
    private javax.swing.JLabel lblFechaDesde;
    private javax.swing.JLabel lblFechaHasta;
    private javax.swing.JLabel lblMateria;
    private javax.swing.JLabel lblTituloAnioLectivo;
    private javax.swing.JLabel lblTituloComision;
    private javax.swing.JLabel lblTituloCuatrimestre;
    private javax.swing.JTable tblAsistencia;
    private javax.swing.JTable tblClase;
    // End of variables declaration//GEN-END:variables
    private java.sql.Date fechaSqlDesde;
    private java.sql.Date fechaSqlHasta;
    private Curso cu = new Curso();

}
