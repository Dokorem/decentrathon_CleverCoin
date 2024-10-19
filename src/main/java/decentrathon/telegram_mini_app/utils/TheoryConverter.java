package decentrathon.telegram_mini_app.utils;

import decentrathon.telegram_mini_app.dto.TheoryDTO;
import decentrathon.telegram_mini_app.entity.Theory;

public class TheoryConverter {
    public static TheoryDTO toDto(Theory theory) {
        return TheoryDTO.builder()
                .themeId(theory.getTheme().getId())
                .title(theory.getTitle())
                .content(theory.getContent())
                .difficult(theory.getDifficult())
                .themeName(theory.getTheme().getThemeName())
                .build();
    }
}
