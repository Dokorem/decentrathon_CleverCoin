package decentrathon.telegram_mini_app.dto;

import decentrathon.telegram_mini_app.entity.Theme;

public record TaskDTO(String question, String[] answers, String correctAnswer, int difficult, String themeName) {
}
