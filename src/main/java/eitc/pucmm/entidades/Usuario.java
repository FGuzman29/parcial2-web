package eitc.pucmm.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Usuario.findAllByUsuario", query = "select u from Usuario u where u.usuario = :user"),
        @NamedQuery(name = "Usuario.autenticarUsuario", query = "select u from Usuario u where u.usuario = :user and u.password = :pass")})

public class Usuario implements Serializable {

    public enum RoleasAPP
    {
        ROLE_USUARIO, ROLE_ADMIN
    }

    @Id
    private String usuario;
    private String nombre;
    private String password;
    private RoleasAPP rol;  //lo estaremos utilizando para los roles.

    //Indicando las referencias bidireccional de la entidad Clase.

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER) // La clase "Clase" es la dueña de la relación.
    private Set<Enlace> misEnlaces;


}
