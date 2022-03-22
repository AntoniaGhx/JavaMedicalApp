package ro.ubb;

import ro.ubb.additional.GenericReflect;
import ro.ubb.domain.Location;
import ro.ubb.domain.Medic;
import ro.ubb.domain.Patient;
import ro.ubb.domain.Procedure;
import ro.ubb.message.Message;
import ro.ubb.server.TcpServer;
import ro.ubb.service.LocationServiceServer;
import ro.ubb.service.MedicServiceServer;
import ro.ubb.service.PatientServiceServer;
import ro.ubb.service.ProcedureServiceServer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public class Handlers {

    public static void addProcedureHandlers(TcpServer tcpServer, ProcedureServiceServer procedureServiceServer){
        tcpServer.addHandler("addLocation", request -> {
            try {
                Procedure procedure = new Procedure();
                procedure = (Procedure) GenericReflect.getEntityFromString(procedure,request.getBody());

                System.out.println(procedure);

                procedureServiceServer.addProcedure(procedure.getName(), procedure.getPrice(),
                        procedure.getSpecialty(), procedure.getDuration());

                System.out.println("Procedure Added Successfully");
                return new Message(Message.OK, "Added Successfully");
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });

        tcpServer.addHandler("findOneProcedure", request -> {
            try {
                Integer id = Integer.parseInt(request.getBody());
                Future<Optional<Procedure>> procedure = procedureServiceServer.findOne(id);
                Optional<Procedure> procedure1 = procedure.get();
                Procedure procedureFinal = procedure1.get();
                String responseBody = GenericReflect.createStringFromEntity(procedureFinal);
                System.out.println("Procedure Added Successfully");
                return new Message(Message.OK, responseBody);
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });

        tcpServer.addHandler("findAllProcedures", request -> {
            try {
                Future<List<Procedure>> procedure = procedureServiceServer.findAll();
                String responseBody = GenericReflect.createStringFromEntityList(procedure.get());
                return new Message(Message.OK, responseBody);
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });

        tcpServer.addHandler("deleteProcedure", request -> {
            try {
                procedureServiceServer.delete(Integer.parseInt(request.getBody()));
                String responseBody = "Deleted Successfully";
                return new Message(Message.OK, responseBody);
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });

        tcpServer.addHandler("updateProcedure", request -> {
            try {
                Procedure updateProcedure = new Procedure();
                updateProcedure =(Procedure) GenericReflect.getEntityFromString(updateProcedure,request.getBody());
                procedureServiceServer.update(updateProcedure.getIdEntity(),updateProcedure.getName(),
                        updateProcedure.getPrice(),updateProcedure.getSpecialty(), updateProcedure.getDuration());
                String responseBody = "Updated Successfully";
                return new Message(Message.OK, responseBody);
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });
    }

   public static void addMedicHandlers(TcpServer tcpServer, MedicServiceServer medicServiceServer){
       tcpServer.addHandler("addMedic", request -> {
           try {
               Medic medic = new Medic();
               medic = (Medic) GenericReflect.getEntityFromString(medic,request.getBody());

               System.out.println(medic);

               medicServiceServer.addMedic(medic.getName(), medic.getUserName(), medic.getPassword(), medic.getEmail(),
                       medic.getSpecialty(), medic.getBeginningDate(), medic.getDateOfBirth(), medic.getIsActive());

               System.out.println("Medic Added Successfully");
               return new Message(Message.OK, "Added Successfully");
           } catch (Exception e) {
               e.printStackTrace();
               return new Message(Message.ERR, e.getMessage());
           }
       });

       tcpServer.addHandler("findOneMedic", request -> {
           try {
               Integer id = Integer.parseInt(request.getBody());
               Future<Optional<Medic>> medic = medicServiceServer.findOne(id);
               Optional<Medic> medic1 = medic.get();
               Medic medicFinal = medic1.get();
               String responseBody = GenericReflect.createStringFromEntity(medicFinal);
               System.out.println("Medic Added Successfully");
               return new Message(Message.OK, responseBody);
           } catch (Exception e) {
               e.printStackTrace();
               return new Message(Message.ERR, e.getMessage());
           }
       });

       tcpServer.addHandler("findAllMedics", request -> {
           try {
               Future<List<Medic>> medic = medicServiceServer.findAll();
               String responseBody = GenericReflect.createStringFromEntityList(medic.get());
               return new Message(Message.OK, responseBody);
           } catch (Exception e) {
               e.printStackTrace();
               return new Message(Message.ERR, e.getMessage());
           }
       });

       tcpServer.addHandler("deleteMedic", request -> {
           try {
               medicServiceServer.delete(Integer.parseInt(request.getBody()));
               String responseBody = "Deleted Successfully";
               return new Message(Message.OK, responseBody);
           } catch (Exception e) {
               e.printStackTrace();
               return new Message(Message.ERR, e.getMessage());
           }
       });

       tcpServer.addHandler("updateMedic", request -> {
           try {
               Medic updateMedic = new Medic();
               updateMedic =(Medic) GenericReflect.getEntityFromString(updateMedic,request.getBody());
               medicServiceServer.update(updateMedic.idEntity,updateMedic.getName(),
                       updateMedic.getUserName(),updateMedic.getPassword(),
                       updateMedic.getEmail(),updateMedic.getSpecialty(),
                       updateMedic.getBeginningDate(),updateMedic.getDateOfBirth(),updateMedic.getIsActive());
               String responseBody = "Updated Successfully";
               return new Message(Message.OK, responseBody);
           } catch (Exception e) {
               e.printStackTrace();
               return new Message(Message.ERR, e.getMessage());
           }
       });
   }

   public static void addPatientHandlers(TcpServer tcpServer, PatientServiceServer patientServiceServer){
        tcpServer.addHandler("addPatient", request -> {
            try {
                Patient patient = new Patient();
                patient = (Patient) GenericReflect.getEntityFromString(patient,request.getBody());

                System.out.println(patient);

                patientServiceServer.addPatient(patient.getName(), patient.getUserName(), patient.getPassword(), patient.getEmail(),
                        patient.getDateOfBirth(), patient.getHired(), patient.getCardNumber());

                System.out.println("Patient Added Successfully");
                return new Message(Message.OK, "Added Successfully");
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });

        tcpServer.addHandler("findOnePatient", request -> {
            try {
                Integer id = Integer.parseInt(request.getBody());
                Future<Optional<Patient>> patient = patientServiceServer.findOne(id);
                // ce se intampla aici?
                Optional<Patient> patient1 = patient.get();
                Patient patientFinal = patient1.get();
                String responseBody = GenericReflect.createStringFromEntity(patientFinal);
                System.out.println("Patient Added Successfully");
                return new Message(Message.OK, responseBody);
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });

        tcpServer.addHandler("findAllPatients", request -> {
            try {
                Future<List<Patient>> patient = patientServiceServer.findAll();
                String responseBody = GenericReflect.createStringFromEntityList(patient.get());
                return new Message(Message.OK, responseBody);
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });

        tcpServer.addHandler("deletePatient", request -> {
            try {
                patientServiceServer.delete(Integer.parseInt(request.getBody()));
                String responseBody = "Deleted Successfully";
                return new Message(Message.OK, responseBody);
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });

        tcpServer.addHandler("updatePatient", request -> {
            try {
                Patient updatePatient = new Patient();
                updatePatient =(Patient) GenericReflect.getEntityFromString(updatePatient,request.getBody());
                patientServiceServer.update(updatePatient.getIdEntity(),updatePatient.getName(),
                        updatePatient.getUserName(),updatePatient.getPassword(),
                        updatePatient.getEmail(),updatePatient.getDateOfBirth(),
                        updatePatient.getHired(),updatePatient.getCardNumber());
                String responseBody = "Updated Successfully";
                return new Message(Message.OK, responseBody);
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERR, e.getMessage());
            }
        });

    }
}
