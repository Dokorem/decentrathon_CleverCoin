package decentrathon.telegram_mini_app.cliient;

import decentrathon.telegram_mini_app.dto.ai.ModelMessageResponse;

public interface AIClient {

    ModelMessageResponse getShortDescription(String theoryDetails);

}
