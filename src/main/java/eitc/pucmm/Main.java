package eitc.pucmm;



import eitc.pucmm.controladores.ApiControlador;
import eitc.pucmm.entidades.Usuario;
import eitc.pucmm.servicios.BootStrapServices;
import eitc.pucmm.servicios.UsuarioService;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinVelocity;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    private static String modoConexion = "";

    public static void main(String[] args){

        String mensaje = "Software ORM - JPA";
        System.out.println(mensaje);

        if(args.length >= 1){
            modoConexion = args[0];
            System.out.println("Modo de Operacion: "+modoConexion);
        }

        //Iniciando la base de datos.
        if(modoConexion.isEmpty()) {
            BootStrapServices.getInstancia().init();

            EntrarDatos();
        }

        //Creando la instancia del servidor.
        Javalin app = Javalin.create(config ->{
            config.enableCorsForAllOrigins();
        }).start(getHerokuAssignedPort());


        //creando los endpoint de las rutas.
        new ApiControlador(app).aplicarRutas();
        JavalinRenderer.register(JavalinVelocity.INSTANCE,".vm");


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
    private static void EntrarDatos() {

        if(UsuarioService.getInstancia().autenticarUsuario("admin","admin") == null)
        {
            //anadiendo los usuarios.
            Usuario usuario1 = new Usuario();
            usuario1.setUsuario("admin");
            usuario1.setNombre("admin");
            usuario1.setRol(Usuario.RoleasAPP.ROLE_ADMIN);
            usuario1.setPassword("admin");
            UsuarioService.getInstancia().crear(usuario1);
        }
    }

    public static String getModoConexion() {
        return modoConexion;
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000; //Retorna el puerto por defecto en caso de no estar en Heroku.
    }
}