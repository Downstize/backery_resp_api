package com.example.springdatabasicdemo.controllers;

class ClientNotFoundSurnameException extends RuntimeException {
    ClientNotFoundSurnameException(String surname) {
        super("Could not find client " + surname);
    }
}