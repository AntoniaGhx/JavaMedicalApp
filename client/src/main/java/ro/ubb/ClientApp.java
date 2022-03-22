package ro.ubb;

import ro.ubb.domain.Patient;
import ro.ubb.service.*;
import ro.ubb.tcpClient.TcpClient;
import ro.ubb.ui.ClientConsole;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        TcpClient tcpClient = new TcpClient();
        MedicService medicService = new MedicServiceClient(tcpClient, executorService);
        PatientService patientService = new PatientServiceClient(tcpClient, executorService);
        LocationService locationService = new LocationServiceClient(tcpClient, executorService);
        ProcedureService procedureService = new ProcedureServiceClient(tcpClient, executorService);
        ClientConsole clientConsole = new ClientConsole(medicService, patientService, locationService, procedureService);
        clientConsole.runConsole();

        System.out.println("Session Closed");
        executorService.shutdown();
    }

}
