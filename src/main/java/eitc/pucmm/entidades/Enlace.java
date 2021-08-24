package eitc.pucmm.entidades;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
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

    @Lob
    private String fotoBase64;

    //Indicando las referencias unidireccional de la entidad Clase.
    @OneToMany(fetch = FetchType.EAGER) // La clase "Clase" es la dueña de la relación.
    private List<Cliente> clientes;

    @ManyToOne(optional = true)
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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public Map<String, Integer> calcularDatos() {
        List<Cliente> clientes1 = new ArrayList<>(clientes);
        if (!clientes1.isEmpty()) {
            Cliente primero = clientes1.get(0);

            Map<String, Integer> cantPorDia = new HashMap<String, Integer>();
            String fechaActual = primero.getFechaFormat();
            int aux = 1;
            int i = 1;
            while (i < clientes1.size()) {
                Cliente client = clientes1.get(i);
                if (!client.getFechaFormat().equalsIgnoreCase(fechaActual)) {
                    cantPorDia.put(fechaActual, aux);
                    aux = 1;
                    fechaActual = client.getFechaFormat();
                } else {
                    aux++;
                }
                i++;
            }
            cantPorDia.put(fechaActual, aux);

            return cantPorDia;
        }
        return null;
    }
}

