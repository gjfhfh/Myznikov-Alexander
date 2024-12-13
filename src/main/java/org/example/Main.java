package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Article.ArticleController;
import org.example.Article.ArticleService;
import org.example.Article.InMemoryArticleRepository;
import org.example.Comment.CommentController;
import org.example.Comment.CommentService;
import org.example.Comment.InMemoryCommentRepository;
import spark.Service;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Service service = Service.ignite();
        ObjectMapper objectMapper = new ObjectMapper();

        // Инициализация репозиториев
        InMemoryArticleRepository inMemoryArticleRepository = new InMemoryArticleRepository();
        InMemoryCommentRepository inMemoryCommentRepository = new InMemoryCommentRepository();

        // Инициализация сервисов
        ArticleService articleService = new ArticleService(inMemoryArticleRepository, inMemoryCommentRepository);
        CommentService commentService = new CommentService(inMemoryCommentRepository, inMemoryArticleRepository);

        // Инициализация контроллеров
        ArticleController articleController = new ArticleController(
                service,
                articleService,
                commentService,
                objectMapper,
        );

        CommentController commentController = new CommentController(
                service,
                articleService,
                commentService,
                objectMapper
        );

        // Запуск приложения
        Application application = new Application(
                List.of(articleController, commentController)
        );
        application.start();
    }
}
