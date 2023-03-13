package com.avaj.simulator.exceptions;

public class WrongFormatException extends RuntimeException {

    public WrongFormatException(Throwable err) {
        super("Wrong format ", err);
    }
}