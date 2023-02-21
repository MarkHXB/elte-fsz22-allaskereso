package hu.elte.joooble.repository;

import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.domain.user.Employer;
import org.springframework.data.repository.CrudRepository;

public interface EmployerRepository extends CrudRepository<Employer,Long> {
    Employer findEmployerByCredential_Email(String email);

}
