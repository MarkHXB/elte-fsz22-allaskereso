package hu.elte.joooble.repository;

import hu.elte.joooble.domain.file.EmployerLogo;
import hu.elte.joooble.domain.user.Credential;
import org.springframework.data.repository.CrudRepository;

public interface CredentialRepository extends CrudRepository<Credential,Long> {
}
