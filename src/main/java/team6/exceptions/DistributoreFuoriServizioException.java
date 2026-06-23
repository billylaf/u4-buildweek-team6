package team6.exceptions;

public class DistributoreFuoriServizioException extends RuntimeException {
    public DistributoreFuoriServizioException(String messaggio) {
        super(messaggio);
    }
}