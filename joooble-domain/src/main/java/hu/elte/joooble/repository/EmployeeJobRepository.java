package hu.elte.joooble.repository;

import hu.elte.joooble.domain.job.EmployeeJob;
import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.domain.job.JobStatusEnum;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeJobRepository extends CrudRepository<EmployeeJob,Long> {
    EmployeeJob findEmployeeJobByEmployee_IdAndJob_Id(Long jobId, Long employeeId);
    EmployeeJob findEmployeeJobByJob_Id(Long id);
    Iterable<EmployeeJob> findEmployeeJobsByEmployee_IdAndStatus(Long employeeId, JobStatusEnum status);
}
