package ro.ubb.ui;

import ro.ubb.additional.DateTimeParser;
import ro.ubb.domain.*;
import ro.ubb.service.LocationService;
import ro.ubb.service.MedicService;
import ro.ubb.service.PatientService;
import ro.ubb.service.ProcedureService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ClientConsole {
    private MedicService medicService;
    private PatientService patientService;
    private ProcedureService procedureService;

    Scanner scan = new Scanner(System.in);

    public ClientConsole(MedicService medicService, PatientService patientService, ProcedureService procedureService) {
        this.medicService = medicService;
        this.patientService = patientService;
        this.procedureService = procedureService;
    }

    public ClientConsole(MedicService medicService, PatientService patientService) {
        this.medicService = medicService;
        this.patientService = patientService;
    }

    public void printMenu() {
        System.out.println(
                "a. Medics menu    || b. Patients menu   || c. Locations menu  || d. Procedures menu\n" +
                "X. Exit");
    }

    public void medicMenu() {
        System.out.println(
                "1. List all medics     \n" +
                "2. Add new Medic       \n" +
                "3. Delete Medic        \n" +
                "4. Find Medic by id    \n" +
                "5. Update Medic of id  \n" +
                "6. Filter User         \n" +
                "<. Go back             \n" +
                "X. Exit");
    }

    public void patientMenu() {
        System.out.println(
                "1. List all Patients     \n" +
                "2. Add Patient           \n" +
                "3. Delete Patient        \n" +
                "4. Find Patient by id    \n" +
                "5. Update Patient of id  \n" +
                "6. Filter Patient        \n" +
                "<. Go back               \n" +
                "X. Exit");
    }

    public void procedureMenu() {
        System.out.println(
                "1. List all Procedures     \n" +
                "2. Add Procedure           \n" +
                "3. Delete Procedure        \n" +
                "4. Find Procedure by id    \n" +
                "5. Update Procedure of id  \n" +
                "6. Filter Procedure        \n" +
                "<. Go back               \n" +
                "X. Exit");
    }

    public void runConsole(){
        Boolean exit = false;
        while(!exit){
            printMenu();
            String option = scan.nextLine();

            switch (option) {
                case "a":
                    exit = medicOptions();
                    break;
                case "b":
                    exit = patientOptions();
                    break;
                case "c":

                    break;
                case "d":
                    exit = procedureOptions();
                    break;
                case "X", "x":
                    exit = true;
                    break;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }

    private Boolean medicOptions() {
        while(true) {
            medicMenu();

            String option = scan.nextLine();
            switch (option) {
                case "1":
                    handleFindAllMedics();
                    break;
                case "2":
                    handleAddMedic();
                    break;
                case "3":
                    handleDeleteMedic();
                    break;
                case "4":
                    handleFindMedic();
                    break;
                case "5":
                    handleUpdateMedic();
                    break;
                case "<":
                    return false;
                case "X", "x":
                    return true;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }

    private Boolean patientOptions() {
        while(true) {
            patientMenu();

            String option = scan.nextLine();
            switch (option) {
                case "1":
                    handleFindAllPatients();
                    break;
                case "2":
                    handleAddPatient();
                    break;
                case "3":
                    handleDeletePatient();
                    break;
                case "4":
                    handleFindPatient();
                    break;
                case "5":
                    handleUpdatePatient();
                    break;
                case "<":
                    return false;
                case "X", "x":
                    return true;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }


    private Boolean procedureOptions() {
        while(true) {
            procedureMenu();

            String option = scan.nextLine();
            switch (option) {
                case "1":
                    handleFindAllProcedures();
                    break;
                case "2":
                    handleAddProcedure();
                    break;
                case "3":
                    handleDeleteProcedure();
                    break;
                case "4":
                    handleFindProcedure();
                    break;
                case "5":
                    handleUpdateProcedure();
                    break;
                case "<":
                    return false;
                case "X", "x":
                    return true;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }

    private void handleUpdateMedic()  {
        System.out.println("Enter Medic ID:");
        Integer id = Integer.parseInt(scan.nextLine());
        Medic currentMedic = new Medic();
        try {
           currentMedic = medicService.findOne(id).get().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(medicService.findOne(id));
        System.out.println("Enter Updated Name:");
        String name = scan.nextLine();
        System.out.println("Enter Updated User Name:");
        String userName = scan.nextLine();
        System.out.println("Enter Updated password:");
        String password = scan.nextLine();
        System.out.println("Enter Updated email:");
        String email = scan.nextLine();
        System.out.println("Choose Updated Specialty by entering the corresponding number:\n" +
                "Dermatology(1)\n" +
                "Cardiology(2)\n" +
                "Family(3)\n" +
                "Internal(4)\n" +
                "Neurology(5)\n" +
                "Gynecology(6)\n" +
                "Ophthalmology(7)\n" +
                "Pathology(8)\n" +
                "Pediatrics(9)\n" +
                "Recuperation(10)\n" +
                "Psychiatry(11)\n" +
                "Radiology(12)\n" +
                "Surgery(13)\n" +
                "Urology(14)\n");
        Integer valueOfLabel = Integer.parseInt(scan.nextLine());
        Specialty specialty;
        if (valueOfLabel <= 0) specialty = currentMedic.getSpecialty();
        else specialty = Specialty.valueOfLabel(valueOfLabel);
        System.out.println("Enter Updated Beginning Date");
        LocalDate beginningDate = DateTimeParser.parseDate(scan.nextLine());
        System.out.println("Enter Updated BirthDate");
        LocalDate dateOfBirth = DateTimeParser.parseDate(scan.nextLine());
        System.out.println("Update if active:\n" +
                "0.NO     1.YES\n");
        String active = scan.nextLine();
        boolean isActive = true;
        if(active.equals("1") || active.matches("[yesYES]{1,3}")) isActive = true;
        if(active.equals("0") || active.matches("[noNO]{1,2}")) isActive = false;
        medicService.update(id,name,userName,password,email,specialty,beginningDate,dateOfBirth,isActive);
    }

    private void handleDeleteMedic() {
        System.out.println("Enter Id to delete:");
        Integer id = Integer.parseInt(scan.nextLine());
        medicService.delete(id);
    }

    private void handleFindAllMedics() {
        try {
            medicService.findAll().get().forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void handleFindMedic() {
        System.out.println("Enter Id to find:");
        Integer id = Integer.parseInt(scan.nextLine());
        try {
            Optional<Medic> medic = medicService.findOne(id).get();
            System.out.println(medic.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void handleAddMedic() {
        System.out.println("Enter Name:");
        String name = scan.nextLine();
        System.out.println("Enter User Name:");
        String userName = scan.nextLine();
        System.out.println("Enter password:");
        String password = scan.nextLine();
        System.out.println("Enter email:");
        String email = scan.nextLine();
        System.out.println("Choose Specialty by entering the corresponding number:\n" +
                "Dermatology(1)\n" +
                "Cardiology(2)\n" +
                "Family(3)\n" +
                "Internal(4)\n" +
                "Neurology(5)\n" +
                "Gynecology(6)\n" +
                "Ophthalmology(7)\n" +
                "Pathology(8)\n" +
                "Pediatrics(9)\n" +
                "Recuperation(10)\n" +
                "Psychiatry(11)\n" +
                "Radiology(12)\n" +
                "Surgery(13)\n" +
                "Urology(14)\n");
        Integer valueOfLabel = Integer.parseInt(scan.nextLine());
        Specialty specialty;
        if (valueOfLabel <= 0 || valueOfLabel > 14) {
            System.out.println("Incorrect value entered, Speciality chosen by default is Dermatology");
            specialty = Specialty.Dermatology;
        }
        else specialty = Specialty.valueOfLabel(valueOfLabel);
        System.out.println("Enter Beginning Date");
        LocalDate beginningDate = DateTimeParser.parseDate(scan.nextLine());
        System.out.println("Enter BirthDate");
        LocalDate dateOfBirth = DateTimeParser.parseDate(scan.nextLine());
        System.out.println("Check if active:\n" +
                "0.NO     1.YES\n");
        String active = scan.nextLine();
        boolean isActive = true;
        if(active.equals("1") || active.matches("[yesYES]{1,3}")) isActive = true;
        if(active.equals("0") || active.matches("[noNO]{1,2}")) isActive = false;

        medicService.addMedic(name,userName,password,email,
                specialty,beginningDate,dateOfBirth,isActive);

    }

    private void handleUpdatePatient() {
        System.out.println("Enter Patient ID:");
        Integer id = Integer.parseInt(scan.nextLine());
        Patient currentPatient = new Patient();
        try {
            currentPatient = patientService.findOne(id).get().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(currentPatient);
        System.out.println("Enter new name:");
        String name = scan.nextLine();
        System.out.println("Enter new username:");
        String userName = scan.nextLine();
        System.out.println("Enter new password:");
        String password = scan.nextLine();
        System.out.println("Enter new email:");
        String email = scan.nextLine();
        System.out.println("Enter new birth date");
        LocalDate dateOfBirth = DateTimeParser.parseDate(scan.nextLine());
        System.out.println("Choose new hired value:\n" +
                "True(1)\n" +
                "False(0)\n" );
        String hiredNumber = scan.nextLine();
        System.out.println("Enter new card number:");
        Integer cardNumber = Integer.parseInt(scan.nextLine());

        //Deocamdata facem update cu lista goala pana facem CRUD
//        System.out.println("Update afflictions:");
        // aici cum putem face? ne trebuie toate afflictions din DB, nu?
//        List<Diagnosis> afflictions = new ArrayList<>();
        boolean hired = true;
        if(hiredNumber.equals("1") || hiredNumber.matches("[trueTrue]{1,3}")) hired = true;
        if(hiredNumber.equals("0") || hiredNumber.matches("[falseFalse]{1,2}")) hired = false;

        patientService.update(id, name,userName,password,email,dateOfBirth,hired,cardNumber);
    }

    private void handleDeletePatient() {
        System.out.println("Enter Id to delete:");
        Integer id = Integer.parseInt(scan.nextLine());
        patientService.delete(id);
    }

    private void handleFindAllPatients() {
        try {
            patientService.findAll().get().forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void handleFindPatient() {
        System.out.println("Enter Id to find:");
        Integer id = Integer.parseInt(scan.nextLine());
        try {
            Optional<Patient> patient = patientService.findOne(id).get();
            System.out.println(patient.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void handleAddPatient() {
        System.out.println("Enter new name:");
        String name = scan.nextLine();
        System.out.println("Enter new username:");
        String userName = scan.nextLine();
        System.out.println("Enter new password:");
        String password = scan.nextLine();
        System.out.println("Enter new email:");
        String email = scan.nextLine();
        System.out.println("Enter new birth date");
        LocalDate dateOfBirth = DateTimeParser.parseDate(scan.nextLine());
        System.out.println("Choose new hired value:\n" +
                "True(1)\n" +
                "False(0)\n" );
        String hiredNumber = scan.nextLine();
        System.out.println("Enter new card number:");
        int cardNumber = Integer.parseInt(scan.nextLine());

        // Ne trebuie CRUD pentu Diagnosis, deocamdata cream cu o lista goala de diagnostic
//        System.out.println("Add afflictions:");
        // aici cum putem face? ne trebuie toate afflictions din DB, nu?
        boolean hired = true;
        if(hiredNumber.equals("1") || hiredNumber.matches("[trueTrue]{1,3}")) hired = true;
        if(hiredNumber.equals("0") || hiredNumber.matches("[falseFalse]{1,2}")) hired = false;

        patientService.addPatient(name,userName,password,email,
                dateOfBirth,hired,cardNumber);
    }

    private void handleUpdateProcedure() {
        System.out.println("Enter Procedure ID:");
        Integer id = Integer.parseInt(scan.nextLine());
        System.out.println("Enter new name:");
        String name = scan.nextLine();
        System.out.println("Enter new price:");
        Float price = Float.parseFloat(scan.nextLine());
        System.out.println("Enter new duration in minutes:");
        Integer duration = Integer.parseInt(scan.nextLine());
        System.out.println("Choose Specialty by entering the corresponding number:\n" +
                "Dermatology(1)\n" +
                "Cardiology(2)\n" +
                "Family(3)\n" +
                "Internal(4)\n" +
                "Neurology(5)\n" +
                "Gynecology(6)\n" +
                "Ophthalmology(7)\n" +
                "Pathology(8)\n" +
                "Pediatrics(9)\n" +
                "Recuperation(10)\n" +
                "Psychiatry(11)\n" +
                "Radiology(12)\n" +
                "Surgery(13)\n" +
                "Urology(14)\n");
        Integer valueOfLabel = Integer.parseInt(scan.nextLine());
        Specialty specialty;
        if (valueOfLabel <= 0 || valueOfLabel > 14) {
            System.out.println("Incorrect value entered, Speciality chosen by default is Dermatology");
            specialty = Specialty.Dermatology;
        }
        else specialty = Specialty.valueOfLabel(valueOfLabel);
        procedureService.update(id,name,price,specialty,duration);
    }

    private void handleDeleteProcedure() {
        System.out.println("Enter Id to delete:");
        Integer id = Integer.parseInt(scan.nextLine());
        procedureService.delete(id);
    }

    private void handleFindAllProcedures() {
        try {
            procedureService.findAll().get().forEach(System.out::println);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void handleFindProcedure() {
        System.out.println("Enter Id to find:");
        Integer id = Integer.parseInt(scan.nextLine());
        try {
            Optional<Procedure> procedure = procedureService.findOne(id).get();
            System.out.println(procedure.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void handleAddProcedure() {
        System.out.println("Enter name:");
        String name = scan.nextLine();
        System.out.println("Enter price:");
        Float price = Float.parseFloat(scan.nextLine());
        System.out.println("Enter duration in minutes:");
        Integer duration = Integer.parseInt(scan.nextLine());
        System.out.println("Choose Specialty by entering the corresponding number:\n" +
                "Dermatology(1)\n" +
                "Cardiology(2)\n" +
                "Family(3)\n" +
                "Internal(4)\n" +
                "Neurology(5)\n" +
                "Gynecology(6)\n" +
                "Ophthalmology(7)\n" +
                "Pathology(8)\n" +
                "Pediatrics(9)\n" +
                "Recuperation(10)\n" +
                "Psychiatry(11)\n" +
                "Radiology(12)\n" +
                "Surgery(13)\n" +
                "Urology(14)\n");
        Integer valueOfLabel = Integer.parseInt(scan.nextLine());
        Specialty specialty;
        if (valueOfLabel <= 0 || valueOfLabel > 14) {
            System.out.println("Incorrect value entered, Speciality chosen by default is Dermatology");
            specialty = Specialty.Dermatology;
        }
        else specialty = Specialty.valueOfLabel(valueOfLabel);
        procedureService.addProcedure(name,price,specialty,duration);
    }
}
