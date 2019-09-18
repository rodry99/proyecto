/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author geragonzalez
 */
public class AsistenciaClaseTableModel  extends AbstractTableModel{
    private List<AsistenciaClase> asistencia;
    private static  String[] COLUMNAS={"IdAsistencia", "IdCursoAlumno", "IdClase", "Apellido y Nombre", "Asistencia"};

    public AsistenciaClaseTableModel (List<AsistenciaClase> asistencia){
        super();
        this.asistencia = asistencia;
    }

    @Override
    public int getRowCount() {
        return this.asistencia.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno  = null;
        AsistenciaClase a = this.asistencia.get(rowIndex);
        switch (columnIndex){
            case 0: retorno = a.getIdAsistencia(); break;
            case 1: retorno = a.getCursoAlumno().getIdCursoAlumno(); break;
            case 2: retorno = a.getClase().getIdClase(); break;
            case 3: retorno = a.getCursoAlumno().getAlumno().getApellido() + ", " + a.getCursoAlumno().getAlumno().getNombre(); break;
            case 4: retorno = a.getAsistencia(); break;
        }
        return retorno;
    }
    
    @Override
    public String getColumnName(int index){
         return COLUMNAS[index];
    }

    // Esto se define para que aparezca el checkbox en la grilla.
    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return Boolean.class;
            default: return String.class;
        }
    }

    // Se hace editable el campo que contiene el checkbox
    @Override
    public boolean isCellEditable(int i, int c){
        return c == 4;
    }
    
    // Actualiza en la tabla el checkbox
    @Override
    public void setValueAt(Object value, int row, int col){
        this.setGrabar(true);
        asistencia.get(row).setAsistencia((Boolean) value);
    }

    // Variable para saber si se tocó la tabla
    private boolean grabar = false;

    public boolean isGrabar() {
        return grabar;
    }

    public void setGrabar(boolean grabar) {
        this.grabar = grabar;
    }

}
