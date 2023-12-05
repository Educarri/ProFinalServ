/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.entidades;

import ProyectoFinal.Final.enumeraciones.Oficios;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Proveedor extends Usuario {

    public Proveedor() {
    }

    @Enumerated(EnumType.STRING)
    private Oficios oficio;
    private Integer precioHs;
    private String descripService;
    private Integer calificacionPromedio;
    private Integer numeroCalificaciones;
    
       
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @OneToOne
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

 

    public String getDescripService() {
        return descripService;
    }

    public void setDescripService(String descripService) {
        this.descripService = descripService;
    }

    public Integer getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(Integer calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public Integer getNumeroCalificaciones() {
        return numeroCalificaciones;
    }

    public void setNumeroCalificaciones(Integer numeroCalificaciones) {
        this.numeroCalificaciones = numeroCalificaciones;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
