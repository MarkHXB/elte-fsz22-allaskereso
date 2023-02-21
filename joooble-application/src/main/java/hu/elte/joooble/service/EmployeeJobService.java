package hu.elte.joooble.service;

import hu.elte.joooble.domain.job.EmployeeJob;
import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.domain.job.JobStatusEnum;
import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.repository.EmployeeJobRepository;
import hu.elte.joooble.repository.EmployeeRepository;
import hu.elte.joooble.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeJobService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeJobRepository employeeJobRepository;
    @Autowired
    private JobRepository jobRepository;

    public Iterable<Job> findAllJobsByEmployeeIdAndJobStatus(Long employeeId, JobStatusEnum status){
        Iterable<EmployeeJob> employeeJobs = employeeJobRepository.findEmployeeJobsByEmployee_IdAndStatus(employeeId, status);
        List<Job> jobs = new ArrayList<>();
        for(EmployeeJob job : employeeJobs){
            jobs.add(job.getJob());
        }
        return jobs;
    }

    public EmployeeJob findByJobId(Long jobId, Long employeeId) {
        EmployeeJob employeeJob = employeeJobRepository.findEmployeeJobByJob_Id(jobId);

        if(employeeJob == null){
            Employee employee = employeeRepository.findById(employeeId).get();
            EmployeeJob newEmployeeJob = new EmployeeJob();
            newEmployeeJob.setStatus(JobStatusEnum.DEFAULT);
            newEmployeeJob.setStatusChanged(LocalDate.now());
            newEmployeeJob.setJob(jobRepository.findById(jobId).get());
            newEmployeeJob.setEmployee(employee);

            EmployeeJob employeeJob1 = employeeJobRepository.save(newEmployeeJob);
            List<EmployeeJob> employeeJobs = employee.getJobs();
            employeeJobs.add(newEmployeeJob);
            employee.setJobs(employeeJobs);
            employeeRepository.save(employee);
            return  employeeJob1;
        }
        return employeeJob;
    }

    public boolean updateEmployeeJob(Long jobId, Long employeeId, JobStatusEnum status) {
        Employee employee = employeeRepository.findById(employeeId).get();
        EmployeeJob employeeJob = employeeJobRepository.findEmployeeJobByJob_Id(jobId);

        if (employeeJob == null) return false;

        employeeJob.setStatusChanged(LocalDate.now());
        employeeJob.setStatus(status);

        employeeRepository.save(employee);
        employeeJobRepository.save(employeeJob);

        return true;
    }

    public boolean withDrawApplicationByJobAndEmployeeId(Long emplyeeId, Long jobId) {
        try{
            EmployeeJob employeeJob = employeeJobRepository.findEmployeeJobByEmployee_IdAndJob_Id(jobId,emplyeeId);
            employeeJobRepository.deleteById(employeeJob.getId());
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
}
