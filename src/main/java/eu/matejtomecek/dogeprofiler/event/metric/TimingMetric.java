package eu.matejtomecek.dogeprofiler.event.metric;

import eu.matejtomecek.dogeprofiler.sender.serializer.SendSerialize;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
record TimingMetric(@NotNull String timingKey, @SendSerialize long total,
                    @SendSerialize List<TimingInstance.Split> splits) implements Metric {

    @Override
    public @NotNull String getMetricKey() {
        return "metric.timing." + timingKey;
    }

}
