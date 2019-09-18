/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.vista;


import ar.com.lujan.asistencia.service.ClaseRN;

import ar.com.lujan.asistencia.modelo.Carrera;
import ar.com.lujan.asistencia.modelo.CarreraMateria;
import ar.com.lujan.asistencia.modelo.Clase;
import ar.com.lujan.asistencia.modelo.ClaseTableModel;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.modelo.Materia;
import ar.com.lujan.asistencia.modelo.Profesor;

import java.awt.event.KeyEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


/**
 *
 * @author usuario
 */
public class ABMClases extends javax.swing.JDialog {

    ClaseRN claseRN = new ClaseRN();
    

/*List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
sorter.setSortKeys(sortKeys);*/
    
    public ABMClases(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.llenarCombos();
        this.cargarGrilla();
        //Desabilitar los botones Borrar/Editar
        jbEditar.setEnabled(false);
        jbBorrar.setEnabled(false);
        
    }
   
    private void cerrar(){
       this.dispose();
   }

    private void llenarCombos(){
        //Combobox Profesor
        if (jcbbProfesor.getSelectedIndex()<1){
            jcbbProfesor.setModel(claseRN.llenarComboProfesor());//llenar combo
            jcbbProfesor.insertItemAt("Todos",0);//agregar el item 0 al CBB = Todos
            jcbbProfesor.setSelectedIndex(0); //y setetearlo como predeterminado
        }

        //Combobox Curso
        if (jcbbCurso.getSelectedIndex()<1){
            jcbbCurso.setModel(claseRN.llenarComboCurso());//llenar combo
            jcbbCurso.insertItemAt("Todos",0);//agregar el item 0 al CBB = Todos
            jcbbCurso.setSelectedIndex(0); //y setetearlo como predeterminado
        }

        //Combobox Año
        if (jcbbAnio.getSelectedIndex()<1){
            jcbbAnio.setModel(claseRN.llenarComboAnio());//llenar combo
            jcbbAnio.insertItemAt("Todos",0);//agregar el item 0 al CBB = Todos
            jcbbAnio.setSelectedIndex(0); //y setetearlo como predeterminado
        }
    }
        
        
    
    private void cargarGrilla(){
        claseRN= new ClaseRN();
        ClaseTableModel tabla;
        
        //si todos los filtros estan en 0
        if (jcbbProfesor.getSelectedIndex()<1 && jcbbCurso.getSelectedIndex()<1 && jdcDesde.getDate()==null  && jdcHasta.getDate()==null && jcbbAnio.getSelectedIndex()<1){ 
                 
            //cargar grilla sin filtros
            tabla = claseRN.cargarGrilla();
            
        }
        else{ //sino
            Profesor profesor=null; //inicializa las variables en null
            Curso curso=null;
            Date desde=null;
            Date hasta=null;
            int anio=0;
            //en caso que tengan contenido, les asigna el valor, sino las deja en null (anio en 0 porque es integer)
            if (jcbbProfesor.getSelectedIndex()!=0)
                profesor=(Profesor)jcbbProfesor.getSelectedItem();
            if (jcbbCurso.getSelectedIndex()!=0){
                curso=(Curso)jcbbCurso.getSelectedItem();
            }
            if (jdcDesde.getDate()!=null){
                desde=jdcDesde.getDate();
            }
            if (jdcHasta.getDate()!=null){
                hasta=jdcHasta.getDate();
            }
            if (jcbbAnio.getSelectedIndex()>0){
                anio=Integer.parseInt(jcbbAnio.getSelectedItem().toString());
            }
            tabla = claseRN.cargarGrillaByFilter(profesor,curso,desde,hasta,anio);
            
            //llena la grilla
        }
        jtClases.setModel(tabla);
        claseRN.formatoGrilla(jtClases); //da formato a la grilla
        //Desabilitar los botones Borrar/Editar, hasta volver a seleccionar fila
        jbBorrar.setEnabled(false);
        jbEditar.setEnabled(false);
   }

    
    private Clase armarObjetoClase(int r){
        Materia m = new Materia();
        m.setIdMateria((int) jtClases.getModel().getValueAt(r,21));
        m.setDescripcion((String) jtClases.getModel().getValueAt(r,22));

        Carrera ca = new Carrera();
        ca.setIdCarrera((int) jtClases.getModel().getValueAt(r,19));
        ca.setDescripcion((String) jtClases.getModel().getValueAt(r,20));

        CarreraMateria cm = new CarreraMateria();
        cm.setIdCarreraMateria((int) jtClases.getModel().getValueAt(r,15));
        cm.setMateria(m);
        cm.setCarrera(ca);
        cm.setAnioCarrera((int) jtClases.getModel().getValueAt(r,18));

        Profesor p = new Profesor();
        p.setIdProfesor((int) jtClases.getModel().getValueAt(r,23));
        p.setApellido((String) jtClases.getModel().getValueAt(r,24));
        p.setNombre((String) jtClases.getModel().getValueAt(r,25));
        p.setDni((int) jtClases.getModel().getValueAt(r,26));
        p.setGenero((String) jtClases.getModel().getValueAt(r,27));
        p.setFechaDeNacimiento((Date) jtClases.getModel().getValueAt(r,28));
        p.setEmail((String) jtClases.getModel().getValueAt(r,29));
        p.setEstado((boolean) jtClases.getModel().getValueAt(r,30));

        Curso cu = new Curso();
        cu.setIdCurso((int) jtClases.getModel().getValueAt(r,5));
        cu.setCarreraMaterias(cm);
        cu.setAnioLectivo((int) jtClases.getModel().getValueAt(r,7));
        cu.setCuatrimestre((String) jtClases.getModel().getValueAt(r,8));
        cu.setComision((String) jtClases.getModel().getValueAt(r,9));
        cu.setProfesor(p);
        cu.setEstado((String) jtClases.getModel().getValueAt(r,11));
        cu.setCantidadInscriptos((int) jtClases.getModel().getValueAt(r,12));
        cu.setDesde((java.sql.Date)jtClases.getModel().getValueAt(r,13));
        cu.setHasta((java.sql.Date)jtClases.getModel().getValueAt(r,14));

        Clase cl = new Clase();
        cl.setIdClase((int) jtClases.getModel().getValueAt(r,0));
        cl.setCurso(cu);
        try {
            cl.setFecha((new SimpleDateFormat("dd-MM-yyyy")).parse((String)jtClases.getModel().getValueAt(r,2)));
        } catch (ParseException ex) {
            Logger.getLogger(ABMClases.class.getName()).log(Level.SEVERE, null, ex);
        }
        cl.setTema((String) jtClases.getModel().getValueAt(r,3));
        cl.setEstado((String) jtClases.getModel().getValueAt(r,4));
        return cl;
        
    } 
   
    private void agregar(){
        AgregarClaseUI agregarClaseUI;
        agregarClaseUI = new AgregarClaseUI(null,true,"AGREGAR");//el primero es la herencia, o sea, extiende de jFrame. el segundo es si es modal o no
        agregarClaseUI.setLocationRelativeTo(null);
        agregarClaseUI.setVisible(true);
        this.cargarGrilla(); 
        
        
    }
    
    private void editar(){
        //si hay una fila seleccionada
        int r = jtClases.getSelectedRow();
        if (r != -1){
            //armar el objeto Alumno con los datos de la fila
            Clase cl=this.armarObjetoClase(r);
            //instanciar la ventana y pasarle el objeto alumno
            AgregarClaseUI editarClaseUI; //el primero es la herencia, o sea, extiende de jFrame. el segundo es si es modal o no, si esta por encima de todo
            editarClaseUI = new AgregarClaseUI(null,true,"EDITAR",cl);
            editarClaseUI.setLocationRelativeTo(null); 
            editarClaseUI.setVisible(true);
            if (editarClaseUI.isGrabo()) {
                this.cargarGrilla();
            }
        }
    }
    
    private void borrar(){
        //levanta el indice de fila seleccionada
        int r = jtClases.getSelectedRow();
        //si es distinto a -1 ( o sea que hay fila seleccionada)
        
        if (r != -1){//Arma el objeto con los datos de la fila
            Clase cl = this.armarObjetoClase(r);
            //Ejecuta el borrar 
            int confirmDialog=JOptionPane.showConfirmDialog(this,"¿Confirma borrado: " +  (new SimpleDateFormat("dd-MM-yyyy")).format(cl.getFecha()) + 
                    " - " + cl.getCurso().toString() +  " " + cl.getTema()+ "?","Borrar Clase",JOptionPane.YES_NO_OPTION);
                    if(confirmDialog == JOptionPane.YES_OPTION){//borra
                        JOptionPane.showMessageDialog(this, "Se ha eliminado exitosamente");
                        claseRN.borrarClase(cl);
                    }
            this.cargarGrilla();
        
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

        jlClases = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtClases = new javax.swing.JTable();
        jbBorrar = new javax.swing.JButton();
        jbEditar = new javax.swing.JButton();
        jbAgregar = new javax.swing.JButton();
        jcbbProfesor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbbCurso = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcbbAnio = new javax.swing.JComboBox<>();
        jdcHasta = new com.toedter.calendar.JDateChooser();
        jdcDesde = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Libro de temas");
        setAlwaysOnTop(true);
        setModal(true);
        setResizable(false);

        jlClases.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jlClases.setText("Libro de temas");

        jtClases.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtClases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtClasesMouseClicked(evt);
            }
        });
        jtClases.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtClasesKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jtClases);

        jbBorrar.setText("Borrar");
        jbBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarActionPerformed(evt);
            }
        });

        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });

        jbAgregar.setText("Agregar");
        jbAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAgregarActionPerformed(evt);
            }
        });

        jcbbProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbProfesorActionPerformed(evt);
            }
        });

        jLabel1.setText("Profesor");

        jLabel2.setText("Curso");

        jcbbCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbCursoActionPerformed(evt);
            }
        });

        jLabel3.setText("Desde");

        jLabel4.setText("Hasta");

        jLabel5.setText("Año");

        jcbbAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbAnioActionPerformed(evt);
            }
        });

        jdcHasta.setDateFormatString("dd-MM-yyyy");
        jdcHasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcHastaPropertyChange(evt);
            }
        });

        jdcDesde.setDateFormatString("dd-MM-yyyy");
        jdcDesde.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcDesdePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbbProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbbCurso, 0, 221, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(3, 3, 3)
                        .addComponent(jdcDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlClases)
                        .addGap(351, 351, 351))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbBorrar)
                        .addGap(296, 296, 296))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlClases)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jcbbProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(jdcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAgregar)
                    .addComponent(jbEditar)
                    .addComponent(jbBorrar))
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtClasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtClasesMouseClicked
        //Habilitar los botones borrar/editar al momento de seleccionar una fila
        jbBorrar.setEnabled(true);
        jbEditar.setEnabled(true);
// TODO add your handling code here:
        if (evt.getClickCount() > 1){
            this.editar();
        }
    }//GEN-LAST:event_jtClasesMouseClicked

    private void jbBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarActionPerformed
        // TODO add your handling code here:
        this.borrar();
    }//GEN-LAST:event_jbBorrarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        // TODO add your handling code here:
        //si hay una fila seleccionada
        this.editar();
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAgregarActionPerformed
        // TODO add your handling code here:
        this.agregar();
    }//GEN-LAST:event_jbAgregarActionPerformed

    private void jcbbProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbProfesorActionPerformed
        // TODO add your handling code here:
        this.cargarGrilla();
    }//GEN-LAST:event_jcbbProfesorActionPerformed

    private void jcbbCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbCursoActionPerformed
        // TODO add your handling code here:
        this.cargarGrilla();
    }//GEN-LAST:event_jcbbCursoActionPerformed

    private void jcbbAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbAnioActionPerformed
        // TODO add your handling code here:
        this.cargarGrilla();
    }//GEN-LAST:event_jcbbAnioActionPerformed

    private void jdcDesdePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcDesdePropertyChange
        // TODO add your handling code here:
        this.cargarGrilla();
        Date date = jdcDesde.getDate();
        System.out.println(date);
    }//GEN-LAST:event_jdcDesdePropertyChange

    private void jdcHastaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcHastaPropertyChange
        // TODO add your handling code here:
        this.cargarGrilla();
    }//GEN-LAST:event_jdcHastaPropertyChange

    private void jtClasesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtClasesKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_DELETE){
            this.borrar();// used to consumed the key in the keytyped event
        }
    }//GEN-LAST:event_jtClasesKeyPressed


    
    
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
            java.util.logging.Logger.getLogger(ABMAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ABMAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ABMAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ABMAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
     
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ABMAlumnos dialog = new ABMAlumnos(new javax.swing.JFrame(), true);
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
    //Variables Personalizadas
    private Clase clase;
    private boolean grabo = false;
    private String operacion;
   
    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    
    public boolean isGrabo() {
        return grabo;
    }

    public void setGrabo(boolean Grabo) {
        this.grabo = Grabo;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbAgregar;
    private javax.swing.JButton jbBorrar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JComboBox<String> jcbbAnio;
    private javax.swing.JComboBox<String> jcbbCurso;
    private javax.swing.JComboBox<String> jcbbProfesor;
    private com.toedter.calendar.JDateChooser jdcDesde;
    private com.toedter.calendar.JDateChooser jdcHasta;
    private javax.swing.JLabel jlClases;
    private javax.swing.JTable jtClases;
    // End of variables declaration//GEN-END:variables
}







