package eitc.pucmm.controladores;

import eitc.pucmm.Excepciones.noExisteUsuario;
import eitc.pucmm.entidades.Enlace;
import eitc.pucmm.entidades.Usuario;
import eitc.pucmm.servicios.EnlaceService;
import eitc.pucmm.servicios.UsuarioService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RestControlador {

    private static RestControlador instancia;
    private List<EnlaceService> listaEstudiante = new ArrayList<>();
    private List<Usuario> listaUsuarios = new ArrayList<>();

    /**
     * Constructor privado.
     */

    public static RestControlador getInstancia(){
        if(instancia==null){
            instancia = new RestControlador();
        }
        return instancia;
    }


    public Usuario buscarUsuario(String usuario){
        List<Usuario> act =  UsuarioService.getInstancia().findAllByUsuario(usuario);
        if(act.size()==0){
            return null; //generar una excepcion...
        }

        for (Usuario user: act
             ) {
            System.out.println(user.getUsuario());
        }
        return act.get(0);
    }

    public Set<Enlace> getListaUsuarios(String usuario){

        Usuario act= buscarUsuario(usuario);
        if(act== null)
        {
            throw new noExisteUsuario("No Existe el usuario: "+usuario);
        }
        return act.getMisEnlaces();
    }

    public Enlace registrarEnlace(String url,String usuario) throws IOException {
        return generarEnlace(EnlaceService.getInstancia().registrarEnlace(url,usuario));
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
