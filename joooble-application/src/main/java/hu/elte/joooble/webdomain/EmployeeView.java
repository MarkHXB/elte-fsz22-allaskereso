package hu.elte.joooble.webdomain;

import hu.elte.joooble.domain.job.EmployeeJob;
import hu.elte.joooble.domain.job.Job;


public class EmployeeView {
    private final String employeeId;
    private final String email;
    private final String birthdate;
    private final String resumeName;
    private final String resumeFileName;
    public final Iterable<Job> appliedJobList;
    public final Iterable<Job> hiredJobList;
    public final Iterable<Job> rejectedJobList;

    public EmployeeView(String employeeId, String email, String birthdate, String resumeName, String resumeFileName, Iterable<Job> appliedJobList, Iterable<Job> hiredJobList, Iterable<Job> rejectedJobList) {
        this.employeeId = employeeId;
        this.email = email;
        this.birthdate = birthdate;
        this.resumeName = resumeName;
        this.resumeFileName = resumeFileName;
        this.appliedJobList = appliedJobList;
        this.hiredJobList = hiredJobList;
        this.rejectedJobList = rejectedJobList;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getResumeName() {
        return resumeName;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public Iterable<Job> getAppliedJobList() {
        return appliedJobList;
    }

    public Iterable<Job> getHiredJobList() {
        return hiredJobList;
    }

    public Iterable<Job> getRejectedJobList() {
        return rejectedJobList;
    }
}
