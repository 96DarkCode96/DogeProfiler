package eu.darkcode.dogeprofiler.event.metric;

import eu.darkcode.dogeprofiler.event.Event;
import eu.darkcode.dogeprofiler.sender.serializer.SendSerialize;
import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
public interface Metric extends Event {

    static @NotNull Metric timing(@NotNull String timingKey, Long timeRun) {
        return new TimingMetric(timingKey, timeRun);
    }

    @SendSerialize
    @NotNull
    String getMetricKey();

    @Override
    default @NotNull String getEventType() {
        return "metric";
    }
}