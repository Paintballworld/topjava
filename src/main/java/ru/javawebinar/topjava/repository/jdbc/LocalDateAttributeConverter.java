package ru.javawebinar.topjava.repository.jdbc;

import org.hsqldb.types.TimestampData;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by yerlan on 16/01/17.
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDateTime, TimestampData>{

    @Override
    public TimestampData convertToDatabaseColumn(LocalDateTime attribute) {
        Timestamp timestamp = Timestamp.valueOf(attribute);
        return new TimestampData(timestamp.getSeconds(), timestamp.getNanos());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(TimestampData dbData) {
        return LocalDateTime.ofEpochSecond(dbData.getSeconds(), dbData.getNanos(), ZoneOffset.ofTotalSeconds(dbData.getZone()));
    }
}
