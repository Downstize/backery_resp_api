package com.example.springdatabasicdemo.controllers;

class IngredientNotFoundException extends RuntimeException {
    IngredientNotFoundException(Integer id) {
        super("Could not find ingredient " + id);
    }
}