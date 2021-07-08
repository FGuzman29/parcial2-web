package eitc.pucmm.controladores;

import eitc.pucmm.Main;
import eitc.pucmm.entidades.Cliente;
import eitc.pucmm.entidades.Enlace;
import eitc.pucmm.entidades.Usuario;
import eitc.pucmm.servicios.ClienteService;
import eitc.pucmm.servicios.EnlaceService;
import eitc.pucmm.servicios.UsuarioService;
import io.javalin.Javalin;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ApiControlador {
    private Javalin app;
    private ClienteService clienteService = ClienteService.getInstancia();
    private EnlaceService enlaceService = EnlaceService.getInstancia();
    private UsuarioService usuarioService = UsuarioService.getInstancia();




    public ApiControlador(Javalin app) {
        this.app = app;
    }

    public void aplicarRutas() {
        app.routes(() -> {

            //Evitar que Enlaces sea null
            app.before("",ctx -> {
               if(ctx.sessionAttribute("Enlaces") == null) {
                   Set<Enlace> enlaces = new HashSet<>();
                   ctx.sessionAttribute("Enlaces", enlaces);
               }
            });

            //Pagina Inicial
            //Desde aqui se crean los enlaces cortados
            app.get("/", ctx -> {
                Map<String, Object> aux =  new HashMap<>();
                Set<Enlace> enlaces;

                Usuario usuario = ctx.sessionAttribute("usuario");
                if(usuario != null){
                    enlaces = usuario.getMisEnlaces();
                }else{
                    enlaces = ctx.sessionAttribute("Enlaces");
                }

                aux.put("usuario",usuario);
                aux.put("links",enlaces);
                ctx.render("/publico/index.vm",aux);
            });

            //crear enlace
            app.post("/acortarEnlace", ctx -> {
                String URL = ctx.formParam("link");

                Usuario usuario = ctx.sessionAttribute("usuario");

                Enlace act = new Enlace();
                boolean res = false;
                String cod = "";
                while(!res){
                    cod = Main.codeGenerator();
                    res = enlaceService.verificarCod(cod);
                }
                act.setURL(URL);
                //act.setURLAcostarda("short.fguzman.codes/"+cod); //metodo de acortar URL
                act.setURLAcostarda(cod);
                act.setUsuario(usuario);
                enlaceService.crear(act);

                Set<Enlace> listaActual;
                if(usuario!=null)
                {
                    listaActual = usuario.getMisEnlaces();
                    listaActual.add(act);
                    usuario.setMisEnlaces(listaActual);
                    usuarioService.editar(usuario);
                }else{
                    listaActual= ctx.sessionAttribute("Enlaces");
                    listaActual.add(act);
                    ctx.sessionAttribute("Enlaces", listaActual);
                }
                ctx.redirect("/");
            });

            //Redireccionar
            app.get("/re/:redirect",ctx -> {
                int id = ctx.pathParam("redirect",Integer.class).get();
                Enlace aux = enlaceService.find(id);
                String detalles = getOS(ctx.userAgent().toString().toLowerCase());
                String nav = getNav(ctx.header("sec-ch-ua").toString().toLowerCase());
                Cliente client = new Cliente();

                client.setIp(ctx.ip());
                client.setSistema(detalles);
                client.setNavegador(nav);

                aux.setVecesAccesidas(aux.getVecesAccesidas()+1);
                clienteService.crear(client);

                Set<Cliente> clientes = aux.getClientes();
                clientes.add(client);
                aux.setClientes(clientes);
                enlaceService.editar(aux);

                ctx.redirect(aux.getURL());
            });

            app.get("/ver/:id", ctx -> {
               int id = ctx.pathParam("id",Integer.class).get();
               Enlace enlace = enlaceService.find(id);

               Map<String,Object> map = new HashMap<>();
                map.put("usuario",ctx.sessionAttribute("usuario"));
                map.put("enlace",enlace);

               ctx.render("/publico/verEnlace.vm",map);
            });

            app.get("/estadisticas/:id",ctx -> {
                int id = ctx.pathParam("id", Integer.class).get();
                Enlace enlace = enlaceService.find(id);

                Map<String,Object> map1 = new HashMap<>();
                map1.put("usuario",ctx.sessionAttribute("usuario"));
                map1.put("map",enlace.calcularDatos());
                ctx.render("/publico/estadistica.vm",map1);
            });



            //carga vista login
            app.get("/login", ctx -> {
                ctx.render("/publico/autentificacion.vm");
            });

            app.get("/registrarse", ctx -> {
                ctx.render("/publico/registro.vm");
            });

            //guardar crear usuario
            app.post("/crear/user", ctx -> {
                //obteniendo la información enviada.
                String usuario = ctx.formParam("usuario");
                String nombre = ctx.formParam("nombre");
                String password = ctx.formParam("password");
                Usuario.RoleasAPP rol = Usuario.RoleasAPP.ROLE_USUARIO;

                Set<Enlace> misEnlaces = new HashSet<Enlace>();
                Usuario tmp = new Usuario();
                tmp.setUsuario(usuario);
                tmp.setNombre(nombre);
                tmp.setPassword(password);
                tmp.setRol(rol);
                usuarioService.crear(tmp);

                ctx.sessionAttribute("usuario",tmp);
                ctx.redirect("/ListarEnlaces");
            });

            //guardar editar usuario
            app.post("/ascender/:idUsuario", ctx -> {
                //obtengo el usuario
                Usuario tmp = usuarioService.find(ctx.pathParam("idUsuario", Integer.class).get());
                tmp.setRol(Usuario.RoleasAPP.ROLE_ADMIN);
                usuarioService.editar(tmp);
                ctx.redirect("/ListarUsuarios");
            });

            //eliminar usuario
            app.post("/eliminar/:idUsuario", ctx -> {
                //obtengo el usuario
                int id =ctx.pathParam("idUsuario", Integer.class).get();
                usuarioService.eliminar(id);
                ctx.redirect("/ListarUsuarios");
            });


            //INICIO DE SECCION
            app.post("/autenticar", ctx -> {
                //Obteniendo la informacion de la peticion. Pendiente validar los parametros.
                String user = ctx.formParam("usuario");
                String password = ctx.formParam("password");

                //Autenticando el usuario para nuestro ejemplo siempre da una respuesta correcta.
                Usuario usuario = usuarioService.autenticarUsuario(user, password);

                if( usuario != null ){
                    //agregando el usuario en la session...
                    ctx.sessionAttribute("usuario", usuario);
                    ctx.sessionAttribute("Enlaces", usuario.getMisEnlaces());
                    ctx.redirect("/");
                }

            });

            //cerrar seccion
            app.get("/logout", ctx -> {
                ctx.sessionAttribute("usuario", null);
                ctx.sessionAttribute("Enlaces", new HashSet<Enlace>());
                ctx.redirect("/login");
            });

            //listar usuario
            app.get("/ListarUsuarios", ctx -> {

                //obtenemos los valores del session
                Usuario usuarioTmp = ctx.sessionAttribute("usuario");
                List<Usuario> lista = usuarioService.findAll();

                Map<String, Object> modelo = new HashMap<>();
                modelo.put("usuario", usuarioTmp);
                modelo.put("usuarios",lista);
                //enviando al sistema de plantilla.
                ctx.render("/publico/usuarios.vm",modelo);
            });


            //listar enlaces
            app.get("/ListarEnlaces", ctx -> {

                //obtenemos los valores del session
                Usuario usuario = ctx.sessionAttribute("usuario");

                //paso los parametro
                List<Enlace> lista = enlaceService.findAll();
                //enviando al sistema de plantilla.
                Map<String, Object> modelo = new HashMap<>();
                modelo.put("links", lista);
                modelo.put("usuario",usuario);
                ctx.render("/publico/enlaces.vm",modelo);
            });

            //eliminar enlace
            app.post("/eliminar/enlace/:id", ctx -> {
                //obtenemos los valores del session
                int id =ctx.pathParam("id", Integer.class).get();
                Boolean estado = enlaceService.eliminar(id);

                if(estado)
                {
                    Usuario usuario = ctx.sessionAttribute("usuario");
                    if(usuario == null)
                    {
                        Set<Enlace> listaEnlaces = ctx.sessionAttribute("Enlaces");
                        Set<Enlace> newEnlace = enlaceService.eliminarEnlaceByID(id, listaEnlaces);

                        if(newEnlace != null)
                        {
                            ctx.sessionAttribute("Enlaces",newEnlace);
                        }
                    }
                }

                ctx.redirect("/ListarEnlaces");
            });

        });
        app.exception(Exception.class, (exception, ctx) -> {
                ctx.status(500);
                ctx.html("<h1>Error no recuperado:"+exception.getMessage()+"</h1>");
                exception.printStackTrace();
            });
    }

    private String getOS(String user){
        String detalles = "";
        if(user.indexOf("windows") >= 0){
            detalles = "Windows";
        }else if(user.indexOf("mac") >= 0){
            detalles = "MacOs";
        }else if(user.indexOf("x11") >= 0){
            detalles = "Unix";
        }else if(user.indexOf("android") >= 0){
            detalles = "Android";
        }else if(user.indexOf("iphone") >= 0){
            detalles = "IOS";
        }
        return detalles;

    }
    private String getNav(String user){
        String detalles = "";

        if(user.contains("edge")  ){
            detalles = "Edge";
        }else if(user.contains("safari")){
            detalles = "Safari";
        }else if(user.contains("opera") ){
            detalles = "Opera";
        }else if(user.contains("chrome")){
            detalles = "Chrome";
        }else if(user.contains("firefox")){
            detalles = "Firefox";
        }
        return detalles;
    }

}
