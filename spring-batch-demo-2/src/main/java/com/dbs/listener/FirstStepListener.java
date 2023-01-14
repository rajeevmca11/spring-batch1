package com.dbs.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
    System.out.println("before Step"+stepExecution.getStepName());
    System.out.println("Job execution context"+stepExecution.getJobExecution().getExecutionContext());
    System.out.println("Step execution context"+stepExecution.getExecutionContext());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("after Step"+stepExecution.getStepName());
        System.out.println("Job execution context"+stepExecution.getJobExecution().getExecutionContext());
        System.out.println("Step execution context"+stepExecution.getExecutionContext());
        return  null;
    }
}
