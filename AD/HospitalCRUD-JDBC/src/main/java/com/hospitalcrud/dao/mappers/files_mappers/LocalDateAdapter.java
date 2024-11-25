package com.hospitalcrud.dao.mappers.files_mappers;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public LocalDate unmarshal(String v) {
        return LocalDate.parse(v,df);
    }

    public String marshal(LocalDate v)  {
        return v.format(df);
    }
}
