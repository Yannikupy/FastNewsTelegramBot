package borisov.project.SpringNewsBot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@Configuration("application.properties")
public class Controller {

    @GetMapping("/getNews")
    public ArrayList<News.Article> getNews(String apiKey) throws JsonProcessingException {
        String url = "https://newsapi.org/v2/top-headlines?country=ru&apiKey=" + apiKey ;
        RestTemplate restTemplate = new RestTemplate();
        String jsonResult = restTemplate.getForObject(url, String.class);
        ObjectMapper om = new ObjectMapper();
        News.Root root = om.readValue(jsonResult, News.Root.class);
        return root.articles;
    }
}
