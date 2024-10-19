package decentrathon.telegram_mini_app.dto;

import decentrathon.telegram_mini_app.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Builder
public record TheoryDTO(int themeId, String title, String content, int difficult, String themeName) {
}
