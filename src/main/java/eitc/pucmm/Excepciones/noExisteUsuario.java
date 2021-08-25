package eitc.pucmm.Excepciones;

public class noExisteUsuario extends RuntimeException {

    public noExisteUsuario(String message) {
        super(message);
    }
}
