/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

import java.util.Date;

/**
 *
 * @author Lucho
 */
public class Profesor {
    
    //atributos
    private int IdProfesor;
    private String apellido;
    private String nombre;
    private int DNI;
    private String genero;
    private Date fechaDeNacimiento;
    private String email;
    private boolean estado;

    //getter & setter
    public int getIdProfesor() {
        return IdProfesor;
    }
    public void setIdProfesor(int IdProfesor) {
        this.IdProfesor = IdProfesor;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getDni() {
        return DNI;
    }
    public void setDni(int dni) {
        this.DNI = dni;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    //toString

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }


    
    
    
}

