package com.shivali.postOffice.exceptions;

public class PostOfficeNotInProvinceException extends RuntimeException {
    public PostOfficeNotInProvinceException() {
        super("Destination post office is not in province");
    }
}
