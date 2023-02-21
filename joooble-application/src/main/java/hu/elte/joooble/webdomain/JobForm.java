package hu.elte.joooble.webdomain;

import hu.elte.joooble.domain.job.JobCategoryEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class JobForm {
    @NotBlank(message = "You should give a title name")
    @Length(min=3,max=30,message = "The title's length should be between 3 and 30 characters.")
    private String title;

    @NotBlank(message = "You should set a job location")
    @Length(min=3,message = "The location's length should be more than 3 characters.")
    private String location;

    @NotBlank(message = "Please describe the details of the job")
    @Length(min=10,max=500,message = "The description should be between 10 and 500 characters.")
    private String description;

    @NotNull
    private JobCategoryEnum jobCategory;

    @NotNull
    private BigDecimal salary;

    @NotNull
    @Min(1)
    @Max(40)
    private int workingHours;

    @NotBlank(message = "Please give the job's position name")
    @Length(min=5,max=50,message = "The position name should be between 5 and 50 characters.")
    private String positionName;
    private Long employerId;

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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
}
