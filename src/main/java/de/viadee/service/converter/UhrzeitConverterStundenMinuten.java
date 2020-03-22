package de.viadee.service.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Converter(autoApply = true)
public final class UhrzeitConverterStundenMinuten implements AttributeConverter<LocalTime, String> {

    private static final Pattern PATTERN = Pattern.compile("(?<stunden>\\d{1,2}):(?<minuten>\\d{2})");

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public final String convertToDatabaseColumn(final LocalTime attribute) {
        return attribute == null ? null : attribute.format(FORMATTER);
    }

    @Override
    public final LocalTime convertToEntityAttribute(final String dbValue) {
        if (dbValue == null || dbValue.length() == 0) {
            return null;
        }
        final Matcher matcher = PATTERN.matcher(dbValue);
        if (matcher.matches()) {
            try {
                return LocalTime
                    .of(Integer.valueOf(matcher.group("stunden")), Integer.valueOf(matcher.group("minuten")));
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("'" + dbValue + "' kann nicht in eine Uhrzeit konvertiert werden.");
            }
        } else {
            throw new IllegalArgumentException("'" + dbValue + "' kann nicht in eine Uhrzeit konvertiert werden.");
        }
    }
}