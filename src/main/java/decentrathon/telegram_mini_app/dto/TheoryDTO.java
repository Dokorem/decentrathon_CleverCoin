package decentrathon.telegram_mini_app.dto;

import decentrathon.telegram_mini_app.entity.Theme;

import java.io.Serializable;

public record TheoryDTO(String title, String content, int difficult, String themeName) {
}
