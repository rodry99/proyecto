/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.vista;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author geragonzalez
 */
public class JPanelConFondo extends JPanel {
 
    private Image imagen;
 
    public JPanelConFondo() {
    }
 
    public JPanelConFondo(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(
                           getClass().getResource(nombreImagen)
                           ).getImage();
        }
    }
 
    public JPanelConFondo(Image imagenInicial) {
        if (imagenInicial != null) {
            imagen = imagenInicial;
        }
    }
 
    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = new ImageIcon(
                   getClass().getResource(nombreImagen)
                   ).getImage();
        } else {
            imagen = null;
        }
 
        repaint();
    }
 
    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;
 
        repaint();
    }
 
    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
                              this);
 
            setOpaque(false);
        } else {
            setOpaque(true);
        }
 
        super.paint(g);
    }
}
    

