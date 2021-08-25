package eitc.pucmm.Excepciones;

public class noExisteToken extends RuntimeException {

    public noExisteToken(String message) {
        super(message);
    }
}
