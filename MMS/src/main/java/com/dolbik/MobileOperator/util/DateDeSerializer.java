package com.dolbik.MobileOperator.util;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeSerializer extends StdDeserializer<Date> {
    protected DateDeSerializer() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String value = jsonParser.readValueAs(String.class);
        try{
            return new SimpleDateFormat("dd.MM.yyyy").parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
