package decentrathon.telegram_mini_app.config;

import decentrathon.telegram_mini_app.client.impl.AIClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public AIClientImpl getAIClient(
            @Value("${openai.url}") String baseAIConnectUrl,
            @Value("${openai.token}") String userAIToken
    ) {
        return new AIClientImpl(RestClient.builder()
                .baseUrl(baseAIConnectUrl)
                .defaultHeader("Authorization", "Bearer " + userAIToken)
                .defaultHeader("Content-Type", "application/json")
                .build());
    }

}
