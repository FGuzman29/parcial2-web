package eitc.pucmm.soapServices;

import eitc.pucmm.entidades.Enlace;
import eitc.pucmm.entidades.Usuario;
import eitc.pucmm.servicios.ClienteService;
import eitc.pucmm.servicios.EnlaceService;
import eitc.pucmm.servicios.UsuarioService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class SoapWebService {
    private EnlaceService enlaceService = EnlaceService.getInstancia();
    private UsuarioService usuarioService = UsuarioService.getInstancia();

    @WebMethod
    public boolean autentificacion(String user,String password){
       if(usuarioService.autenticarUsuario(user,password)!= null){
           return true;
       }else {
           return false;
       }
    }

    @WebMethod
    public List<Enlace> getEnlaces(String user){
        Usuario usuario = usuarioService.findAllByUsuario(user).get(0);
        return (List<Enlace>) usuario.getMisEnlaces();
    }

    @WebMethod
    public Enlace getEnlace(int enlace){
        return enlaceService.find(enlace);
    }

    @WebMethod
    public Enlace registrarEnlace(String url){
        Enlace enlace = new Enlace();
        String preview = enlaceService.getPreview(url);
        String acortado = enlaceService.getAcortado();

        enlace.setFotoBase64(preview);
        enlace.setURL(url);
        enlace.setURLAcostarda(acortado);

        enlaceService.crear(enlace);
        return enlace;
    }
}
