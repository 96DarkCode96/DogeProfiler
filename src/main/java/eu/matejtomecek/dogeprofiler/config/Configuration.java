package eu.matejtomecek.dogeprofiler.config;

import eu.matejtomecek.dogeprofiler.event.report.before.BeforeReport;
import eu.matejtomecek.dogeprofiler.event.report.before.DefaultBeforeReport;
import eu.matejtomecek.dogeprofiler.sender.AsynchronousHttpSender;
import eu.matejtomecek.dogeprofiler.sender.HttpSender;
import eu.matejtomecek.dogeprofiler.sender.Sender;
import eu.matejtomecek.dogeprofiler.sender.serializer.DefaultObjectSerializer;
import eu.matejtomecek.dogeprofiler.sender.serializer.ObjectSerializer;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
@Getter
@Setter
@SuppressWarnings("unused")
public final class Configuration {

    private final @NotNull String apiKey;
    private @Nullable String appVersion;

    private @NotNull ObjectSerializer serializer = new DefaultObjectSerializer();
    private @NotNull Sender sender = new AsynchronousHttpSender();

    private final @NotNull List<@NotNull String> projectPackages = new ArrayList<>();
    private final @NotNull List<@NotNull BeforeReport> reportListenerList = new ArrayList<>();

    private final @NotNull Map<String, String> httpHeaders = new HashMap<>();

    public Configuration(@NotNull String apiKey) {
        this.apiKey = apiKey;
        addHeader("DogeProfiler-Api-Key", apiKey);
        addListener(new DefaultBeforeReport(this));
    }

    public void addProjectPackage(@NotNull String pkg) {
        this.projectPackages.add(pkg);
    }

    public void addListener(@NotNull BeforeReport reportListener) {
        this.reportListenerList.add(reportListener);
    }

    public void addHeader(@NotNull String key, @NotNull String value) {
        this.httpHeaders.put(key, value);
    }

    public void setEndpoint(@NotNull String endpoint) {
        if (getSender() instanceof HttpSender httpSender)
            httpSender.setEndpoint(endpoint);
    }

    public void setTimeout(int timeout) {
        if (getSender() instanceof HttpSender httpSender)
            httpSender.setTimeout(timeout);
    }

    public void setProxy(@Nullable Proxy proxy) {
        if (getSender() instanceof HttpSender httpSender)
            httpSender.setProxy(proxy);
    }

    public boolean isProjectFile(@NotNull String className) {
        for (String projectPackage : projectPackages)
            if (className.startsWith(projectPackage))
                return true;
        return false;
    }
}