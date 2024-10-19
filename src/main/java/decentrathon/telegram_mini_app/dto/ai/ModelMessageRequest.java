package decentrathon.telegram_mini_app.dto.ai;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModelMessageRequest {

    private String model;
    private List<ChatMessage> messages;

    public ModelMessageRequest(String model, String message) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new ChatMessage("user", message));
    }
}
