package eitc.pucmm.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.Set;

@Entity

public class Enlace implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnlace;
    private String URL;
    private Date fecha = new Date();
    private String URLAcostarda;
    private int vecesAccesidas = 0;

    //Indicando las referencias unidireccional de la entidad Clase.
    @OneToMany(fetch = FetchType.EAGER) // La clase "Clase" es la dueña de la relación.
    private Set<Cliente> clientes;

    @ManyToOne()
    private Usuario usuario; //muchos enlaces tienen 1 usuario

    public int getIdEnlace() {
        return idEnlace;
    }

    public void setIdEnlace(int idEnlace) {
        this.idEnlace = idEnlace;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getURLAcostarda() {
        return URLAcostarda;
    }

    public void setURLAcostarda(String URLAcostarda) {
        this.URLAcostarda = URLAcostarda;
    }

    public int getVecesAccesidas() {
        return vecesAccesidas;
    }

    public void setVecesAccesidas(int vecesAccesidas) {
        this.vecesAccesidas = vecesAccesidas;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
