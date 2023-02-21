package hu.elte.joooble.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hu.elte.joooble.domain.job.JobStatusEnum;
import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.security.SecurityHelper;
import hu.elte.joooble.service.*;
import hu.elte.joooble.storage.StorageService;
import hu.elte.joooble.transformer.JobTransformer;
import hu.elte.joooble.webdomain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.domain.job.JobCategoryEnum;
import hu.elte.joooble.domain.user.Employer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class JobsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobsController.class);
    @Autowired
    private JobService jobService;
    @Autowired
    private JobTransformer jobTransformer;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private FileService fileService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeJobService employeeJobService;

    @GetMapping("/jobs")
    public String jobs(Model model) {
        if (SecurityHelper.isEmployee()) {
            List<JobListView> recommendedJobs = new ArrayList<>();
            String employeeUsername = SecurityHelper.getUsername();

            List<Job> recommendedJobsList = jobService.getRecommendedJobsList(employeeUsername);

            for (Job job : recommendedJobsList) {
                String companyLogoPath = fileService.getFileAbsolutePath(storageService.load(job.getEmployer().getLogo().getFileName()));

                JobListView dto = new JobListView(job.getId(), job.getTitle(), companyLogoPath, job.getEmployer().getCompanyName(),
                        job.getPositionName(),
                        job.getDescription());
                recommendedJobs.add(dto);
            }

            List<JobListView> otherJobs = new ArrayList<>();

            for (Job job : jobService.findAllJob()) {
                String companyLogoPath = fileService.getFileAbsolutePath(storageService.load(job.getEmployer().getLogo().getFileName()));

                JobListView dto = new JobListView(job.getId(), job.getTitle(), companyLogoPath, job.getEmployer().getCompanyName(),
                        job.getPositionName(),
                        job.getDescription());
                otherJobs.add(dto);
            }

            model.addAttribute("otherjobs", otherJobs);
            model.addAttribute("recommendedJobs", recommendedJobs);
        }
        else if(SecurityHelper.isEmployer()){
            Employer employer = employerService.findEmployerByCredentialEmail(SecurityHelper.getUsername());
            Iterable<Job> activeJobs = jobService.findAllActiveJobsByEmployerId(employer.getId());
            List<JobListView> openJobsView = new ArrayList<>();
            for(Job job: activeJobs){
                String companyLogoPath = fileService.getFileAbsolutePath(storageService.load(job.getEmployer().getLogo().getFileName()));
                JobListView dto = new JobListView(job.getId(), job.getTitle(), companyLogoPath, job.getEmployer().getCompanyName(),
                        job.getPositionName(),
                        job.getDescription());
                openJobsView.add(dto);
            }
            model.addAttribute("openjobs", openJobsView);
        }

        return "jobs";
    }

    @GetMapping("/jobs/{category}")
    public String jobsByCategory(Model model, @PathVariable("category") String category) {
        List<JobListView> jobsByCategory = new ArrayList<>();
        List<JobListView> otherJobs = new ArrayList<>();

        for (Job job : jobService.findAllJob()) {
            String absoluteLogoPath = fileService.getFileAbsolutePath(storageService.load(job.getEmployer().getLogo().getFileName()));

            JobListView dto = new JobListView(job.getId(), job.getTitle(), absoluteLogoPath, job.getEmployer().getCompanyName(),
                    job.getPositionName(), job.getDescription());
            if (job.getJobCategory().name() == JobCategoryEnum.valueOf(category).name()) {
                jobsByCategory.add(dto);
            } else {
                otherJobs.add(dto);
            }
        }

        model.addAttribute("selectedCategory", JobCategoryEnum.valueOf(category).getCategory());
        model.addAttribute("jobsbycategory", jobsByCategory);
        model.addAttribute("otherjobs", otherJobs);

        return "jobs";
    }

    @GetMapping("/jobs/addJob")
    public String addJob(JobForm jobForm) {
        return "addJob";
    }

    @PostMapping("/jobs/addJob")
    public String addJob(@Valid JobForm jobForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "addJob";
        }

        Job job = jobTransformer.transform(jobForm);
        Employer emp = employerService.findEmployerByCredentialEmail(SecurityHelper.getUsername());
        job.setEmployer(emp);
        List<Job> empJobs = emp.getJobs();
        empJobs.add(job);
        emp.setJobs(empJobs);

        jobService.save(job);
        employerService.saveEmployer(emp.getId(), emp.getCredential().getEmail(), emp.getCredential().getPassword(), emp.getCompanyName(), emp.getLocation(), emp.getLogo(), emp.getJobs());

        LOGGER.info("Job [id: {}] successfully saved.", job.getId());
        redirectAttributes.addFlashAttribute("messageView", new MessageBoxView("message","Job operation","Job sucessfully saved."));

        return "redirect:/jobs";
    }

    @PostMapping("/jobs/applyJob")
    public String applyToJob(@RequestParam("jobId") Long jobId,RedirectAttributes redirectAttributes){
        Long employeeId = employeeService.findEmployeeByCredentialEmail(SecurityHelper.getUsername()).getId();
        Job job = jobService.findById(jobId);
        boolean isSuccessfulSave = employeeJobService.updateEmployeeJob(jobId,employeeId, JobStatusEnum.APPLIED);

        if(!isSuccessfulSave){
            redirectAttributes.addFlashAttribute("error", "You've already applied for this job");

            return "redirect:/job/details/"+jobId.toString();
        }

        redirectAttributes.addFlashAttribute("message", String.format("You've applied successfully to %s", job.getPositionName()));

        return "redirect:/jobs";
    }

    @PostMapping("/jobs/hireEmployee")
    public String hireEmployee(@RequestParam("jobId") Long jobId,@RequestParam("employeeId") Long employeeId, RedirectAttributes redirectAttributes){
        boolean isSuccessfulSave = employeeJobService.updateEmployeeJob(jobId,employeeId, JobStatusEnum.HIRED);

        if(!isSuccessfulSave){
            redirectAttributes.addFlashAttribute("messageView", new MessageBoxView("error","Applicants operation","Couldn't save your operation currently."));
        }

        redirectAttributes.addFlashAttribute("messageView", new MessageBoxView("message","Applicants operation","Successfully hired person."));

        return "redirect:/job/applicants/"+jobId.toString();
    }

    @PostMapping("/jobs/rejectEmployee")
    public String rejectEmployee(@RequestParam("jobId") Long jobId, @RequestParam("employeeId") Long employeeId,RedirectAttributes redirectAttributes){
        boolean isSuccessfulSave = employeeJobService.updateEmployeeJob(jobId,employeeId, JobStatusEnum.REJECTED);

        if(!isSuccessfulSave){
            redirectAttributes.addFlashAttribute("messageView", new MessageBoxView("error","Applicants operation","Couldn't save your operation currently."));
        }

        redirectAttributes.addFlashAttribute("messageView", new MessageBoxView("message","Applicants operation","Successfully rejected person."));

        return "redirect:/job/applicants/"+jobId.toString();
    }


    @ModelAttribute("getCurrentUserType")
    public String getCurrentUserTypeIsEmployer() {
        return SecurityHelper.isEmployer() ? "employer" : "employee";
    }

    @ModelAttribute("jobcategories")
    public Map<JobCategoryEnum, String> getJobCategories() {
        return JobCategoryEnum.toMap();
    }

    @ModelAttribute("categorystatistics")
    public Iterable<CategoryStatisticsView> CategoryStatistics() {
        List<CategoryStatisticsView> view = new ArrayList<>();

        for(Map.Entry<String,Long> entry : jobService.getCategoryStatistics().entrySet()){
            String ctId = entry.getKey();
            Long ctCount = entry.getValue();
            String ctName = JobCategoryEnum.valueOf(ctId).getCategory();
            CategoryStatisticsView dto = new CategoryStatisticsView(ctId,ctName,ctCount);
            view.add(dto);
        }

        return view;
    }
}
