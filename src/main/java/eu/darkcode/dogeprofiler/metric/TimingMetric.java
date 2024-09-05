package eu.darkcode.dogeprofiler.metric;

import eu.darkcode.dogeprofiler.TimingKey;
import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
record TimingMetric(@NotNull TimingKey timingKey, long timeRun) implements Metric {

    @Override
    public @NotNull String getKey() {
        return "metric.timing";
    }

}
