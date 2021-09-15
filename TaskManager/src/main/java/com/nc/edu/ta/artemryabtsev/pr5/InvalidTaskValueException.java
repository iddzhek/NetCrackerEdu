package com.nc.edu.ta.artemryabtsev.pr5;

public class InvalidTaskValueException extends NullPointerException{
    public InvalidTaskValueException() {
    }

    public InvalidTaskValueException(String s) {
        super(s);
    }
}
