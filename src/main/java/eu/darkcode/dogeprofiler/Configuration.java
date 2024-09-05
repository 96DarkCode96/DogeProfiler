package eu.darkcode.dogeprofiler;

import eu.darkcode.dogeprofiler.injectors.ApplicationReportInjector;
import eu.darkcode.dogeprofiler.sender.DefaultSender;
import eu.darkcode.dogeprofiler.sender.Sender;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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
    private Sender sender = new DefaultSender(this);
    private String appVersion;
    private List<ReportListener> reportListenerList = new ArrayList<>();

    public Configuration(@NotNull String apiKey) {
        this.apiKey = apiKey;
        addListener(new ApplicationReportInjector(this));
    }

    public void addListener(@NotNull ReportListener reportListener) {
        reportListenerList.add(reportListener);
    }

}