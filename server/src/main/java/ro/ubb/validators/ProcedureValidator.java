package ro.ubb.validators;

import ro.ubb.domain.BaseEntity;
import ro.ubb.domain.Procedure;
import ro.ubb.repository.RepositoryException;

public class ProcedureValidator implements Validator<Procedure>{
    @Override
    public void validate(Procedure orice) throws ValidatorException {

    }

    @Override
    public void validateId(Integer id, Iterable<? extends BaseEntity> allEntities) throws RepositoryException {

    }

    @Override
    public Procedure checkEmptyBeforeUpdate(Procedure updated, Procedure beforeUpdate) throws ValidatorException {
        return null;
    }
}
