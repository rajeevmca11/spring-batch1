package com.dbs.config;

import com.dbs.listener.FirstJobListener;
import com.dbs.listener.FirstStepListener;
import com.dbs.processor.FirstItemProcessor;
import com.dbs.reader.FirstItemReader;
import com.dbs.service.SecondTasklate;
import com.dbs.writer.FirstItemWritter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    SecondTasklate secondTasklate;

    @Autowired
    FirstJobListener firstJobListener;

    @Autowired
    FirstStepListener firstStepListener;

    @Autowired
    FirstItemReader firstItemReader;

    @Autowired
    FirstItemProcessor firstItemProcessor;
    @Autowired
    FirstItemWritter firstItemWritter;



    @Bean
    public Job firstJob(){
        return jobBuilderFactory.get("firstJob")
                .incrementer(new RunIdIncrementer())
                .start(firstStep())
                .next(secondStep())
                .listener(firstJobListener)
                .build();
    }



    @Bean
    public Step firstStep(){
    return stepBuilderFactory.get("firstStep")
            .tasklet(firstTask())
            .listener(firstStepListener)
            .build();
    }

    @Bean
    public Step secondStep(){
        return stepBuilderFactory.get("secondstep")
                .tasklet(secondTasklate)
                .build();
    }

    private Tasklet firstTask(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
               System.out.println("first task");
               return RepeatStatus.FINISHED;
            }
        };
    }

    /*private Tasklet secondTask(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("second task");
                return RepeatStatus.FINISHED;
            }
        };
    }*/
    @Bean
    public Job secondJob(){
        return jobBuilderFactory.get("secondJob")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .next(secondStep())
                .build();
    }
    public Step firstChunkStep(){
        return stepBuilderFactory.get("First Chunk Step").<Integer, Long>chunk(3)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWritter)
                .build();
    }
}
