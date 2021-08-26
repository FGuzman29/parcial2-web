package eitc.pucmm.ClienteRestApi;

import eitc.pucmm.entidades.Enlace;
import eitc.pucmm.entidades.LoginResponse;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClienteRest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url;
        String usuario;
        String password;
        int num =1;
        do{
            System.out.println("Menu:\n1.Lista enlaces de un cliente\n2.Registrar URL\nSeleccione:");
            num = sc.nextInt();

            System.out.println("Ingrese el usuario: ");
            sc.nextLine();
            usuario = sc.nextLine();
            System.out.println("Ingrese el password: ");

            password = sc.nextLine();
            switch(num)
            {
                case 1 :
                    listarEnlace(usuario,password);
                    break; // break es opcional

                case 2 :
                    System.out.println("Ingrese la URL: ");
                    url = sc.nextLine();
                    registrarURL(url,usuario,password);
                    break; // break es opcional

                default :
                    System.out.println("Opcion incorrecta");
            }

        }while(num != 0);



    }

    public static void registrarURL(String url,String usuario, String password)
    {

        System.out.println("url"+url);
        System.out.println(usuario);
        System.out.println(password);
        LoginResponse jsonResponse = Unirest.post("http://localhost:7000/login/RestApi")
                .queryString("usuario", usuario)
                .queryString("password", password)
                .asObject(LoginResponse.class)
                .getBody();

        if(jsonResponse != null)
        {
            Map<String, String> headers = new HashMap<>();
            headers.put("accept", "application/json");
            headers.put("Authorization", "Bearer "+jsonResponse.getToken());

            HttpResponse<JsonNode> urlCreada
                    = Unirest.post("http://localhost:7000/RestApi/registrarURL")
                    .queryString("usuario", usuario)
                    .queryString("url", url)
                    .headers(headers)
                    .asJson();

            System.out.println("Datos sobre la Url creada:\n"+urlCreada.getBody().toString());
        }else{
            System.out.println(401+", Autentificacion no Correcta");
        }
    }

    public static void listarEnlace(String usuario, String password)
    {

        LoginResponse jsonResponse = Unirest.post("http://localhost:7000/login/RestApi")
                .queryString("usuario", usuario)
                .queryString("password", password)
                .asObject(LoginResponse.class)
                .getBody();

        if(jsonResponse != null)
        {
            Map<String, String> headers = new HashMap<>();
            headers.put("accept", "application/json");
            headers.put("Authorization", "Bearer "+jsonResponse.getToken());

            HttpResponse<JsonNode> listaEnlaceGet
                    = Unirest.get("http://localhost:7000/RestApi/ListarUrl/"+usuario)
                    .headers(headers)
                    .asJson();

            System.out.println("Enlaces del usuario:\n"+listaEnlaceGet.getBody().toString());
        }else{
            System.out.println(401+", Autentificacion no Correcta");
        }
    }


}
