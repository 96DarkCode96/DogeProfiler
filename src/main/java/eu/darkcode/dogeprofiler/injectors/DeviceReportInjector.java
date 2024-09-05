package eu.darkcode.dogeprofiler.injectors;

import eu.darkcode.dogeprofiler.Report;
import eu.darkcode.dogeprofiler.ReportListener;
import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
public final class DeviceReportInjector implements ReportListener {

    @Override
    public void beforeNotify(@NotNull Report report) {
        report.addDeviceInfo("os.version", System.getProperty("os.version"));
        report.addDeviceInfo("os.name", System.getProperty("os.name"));
    }
}
