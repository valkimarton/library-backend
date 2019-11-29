package hu.bme.szoftarch.library.libbackend.utils.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String s) {
        super(s);
    }
}
