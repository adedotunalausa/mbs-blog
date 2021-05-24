package com.adedotunalausa.week9taskadedotunalausa.util;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class MethodUtils {

    private  MethodUtils() {}

    public static String prepareErrorJSON(HttpStatus status, Exception ex) {

        JSONObject errorJSON = new JSONObject();
        errorJSON.put("status", status.value());
        errorJSON.put("error", status.getReasonPhrase());
        errorJSON.put("message", Objects.requireNonNull(ex.getMessage()).split(":")[0]);
        return errorJSON.toString();
    }
}
