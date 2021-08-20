package eitc.pucmm.servicios;

import com.sun.tools.jconsole.JConsoleContext;
import eitc.pucmm.Main;
import eitc.pucmm.entidades.Enlace;
import kong.unirest.Unirest;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.xml.transform.Result;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.*;

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
            Query query = em.createQuery("select e from Enlace e where e.URLAcostarda like :cod", Enlace.class);
            query.setParameter("cod",cod+"%");
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
        Query query = em.createQuery("select e from Enlace e where e.URLAcostarda like :cod" , Enlace.class);
        query.setParameter("cod","'%"+path+"%'");
        List<Enlace> enlaces  = query.getResultList();
        return enlaces.get(0);
    }


    public String getPreview(String url) {
        String response = Unirest.get("https://api.microlink.io?url="+url+"&screenshot=true&meta=false")
                .asJson().getBody().getObject().getJSONObject("data")
                .getJSONObject("screenshot").get("url").toString();
        try {
            java.net.URL aux = new URL(response);
            BufferedImage image = ImageIO.read(aux);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image,"png",bos);
            response = Base64.getEncoder().encodeToString(bos.toByteArray());
        }catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getAcortado() {
        boolean res = false;
        String cod = "";

        while(!res){
            cod = Main.codeGenerator();
            res = instancia.verificarCod(cod);
        }
        return cod;
    }
}
