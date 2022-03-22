package ro.ubb.service;

import ro.ubb.additional.GenericReflect;
import ro.ubb.domain.*;
import ro.ubb.domain.Procedure;
import ro.ubb.domain.Procedure;
import ro.ubb.message.Message;
import ro.ubb.tcpClient.TcpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ProcedureServiceClient implements ProcedureService{

    private TcpClient tcpClient;
    private ExecutorService executorService;

    public ProcedureServiceClient(TcpClient tcpClient, ExecutorService executorService) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public void addProcedure(String name, Float price, Specialty specialty, Integer duration) {
        executorService.submit( () ->{
            String requestHeader = "addProcedure";

            Procedure procedure = new Procedure(name,price,specialty,duration);
            String requestBody = GenericReflect.createStringFromEntity(procedure);

            System.out.println(requestBody);
            Message request = new Message(requestHeader, requestBody);
            Message response = tcpClient.sendAndReceive(request);
            System.out.println("Response ==> " + response);
        });
    }

    @Override
    public Future<Optional<Procedure>> findOne(Integer id) {
        return executorService.submit(()->{
            String requestHeader = "findOneProcedure";
            String requestBody = String.valueOf(id);
            Message request = new Message(requestHeader,requestBody);
            Message response = tcpClient.sendAndReceive(request);
            Procedure procedure = new Procedure();
            procedure =(Procedure) GenericReflect.getEntityFromString(procedure,response.getBody());
            Optional<Procedure> procedureOptional = Optional.of(procedure);
            return procedureOptional;
        });
    }

    @Override
    public Future<List<Procedure>> findAll() {
        return executorService.submit(()->{
            String requestHeader = "findAllProcedures";
            Message request = new Message(requestHeader,"");
            List<Procedure> allProcedures = new ArrayList<>();
            Message response = tcpClient.sendAndReceive(request);
            allProcedures = (List<Procedure>) GenericReflect.getEntityListFromString(response.getBody());
            return allProcedures;
        });
    }

    @Override
    public void delete(Integer id) {
        executorService.submit(()->{
            String requestHeader = "deleteProcedure";
            String requestBody = String.valueOf(id);
            Message request= new Message(requestHeader,requestBody);
            Message response = tcpClient.sendAndReceive(request);
            System.out.println(response.getBody());
        });
    }

    @Override
    public void update(Integer idEntity, String name, Float price, Specialty specialty, Integer duration) {
        executorService.submit(()-> {
            Procedure updatedProcedure = new Procedure(idEntity, name, price, specialty, duration);
            String requestHeader = "updateProcedure";
            String requestBody = GenericReflect.createStringFromEntity(updatedProcedure);
            Message request= new Message(requestHeader,requestBody);
            Message response = tcpClient.sendAndReceive(request);
            System.out.println(response.getBody());
        });
    }
}
