package borisov.project.SpringNewsBot.service;

import java.util.ArrayList;
import java.util.Date;

public class News {
    public static class Article{
        public Source source;
        public Object author;
        public String title;
        public String description;
        public String url;
        public String urlToImage;
        public Date publishedAt;
        public String content;

        public Article(Source source, Object author, String title, String description, String url, String urlToImage, Date publishedAt, String content) {
            this.source = source;
            this.author = author;
            this.title = title;
            this.description = description;
            this.url = url;
            this.urlToImage = urlToImage;
            this.publishedAt = publishedAt;
            this.content = content;
        }

        public Article() {

        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public Object getAuthor() {
            return author;
        }

        public void setAuthor(Object author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public Date getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(Date publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class Root{
        public String status;
        public int totalResults;
        public ArrayList<Article> articles;

        public Root(String status, int totalResults, ArrayList<Article> articles) {
            this.status = status;
            this.totalResults = totalResults;
            this.articles = articles;
        }

        public Root() {
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public ArrayList<Article> getArticles() {
            return articles;
        }

        public void setArticles(ArrayList<Article> articles) {
            this.articles = articles;
        }
    }

    public static class Source{
        public String id;
        public String name;

        public Source(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Source() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
