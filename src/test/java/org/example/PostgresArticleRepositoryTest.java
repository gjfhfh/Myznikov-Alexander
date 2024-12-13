package org.example.Article;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostgresArticleRepositoryTest {

    private PostgreSQLContainer<?> postgresContainer;
    private PostgresArticleRepository repository;

    @BeforeEach
    void setUp() {
        // Запуск контейнера PostgreSQL
        postgresContainer = new PostgreSQLContainer<>("postgres:latest");
        postgresContainer.start();

        // Инициализация репозитория с параметрами подключения к контейнеру
        repository = new PostgresArticleRepository(
                postgresContainer.getJdbcUrl(),
                postgresContainer.getUsername(),
                postgresContainer.getPassword()
        );

        // Здесь можно добавить миграции, если это необходимо
    }

    @AfterEach
    void tearDown() {
        // Остановка контейнера после тестов
        postgresContainer.stop();
    }

    @Test
    void testCreateAndFindArticle() {
        // Создание статьи
        Article article = new Article(new ArticleId(1), "Test Article", Set.of("tag1", "tag2"), new ArrayList<>());
        repository.create(article);

        // Поиск статьи
        Article foundArticle = repository.findById(article.getId());
        assertEquals(article.getTitle(), foundArticle.getTitle());
        assertEquals(article.getTags(), foundArticle.getTags());
    }
}
