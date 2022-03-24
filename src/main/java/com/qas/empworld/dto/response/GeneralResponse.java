package com.qas.empworld.dto.response;

import com.google.gson.JsonObject;

public class GeneralResponse {
    private String message;
    private JsonObject info;

    public GeneralResponse(String message, JsonObject info) {
        this.message = message;
        this.info = info;
    }
}

