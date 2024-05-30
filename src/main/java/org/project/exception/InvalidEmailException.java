package org.project.exception;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(String email) {
        super(String.format("Invalid email adress:%s",email));
    }
}