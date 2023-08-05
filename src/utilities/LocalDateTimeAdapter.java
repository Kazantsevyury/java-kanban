package utilities;


import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        String formattedDateTime = localDateTime.format(formatter);
        jsonWriter.value(formattedDateTime);
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        String dateTimeString = jsonReader.nextString();
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}

