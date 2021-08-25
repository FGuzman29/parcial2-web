package eitc.pucmm.ApiServices;

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

        int matricula;
        String usuario;
        String password;
        int num =1;
        do{
            System.out.println("Menu:\n1.Lista enlaces de un cliente\n2.Registrar URL\nSeleccione:");
            num = sc.nextInt();
            switch(num)
            {
                case 1 :
                    System.out.println("Ingrese el usuario: ");
                    usuario = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Ingrese el password: ");
                    password = sc.nextLine();
                    listarEnlace(usuario,password);
                    break; // break es opcional

                case 2 :
                    System.out.println("Ingrese la matricula: ");
                    matricula = sc.nextInt();

                    break; // break es opcional

                default :
                    System.out.println("Opcion incorrecta");
            }
        }while(num != 0);
        String cadena = sc.nextLine();


    }

    public static void listarEnlace(String usuario, String password)
    {

        HttpResponse<LoginResponse> tokenGet = Unirest.get("http://localhost:7000/login/RestApi")
            .header("accept", "application/json")
            .queryString("usuario", usuario)
            .queryString("password", password)
            .asObject(new GenericType<LoginResponse>() {});

        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer "+tokenGet);

        HttpResponse <List<Enlace>> enlaceLista = Unirest.get("http://localhost:7000/RestApi/ListarUrl/"+usuario)
            .headers(headers)
            .asObject(new GenericType<List<Enlace>>() {});

        System.out.println("Enlaces del usuario:\n"+enlaceLista.getBody().toString());
    }


}
