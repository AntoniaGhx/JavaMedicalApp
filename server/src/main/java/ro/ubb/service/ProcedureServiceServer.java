package ro.ubb.service;

import ro.ubb.domain.*;
import ro.ubb.domain.Procedure;
import ro.ubb.domain.Procedure;
import ro.ubb.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ProcedureServiceServer implements ProcedureService{

    private ExecutorService executorService;
    private Repository<Integer, Procedure> procedureRepository;
    public static final String AddProcedure = "addProcedure";

    public ProcedureServiceServer(ExecutorService executorService,Repository<Integer, Procedure> procedureRepository) {
        this.procedureRepository = procedureRepository;
        this.executorService = executorService;
    }
    
    @Override
    public void addProcedure(String name, Float price, Specialty specialty, Integer duration) {
        System.out.println("Started adding Procedure");
        Procedure procedure = new Procedure(name,price,specialty,duration);
        procedureRepository.save(procedure);
    }

    @Override
    public Future<Optional<Procedure>> findOne(Integer id) {
        return executorService.submit(()-> procedureRepository.findOne(id));
    }

    @Override
    public Future<List<Procedure>> findAll() {
        return executorService.submit(()-> (List<Procedure>) procedureRepository.findAll());
    }

    @Override
    public void delete(Integer id) {
        executorService.submit(()-> {
            if (procedureRepository.findOne(id).isPresent())
                procedureRepository.delete(id);});
    }

    @Override
    public void update(Integer idEntity, String name, Float price, Specialty specialty, Integer duration) {
        executorService.submit(()-> {
            Procedure updatedProcedure = new Procedure(idEntity, name, price, specialty, duration);
            procedureRepository.update(updatedProcedure);
        });
    }
}
