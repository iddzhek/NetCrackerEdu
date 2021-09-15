package com.nc.edu.ta.artemryabtsev.pr5;

public class InvalidTaskIndexException extends IndexOutOfBoundsException {
    public InvalidTaskIndexException() {
    }

    public InvalidTaskIndexException(String s) {
        super(s);
    }
}
