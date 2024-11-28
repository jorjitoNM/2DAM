package data.mappers;

import jakarta.inject.Inject;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public LocalDate unmarshal(String v) {
        return LocalDate.parse(v,df);
    }

    public String marshal(LocalDate v)  {
        return v.format(df);
    }
}
