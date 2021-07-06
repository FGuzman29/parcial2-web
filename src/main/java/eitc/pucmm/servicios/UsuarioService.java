package eitc.pucmm.servicios;

import eitc.pucmm.entidades.Usuario;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UsuarioService extends GestionDb<Usuario> {

    private static UsuarioService instancia;

    private UsuarioService(){
        super(Usuario.class);
    }

    public static UsuarioService getInstancia(){
        if(instancia==null){
            instancia = new UsuarioService();
        }
        return instancia;
    }



    /**
     *
     * @param user
     * @return
     */
    public List<Usuario> findAllByUsuario(String user){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Usuario.findAllByUsuario");
        query.setParameter("user", "%"+user+"%");
        List<Usuario> lista = query.getResultList();
        return lista;


    }

    /**
     *
     * @param user
     *  @param pass
     * @return
     */

    public  Usuario autenticarUsuario(String user, String pass){
        EntityManager em = getEntityManager();

        Query query = em.createNamedQuery("Usuario.autenticarUsuario");
        query.setParameter("user", user);
        query.setParameter("pass", pass);

        List<Usuario> lista =  query.getResultList();
        return lista.isEmpty() ? null : lista.get(0);
    }
}
