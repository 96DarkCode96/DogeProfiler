package eu.matejtomecek.dogeprofiler.event.report.before;

import eu.matejtomecek.dogeprofiler.config.Configuration;
import eu.matejtomecek.dogeprofiler.event.report.Report;
import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
public record DefaultBeforeReport(@NotNull Configuration configuration) implements BeforeReport {

    @Override
    public void beforeNotify(@NotNull Report report) {
        report.addAppInfo("version", configuration.getAppVersion());

        report.addDeviceInfo("os.version", System.getProperty("os.version"));
        report.addDeviceInfo("os.name", System.getProperty("os.name"));
    }
}
