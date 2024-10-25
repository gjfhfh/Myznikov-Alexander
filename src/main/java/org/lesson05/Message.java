import java.util.Map;

public class Message {
    private Map<String, String> content;
    private EnrichmentType enrichmentType;

    public enum EnrichmentType {
        MSISDN
    }

    // Конструкторы, геттеры и сеттеры
    public Message(Map<String, String> content, EnrichmentType enrichmentType) {
        this.content = content;
        this.enrichmentType = enrichmentType;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public EnrichmentType getEnrichmentType() {
        return enrichmentType;
    }

    public void setEnrichmentType(EnrichmentType enrichmentType) {
        this.enrichmentType = enrichmentType;
    }
}
