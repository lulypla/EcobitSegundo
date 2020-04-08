package com.example.ecobit.utils;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class JSON {
    public static JsonObject nuevo() {
        return new JsonObject();
    }

    public static JsonObject bodyActualizarFoto(String foto, String email) {
        JsonObject body = JSON.nuevo();
        try {
            body.addProperty("foto", foto);
            body.addProperty("email", email);
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
        return body;
    }

}
