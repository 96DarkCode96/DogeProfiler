package eu.darkcode.dogeprofiler.metric;

import eu.darkcode.dogeprofiler.TimingKey;
import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
public interface Metric {

    static @NotNull Metric timing(@NotNull TimingKey timingKey, Long timeRun) {
        return new TimingMetric(timingKey, timeRun);
    }

    @NotNull
    String getKey();
    
}