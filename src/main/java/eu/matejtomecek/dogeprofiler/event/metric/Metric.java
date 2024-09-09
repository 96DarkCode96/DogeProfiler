package eu.matejtomecek.dogeprofiler.event.metric;

import eu.matejtomecek.dogeprofiler.event.Event;
import eu.matejtomecek.dogeprofiler.sender.serializer.SendSerialize;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
public interface Metric extends Event {

    static @NotNull Metric timing(@NotNull String timingKey, long total, @NotNull List<TimingInstance.Split> splits) {
        return new TimingMetric(timingKey, total, splits);
    }

    @SendSerialize
    @NotNull
    String getMetricKey();

    @Override
    default @NotNull String getEventType() {
        return "metric";
    }
}