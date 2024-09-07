package eu.darkcode.dogeprofiler.metric;

import eu.darkcode.dogeprofiler.Event;
import eu.darkcode.dogeprofiler.SendSerialize;
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