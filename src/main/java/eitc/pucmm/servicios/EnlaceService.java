package eitc.pucmm.servicios;

import eitc.pucmm.entidades.Enlace;

import javax.persistence.*;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.List;
import java.util.Locale;
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

    public static boolean verificarCod(String cod) {
        EntityManager em = getEntityManager();
        boolean res = false;
        try {
            Query query = em.createNativeQuery("select * from Enlace where URLAcostarda = " + cod.toString(), Enlace.class);

             res = query.getResultList().isEmpty();
        } catch (Exception e) {
            res = true;
        }
        System.out.println(res);
        return res;
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


    public Enlace findEnlace(String path) {
        EntityManager em = getEntityManager();
        List<Enlace> enlaces;
        try {
            Query query = em.createNativeQuery("select * from Enlace where URLAcostarda = " + path.toString(), Enlace.class);
            enlaces = query.getResultList();
            return enlaces.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
