package borisov.project.SpringNewsBot.service;

import borisov.project.SpringNewsBot.config.BotConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class NewsBot extends TelegramLongPollingBot {

    final BotConfig config;
    @Value("${api.key}")
    private String apiKey;

    Set<Long> chatIds = new HashSet<>();

    public NewsBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()) {
            chatIds.add(update.getMessage().getChatId());
        }

    }

    private void startCommandReceived(long chatId, String name) {

        String answer = "Hi, " + name +", nice to meet you!";
        sendMessage(chatId, answer);

    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try{
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendNews() throws JsonProcessingException {
        Controller controller = new Controller();
        ArrayList<News.Article> news = null;
        try {
            news = controller.getNews(apiKey);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder message = new StringBuilder();
        for(News.Article article : news) {
            message.append(article.title + "\n");
        }
        for(long id : chatIds) {
            sendMessage(id, message.toString());
        }
    }
}
