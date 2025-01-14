package com.unwize.cobblebank;

import com.unwize.cobblebank.json.FastJsonMapper;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        var app = Javalin.create(config -> config.jsonMapper(new FastJsonMapper()))
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7070);
    }
}