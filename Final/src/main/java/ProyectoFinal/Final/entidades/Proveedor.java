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
    private Double calificacionPromedio;
    private Integer numeroCalificaciones;
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
    
       
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
<<<<<<< HEAD
>>>>>>> b216b020352c169027d44315cc5220324d97eaad
=======
>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c

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

    public Double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(Double calificacionPromedio) {
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

<<<<<<< HEAD
<<<<<<< HEAD
=======
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
}
=======
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
>>>>>>> b216b020352c169027d44315cc5220324d97eaad
