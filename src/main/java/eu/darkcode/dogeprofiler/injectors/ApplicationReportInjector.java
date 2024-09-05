package eu.darkcode.dogeprofiler.injectors;

import eu.darkcode.dogeprofiler.Configuration;
import eu.darkcode.dogeprofiler.Report;
import eu.darkcode.dogeprofiler.ReportListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
@AllArgsConstructor
@Getter
public final class ApplicationReportInjector implements ReportListener {

    private final Configuration configuration;

    @Override
    public void beforeNotify(@NotNull Report report) {
        report.addAppInfo("version", configuration.getAppVersion());
    }
}
