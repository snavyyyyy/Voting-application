package org.project.exception;

public class UsernameAlreadyExistsException extends Exception{
    public UsernameAlreadyExistsException(String username){
        super(String.format("Username %s already exists.",username));
    }
}
