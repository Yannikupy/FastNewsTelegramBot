package borisov.project.SpringNewsBot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.quartz.*;

public class SendNewsJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        SchedulerContext schedulerContext;
        try {
            schedulerContext = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        NewsBot bot = (NewsBot) schedulerContext.get("bot");
        try {
            bot.sendNews();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
