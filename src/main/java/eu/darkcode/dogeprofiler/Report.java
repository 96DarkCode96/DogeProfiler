package eu.darkcode.dogeprofiler;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final @NotNull Map<String, Object> appInfo = new HashMap<>();
    private final @NotNull Map<String, Object> deviceInfo = new HashMap<>();
    private final @NotNull Map<String, Object> metaData = new HashMap<>();

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
    public String getThreadName() {
        return thread.getName();
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
        appInfo.put(key, data);
    }

    public void addDeviceInfo(String key, Object data) {
        deviceInfo.put(key, data);
    }

    public void addMetaData(String key, Object data) {
        metaData.put(key, data);
    }

    @SendSerialize
    public @NotNull Map<String, Object> getAppInfo() {
        return appInfo;
    }

    @SendSerialize
    public @NotNull Map<String, Object> getDeviceInfo() {
        return deviceInfo;
    }

    @SendSerialize
    public @NotNull Map<String, Object> getMetaData() {
        return metaData;
    }

    @Override
    public @NotNull String getEventType() {
        return "error";
    }
}