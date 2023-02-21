package hu.elte.joooble.repository;

import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.domain.job.JobCategoryEnum;

import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job,Long> {
	Job findJobByEmployerId(Long empId);
	Iterable<Job> findAllJobsByJobCategory(JobCategoryEnum category);
	Iterable<Job> findAllJobsByEmployerId(Long empId);

}
