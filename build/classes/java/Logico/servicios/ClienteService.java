package logico;

import logico.entidades.Foto;
import logico.servicios.GestionDb;

/**
 *
 */
public class ClienteService extends GestionDb<Cliente> {

    private static ClienteService instancia;

    private ClienteService(){
        super(Cliente.class);
    }

    public static ClienteService getInstancia(){
        if(instancia==null){
            instancia = new ClienteService();
        }
        return instancia;
    }

}
