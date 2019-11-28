package hu.bme.szoftarch.library.libbackend.utils.exceptions;

public class UnauthenticatedUserException extends RuntimeException {
    public UnauthenticatedUserException(String s){
        super(s);
    }
}
