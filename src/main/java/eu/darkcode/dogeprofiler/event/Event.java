package eu.darkcode.dogeprofiler.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.darkcode.dogeprofiler.sender.serializer.SendSerialize;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

/**
 * @author darkcode
 * @date 07.09.24
 **/
public interface Event {
    @SendSerialize
    @NotNull
    String getEventType();

    @SendSerialize
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    default Date getTime() {
        return Calendar.getInstance().getTime();
    }
}