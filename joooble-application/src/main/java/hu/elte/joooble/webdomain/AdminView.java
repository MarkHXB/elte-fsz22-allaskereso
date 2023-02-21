package hu.elte.joooble.webdomain;
import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.domain.user.Credential;
import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.domain.user.Employer;
import java.util.List;

public class AdminView {

    private Long id;

    private Credential credential;

    private List<Job> jobs;

    private List<Employee> employees;

    private List<Employer> employers;

    public AdminView(long id,Credential credential,List<Job> jobs,List<Employee> employees,List<Employer> employers){
        this.id = id;
        this.credential = credential;
        this.jobs = jobs;
        this.employees = employees;
        this.employers = employers;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Credential getCredential() {
        return credential;
    }
    public void setCredential(Credential credential) {
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
