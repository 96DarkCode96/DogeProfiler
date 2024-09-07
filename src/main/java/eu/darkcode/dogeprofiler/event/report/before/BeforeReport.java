package eu.darkcode.dogeprofiler.event.report.before;

import eu.darkcode.dogeprofiler.event.report.Report;
import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
public interface BeforeReport {
    void beforeNotify(@NotNull Report report);
}