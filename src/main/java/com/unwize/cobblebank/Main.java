package com.unwize.cobblebank;

import com.unwize.cobblebank.json.FastJsonMapper;
import io.javalin.Javalin;
import org.hibernate.SessionFactory;

import static com.unwize.cobblebank.orm.SetupORM.setUp;


public class Main {

    public static SessionFactory sessionFactory;



    // Configure an instance of the API server and return it
    private static Javalin instantiateServer() {
        return Javalin.create(config -> config.jsonMapper(new FastJsonMapper()))
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7070);
    }

    public static void main(String[] args) {
        sessionFactory = setUp();
        Javalin app = instantiateServer();
    }
}