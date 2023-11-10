/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.entidades;

<<<<<<< HEAD:Final/src/main/java/entidades/Proveedor.java
import enumeraciones.Oficios;
import javax.persistence.CascadeType;
=======
import ProyectoFinal.Final.enumeraciones.Oficios;
>>>>>>> ccf5e717874618087f7981d6918a61ea05a25247:Final/src/main/java/ProyectoFinal/Final/entidades/Proveedor.java
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
public class Proveedor extends Usuario{

    public Proveedor() {
    }
    
    @Enumerated(EnumType.STRING)
    private Oficios oficio;
    private Integer precioHs;
    private Integer reputacion;
    private String descripcionService;
    
    
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Imagen imagen;

    public Oficios getOficio() {
        return oficio;
    }

    public void setOficio(Oficios oficio) {
        this.oficio = oficio;
    }

    public Integer getPrecioHs() {
        return precioHs;
    }

    public void setPrecioHs(Integer precioHs) {
        this.precioHs = precioHs;
    }

    public Integer getReputacion() {
        return reputacion;
    }

    public void setReputacion(Integer reputacion) {
        this.reputacion = reputacion;
    }

    public String getDescrService() {
        return descripcionService;
    }

    public void setDescrService(String descrService) {
        this.descripcionService = descrService;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }
    
    
    
    
    
    
}
