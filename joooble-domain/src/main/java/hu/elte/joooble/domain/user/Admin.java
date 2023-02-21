package hu.elte.joooble.domain.user;

import hu.elte.joooble.domain.job.Job;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Admin{
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @OneToOne
    private Credential credential;

    @OneToMany
    private List<Job> jobs;
    @OneToMany
    private List<Employee> employees;
    @OneToMany
    private List<Employer> employers;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Credential getCredential() {
        return credential;
    }
    public void setCredential(@NonNull Credential credential) {
        this.credential = credential;
    }


    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }
}
