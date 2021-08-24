package eitc.pucmm.ApiServices;

import eitc.pucmm.entidades.Cliente;
import eitc.pucmm.entidades.Enlace;
import eitc.pucmm.servicios.EnlaceService;
import eitc.pucmm.servicios.UsuarioService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.IOException;



@WebService
public class SoapWebService {
    private EnlaceService enlaceService = EnlaceService.getInstancia();
    private UsuarioService usuarioService = UsuarioService.getInstancia();

    @WebMethod
    public boolean autentificacion(String user,String password){
       return (usuarioService.autenticarUsuario(user,password) != null)?true:false;
    }

    @WebMethod
    public Enlace[] getEnlaces(String user){
        return crearArreglo(user);
    }

    @WebMethod
    public Enlace getEnlace(int enlace,String user){
        return generarEnlace(enlaceService.find(enlace));
    }

    @WebMethod
    public Enlace registrarEnlace(String url,String usuario) throws IOException {
        return generarEnlace(EnlaceService.getInstancia().registrarEnlace(url,usuario));
    }

    @WebMethod
    public Cliente[] getClientes(int id){
        Enlace enlace = EnlaceService.getInstancia().find(id);
        return enlace.getClientes().toArray(new Cliente[enlace.getClientes().size()]);
    }

    public Enlace[] crearArreglo(String user){
        Enlace[] enlaces = EnlaceService.getInstancia().getEnlaces(user);
        for(int i = 0; i < enlaces.length; i++){
            Enlace aux = generarEnlace(enlaces[i]);
            enlaces[i] = aux;
        }
        return enlaces;
    }
    private Enlace generarEnlace(Enlace enlace) {
        Enlace aux = new Enlace();
        aux.setIdEnlace(enlace.getIdEnlace());
        aux.setURL(enlace.getURL());
        aux.setFecha(enlace.getFecha());
        aux.setURLAcostarda(enlace.getURLAcostarda());
        aux.setVecesAccesidas(enlace.getVecesAccesidas());
        aux.setFotoBase64(enlace.getFotoBase64());
        aux.setClientes(aux.getClientes());
        return aux;
    }
}
