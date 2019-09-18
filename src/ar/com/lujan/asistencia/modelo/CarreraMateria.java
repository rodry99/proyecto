/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

/**
 *
 * @author Papa
 */
public class CarreraMateria {
    private int idCarreraMateria;
    private Carrera carrera;
    private Materia materia;
    private int anioCarrera;
    
    public int getIdCarreraMateria() {
        return idCarreraMateria;
    }

    public void setIdCarreraMateria(int idCarreraMateria) {
        this.idCarreraMateria = idCarreraMateria;
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
    
    public int getAnioCarrera() {
        return anioCarrera;
    }

    public void setAnioCarrera(int anioCarrera) {
        this.anioCarrera = anioCarrera;
    }
    
    @Override
    public String toString (){
        return this.materia.getDescripcion();
    }

}
