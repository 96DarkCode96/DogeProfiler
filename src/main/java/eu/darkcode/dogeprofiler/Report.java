package eu.darkcode.dogeprofiler;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author darkcode
 * @date 20.07.24
 **/
@Getter
@AllArgsConstructor
public class Report {

    private final @NotNull DogeProfiler dogeProfiler;
    @Expose
    private final @NotNull Throwable exception;
    @Expose
    private final @NotNull NotifyState notifyState;
    private final @NotNull Thread thread;

    public Report(@NotNull DogeProfiler dogeProfiler, @NotNull Throwable exception) {
        this(dogeProfiler, exception, new NotifyState(NotifyState.NotifyType.HANDLED_EXCEPTION, Severity.ERROR));
    }

    public Report(@NotNull DogeProfiler dogeProfiler, @NotNull Throwable exception, @NotNull NotifyState notifyState) {
        this(dogeProfiler, exception, notifyState, Thread.currentThread());
    }

    @NotNull
    public Severity getSeverity() {
        return getNotifyState().getSeverity();
    }

    @Override
    public String toString() {
        return "Report{" +
                "dogeProfiler=" + dogeProfiler +
                ", exception=" + exception +
                ", notifyState=" + notifyState +
                ", thread=" + thread +
                '}';
    }

    public void addAppInfo(String key, Object data) {

    }

    public void addDeviceInfo(String key, Object data) {

    }
}