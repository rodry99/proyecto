/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author impztz
 */
public class CarreraMateriaTableModel extends AbstractTableModel {
    
    private List<CarreraMateria> carreraMateria;
    private static  String[] COLUMNAS={"idCarreraMateria", "idCarrera", "idMateria", "Carrera", "Materia", "Año Curso"};
    
    
    public CarreraMateriaTableModel (List<CarreraMateria> carreraMateria){
        super();
        this.carreraMateria = carreraMateria;
    }
    
    @Override
    public int getRowCount() {
        return carreraMateria.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno  = null;
        CarreraMateria cm = this.carreraMateria.get(rowIndex);
        switch (columnIndex){
            case 0: retorno = cm.getIdCarreraMateria(); break;
            case 1: retorno = cm.getCarrera().getIdCarrera(); break;
            case 2: retorno = cm.getMateria().getIdMateria(); break;
            case 3: retorno = cm.getCarrera().getDescripcion(); break;
            case 4: retorno = cm.getMateria().getDescripcion(); break;
            case 5: retorno = cm.getAnioCarrera();break;
        }
        return retorno;
        
    }
    
    @Override
    public String getColumnName(int index){
         return COLUMNAS[index];
    }
}
