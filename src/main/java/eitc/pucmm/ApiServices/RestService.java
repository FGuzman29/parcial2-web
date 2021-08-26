package eitc.pucmm.ApiServices;



import eitc.pucmm.controladores.RestControlador;
import eitc.pucmm.entidades.LoginResponse;
import eitc.pucmm.entidades.Usuario;
import eitc.pucmm.servicios.EnlaceService;
import eitc.pucmm.servicios.UsuarioService;
import io.javalin.Javalin;
import eitc.pucmm.Excepciones.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Handler;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RestService {
    private Javalin app;

    private EnlaceService enlaceService = EnlaceService.getInstancia();
    private RestControlador restControlador = RestControlador.getInstancia();

    //Llave de 32 bytes por la firma utilizada
    public final static String LLAVE_SECRETA = "asd12D1234dfr123@#4Fsdcasdd5g78a";
    public final static String ACCEPT_TYPE_JSON = "application/json";
    public final static String ACCEPT_TYPE_XML = "application/xml";
    public final static int BAD_REQUEST = 400;
    public final static int UNAUTHORIZED = 401;
    public final static int FORBIDDEN = 403;
    public final static int ERROR_INTERNO = 500;




    public RestService(Javalin app) {
        this.app = app;
    }

    public void aplicarRutas() {
        app.routes(() -> {

            app.options("/*", ctx -> {
                System.out.println("Entrando al metodo de options");
                String accessControlRequestHeaders = ctx.header("Access-Control-Request-Headers");
                if (accessControlRequestHeaders != null) {
                    ctx.header("Access-Control-Allow-Headers",accessControlRequestHeaders);
                }

                String accessControlRequestMethod = ctx.header("Access-Control-Request-Method");
                if (accessControlRequestMethod != null) {
                    ctx.header("Access-Control-Allow-Methods",accessControlRequestMethod);
                }

            });

            //Filtro para validar el CORS
            before(ctx -> {
                System.out.println("Aplicando header del API del CORS");
                ctx.header("Access-Control-Allow-Origin", "*");
                //response.type("application/json");
            });

            app.post("/login/RestApi", ctx -> {

                String usuario = ctx.queryParam("usuario");
                String password = ctx.queryParam("password");

                System.out.println(usuario);
                System.out.println(password);
                Usuario usuarioObj=UsuarioService.getInstancia().autenticarUsuario(usuario,password);
                if(usuarioObj == null)
                {
                    System.out.println("Autentificacion no Correcta");
                    ctx.status(UNAUTHORIZED).result("Autentificacion no Correcta");
                }else{
                    System.out.println(usuarioObj.getUsuario());
                    ctx.json(generacionJsonWebToken(usuarioObj));
                }

            });

            path("/RestApi", () -> {
                /**
                 * Ejemplo de una API REST, implementando el CRUD
                 * ir a
                 */

                before(ctx ->{
                    System.out.println("Analizando que exista el token");

                    //si es del tipo options lo dejo pasar.
                    if(ctx.req.getMethod() == "OPTIONS"){
                        return;
                    }

                    //informacion para consultar en la trama.
                    String header = "Authorization";
                    String prefijo = "Bearer";

                    //mostrando todos los header recibidos.
                    Set<String> listaHeader = ctx.headerMap().keySet();
                    for(String key : listaHeader){
                        System.out.println(String.format("header[%s] = %s", key, ctx.header(key)));
                    }

                    //Verificando si existe el header de autorizacion.
                    String headerAutentificacion = ctx.header(header);
                    if(headerAutentificacion ==null || !headerAutentificacion.startsWith(prefijo)){
                        throw new noExisteToken("No tiene permiso para acceder al recurso, no enviando header de autorizacion");
                    }
                    //recuperando el token y validando
                    String tramaJwt = headerAutentificacion.replace(prefijo, "");
                    try {
                        Claims claims = Jwts.parser()
                                .setSigningKey(Keys.hmacShaKeyFor(LLAVE_SECRETA.getBytes()))
                                .parseClaimsJws(tramaJwt).getBody();
                        //mostrando la información para demostración.
                        System.out.println("Mostrando el JWT recibido: " + claims.toString());
                    }catch (ExpiredJwtException | MalformedJwtException | SignatureException e){ //Excepciones comunes
                        throw new noExisteToken(e.getMessage());
                    }

                    //En este punto puedo realizar validaciones en función a los permisos del usuario.
                    // tener pendiente que el JWT está formado no encriptado
                });

                after(ctx -> {
                    ctx.header("Content-Type", "application/json");
                });

                //Listado de las URL publicadas por un usuario incluyendo las estadísticas
                //asociadas.
                get("/ListarUrl/:usuario", ctx -> {
                    ctx.json(RestControlador.getInstancia().crearArreglo(ctx.pathParam("usuario",String.class).get()));
                });

                //Creación de registro de URL para un usuario retornando la estructura básica
                //(url completa, url acortada, fecha creación, objeto de estadística y la imagen
                //actual del site (vista previa) en base64.

                //crear enlace
                post("/registrarURL", ctx -> {
                    String usuario = ctx.queryParam("usuario");
                    String url = ctx.queryParam("url");
                    ctx.json(RestControlador.getInstancia().registrarEnlace(url,usuario));
                });
            });

        });



        app.exception(noExisteToken.class, (exception, ctx) -> {
            ctx.status(FORBIDDEN);
            ctx.json(""+exception.getLocalizedMessage());
        });

        app.exception(noExisteUsuario.class, (exception, ctx) -> {
            ctx.status(404);
            ctx.json(""+exception.getLocalizedMessage());
        });


    }

    private static LoginResponse generacionJsonWebToken(Usuario usuario){
        LoginResponse loginResponse = new LoginResponse();
        //generando la llave.
        SecretKey secretKey = Keys.hmacShaKeyFor(LLAVE_SECRETA.getBytes());
        //Generando la fecha valida
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(3);
        System.out.println("La fecha actual: "+localDateTime.toString());

        //
        Date fechaExpiracion = Date.from(localDateTime.toInstant(ZoneOffset.ofHours(-4)));
        // creando la trama.
        String jwt = Jwts.builder()
                .setIssuer("PUCMM-ProgramacionWeb")
                .setSubject("Practica-Final")
                .setExpiration(fechaExpiracion)
                .claim("usuario", usuario.getUsuario())
                .claim("rol", usuario.getRol())
                .signWith(secretKey)
                .compact();
        loginResponse.setToken(jwt);
        loginResponse.setExpires_in(fechaExpiracion.getTime());
        return loginResponse;
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
