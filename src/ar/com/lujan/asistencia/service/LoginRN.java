/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.service;

import ar.com.lujan.asistencia.database.LoginDAO;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Lucho
 */
public class LoginRN {
    
    LoginDAO loginDAO = new LoginDAO();
    
    public boolean validarLogin(String username, String password,Boolean modoSeguro){
        boolean b = false;
        /*
        if (loginDAO.validarUsuario(username)==1){
            System.out.println("USUARIO EXISTENTE");
        }
        else{
            System.out.println("USUARIO INEXISTENTE");
        }*/
        if (modoSeguro==true) //Si el modo seguro esta activado, codificar la contraseña
            password=DigestUtils.md5Hex(password);
        if (loginDAO.validarUsername(username)==1)//si el usuario existe
            if (password.equals(loginDAO.obtenerHash(username)))//si la contraseña esta bien 
               b=true; //devolver verdadero
            else
                System.out.println("login failed");
        else
            System.out.println("login failed");
        return b;
    }
    
    
    
    
}
