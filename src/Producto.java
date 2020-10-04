/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juan-Gtsk
 */
public class Producto {
    private String nombreProducto = "";
    private int cantidaProducto = 0;
    
    public Producto(String nombreProducto, int cantidadProducto) {
        this.nombreProducto = nombreProducto;
        this.cantidaProducto = cantidadProducto; 
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidaProducto() {
        return this.cantidaProducto;
    }

    public void setCantidaProducto(int cantidadProducto) {
        this.cantidaProducto = cantidadProducto;
    }
    
}
