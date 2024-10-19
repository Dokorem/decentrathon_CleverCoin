package decentrathon.telegram_mini_app.dto;


public record TaskProgressDTO (String chatId, Integer taskId, Boolean correctAnswer) {

}
