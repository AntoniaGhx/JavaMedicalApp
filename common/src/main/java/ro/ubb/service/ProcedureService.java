package ro.ubb.service;

import ro.ubb.domain.Procedure;
import ro.ubb.domain.Specialty;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface ProcedureService {
    void addProcedure(String name, Float price, Specialty specialty, Integer duration);

    Future<Optional<Procedure>> findOne(Integer id);

    Future<List<Procedure>> findAll();

    void delete(Integer id);

    void update(Integer idEntity, String name, Float price, Specialty specialty, Integer duration);
}
