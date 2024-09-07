package eu.darkcode.dogeprofiler;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author darkcode
 * @date 20.07.24
 **/
@AllArgsConstructor
public class Report implements Event {

    private final @NotNull DogeProfiler dogeProfiler;
    private final @NotNull ExceptionWrapper exception;
    private final @NotNull NotifyState notifyState;
    private final @NotNull Thread thread;

    public Report(@NotNull DogeProfiler dogeProfiler, @NotNull Throwable exception) {
        this(dogeProfiler, exception, new NotifyState(NotifyState.NotifyType.HANDLED_EXCEPTION, Severity.ERROR));
    }

    public Report(@NotNull DogeProfiler dogeProfiler, @NotNull Throwable exception, @NotNull NotifyState notifyState) {
        this(dogeProfiler, exception, notifyState, Thread.currentThread());
    }

    public Report(@NotNull DogeProfiler dogeProfiler, @NotNull Throwable exception, @NotNull NotifyState notifyState, @NotNull Thread thread) {
        this(dogeProfiler, new ExceptionWrapper(dogeProfiler.getConfiguration(), exception), notifyState, thread);
    }

    @SendSerialize
    @NotNull
    public String getSeverity() {
        return notifyState.getSeverity().getValue();
    }

    @SendSerialize
    public List<ExceptionWrapper> getExceptions() {
        List<ExceptionWrapper> exceptions = new ArrayList<>();
        exceptions.add(exception);

        Throwable currentThrowable = exception.getThrowable().getCause();
        while (currentThrowable != null) {
            exceptions.add(new ExceptionWrapper(dogeProfiler.getConfiguration(), currentThrowable));
            currentThrowable = currentThrowable.getCause();
        }

        return exceptions;
    }

    public void addAppInfo(String key, Object data) {

    }

    public void addDeviceInfo(String key, Object data) {

    }

    @Override
    public @NotNull String getEventType() {
        return "error";
    }
}