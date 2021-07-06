package logico;

import logico.controladores.ApiControlador;
import logico.controladores.FotoControlador;
import logico.entidades.Producto;
import logico.entidades.Usuario;
import logico.servicios.BootStrapServices;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import logico.servicios.ProductoService;
import logico.servicios.UsuarioService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {

    //indica el modo de operacion para la base de datos.
    private static String modoConexion = "";

    public static void main(String[] args) {
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
            config.addStaticFiles("/publico"); //desde la carpeta de resources
            config.registerPlugin(new RouteOverviewPlugin("/rutas")); //aplicando plugins de las rutas
            config.enableCorsForAllOrigins();
        }).start(getHerokuAssignedPort());

        //creando los endpoint de las rutas.
        new ApiControlador(app).aplicarRutas();
        new FotoControlador(app).aplicarRutas();
    }

    private static void EntrarDatos() {
        //anadiendo los usuarios.
        Usuario usuario1 = new Usuario();
        usuario1.setUsuario("admin");
        usuario1.setNombre("John");
        usuario1.setRol(Usuario.RoleasAPP.ROLE_ADMIN);
        usuario1.setPassword("admin");


        if( UsuarioService.getInstancia().autenticarUsuario(usuario1.getUsuario(),usuario1.getPassword()) ==null) {
            Usuario usuario2 = new Usuario();
            usuario2.setUsuario("logueado");
            usuario2.setNombre("Gabriela");
            usuario2.setRol(Usuario.RoleasAPP.ROLE_USUARIO);
            usuario2.setPassword("logueado");

            Usuario usuario3 = new Usuario();
            usuario3.setUsuario("usuario");
            usuario3.setNombre("Carlos");
            usuario3.setRol(Usuario.RoleasAPP.ROLE_USUARIO);
            usuario3.setPassword("usuario");

            UsuarioService.getInstancia().crear(usuario1);
            UsuarioService.getInstancia().crear(usuario2);
            UsuarioService.getInstancia().crear(usuario3);
        }
    }

    /**
     * Metodo para indicar el puerto en Heroku
     * @return
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000; //Retorna el puerto por defecto en caso de no estar en Heroku.
    }

    /**
     * Nos
     * @return
     */
    public static String getModoConexion(){
        return modoConexion;
    }
}
