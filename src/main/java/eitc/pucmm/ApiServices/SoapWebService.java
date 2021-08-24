package eitc.pucmm.ApiServices;

import com.google.gson.Gson;
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
        return EnlaceService.getInstancia().getEnlaces(user);
    }

    @WebMethod
    public Enlace getEnlace(int enlace,String user){
        Enlace enlace1 = enlaceService.find(enlace);
        if(enlace1 != null){
            if(enlace1.getUsuario().getUsuario().equalsIgnoreCase(user));
            System.out.println(enlace1.getUsuario().getUsuario());
                return enlace1;
        }
        return enlace1;
    }

    @WebMethod
    public String registrarEnlace(String url,String usuario) throws IOException {
        return new Gson().toJson(EnlaceService.getInstancia().registrarEnlace(url,usuario)).toString();
    }


    @WebMethod
    public Cliente[] getClientes(int id){
        Enlace enlace = EnlaceService.getInstancia().find(id);
        return enlace.getClientes().toArray(new Cliente[enlace.getClientes().size()]);
    }
}
