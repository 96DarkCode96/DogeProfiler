package eu.darkcode.dogeprofiler;

import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
public interface ReportListener {
    void beforeNotify(@NotNull Report report);
}