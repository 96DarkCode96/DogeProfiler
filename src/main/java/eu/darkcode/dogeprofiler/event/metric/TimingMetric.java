package eu.darkcode.dogeprofiler.event.metric;

import eu.darkcode.dogeprofiler.sender.serializer.SendSerialize;
import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
record TimingMetric(@NotNull String timingKey, @SendSerialize long timeRun) implements Metric {

    @Override
    public @NotNull String getMetricKey() {
        return "metric.timing." + timingKey;
    }

}
