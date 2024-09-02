package eu.darkcode.dogeprofiler;

import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 02.09.2024
 **/
public final class DogeProfiler {

    public DogeProfiler(String apiKey) {
        if (apiKey == null) throw new NullPointerException("You must set a valid api key!");
        ExceptionHandler.enable(this);
    }

    public boolean notify(@NotNull Throwable throwable) {
        return notify(new Report(this, throwable));
    }

    public boolean notify(@NotNull Throwable throwable, @NotNull NotifyState notifyState, @NotNull Thread thread) {
        return notify(new Report(this, throwable, notifyState, thread));
    }

    public boolean notify(@NotNull Report report) {
        System.out.println(report);
        return false;
    }

}