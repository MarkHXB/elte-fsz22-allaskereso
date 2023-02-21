package hu.elte.joooble.webdomain;

import hu.elte.joooble.domain.file.EmployerLogo;
import hu.elte.joooble.domain.job.Job;

import java.util.List;

public class EmployerView {
    private final String employerId;
    private final String companyName;
    private final String companyAddress;
    private final String email;
    private final List<Job> activeJobList;
    private final List<Job> inactiveJobList;
    private final String logoName;
    private final String logoPath;

    public EmployerView(String employerId, String companyName, String companyAddress, String email, List<Job> activeJobList, List<Job> inactiveJobList, String logoName, String logoPath) {
        this.employerId = employerId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.email = email;
        this.activeJobList = activeJobList;
        this.inactiveJobList = inactiveJobList;
        this.logoName = logoName;
        this.logoPath = logoPath;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getEmail() {
        return email;
    }

    public List<Job> getActiveJobList() {
        return activeJobList;
    }

    public List<Job> getInactiveJobList() {
        return inactiveJobList;
    }

    public String getLogoName() {
        return logoName;
    }

    public String getLogoPath() {
        return logoPath;
    }
}
