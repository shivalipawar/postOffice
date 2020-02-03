package com.shivali.postOffice;

class PostOfficeNotInProvinceException extends RuntimeException {
    PostOfficeNotInProvinceException(String message) {
        super(message);
    }
}
