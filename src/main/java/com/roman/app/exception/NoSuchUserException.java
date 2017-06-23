package com.roman.app.exception;

public class NoSuchUserException extends Exception{

    public NoSuchUserException () {
        super("Can't find user with this id");
    }
}