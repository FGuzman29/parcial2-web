package eitc.pucmm.ApiServices;

import com.sun.tools.jconsole.JConsoleContext;
import eitc.pucmm.Excepciones.noExisteEnlace;
import eitc.pucmm.entidades.Cliente;
import eitc.pucmm.entidades.Enlace;
import eitc.pucmm.servicios.EnlaceService;
import eitc.pucmm.servicios.UsuarioService;
import enlacern.EnlaceRnGrpc;
import enlacern.EnlaceRnOuterClass;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnlaceGrpcService extends EnlaceRnGrpc.EnlaceRnImplBase {
    private final EnlaceService enlaceService = EnlaceService.getInstancia();
    private final UsuarioService usuarioService = UsuarioService.getInstancia();

    public void autentificacion(EnlaceRnOuterClass.usuarioRequest request, StreamObserver<EnlaceRnOuterClass.usuarioResponse> responseObserver){
        boolean autentificar = (usuarioService.autenticarUsuario(request.getUsuario(),request.getPassword()) != null) ? true:false;
        System.out.println(autentificar);
        responseObserver.onNext(EnlaceRnOuterClass.usuarioResponse.newBuilder().setOk(autentificar).build());
        responseObserver.onCompleted();
    }
    public void registrarEnlace(EnlaceRnOuterClass.EnlaceRequest request, StreamObserver<EnlaceRnOuterClass.EnlaceResponse> responseObserver){
        try {
            Enlace aux = enlaceService.registrarEnlace(request.getEnlace(),request.getUsuario());
            responseObserver.onNext(convertir(aux));
            responseObserver.onCompleted();
        } catch (IOException e) {
            responseObserver.onError(e.getCause());
        }

    }

    public void getEnlace(EnlaceRnOuterClass.clientesRequest request, StreamObserver<EnlaceRnOuterClass.EnlaceResponse> responseObserver){
        Enlace enlace = enlaceService.find(request.getIdEnlace());
        if (enlace != null){
            responseObserver.onNext(convertir(enlace));
            responseObserver.onCompleted();
        }else{
            responseObserver.onError(new noExisteEnlace("No existe el enlace: " + request.getIdEnlace()));

        }
    }
    public void getEnlaces(EnlaceRnOuterClass.enlace request,StreamObserver<EnlaceRnOuterClass.ListaEnlace> responseObserver){
         Enlace[] enlaces = enlaceService.getEnlaces(request.getUser());
         List<EnlaceRnOuterClass.EnlaceResponse> enlaceResponses = new ArrayList<>();
         for (Enlace e : enlaces){
             enlaceResponses.add(convertir(e));
         }
         EnlaceRnOuterClass.ListaEnlace build = EnlaceRnOuterClass.ListaEnlace.newBuilder().addAllElace(enlaceResponses).build();
         responseObserver.onNext(build);
         responseObserver.onCompleted();
    }
    public void getClientes(EnlaceRnOuterClass.clientesRequest request, StreamObserver<EnlaceRnOuterClass.clienteReponse> responseObserver){
        List<Cliente> clientes = enlaceService.find(request.getIdEnlace()).getClientes();
        List<EnlaceRnOuterClass.cliente> clientesRN = new ArrayList<>();

        for (Cliente e: clientes){
            clientesRN.add(convertirCliente(e));
        }
        EnlaceRnOuterClass.clienteReponse build = EnlaceRnOuterClass.clienteReponse.newBuilder().addAllClientes(clientesRN).build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    private EnlaceRnOuterClass.cliente convertirCliente(Cliente e) {
        return EnlaceRnOuterClass.cliente.newBuilder()
                .setFecha(e.getFecha().toString())
                .setIp(e.getIp())
                .setNavegador(e.getNavegador())
                .setSistema(e.getSistema())
                .build();
    }

    private EnlaceRnOuterClass.EnlaceResponse convertir(Enlace aux) {
        return EnlaceRnOuterClass.EnlaceResponse.newBuilder()
                .setEnlaceId(aux.getIdEnlace())
                .setUrl(aux.getURL())
                .setFecha(aux.getFecha().toString())
                .setAcortado(aux.getURLAcostarda())
                .setVeces(aux.getVecesAccesidas())
                .setFoto(aux.getFotoBase64())
                .build();
    }
}
