package org.example.Article;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Comment.CommentService;
import spark.Request;
import spark.Response;
import spark.Service;

public class ArticleController implements Controller {

    private final Service service;
    private final ArticleService articleService;
    private final CommentService commentService;
    private final ObjectMapper objectMapper;
    private final ArticleRepository articleRepository; // Объявление переменной

    public ArticleController(Service service, ArticleService articleService, CommentService commentService, ObjectMapper objectMapper, ArticleRepository articleRepository) {
        this.service = service;
        this.articleService = articleService;
        this.commentService = commentService;
        this.objectMapper = objectMapper;
        this.articleRepository = articleRepository; // Инициализация
    }

    @Override
    public void initializeEndpoints() {
        createArticle();
        getArticle();
        getAllArticles();
        deleteArticle();
        updateArticle();
    }

    private void createMultipleArticles() {
        service.post(
                "/api/articles/multiple",
                (Request request, Response response) -> {
                    response.type("application/json");
                    List<ArticleCreateRequest> articleRequests = objectMapper.readValue(request.body(),
                            new TypeReference<List<ArticleCreateRequest>>() {
                            });
                    List<Article> articles = new ArrayList<>();
                    for (ArticleCreateRequest req : articleRequests) {
                        articles.add(new Article(articleRepository.generateId(), req.title(), req.tags(), new ArrayList<>()));
                    }
                    articleRepository.createMultiple(articles);
                    response.status(201);
                    return objectMapper.writeValueAsString(articles);
                }
        );
    }

    private void createArticle() {
        service.post(
                "/api/articles",
                (Request request, Response response) -> {
                    response.type("application/json");
                    String body = request.body();
                    ArticleCreateRequest articleCreateRequest = objectMapper.readValue(body,
                            ArticleCreateRequest.class);
                    try {
                        ArticleId articleId = articleService.create(articleCreateRequest.title(),
                                articleCreateRequest.tags());
                        LOG.debug("Article created: {}", articleId);
                        response.status(200);
                        return objectMapper.writeValueAsString(new ArticleCreateResponse(articleId));
                    } catch (ArticleCreateException e) {
                        LOG.warn("Cannot create article", e);
                        response.status(400);
                        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                    }
                }
        );
    }

    private void getArticle() {
        service.get(
                "/api/articles/:articleId",
                (Request request, Response response) -> {
                    response.type("application/json");
                    ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
                    Article article;

                    try {
                        article = articleService.findById(articleId);
                    } catch (ArticleFindException e) {
                        LOG.warn("Article not found", e);
                        response.status(404);
                        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                    }

                    LOG.debug("Article found: {}", articleId);
                    response.status(200);
                    return objectMapper.writeValueAsString(new ArticleFindResponse(
                            articleId.toString(),
                            article.getTitle(),
                            article.getTags(),
                            article.getComments()
                    ));
                }
        );
    }

    private void getAllArticles() {
        service.get(
                "/api/all-articles",
                (Request request, Response response) -> {
                    response.type("application/json");
                    List<Article> articles = articleService.findAll();
                    Map<String, ArticleAllFindResponse> articlesMap = new HashMap<>();

                    for (Article article : articles) {
                        articlesMap.put(article.getId().toString(), new ArticleAllFindResponse(
                                article.getTitle(),
                                article.getTags(),
                                article.getComments()
                        ));
                    }

                    LOG.debug("Successfully retrieved all articles");
                    response.status(200);
                    return objectMapper.writeValueAsString(articlesMap);
                }
        );
    }

    private void deleteArticle() {
        service.delete(
                "/api/delete-article/:articleId",
                (Request request, Response response) -> {
                    response.type("application/json");
                    ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
                    Article article;

                    try {
                        article = articleService.findById(articleId);
                    } catch (ArticleFindException e) {
                        LOG.warn("Article not found", e);
                        response.status(404);
                        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                    }

                    articleService.delete(articleId);
                    LOG.debug("Article deleted: {}", articleId);
                    response.status(200);
                    return objectMapper.writeValueAsString(new ArticleDeleteResponse(articleId));
                }
        );
    }

    private void updateArticle() {
        service.put(
                "api/update-article/:articleId",
                (Request request, Response response) -> {
                    response.type("application/json");
                    String body = request.body();
                    ArticleUpdateRequest articleUpdateRequest = objectMapper.readValue(body, ArticleUpdateRequest.class);

                    ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
                    Article article;

                    try {
                        article = articleService.findById(articleId);
                    } catch (ArticleFindException e) {
                        LOG.warn("Article not found", e);
                        response.status(404);
                        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                    }

                    articleService.update(articleId, articleUpdateRequest.title(), articleUpdateRequest.tags());
                    LOG.debug("Article updated: {}", articleId);
                    response.status(200);
                    return objectMapper.writeValueAsString(new ArticleUpdateResponse(articleId));
                }
        );
    }
}
