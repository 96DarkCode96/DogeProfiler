package eu.darkcode.dogeprofiler.event.metric;

import eu.darkcode.dogeprofiler.DogeProfiler;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author darkcode
 * @date 24.08.24
 **/
@SuppressWarnings("unused")
public final class TimingInstance {

    private final @NotNull DogeProfiler dogeProfiler;
    @Getter
    private final @NotNull String timingKey;
    private final long start;
    private Long timeRun;

    public TimingInstance(@NotNull DogeProfiler dogeProfiler, @NotNull String timingKey, long start) {
        this.dogeProfiler = dogeProfiler;
        this.timingKey = timingKey;
        this.start = start;
    }

    public long end() {
        if (timeRun == null) {
            timeRun = System.currentTimeMillis() - this.start;
            activateThreshold();
        }
        return timeRun;
    }

    private void activateThreshold() {
        dogeProfiler.sendMetric(Metric.timing(timingKey, Objects.requireNonNull(timeRun, "Please do not use reflection!")));
    }

}
