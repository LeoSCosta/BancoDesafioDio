package Banco.Segurança;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(String msg) {
        super(msg);
    }
}
