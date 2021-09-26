package com.nc.edu.ta.artemryabtsev.pr6;

public class InvalidTaskValueException extends NullPointerException{
    public InvalidTaskValueException() {
    }

    public InvalidTaskValueException(String s) {
        super(s);
    }
}
