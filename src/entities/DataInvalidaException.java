package entities;

@SuppressWarnings("serial")
public class DataInvalidaException extends Exception {

    public DataInvalidaException(String mensagem) {
        super(mensagem);
    }
}