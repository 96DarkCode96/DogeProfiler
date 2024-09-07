package eu.darkcode.dogeprofiler.event.report;

import eu.darkcode.dogeprofiler.DogeProfiler;
import eu.darkcode.dogeprofiler.sender.serializer.SendSerialize;
import eu.darkcode.dogeprofiler.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    private final @NotNull ReportType reportType;
    private final @NotNull Thread thread;
    private final @NotNull Map<String, Object> appInfo = new HashMap<>();
    private final @NotNull Map<String, Object> deviceInfo = new HashMap<>();
    private final @NotNull Map<String, Object> metaData = new HashMap<>();

    public Report(@NotNull DogeProfiler dogeProfiler, @NotNull Throwable exception) {
        this(dogeProfiler, exception, ReportType.HANDLED_EXCEPTION);
    }

    public Report(@NotNull DogeProfiler dogeProfiler, @NotNull Throwable exception, @NotNull ReportType reportType) {
        this(dogeProfiler, exception, reportType, Thread.currentThread());
    }

    public Report(@NotNull DogeProfiler dogeProfiler, @NotNull Throwable exception, @NotNull ReportType reportType, @NotNull Thread thread) {
        this(dogeProfiler, new ExceptionWrapper(dogeProfiler.getConfig(), exception), reportType, thread);
    }

    @SendSerialize
    @NotNull
    public String getThreadName() {
        return thread.getName();
    }

    @SendSerialize
    @NotNull
    public String getReportType() {
        return reportType.getName();
    }

    @SendSerialize
    public List<ExceptionWrapper> getExceptions() {
        List<ExceptionWrapper> exceptions = new ArrayList<>();
        exceptions.add(exception);

        Throwable currentThrowable = exception.getThrowable().getCause();
        while (currentThrowable != null) {
            exceptions.add(new ExceptionWrapper(dogeProfiler.getConfig(), currentThrowable));
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

    @AllArgsConstructor
    @Getter
    public enum ReportType {
        UNHANDLED_EXCEPTION("unhandledException"),
        HANDLED_EXCEPTION("handledException");
        private final String name;
    }
}