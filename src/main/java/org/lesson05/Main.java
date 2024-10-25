import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Создаем репозиторий пользователей
        UserRepository userRepository = new InMemoryUserRepository();

        // Добавляем пользователя в репозиторий
        User user = new User();
        user.setMsisdn("88005553535");
        user.setFirstName("Vasya");
        user.setLastName("Ivanov");
        userRepository.updateUserByMsisdn("88005553535", user);

        // Создаем сервис обогащения
        EnrichmentService enrichmentService = new EnrichmentService(userRepository);

        // Создаем сообщение для обогащения
        Map<String, String> input = new HashMap<>();
        input.put("action", "button_click");
        input.put("page", "book_card");
        input.put("msisdn", "88005553535");

        Message message = new Message();
        message.setContent(input);
        message.setEnrichmentType(Message.EnrichmentType.MSISDN);

        // Обогащаем сообщение
        Message enrichedMessage = enrichmentService.enrich(message);

        // Выводим обогащенное сообщение
        System.out.println("Enriched Message: " + enrichedMessage.getContent());
    }
}
