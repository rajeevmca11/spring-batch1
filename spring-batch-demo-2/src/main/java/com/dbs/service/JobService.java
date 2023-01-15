package com.dbs.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    JobLauncher jobLauncher;

    @Qualifier("firstJob")
    @Autowired
    Job firstJob;

    @Qualifier("secondJob")
    @Autowired
    Job secondJob;
    @Async
    public void startJob(String jobName){
        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(params);

        try {
            JobExecution jobExecution=null;
            if (jobName.equals("firstJob")) {
                jobExecution = jobLauncher.run(firstJob, parameters);
            } else if (jobName.equals("secondJob")) {
                jobExecution = jobLauncher.run(secondJob, parameters);
            }
            System.out.println("Job Execution ID = " + jobExecution.getId());
        }
        catch(Exception ex){
            System.out.println("Exception while starting the job: "+ex);
        }
    }
}
