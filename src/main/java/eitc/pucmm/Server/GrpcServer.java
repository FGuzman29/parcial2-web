package eitc.pucmm.Server;

import eitc.pucmm.ApiServices.EnlaceGrpcService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    private static final int PORT = 7001;
    private Server server;

    public void start() throws IOException{
        server = ServerBuilder.forPort(PORT).addService(new EnlaceGrpcService()).build().start();
    }

    public void blockunitlShutdown() throws InterruptedException{
        if (server == null){
            return;
        }
        server.awaitTermination();
    }
}
