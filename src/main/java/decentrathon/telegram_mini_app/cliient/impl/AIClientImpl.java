package decentrathon.telegram_mini_app.cliient.impl;

import decentrathon.telegram_mini_app.cliient.AIClient;
import decentrathon.telegram_mini_app.dto.ai.ModelMessageRequest;
import decentrathon.telegram_mini_app.dto.ai.ModelMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Slf4j
public class AIClientImpl implements AIClient {

    @Value("${openai.model}")
    private String AIModel;

    private final RestClient restClient;

    @Override
    public ModelMessageResponse getShortDescription(String theoryDetails) {
        try {
            return restClient
                    .post()
                    .uri("/chat/completions")
                    .body(new ModelMessageRequest(AIModel, theoryDetails))
                    .retrieve()
                    .body(ModelMessageResponse.class);
        } catch (Exception e) {
            log.error("Ошибка при запросе к OpenAI", e);
            throw e;
        }
    }
}
