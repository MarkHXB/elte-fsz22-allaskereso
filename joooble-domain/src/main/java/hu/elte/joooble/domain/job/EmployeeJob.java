package hu.elte.joooble.domain.job;

import hu.elte.joooble.domain.user.Employee;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
public class EmployeeJob{
    @Id
    @GeneratedValue
    private Long id;
    // When changed the status of the current job for the employee...
    private LocalDate statusChanged;

    @Enumerated(EnumType.STRING)
    private JobStatusEnum status;

    @OneToOne
    private Job job;

    @ManyToOne
    private Employee employee;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }
    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDate getStatusChanged() {
        return statusChanged;
    }

    public void setStatusChanged(LocalDate statusChanged) {
        this.statusChanged = statusChanged;
    }

    public JobStatusEnum getStatus() {
        return status;
    }

    public void setStatus(JobStatusEnum status) {
        this.status = status;
    }
}
