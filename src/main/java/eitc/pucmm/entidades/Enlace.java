package eitc.pucmm.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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


}
