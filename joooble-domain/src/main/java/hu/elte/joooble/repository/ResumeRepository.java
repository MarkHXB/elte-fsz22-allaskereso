package hu.elte.joooble.repository;

import hu.elte.joooble.domain.file.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepository extends CrudRepository<Resume,Long> {
	Resume findByEmployeeId(Long id);
	Iterable<Resume> findAllByEmployeeId(Long id);
}
