import org.flywaydb.core.Flyway;
import org.example.Article.Article;
import org.example.Article.ArticleId;
import org.example.Article.ArticleRepository;

import java.sql.*;
import java.util.List;

public class PostgresArticleRepository implements ArticleRepository {
    private final String url;
    private final String user;
    private final String password;

    public PostgresArticleRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        // Initialize Flyway
        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();
    }

    public void createMultiple(List<Article> articles) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO articles (title, tags, trending) VALUES (?, ?, ?)")) {
                for (Article article : articles) {
                    stmt.setString(1, article.getTitle());
                    stmt.setArray(2, connection.createArrayOf("text", article.getTags().toArray()));
                    stmt.setBoolean(3, article.isTrending());
                    stmt.addBatch();
                }
                stmt.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Failed to create articles", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    public ArticleId generateId() {
        // Implement ID generation logic
    }

    @Override
    public List<Article> findAll() {
        // Implement find all articles logic
    }

    @Override
    public Article findById(ArticleId id) {
        // Implement find article by ID logic
    }

    @Override
    public void create(Article article) {
        // Implement create article logic
    }

    @Override
    public void update(Article article) {
        // Implement update article logic
    }

    @Override
    public void delete(ArticleId id) {
        // Implement delete article logic
    }
}
