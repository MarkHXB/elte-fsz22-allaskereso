package hu.elte.joooble.domain.user;

import hu.elte.joooble.domain.job.EmployeeJob;
import hu.elte.joooble.domain.file.Resume;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Employee extends User{
    @NonNull
    @Column(name = "birthdate",  nullable = false)
    private LocalDate birthdate;

    @OneToOne
    private Resume resume;
    
    @OneToMany
    private List<EmployeeJob> jobs;

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}
	public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

	public List<EmployeeJob> getJobs() {
		return jobs;
	}

	public void setJobs(List<EmployeeJob> jobs) {
		this.jobs = jobs;
	}

	@Override
    public String toString() {
        return "Employee{" +
                "birthdate=" + birthdate +
                '}';
    }
}
