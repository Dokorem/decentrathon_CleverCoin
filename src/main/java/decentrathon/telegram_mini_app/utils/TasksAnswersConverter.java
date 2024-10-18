package decentrathon.telegram_mini_app.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TasksAnswersConverter implements AttributeConverter<String[], String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(String[] taskAnswers) {
        if(taskAnswers == null || taskAnswers.length == 0) {
            return null;
        }
        return String.join(SEPARATOR, taskAnswers);
    }

    @Override
    public String[] convertToEntityAttribute(String taskAnswers) {
        if(taskAnswers == null || taskAnswers.isEmpty()) {
            return null;
        }
        return taskAnswers.split(SEPARATOR);
    }
}
