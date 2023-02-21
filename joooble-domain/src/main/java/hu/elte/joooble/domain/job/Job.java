package hu.elte.joooble.domain.job;

import hu.elte.joooble.domain.user.Employer;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String location;

    private String description;

    //private List<String> requirements;

    @Enumerated(EnumType.STRING)
    private JobCategoryEnum jobCategory;

    private LocalDateTime addedDate;

    private BigDecimal salary;

    private int workingHours;

    private String positionName;

    @OneToOne
    private Employer employer;
    
    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getLocation() {
        return location;
    }

    public void setLocation(@NonNull String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobCategoryEnum getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(JobCategoryEnum jobCategory) {
        this.jobCategory = jobCategory;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

	@Override
	public String toString() {
		return "Job [title=" + title + ", location=" + location + ", description=" + description + ", jobCategory="
				+ jobCategory + ", addedDate=" + addedDate + ", salary=" + salary + ", workingHours=" + workingHours
				+ ", positionName=" + positionName + ", employer=" + employer + ", isActive=" + isActive + "]";
	}

    
}
