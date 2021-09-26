package com.nc.edu.ta.artemryabtsev.pr6;

public class InvalidTaskParametersException extends RuntimeException{
    public InvalidTaskParametersException() {
    }

    public InvalidTaskParametersException(String message) {
        super(message);
    }
}
