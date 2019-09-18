/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.vista;

import ar.com.lujan.asistencia.database.CarreraDAO;
import ar.com.lujan.asistencia.database.CarreraMateriaDAO;
import ar.com.lujan.asistencia.database.CursoDAO;
import ar.com.lujan.asistencia.database.MateriaDAO;
import ar.com.lujan.asistencia.database.ProfesorDAO;
import ar.com.lujan.asistencia.modelo.Alumno;
import ar.com.lujan.asistencia.modelo.Carrera;
import ar.com.lujan.asistencia.modelo.CarreraMateria;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.modelo.CursoTableModel;
import ar.com.lujan.asistencia.modelo.Materia;
import ar.com.lujan.asistencia.modelo.Profesor;
import ar.com.lujan.asistencia.vista.ABMCurso;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//import java.util.Date;
//import java.util.Calendar;

/**
 *
 * @author Andres
 */
public class AgregarCursoUI extends javax.swing.JDialog {

    /**
     * Creates new form AgregarCursoUI
     */
         
     
    public AgregarCursoUI(java.awt.Frame parent, boolean modal,String operacion) {
        super(parent, modal);
        initComponents();
        this.setOperation(operacion);      
        ((JTextField) this.jtfDesde.getDateEditor()).setEditable(false);
        ((JTextField) this.jtfHasta.getDateEditor()).setEditable(false);
    }    

    public AgregarCursoUI(java.awt.Frame parent, boolean modal,String operacion,Curso c)   {
           super(parent, modal);
            initComponents();
            
            jtfID.setText(String.valueOf(c.getIdCurso()));
            jcbbCarrera.setEnabled(false);
            ArrayList<Carrera> carrera = new ArrayList<Carrera>();
            carrera.add(c.getCarreraMaterias().getCarrera());
            jcbbCarrera.setModel((new DefaultComboBoxModel(carrera.toArray())));     //reemplazar por el metodo de listar Carrera       
            jcbbCarrera.setSelectedIndex(0);
            jcbbCarrera.setSelectedItem((Object)c.getCarreraMaterias().getCarrera());
            jcbbMateria.setEnabled(false);
            ArrayList<Materia> materia = new ArrayList<Materia>();
            materia.add(c.getCarreraMaterias().getMateria());
            jcbbMateria.setModel((new DefaultComboBoxModel(materia.toArray())));            
            jcbbMateria.setSelectedIndex(0);
            jcbbMateria.setSelectedItem((Object)c.getCarreraMaterias().getMateria());
            jcbbAnioLectivo.setYear(c.getAnioLectivo());
            ArrayList<String> cuatrimestre = new ArrayList<String>();
            cuatrimestre.add("Anual");
            cuatrimestre.add("Primer Cuatrimestre");
            cuatrimestre.add("Segundo Cuantrimestre");
            jcbbCuatrimestre.setModel(new DefaultComboBoxModel(cuatrimestre.toArray()));
            int r=-1;
            if (c.getCuatrimestre().equals("A")) r=0;
            if (c.getCuatrimestre().equals("P")) r=1;
            if (c.getCuatrimestre().equals("S")) r=2;
            jcbbCuatrimestre.setSelectedItem(r);
            jtfComision.setText(c.getComision());
            jcbbProfesor.setEnabled(false);
            ArrayList <Profesor> profesor = new ArrayList<Profesor>();
            profesor.add(c.getProfesor());
            jcbbProfesor.setModel((new DefaultComboBoxModel(profesor.toArray())));
            jcbbProfesor.setSelectedIndex(0);
            jcbbProfesor.setSelectedItem((Object)c.getProfesor());  
            ArrayList<String> estado = new ArrayList<String>();
            estado.add("Abierto");
            estado.add("En curso");
            estado.add("Finalizado");
            jcbbEstado.setModel(new DefaultComboBoxModel(estado.toArray()));
            int t= -1;
            if(c.getEstado().equals("A")) t= 0;
            if(c.getEstado().equals("E")) t= 1;
            if(c.getEstado().equals("F")) t= 2;
            jcbbEstado.setSelectedItem(t);
            jLCantidadInscriptos.setText(String.valueOf(c.getCantidadInscriptos()));
            jtfDesde.setDate(c.getDesde());
            jtfHasta.setDate(c.getHasta());
            
            this.setOperation(operacion); 
    }
    
    public AgregarCursoUI(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        

     public int agregarEditarCurso(Curso c,String operacion){
        int r=0;
        CursoDAO cDAO = new CursoDAO();
        if (operacion.equals("AGREGAR")){
            r= cDAO.insertarCurso(c);  
        }
        if (operacion.equals("EDITAR")){
            r= cDAO.ActualizarCurso(c);
        }        
        return r;
    }    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbSalir = new javax.swing.JButton();
        jlCarrera = new javax.swing.JLabel();
        jbGrabar = new javax.swing.JButton();
        jlMateria = new javax.swing.JLabel();
        jlAnioLectivo = new javax.swing.JLabel();
        jlCuatrimestre = new javax.swing.JLabel();
        jlComision = new javax.swing.JLabel();
        jtfComision = new javax.swing.JTextField();
        jcbbMateria = new javax.swing.JComboBox<>();
        jlProfesor = new javax.swing.JLabel();
        jlEstado = new javax.swing.JLabel();
        jlCantidadInscriptos = new javax.swing.JLabel();
        jlDesde = new javax.swing.JLabel();
        jlHasta = new javax.swing.JLabel();
        jcbbProfesor = new javax.swing.JComboBox<>();
        jcbbEstado = new javax.swing.JComboBox<>();
        jcbbCarrera = new javax.swing.JComboBox<>();
        jcbbCuatrimestre = new javax.swing.JComboBox<>();
        jLCantidadInscriptos = new javax.swing.JLabel();
        jcbbAnioLectivo = new com.toedter.calendar.JYearChooser();
        jtfDesde = new com.toedter.calendar.JDateChooser();
        jtfHasta = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jtfID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Curso");

        jbSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jlCarrera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlCarrera.setText("Carrera");

        jbGrabar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbGrabar.setText("Grabar");
        jbGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGrabarActionPerformed(evt);
            }
        });

        jlMateria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlMateria.setText("Materia");

        jlAnioLectivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlAnioLectivo.setText("Año Lectivo");

        jlCuatrimestre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlCuatrimestre.setText("Cuatrimestre");

        jlComision.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlComision.setText("Comision");

        jtfComision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfComisionActionPerformed(evt);
            }
        });

        jcbbMateria.setModel(new DefaultComboBoxModel( (new MateriaDAO()).getMaterias().toArray()));
        jcbbMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbMateriaActionPerformed(evt);
            }
        });

        jlProfesor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlProfesor.setText("Profesor");

        jlEstado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlEstado.setText("Estado");

        jlCantidadInscriptos.setFont(jlCantidadInscriptos.getFont().deriveFont(jlCantidadInscriptos.getFont().getSize()+3f));
        jlCantidadInscriptos.setText("Cant. Inscriptos");

        jlDesde.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlDesde.setText("Desde");

        jlHasta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlHasta.setText("Hasta");

        jcbbProfesor.setModel(new DefaultComboBoxModel((new ProfesorDAO()).getProfesor().toArray()));
        jcbbProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbProfesorActionPerformed(evt);
            }
        });

        jcbbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Abierto", "En curso", "Finalizado" }));

        jcbbCarrera.setModel(new DefaultComboBoxModel( (new CarreraDAO()).getCarreras().toArray()));
        jcbbCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbCarreraActionPerformed(evt);
            }
        });

        jcbbCuatrimestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Anual", "Primer cuatrimestre", "Segundo cuatrimestre" }));
        jcbbCuatrimestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbCuatrimestreActionPerformed(evt);
            }
        });

        jLCantidadInscriptos.setText("0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID");

        jtfID.setEnabled(false);
        jtfID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIDActionPerformed(evt);
            }
        });

        jcbbMateria.insertItemAt("Todas las Materias", 0);
        jcbbProfesor.insertItemAt("Todos los Profesores", 0);
        jcbbCarrera.insertItemAt("Todas las Carreras", 0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlEstado)
                    .addComponent(jlProfesor)
                    .addComponent(jlComision)
                    .addComponent(jlCuatrimestre)
                    .addComponent(jlAnioLectivo)
                    .addComponent(jlMateria)
                    .addComponent(jlCantidadInscriptos)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlHasta)
                        .addGap(4, 4, 4))
                    .addComponent(jlDesde)
                    .addComponent(jlCarrera)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCantidadInscriptos, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jcbbEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbbProfesor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfComision, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jcbbCuatrimestre, javax.swing.GroupLayout.Alignment.LEADING, 0, 333, Short.MAX_VALUE)
                        .addComponent(jcbbMateria, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbbCarrera, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jtfHasta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addComponent(jtfDesde, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jcbbAnioLectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlCarrera, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbbCarrera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMateria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbbAnioLectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlAnioLectivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlCuatrimestre)
                    .addComponent(jcbbCuatrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlComision)
                    .addComponent(jtfComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlProfesor)
                    .addComponent(jcbbProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCantidadInscriptos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLCantidadInscriptos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlDesde)
                    .addComponent(jtfDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlHasta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGrabar)
                    .addComponent(jbSalir))
                .addGap(31, 31, 31))
        );

        jcbbMateria.setSelectedIndex(0);
        jcbbProfesor.setSelectedIndex(0);
        jcbbCarrera.setSelectedIndex(0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrar(){
        this.dispose();
    }
    
    private int grabarCurso(String operation) throws ParseException{
        int r=0;
        //Verifica los campos que no nulos en la tabla Curso
        if(jcbbCarrera.getSelectedItem().equals("") || jcbbMateria.getSelectedItem().equals("") 
            || jcbbCuatrimestre.getSelectedItem().equals("") || jtfComision.getText().equals("")
            || jcbbEstado.getSelectedItem().equals("") 
            ||jtfDesde.getDate()==null
            || jtfHasta.getDate() ==null)
        {            
             JOptionPane.showMessageDialog(this,"Error al intentar añadir, debe completar los campos obligatorios");            
        }
        else{
            //instancia la clase Curso
            Curso c = new Curso();
            
            if (this.operation.equals("EDITAR")){
                //c.setIdCurso(Integer.parseInt(operation));
                c.setIdCurso(Integer.parseInt(jtfID.getText()));
            }
            //instancia las clases a utilizar
            Carrera ca = new Carrera();
            Materia ma = new Materia();
            Profesor pr = new Profesor();
            CarreraMateria cm = new CarreraMateria(); 
            CarreraMateriaDAO cmDAO = new CarreraMateriaDAO();
            ProfesorDAO prDAO =new ProfesorDAO();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            //obtienen los objetos de Carrera, materia y profesor para porder ser insertados en un Curso.
            this.setCarrera((Carrera) jcbbCarrera.getSelectedItem());
            this.setMateria((Materia) jcbbMateria.getSelectedItem());            
            this.setProfesor((Profesor) jcbbProfesor.getSelectedItem());   
            int year = jcbbAnioLectivo.getYear();
            ca.setIdCarrera(this.getCarrera().getIdCarrera());
            ca.setDescripcion(this.getCarrera().getDescripcion());
            ma.setIdMateria(this.getMateria().getIdMateria());
            ma.setDescripcion(this.getMateria().getDescripcion());               
            cm = cmDAO.buscarIdCarreraMateria(ca, ma);
            
            pr.setIdProfesor(this.getProfesor().getIdProfesor());
            pr.setDni(this.getProfesor().getDni());
            pr.setNombre(this.getProfesor().getNombre());
            pr.setApellido(this.getProfesor().getApellido());
            pr.setEmail(this.getProfesor().getEmail());
            pr.setGenero(this.getProfesor().getGenero());
            pr.setFechaDeNacimiento(this.getProfesor().getFechaDeNacimiento());
            int añoDesde = jtfDesde.getCalendar().get(Calendar.YEAR);
            int mesDesde = jtfDesde.getCalendar().get(Calendar.MONTH) + 1;
            int diaDesde = jtfDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
            String fechaDesde = añoDesde + "-" + mesDesde + "-" + diaDesde;
            java.util.Date dateDesde = formatter.parse(fechaDesde);
            java.sql.Date sqlfechaDesde = new java.sql.Date( dateDesde.getTime());
                   
            int añoHasta = jtfHasta.getCalendar().get(Calendar.YEAR);
            int mesHasta = jtfHasta.getCalendar().get(Calendar.MONTH) + 1 ;
            int diaHasta = jtfHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
            String fechaHasta = añoHasta + "-" + mesHasta + "-" + diaHasta;
            java.util.Date dateHasta = formatter.parse(fechaHasta);
            java.sql.Date sqlfechaHasta = new java.sql.Date(dateHasta.getTime());
                        
        
            //SET
            c.setCarreraMaterias(cm);
            c.setAnioLectivo(year);
            c.setCuatrimestre(String.valueOf(jcbbCuatrimestre.getSelectedItem().toString().charAt(0)));
            c.setComision((String) jtfComision.getText());    
            c.setProfesor(pr);
            c.setEstado((String.valueOf(jcbbEstado.getSelectedItem().toString().charAt(0))));
            c.setCantidadInscriptos(0);         
            c.setHasta(sqlfechaHasta);
            c.setDesde(sqlfechaDesde);
            
            
            //Verifica si existe un curso con la misma CarreraMateria, anio_lectivo,Cuatrimestre, comision 
            CursoDAO cDAO = new CursoDAO();
            int existe = cDAO.ExisteCurso(c);
            
            if (existe == 0){
                //Graba
               
                if(dateHasta.compareTo(dateDesde) < 0){
                    JOptionPane.showMessageDialog(this, "No se puede ingresar una FECHA DESDE posterior a la FECHA HASTA");
                }
                else{
                    r = agregarEditarCurso(c,operation);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Ya existe un curso con los atributos ingresados (CARRERA-MATERIA-ANIO LECTIVO - CUATRIMESTRE - COMISION)");
                
            }
  
            
        }
            
        return r;
    }
    
    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jbSalirActionPerformed
    
     private void cargarGrillaByFilters(){
         try {
             if (jcbbCarrera.getSelectedIndex() != 0 && jcbbCarrera.getSelectedIndex() == 0) {
                 Carrera carrera = (Carrera) jcbbCarrera.getSelectedItem();
                 jcbbCarrera.setModel((ComboBoxModel<String>) new CursoTableModel((new CursoDAO()).getCursosByCarrera(carrera)));
             }
             if (jcbbMateria.getSelectedIndex() == 0 && jcbbMateria.getSelectedIndex() != 0) {
                 Materia materia = (Materia) jcbbMateria.getSelectedItem();
                 jcbbMateria.setModel((ComboBoxModel<String>) new CursoTableModel((new CursoDAO()).getCursosByMateria(materia)));
             }
         }
         catch (Exception e) {
         
         }         
     }
     
    private void jbGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGrabarActionPerformed
        if (operation.equals("AGREGAR")){ 
            try {
                if (this.grabarCurso("AGREGAR") == 1) { //comprobar si grabó
                    this.setGrabo(true);
                    this.cerrar();
                }
//                    else
//                        JOptionPane.showMessageDialog(this,"Error");
            } catch (ParseException ex) {
                Logger.getLogger(AgregarCursoUI.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        }
        if (operation.equals("EDITAR")){
            try {
                if (this.grabarCurso("EDITAR") == 1) { //comprobar si grabó
                    this.setGrabo(true);
                    this.cerrar(); 
                }
//                    else
//                        JOptionPane.showMessageDialog(this,"Error");
            } catch (ParseException ex) {
                Logger.getLogger(AgregarCursoUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
    }//GEN-LAST:event_jbGrabarActionPerformed

    private void jtfComisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfComisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfComisionActionPerformed

    private void jcbbMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbMateriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbbMateriaActionPerformed

    private void jcbbProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbProfesorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbbProfesorActionPerformed

    private void jcbbCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbCarreraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbbCarreraActionPerformed

    private void jcbbCuatrimestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbCuatrimestreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbbCuatrimestreActionPerformed

    private void jtfIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIDActionPerformed

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
            java.util.logging.Logger.getLogger(AgregarCursoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarCursoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarCursoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarCursoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
	AgregarCursoUI dialog = new AgregarCursoUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLCantidadInscriptos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbGrabar;
    private javax.swing.JButton jbSalir;
    private com.toedter.calendar.JYearChooser jcbbAnioLectivo;
    private javax.swing.JComboBox<String> jcbbCarrera;
    private javax.swing.JComboBox<String> jcbbCuatrimestre;
    private javax.swing.JComboBox<String> jcbbEstado;
    private javax.swing.JComboBox<String> jcbbMateria;
    private javax.swing.JComboBox<String> jcbbProfesor;
    private javax.swing.JLabel jlAnioLectivo;
    private javax.swing.JLabel jlCantidadInscriptos;
    private javax.swing.JLabel jlCarrera;
    private javax.swing.JLabel jlComision;
    private javax.swing.JLabel jlCuatrimestre;
    private javax.swing.JLabel jlDesde;
    private javax.swing.JLabel jlEstado;
    private javax.swing.JLabel jlHasta;
    private javax.swing.JLabel jlMateria;
    private javax.swing.JLabel jlProfesor;
    private javax.swing.JTextField jtfComision;
    private com.toedter.calendar.JDateChooser jtfDesde;
    private com.toedter.calendar.JDateChooser jtfHasta;
    private javax.swing.JTextField jtfID;
    // End of variables declaration//GEN-END:variables
    private String operation;
    private boolean Grabo = false;
    private Carrera carrera ;    
    private Materia materia;
    private Profesor profesor;
    private CarreraMateria carreraMateria;

    public CarreraMateria getCarreraMateria() {
        return carreraMateria;
    }

    public void setCarreraMateria(CarreraMateria carreraMateria) {
        this.carreraMateria = carreraMateria;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    public boolean isGrabo() {
        return Grabo;
    }

    public void setGrabo(boolean Grabo) {
        this.Grabo = Grabo;
    }
    
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

}
