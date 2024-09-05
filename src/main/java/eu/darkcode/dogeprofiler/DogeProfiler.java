package eu.darkcode.dogeprofiler;

import eu.darkcode.dogeprofiler.metric.Metric;
import eu.darkcode.dogeprofiler.sender.Sender;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author _dark_code_
 * @date 02.09.2024
 **/
@Getter
public final class DogeProfiler {

    private final Configuration configuration;

    public DogeProfiler(@Nullable String apiKey) {
        if (apiKey == null) throw new NullPointerException("You must set a valid api key!");
        ExceptionHandler.setup(this);
        this.configuration = new Configuration(apiKey);
    }

    public void notify(@NotNull Throwable throwable) {
        notify(new Report(this, throwable));
    }

    public void notify(@NotNull Throwable throwable, @NotNull NotifyState notifyState, @NotNull Thread thread) {
        notify(new Report(this, throwable, notifyState, thread));
    }

    public void notify(@NotNull Report report) {
        for (ReportListener listener : configuration.getReportListenerList())
            listener.beforeNotify(report);
        if (configuration.getSender() == null)
            return;
        configuration.getSender().send(report);
    }

    public void setAppVersion(String appVersion) {
        this.configuration.setAppVersion(appVersion);
    }

    public void setSender(@NotNull Sender sender) {
        this.configuration.setSender(sender);
    }

    public @NotNull TimingInstance timing(@NotNull TimingKey timingKey) {
        return new TimingInstance(this, timingKey, System.currentTimeMillis());
    }

    public void sendMetric(@NotNull Metric metric) {
        Sender sender = configuration.getSender();
        if (sender != null)
            sender.send(metric);
    }
}