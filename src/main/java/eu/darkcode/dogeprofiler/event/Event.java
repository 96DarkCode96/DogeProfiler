package eu.darkcode.dogeprofiler.event;

import eu.darkcode.dogeprofiler.sender.serializer.SendSerialize;
import org.jetbrains.annotations.NotNull;

/**
 * @author darkcode
 * @date 07.09.24
 **/
public interface Event {
    @SendSerialize
    @NotNull
    String getEventType();
}