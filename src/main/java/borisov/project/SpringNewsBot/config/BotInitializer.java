package borisov.project.SpringNewsBot.config;

import borisov.project.SpringNewsBot.service.SendNewsJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {

    @Autowired
    LongPollingBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException{
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(bot);

            /* Instantiate the job that will call the bot function */
            JobDetail jobSendNews = JobBuilder.newJob(SendNewsJob.class)
                    .withIdentity("sendAd")
                    .build();

            /* Define a trigger for the call */
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("everyMorningAt8")
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(8, 0))
                    .build();

            /* Create a scheduler to manage triggers */
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.getContext().put("bot", bot);
            scheduler.start();
            scheduler.scheduleJob(jobSendNews, trigger);
        }
        catch (TelegramApiException ignored) {

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
