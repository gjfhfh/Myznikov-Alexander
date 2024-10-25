import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrichmentServiceTest {
    private EnrichmentService enrichmentService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new InMemoryUserRepository();
        userRepository.updateUserByMsisdn("88005553535", new User("88005553535", "Vasya", "Ivanov"));
        enrichmentService = new EnrichmentService(userRepository);
    }

    @Test
    public void testEnrichment() {
        Map<String, String> input = new HashMap<>();
        input.put("action", "button_click");
        input.put("page", "book_card");
        input.put("msisdn", "88005553535");

        Message message = new Message(input, Message.EnrichmentType.MSISDN);
        Message enrichedMessage = enrichmentService.enrich(message);

        Map<String, String> enrichedContent = enrichedMessage.getContent();
        assertEquals("Vasya", enrichedContent.get("firstName"));
        assertEquals("Ivanov", enrichedContent.get("lastName"));
    }
}
