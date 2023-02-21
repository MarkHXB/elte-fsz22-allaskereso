package hu.elte.joooble.controller;

import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.domain.job.JobStatusEnum;
import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.domain.user.Employer;
import hu.elte.joooble.security.SecurityHelper;
import hu.elte.joooble.service.*;
import hu.elte.joooble.storage.StorageService;
import hu.elte.joooble.webdomain.JobDetailsView;
import hu.elte.joooble.webdomain.MessageBoxView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;

@Controller
public class JobController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private FileService fileService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private JobService jobService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeJobService employeeJobService;


    @PostMapping("/job/activate")
    public String activateJob(@RequestParam("jobId") Long jobId, RedirectAttributes redirectAttributes){
        if(jobId == null){
            redirectAttributes.addFlashAttribute("messageView", new MessageBoxView("error","Job status update","You should give a job ID to make the active"));
            return "redirect:/profile";
        }

        jobService.saveJobActivity(jobId,true);

        redirectAttributes.addFlashAttribute("messageView", new MessageBoxView("message","Job status update","You successfully activated the job."));

        return "redirect:/profile";
    }

    @PostMapping("/job/inactivate")
    public String inActivateJob(@RequestParam("jobId") Long jobId, RedirectAttributes redirectAttributes){
        if(jobId == null){
            redirectAttributes.addFlashAttribute("messageView", new MessageBoxView("error","Job status update","You should give a job ID to make the active"));
            return "redirect:/profile";
        }

        jobService.saveJobActivity(jobId,false);

        redirectAttributes.addFlashAttribute("messageView", new MessageBoxView("message","Job status update","You successfully inactivated the job."));

        return "redirect:/profile";
    }

    @GetMapping("/job/applicants/{jobId}")
    public String getApplicantsToJob(@PathVariable("jobId") Long jobId, Model model){
        Long employerId = employerService.findEmployerByCredentialEmail(SecurityHelper.getUsername()).getId();

        Iterable<Employee> appliedEmployees = jobService.findAllEmployeeByAppliedJobEmployerId(jobId,employerId);
        Iterable<Employee> hiredEmployees = jobService.findAllEmployeeByHiredJobEmployerId(jobId,employerId);
        Iterable<Employee> rejectedEmployees = jobService.findAllEmployeeByRejectedJobEmployerId(jobId,employerId);
        model.addAttribute("jobId",jobId);
        model.addAttribute("appliedEmployees",appliedEmployees);
        model.addAttribute("hiredEmployees",hiredEmployees);
        model.addAttribute("rejectedEmployees",rejectedEmployees);

        return "applicantsList";
    }

    @GetMapping("job/details/{id}")
    public String jobDetails(Model model, @PathVariable("id") Long jobId) {
        Job job = jobService.findById(jobId);
        Employer employer = job.getEmployer();
        String absoluteLogoPath = fileService.getFileAbsolutePath(storageService.load(job.getEmployer().getLogo().getFileName()));


        boolean jobIsAvailable = false;
        String userType = SecurityHelper.isEmployer() ? "employer" : "employee";

        String jobStatus = "";
        if(SecurityHelper.isEmployee()){
            Employee employee = employeeService.findEmployeeByCredentialEmail(SecurityHelper.getUsername());
            jobStatus = JobStatusEnum.valueOf(employeeJobService.findByJobId(jobId,employee.getId()).getStatus().name()).toString();
            jobIsAvailable = jobService.isJobAvaliableForEmployee(employee.getId(),jobId);
        }

        JobDetailsView dto = new JobDetailsView(userType, job.getId(), absoluteLogoPath, employer.getCompanyName(),
                job.getAddedDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), job.getPositionName(),
                job.getLocation(), job.getDescription(), employer.getCredential().getEmail(), employer.getLocation(),jobStatus,jobIsAvailable);

        model.addAttribute("view", dto);

        return "job";
    }

    @PostMapping("/job/withdraw-applicant")
    public String withdrawApplication(@RequestParam("jobId") Long jobId,RedirectAttributes redirectAttributes){
        // DAO
        Job job = jobService.findById(jobId);
        Employee employee = employeeService.findEmployeeByCredentialEmail(SecurityHelper.getUsername());
        boolean withdrawIsSuccess = employeeJobService.withDrawApplicationByJobAndEmployeeId(employee.getId(), jobId);

        //MessageBoxView
        String message = "";

        if(!withdrawIsSuccess){
            message = String.format("Your requestation to withdraw the application for %s failed.", job.getPositionName());
            redirectAttributes.addFlashAttribute("messageView",new MessageBoxView("error","Withdraw operation failed",message));
        }

        message = String.format("Successfuly withdraw the application from %s", job.getPositionName());
        redirectAttributes.addFlashAttribute("messageView",new MessageBoxView("message","Withdraw operation completed",message));

        return "redirect:/profile";
    }
}
