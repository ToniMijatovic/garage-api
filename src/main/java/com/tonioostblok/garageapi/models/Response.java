package com.tonioostblok.garageapi.models;

import com.tonioostblok.garageapi.entities.User;

import java.util.Map;

public class Response {
    private final String message;
//    private Map<Integer, Object> data;
    private Object data;

    public Response(String message, Object data){
        this.message = message;
        this.data = data;
    }
    public String getMessage(){
        return this.message;
    }
    public Object getData(){
        return this.data;
    }
}
