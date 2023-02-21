package hu.elte.joooble.transformer;

import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.webdomain.JobForm;
import org.springframework.stereotype.Component;

@Component
public class JobTransformer {
    public Job transform(JobForm form){
        Job job = new Job();
        job.setTitle(form.getTitle());
        job.setLocation(form.getLocation());
        job.setDescription(form.getDescription());
        job.setJobCategory(form.getJobCategory());
        job.setSalary(form.getSalary());
        job.setWorkingHours(form.getWorkingHours());
        job.setPositionName(form.getPositionName());
        return job;
    }
}
