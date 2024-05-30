package org.project.exception;

public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(){
        super(String.format("Invalid username or invalid password"));
    }
}
