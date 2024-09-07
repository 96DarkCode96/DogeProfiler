package eu.darkcode.dogeprofiler;

import eu.darkcode.dogeprofiler.injectors.ApplicationReportInjector;
import eu.darkcode.dogeprofiler.sender.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
@Getter
@Setter
public class Configuration {

    private final String apiKey;
    private Sender sender = new SynchronousHttpSender();
    private String appVersion;
    private ObjectSerializer serializer = new DefaultObjectSerializer();
    private final List<ReportListener> reportListenerList = new ArrayList<>();
    private final List<String> projectPackages = new ArrayList<>();

    public Configuration(@NotNull String apiKey) {
        this.apiKey = apiKey;
        addListener(new ApplicationReportInjector(this));
    }

    public void addListener(@NotNull ReportListener reportListener) {
        reportListenerList.add(reportListener);
    }

    public boolean isProjectFile(String className) {
        for (String projectPackage : projectPackages)
            if (projectPackage != null && className.startsWith(projectPackage))
                return true;
        return false;
    }

    public void setEndpoint(@NotNull String endpoint) {
        if (getSender() instanceof HttpSender httpSender)
            httpSender.setEndpoint(endpoint);
    }

    public void setProxy(@Nullable Proxy proxy) {
        if (getSender() instanceof HttpSender httpSender)
            httpSender.setProxy(proxy);
    }
}