package eitc.pucmm;



import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinVelocity;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){
        Javalin app = Javalin.create().start(7000);
        JavalinRenderer.register(JavalinVelocity.INSTANCE,".vm");

        app.get("/",ctx -> {
           ctx.render("/publico/index.vm") ;
        });
        app.get("/autentificacion", ctx -> {
            ctx.render("/publico/autentificacion.vm");
        });
        app.get("/enlaces", ctx -> {
            ctx.render("/publico/enlaces.vm");
        });

        app.get("/usuarios", ctx -> {
           ctx.render("/publico/usuarios.vm");
        });
        app.get("/ver", ctx -> {
            ctx.render("/publico/verEnlace.vm");
        });
        app.get("/registrarse", ctx -> {
            ctx.render("/publico/registro.vm");
        });

    }

    public static String codeGenerator() {
        int[] arr = {58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96};
        int leftLimit = 48; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            if (!IntStream.of(arr).anyMatch(n -> n == randomLimitedInt)) {
                buffer.append((char) randomLimitedInt);
            } else {
                i--;
            }
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

}