package team6.exceptions;

public class ElementoNonTrovatoException extends RuntimeException {
    public ElementoNonTrovatoException(String messaggio) {
        super(messaggio);
    }
}