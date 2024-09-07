package eu.darkcode.dogeprofiler;

import eu.darkcode.dogeprofiler.config.Configuration;
import eu.darkcode.dogeprofiler.event.Event;
import eu.darkcode.dogeprofiler.event.Notification;
import eu.darkcode.dogeprofiler.event.metric.Metric;
import eu.darkcode.dogeprofiler.event.metric.TimingInstance;
import eu.darkcode.dogeprofiler.event.report.ExceptionHandler;
import eu.darkcode.dogeprofiler.event.report.Report;
import eu.darkcode.dogeprofiler.event.report.before.BeforeReport;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author _dark_code_
 * @date 02.09.2024
 **/
@Getter
public final class DogeProfiler {

    private final Configuration config;

    public DogeProfiler(@Nullable String apiKey) {
        if (apiKey == null) throw new NullPointerException("You must set a valid api key!");
        this.config = new Configuration(apiKey);
        ExceptionHandler.setup(this);
    }

    public void notify(@NotNull Throwable throwable) {
        notify(new Report(this, throwable));
    }

    public void notify(@NotNull Throwable throwable, @NotNull Report.ReportType reportType, @NotNull Thread thread) {
        notify(new Report(this, throwable, reportType, thread));
    }

    public @NotNull TimingInstance timing(@NotNull String timingKey) {
        return new TimingInstance(this, timingKey, System.currentTimeMillis());
    }

    public void sendMetric(@NotNull Metric metric) {
        handleEvent(metric);
    }

    private void notify(@NotNull Report report) {
        for (BeforeReport listener : getConfig().getReportListenerList())
            listener.beforeNotify(report);
        handleEvent(report);
    }

    private void handleEvent(@NotNull Event event) {
        Notification object = new Notification();
        object.getEvents().add(event);
        getConfig().getSender().send(getConfig().getSerializer(), object, getConfig().getHttpHeaders());
    }

}