import ro.ubb.Handlers;
import ro.ubb.domain.Location;
import ro.ubb.domain.Medic;
import ro.ubb.domain.Patient;
import ro.ubb.domain.Procedure;
import ro.ubb.message.Message;
import ro.ubb.repository.DataBaseRepository;
import ro.ubb.repository.Repository;
import ro.ubb.server.TcpServer;
import ro.ubb.additional.GenericReflect;
import ro.ubb.service.*;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {
    public static void main(String[] args) {

        final String URL = System.getProperty("url");
        final String USER = System.getProperty("userName");
        final String PASSWORD = System.getProperty("password");

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Repository<Integer, Medic> medicRepository = new DataBaseRepository(Medic.class,URL,USER,PASSWORD);
        MedicServiceServer medicServiceServer = new MedicServiceServer(executorService,medicRepository);

        Repository<Integer, Patient> patientRepository = new DataBaseRepository(Patient.class,URL,USER,PASSWORD);
        PatientServiceServer patientServiceServer = new PatientServiceServer(executorService,patientRepository);

        Repository<Integer, Location> locationRepository = new DataBaseRepository(Location.class,URL,USER,PASSWORD);
        LocationServiceServer locationServiceServer = new LocationServiceServer(executorService,locationRepository);

        Repository<Integer, Procedure> procedureRepository = new DataBaseRepository(Procedure.class,URL,USER,PASSWORD);
        ProcedureServiceServer procedureServiceServer = new ProcedureServiceServer(executorService,procedureRepository);
        
        TcpServer tcpServer = new TcpServer(executorService);

        Handlers.addPatientHandlers(tcpServer, patientServiceServer);
        Handlers.addMedicHandlers(tcpServer, medicServiceServer);
        Handlers.addLocationHandlers(tcpServer, locationServiceServer);
        Handlers.addProcedureHandlers(tcpServer, procedureServiceServer);

        tcpServer.startServer();

        System.out.println();
    }
}
