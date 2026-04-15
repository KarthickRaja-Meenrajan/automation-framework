package com.genesys.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;


public class JsonUtil {

    public static JSONObject readJson(String path) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(path)));
        return new JSONObject(content);
    }
}