package eu.matejtomecek.dogeprofiler;

import eu.matejtomecek.dogeprofiler.config.Configuration;
import eu.matejtomecek.dogeprofiler.event.Event;
import eu.matejtomecek.dogeprofiler.event.Notification;
import eu.matejtomecek.dogeprofiler.event.metric.Metric;
import eu.matejtomecek.dogeprofiler.event.metric.TimingInstance;
import eu.matejtomecek.dogeprofiler.event.report.ExceptionHandler;
import eu.matejtomecek.dogeprofiler.event.report.Report;
import eu.matejtomecek.dogeprofiler.event.report.before.BeforeReport;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author _dark_code_
 * @date 02.09.2024
 **/
@Getter
@SuppressWarnings("unused")
public final class DogeProfiler {

    private final Configuration config;

    public DogeProfiler(@Nullable String apiKey) {
        if (apiKey == null) throw new NullPointerException("You must set a valid api key!");
        this.config = new Configuration(apiKey);
        ExceptionHandler.setup(this);
    }

    public void notify(@NotNull Throwable throwable) {
        handleReport(new Report(this, throwable));
    }

    public void notify(@NotNull Throwable throwable, @NotNull BeforeReport... beforeReports) {
        handleReport(new Report(this, throwable), beforeReports);
    }

    public void notify(@NotNull Throwable throwable, @NotNull Report.ReportType reportType, @NotNull Thread thread) {
        handleReport(new Report(this, throwable, reportType, thread));
    }

    public void notify(@NotNull Throwable throwable, @NotNull Report.ReportType reportType, @NotNull Thread thread, @NotNull BeforeReport... beforeReports) {
        handleReport(new Report(this, throwable, reportType, thread), beforeReports);
    }

    public @NotNull TimingInstance timing(@NotNull String timingKey) {
        return new TimingInstance(this, timingKey, System.currentTimeMillis());
    }

    public void sendMetric(@NotNull Metric metric) {
        handleEvent(metric);
    }

    private void handleReport(@NotNull Report report, @NotNull BeforeReport... beforeReports) {
        for (BeforeReport listener : getConfig().getReportListenerList())
            listener.beforeNotify(report);
        for (BeforeReport beforeReport : beforeReports)
            beforeReport.beforeNotify(report);
        handleEvent(report);
    }

    private void handleEvent(@NotNull Event event) {
        Notification object = new Notification();
        object.getEvents().add(event);
        getConfig().getSender().send(getConfig().getSerializer(), object, getConfig().getHttpHeaders());
    }

}