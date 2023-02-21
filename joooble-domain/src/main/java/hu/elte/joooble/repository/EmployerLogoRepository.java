package hu.elte.joooble.repository;

import hu.elte.joooble.domain.file.EmployerLogo;
import org.springframework.data.repository.CrudRepository;

public interface EmployerLogoRepository extends CrudRepository<EmployerLogo,Long> {
	EmployerLogo findEmployerLogoByEmployerId(Long id);
	Iterable<EmployerLogo> findAllEmployerLogoByEmployerId(Long empId);
}
