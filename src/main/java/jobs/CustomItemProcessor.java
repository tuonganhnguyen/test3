package jobs;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomItemProcessor implements Tasklet {
 
    private String message;
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        for(int i= 0; i < 1000000; i++)
        {
            System.out.println("Test:" + arg1.getStepContext().getJobParameters().toString());
        }
        
        
        return RepeatStatus.FINISHED;
    }
}

