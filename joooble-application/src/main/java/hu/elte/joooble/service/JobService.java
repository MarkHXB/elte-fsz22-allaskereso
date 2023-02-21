package hu.elte.joooble.service;

import hu.elte.joooble.domain.job.*;
import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.domain.user.Employer;
import hu.elte.joooble.repository.EmployeeJobRepository;
import hu.elte.joooble.repository.EmployeeRepository;
import hu.elte.joooble.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.elte.joooble.repository.JobRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private EmployeeJobRepository employeeJobRepository;

    public Job findById(Long id) {
        return jobRepository.findById(id).get();
    }

    public Iterable<Job> findAllJob() {
        return jobRepository.findAll();
    }

    public Job findJobByEmployerId(Long employerId) {
        return jobRepository.findJobByEmployerId(employerId);
    }

    public Iterable<Job> findAllJobsByCategory(String category) {
        return jobRepository.findAllJobsByJobCategory(JobCategoryEnum.valueOf(category));
    }

    public Map<String, Long> getCategoryStatistics() {
        List<String> categories = new ArrayList<>();
        for (Job j : findAllJob()) {
            categories.add(j.getJobCategory().name());
        }

        Map<String, Long> stat = categories.stream().collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()));

        //Adding the reamining categories
        for(JobCategoryEnum category : JobCategoryEnum.values()){
            String value = category.name();
            boolean found = false;

            for(Map.Entry<String,Long> entry : stat.entrySet()) {
                if (entry.getKey() == value) {
                    found = true;
                    break;
                }
            }

            if(!found){
                stat.put(category.name(), 0L);
            }
        }


        return stat;
    }

    public boolean isJobAvaliableForEmployee(Long employeeId, Long jobId) {
        Employee emp = employeeRepository.findById(employeeId).get();
        Job job = jobRepository.findById(jobId).get();

        if (employeeJobRepository.findEmployeeJobByJob_Id(job.getId()) != null) {
            return false;
        }

        return true;
    }

    // Edit and save
    public Job save(Job job) {
        job.setActive(true);
        job.setAddedDate(LocalDateTime.now());
        return jobRepository.save(job);
    }

    public Job saveJobActivity(Long jobId, boolean status) {
        Job job = jobRepository.findById(jobId).get();
        job.setActive(status);
        return jobRepository.save(job);
    }

    public List<Job> getRecommendedJobsList(String employeeUsername) {
        Employee emp = employeeRepository.findEmployeeByCredential_Email(employeeUsername);
        List<JobCategoryEnum> recommendedCategories = new ArrayList<>();
        List<Job> recommendedJobs = new ArrayList<>();

        // Function
        Map<JobCategoryEnum, Integer> groupedAppliences = new HashMap<>();

        //Grouping jobs according to category
        for (EmployeeJob employeeJob : emp.getJobs()) {
            if (employeeJob.getStatus() == JobStatusEnum.APPLIED) {
                groupedAppliences.merge(employeeJob.getJob().getJobCategory(), 1, Integer::sum);
            }
        }

        //Sorted by value desc category
        groupedAppliences.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(job -> {
                    recommendedCategories.add(job.getKey());
                });

        //Fill the recommended jobs list according to categories
        Iterable<Job> jobs = findAllJob();
        for (JobCategoryEnum category : recommendedCategories) {
            for (Job job : jobs) {
                // && emp.getHiredJobs().stream().filter(j->j.getJob() == job).count() == 0
                if (job.getJobCategory() == category) {
                    recommendedJobs.add(job);
                }
            }
        }

        return recommendedJobs;
    }

    public List<Job> findAllActiveJobsByEmployerId(Long employerId) {
        Employer employer = employerRepository.findById(employerId).get();
        List<Job> activeJobs = new ArrayList<>();
        for (Job job : employer.getJobs()) {
            if (job.isActive())
                activeJobs.add(job);
        }
        return activeJobs;
    }

    public List<Job> findAllInActiveJobsByEmployerId(Long employerId) {
        Employer employer = employerRepository.findById(employerId).get();
        List<Job> inActiveJobs = new ArrayList<>();
        for (Job job : employer.getJobs()) {
            if (!job.isActive())
                inActiveJobs.add(job);
        }
        return inActiveJobs;
    }

    public Iterable<Employee> findAllEmployeeByAppliedJobEmployerId(Long jobId, Long employerId) {
        List<Employee> employees = new ArrayList<>();

        for (Employee employee : employeeRepository.findAll()) {
            for (EmployeeJob appliedJob : employee.getJobs()) {
                if (appliedJob.getJob().getId() == jobId && appliedJob.getJob().getEmployer().getId() == employerId && appliedJob.getStatus() == JobStatusEnum.APPLIED) {
                    employees.add(employee);
                }
            }
        }

        return employees;
    }

    public Iterable<Employee> findAllEmployeeByHiredJobEmployerId(Long jobId, Long employerId) {
        List<Employee> employees = new ArrayList<>();

        for (Employee employee : employeeRepository.findAll()) {
            for (EmployeeJob hiredJob : employee.getJobs()) {
                if (hiredJob.getJob().getId() == jobId && hiredJob.getJob().getEmployer().getId() == employerId && hiredJob.getStatus() == JobStatusEnum.HIRED) {
                    employees.add(employee);
                }
            }
        }

        return employees;
    }

    public Iterable<Employee> findAllEmployeeByRejectedJobEmployerId(Long jobId, Long employerId) {
        List<Employee> employees = new ArrayList<>();

        for (Employee employee : employeeRepository.findAll()) {
            for (EmployeeJob rejectedJob : employee.getJobs()) {
                if (rejectedJob.getJob().getId() == jobId && rejectedJob.getJob().getEmployer().getId() == employerId && rejectedJob.getStatus() == JobStatusEnum.REJECTED) {
                    employees.add(employee);
                }
            }
        }

        return employees;
    }


}
