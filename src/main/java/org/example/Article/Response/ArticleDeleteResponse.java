package org.example.Article.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.Article.ArticleId;

public record ArticleDeleteResponse(ArticleId articleId) {
  @JsonProperty
  public String getArticleId() {
    return articleId.toString();
  }
}
