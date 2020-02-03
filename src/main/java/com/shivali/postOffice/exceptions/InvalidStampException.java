package com.shivali.postOffice.exceptions;

public class InvalidStampException extends RuntimeException {
    public InvalidStampException() {
        super("Cannot submit postcard to other state without national stamp");
    }
}
