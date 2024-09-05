package eu.darkcode.dogeprofiler;

import org.jetbrains.annotations.NotNull;

/**
 * @author darkcode
 * @date 24.08.24
 **/
public record TimingKey(@NotNull String key, long criticalThreshold) {
}