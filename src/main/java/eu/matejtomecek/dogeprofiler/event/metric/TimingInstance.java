package eu.matejtomecek.dogeprofiler.event.metric;

import eu.matejtomecek.dogeprofiler.DogeProfiler;
import eu.matejtomecek.dogeprofiler.sender.serializer.SendSerialize;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    private final List<Split> splits = new ArrayList<>();
    private long lastSplit;
    private boolean ended = false;

    public TimingInstance(@NotNull DogeProfiler dogeProfiler, @NotNull String timingKey) {
        this.dogeProfiler = dogeProfiler;
        this.timingKey = timingKey;
        this.start = System.nanoTime();
        this.lastSplit = this.start;
    }

    public void addSplit(@NotNull String splitLabel) {
        if (ended)
            return;
        long current = System.nanoTime();
        splits.add(new Split(splitLabel, current - this.lastSplit));
        this.lastSplit = current;
    }

    public void end() {
        if (ended)
            return;
        this.ended = true;
        long total = System.nanoTime() - this.start;
        dogeProfiler.sendMetric(Metric.timing(timingKey, total, splits));
    }

    public record Split(@SendSerialize @NotNull String splitLabel, @SendSerialize long took) {
    }

}
