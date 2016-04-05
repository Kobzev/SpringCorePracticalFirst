package ua.kobzev.theatre.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by kkobziev on 4/5/16.
 */
public class LocalDateTimeAdapter extends XmlAdapter<Date, LocalDateTime>
{
    @Override
    public LocalDateTime unmarshal(Date v) throws Exception {
        return LocalDateTime.ofInstant(v.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public Date marshal(LocalDateTime v) throws Exception {
        return Date.from(v.atZone(ZoneId.systemDefault()).toInstant());
    }
}
