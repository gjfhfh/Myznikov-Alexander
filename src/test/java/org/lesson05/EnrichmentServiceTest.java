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
        enrichmentService = new EnrichmentService(userRepository);

        User user = new User();
        user.setMsisdn("88005553535");
        user.setFirstName("Vasya");
        user.setLastName("Ivanov");
        userRepository.updateUserByMsisdn("88005553535", user);
    }

    @Test
    public void testEnrichment() {
        Map<String, String> input = new HashMap<>();
        input.put("action", "button_click");
        input.put("page", "book_card");
        input.put("msisdn", "88005553535");

        Message message = new Message();
        message.setContent(input);
        message.setEnrichmentType(Message.EnrichmentType.MSISDN);

        Message enrichedMessage = enrichmentService.enrich(message);

        Map<String, String> enrichedContent = enrichedMessage.getContent();
        assertEquals("Vasya", enrichedContent.get("firstName"));
        assertEquals("Ivanov", enrichedContent.get("lastName"));
    }
}
