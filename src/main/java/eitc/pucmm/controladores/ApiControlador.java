package eitc.pucmm.controladores;

import antlr.collections.List;
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

    //encriptacion
    private AtomicReference<AES256TextEncryptor> textEncryptor = new AtomicReference<>(new AES256TextEncryptor());
    private AtomicReference<StrongPasswordEncryptor> passwordEncryptor = new AtomicReference<>(new StrongPasswordEncryptor());



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
                Set<Enlace> enlaces = ctx.sessionAttribute("Enlaces");
                aux.put("links",enlaces);
                if(enlaces != null){
                    for (Enlace en: enlaces) {
                        System.out.println(en.getURLAcostarda());
                    }
                }
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
                    res = EnlaceService.verificarCod(cod);
                }
                act.setURL(URL);
                //act.setURLAcostarda("short.fguzman.codes/"+cod); //metodo de acortar URL
                act.setURLAcostarda(cod);
                act.setUsuario(usuario);
                enlaceService.crear(act);

                Set<Enlace> listaActual = ctx.sessionAttribute("Enlaces");
                listaActual.add(act);
                ctx.sessionAttribute("Enlaces", listaActual);

                ctx.redirect("/");
            });

            //Redireccionar
            app.get("/:redirect",ctx -> {
                int id = ctx.pathParam("redirect",Integer.class).get();
                Enlace aux = EnlaceService.getInstancia().find(id);
                String detalles = getOS(ctx.userAgent().toString().toLowerCase());
                String nav = getNav(ctx.header("sec-ch-ua").toString().toLowerCase());
                Cliente client = new Cliente();

                client.setIp(ctx.ip());
                client.setSistema(detalles);
                client.setNavegador(nav);

                aux.setVecesAccesidas(aux.getVecesAccesidas()+1);
                System.out.println(client.toString());
                ClienteService.getInstancia().crear(client);

                Set<Cliente> clientes = aux.getClientes();
                clientes.add(client);
                aux.setClientes(clientes);
                EnlaceService.getInstancia().editar(aux);

                ctx.redirect(aux.getURL());
            });

            app.get("/ver/:id", ctx -> {
               int id = ctx.pathParam("id",Integer.class).get();
               Enlace enlace = EnlaceService.getInstancia().find(id);

               Map<String,Object> map = new HashMap<>();
               map.put("enlace",enlace);

               ctx.render("/publico/verEnlace.vm",map);
            });

            //cerrar seccion
            app.get("/logout", ctx -> {
                ctx.sessionAttribute("usuario", null);
                ctx.sessionAttribute("Enlaces", new HashSet<Enlace>());
                ctx.removeCookie("user", "/");
                ctx.removeCookie("pass", "/");
                ctx.redirect("/login");
            });

            //carga vista login
            app.get("/login", ctx -> {
                ctx.render("/publico/autentificacion.vm");
            });
            app.get("/registrarse", ctx -> {
                ctx.render("/publico/registro.vm");
            });

            //INICIO DE SECCION
            app.post("/autenticar", ctx -> {
                //Obteniendo la informacion de la peticion. Pendiente validar los parametros.
                String user = ctx.formParam("usuario");
                String password = ctx.formParam("password");
                String recuerdame = ctx.formParam("recuerdame");

                //Autenticando el usuario para nuestro ejemplo siempre da una respuesta correcta.
                Usuario usuario = UsuarioService.getInstancia().autenticarUsuario(user, password);

                if( usuario != null && usuario.getUsuario().equals(user) && usuario.getPassword().equals(password)){
                    //agregando el usuario en la session...
                    ctx.sessionAttribute("usuario", usuario);

                    if(recuerdame!=null)
                    {
                        //opciones de encriptancion y guardar cookie por 1 semana
                        textEncryptor.set(new AES256TextEncryptor());
                        passwordEncryptor.set(new StrongPasswordEncryptor());
                        String claveEncriptada = passwordEncryptor.get().encryptPassword(usuario.getPassword());

                        textEncryptor.get().setPassword(claveEncriptada);
                        String usuarioEncriptado = textEncryptor.get().encrypt(usuario.getPassword());
                        ctx.cookie("user", usuarioEncriptado, 86400*7);
                        ctx.cookie("pass", claveEncriptada, 86400*7);
                    }
                    //redireccionando la vista con autorizacion.

                }
                else{
                    //redirect a la vista de login
                }

            });

            //error
            app.get("/error", ctx -> {
                //mando a la vista de error 401
            });

            //crear usuario
            app.get("/crear/usuario", ctx -> {

                Map<String, Object> modelo = new HashMap<>();
                modelo.put("titulo", "Formulario Creacion usuario");
                modelo.put("usuario", new Usuario());
                modelo.put("action", "New");

                //enviando al sistema de plantilla.
                //ctx.render("", modelo);
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

            //editar usuario
            app.get("/editar/usuario", ctx -> {

                Usuario tmp = usuarioService.find(ctx.formParam("id", Integer.class).get());

                Map<String, Object> modelo = new HashMap<>();
                modelo.put("titulo", "Formulario Editar usuario");
                modelo.put("usuario", tmp);
                modelo.put("action", "Editar");

                //enviando al sistema de plantilla.
                //ctx.render("", modelo);
            });

            //guardar editar usuario
            app.post("/editar/user/:id", ctx -> {

                //obtengo el usuario
                Usuario tmp = usuarioService.find(ctx.pathParam("id", Integer.class).get());
                String usuario = ctx.formParam("usuario");
                String nombre = ctx.formParam("nombre");
                String password = ctx.formParam("password");
                String rol = ctx.formParam("rol");
                tmp.setUsuario(nombre);
                tmp.setNombre(nombre);
                tmp.setPassword(password);
                tmp.setRol(Usuario.RoleasAPP.valueOf(rol));
                usuarioService.editar(tmp);

                Map<String, Object> modelo = new HashMap<>();
                //paso datos al modelos
                //modelo.put("tituloCarito", "Carrito: "+carrito.size());
                //enviando al sistema de plantilla.
                //redirect a la lista usuario
                ctx.redirect("/ListarUsuarios");
            });

            //listar usuario
            app.get("/ListarUsuarios", ctx -> {
                Usuario usuarioTmp = ctx.sessionAttribute("usuario");
                //obtenemos los valores del session
                if(usuarioTmp.getRol().equals(Usuario.RoleasAPP.ROLE_USUARIO ))
                {
                    //no tiene acceso
                    ctx.redirect("/error");
                }

                Set<Usuario> lista = (Set<Usuario>) usuarioService.findAll();
                Map<String, Object> modelo = new HashMap<>();
                modelo.put("titulo", "Listado de usuarios");
                modelo.put("listaUsuarios", lista);

                //enviando al sistema de plantilla.

            });


            //listar enlaces
            app.get("/ListarEnlaces", ctx -> {

                //obtenemos los valores del session
                Usuario usuario = ctx.sessionAttribute("usuario");


                Map<String, Object> modelo = new HashMap<>();
                //paso los parametro
                Set<Enlace> lista;
                //verificamos si esta logueado
                if (usuario != null) {
                    if(usuario.getRol().equals(Usuario.RoleasAPP.ROLE_USUARIO ))
                    {
                        //cargo enlaces del usuario
                         lista = usuario.getMisEnlaces();
                    }else{
                        //cargo todos los enlaces
                        lista = (Set<Enlace>) enlaceService.findAll();
                    }
                }else{
                    lista = ctx.sessionAttribute("Enlaces");
                    //paso los enlaces de la sesion a la vista
                }

                //enviando al sistema de plantilla.

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
                    }else{
                        Set<Enlace> newEnlace = enlaceService.eliminarEnlaceByID(id, usuario.getMisEnlaces());

                        if(newEnlace != null)
                        {
                            ctx.sessionAttribute("Enlaces",newEnlace);
                        }
                    }
                }

                ctx.redirect("/ListaEnlaces");
            });

        });
        app.exception(Exception.class, (exception, ctx) -> {
                ctx.status(500);
                ctx.html("<h1>Error no recuperado:"+exception.getMessage()+"</h1>");
                exception.printStackTrace();
            });
    }


    private int verificarCookie(String user, Usuario usuarioTmp) {
        if(user==null){
            //la cookie no se ha esta creada
            return 1;
        }else{
            //existe el cookie
            if(usuarioTmp == null)
            {
                //no existe la session
                return 2;
            }
        }

        //existe la session
        return 3;
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
