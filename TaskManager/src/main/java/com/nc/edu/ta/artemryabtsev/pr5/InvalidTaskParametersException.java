package com.nc.edu.ta.artemryabtsev.pr5;

public class InvalidTaskParametersException extends RuntimeException{
    public InvalidTaskParametersException() {
    }

    public InvalidTaskParametersException(String message) {
        super(message);
    }
}
