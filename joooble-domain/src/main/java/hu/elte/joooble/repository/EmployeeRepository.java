package hu.elte.joooble.repository;

import hu.elte.joooble.domain.user.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Employee findEmployeeByCredential_Email(String email);
}
