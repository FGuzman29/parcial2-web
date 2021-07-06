package eitc.pucmm.servicios;

import eitc.pucmm.entidades.Cliente;

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
