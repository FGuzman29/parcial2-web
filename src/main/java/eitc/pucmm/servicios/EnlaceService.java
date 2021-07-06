package eitc.pucmm.servicios;

import eitc.pucmm.entidades.Enlace;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class EnlaceService extends GestionDb<Enlace> {

    private static EnlaceService instancia;

    private EnlaceService() {
        super(Enlace.class);
    }

    public static EnlaceService getInstancia(){
        if(instancia==null){
            instancia = new EnlaceService();
        }
        return instancia;
    }

    /**
     *
     * @param
     * @return
     */

    public List<Enlace> consultaNativa(){
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery("select * from Enlace ", Enlace.class);

        List<Enlace> lista = query.getResultList();
        return lista;
    }



    public Set<Enlace> eliminarEnlaceByID(Integer actual, Set<Enlace> enlace)
    {
        for (Enlace Eactual : enlace) {
            if(Eactual.getIdEnlace() == actual)
            {
                enlace.remove(Eactual);
                return enlace;
            }
        }

        return null;
    }


}
