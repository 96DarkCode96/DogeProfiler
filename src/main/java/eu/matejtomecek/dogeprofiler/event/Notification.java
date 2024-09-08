package eu.matejtomecek.dogeprofiler.event;

import eu.matejtomecek.dogeprofiler.sender.serializer.SendSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * @author darkcode
 * @date 06.09.24
 **/
public class Notification {

    private final List<Event> events = new ArrayList<>();

    @SendSerialize
    public List<Event> getEvents() {
        return events;
    }

}