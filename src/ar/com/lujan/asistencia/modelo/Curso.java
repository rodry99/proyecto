/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;
import java.sql.Date;

/**
 *
 * @author lledesma
 */
public class Curso {
    private int idCurso;
    private CarreraMateria carreraMaterias;
    private int anioLectivo;
    private String cuatrimestre;
    private String comision;
    private Profesor profesor;
    private String estado;
    private int cantidadInscriptos;
    private Date desde;
    private Date hasta;
    
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    

    public CarreraMateria getCarreraMaterias() {
        return carreraMaterias;
    }

    public void setCarreraMaterias(CarreraMateria carreraMaterias) {
        this.carreraMaterias = carreraMaterias;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

  
    public int getAnioLectivo() {
        return anioLectivo;
    }

    public void setAnioLectivo(int anioLectivo) {
        this.anioLectivo = anioLectivo;
    }

    public String getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(String cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public void setCantidadInscriptos(int cantidadInscriptos) {
        this.cantidadInscriptos = cantidadInscriptos;
    }

    @Override
    public String toString() {
        return carreraMaterias.getMateria().getDescripcion() + " - " + anioLectivo;
    }
    
    
}
