package eu.darkcode.dogeprofiler;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author darkcode
 * @date 20.07.24
 **/

@RequiredArgsConstructor
final class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final Thread.UncaughtExceptionHandler originalHandler;
    private final List<DogeProfiler> profilers = new ArrayList<>();

    static void setup(@NotNull DogeProfiler dogeProfiler) {
        Thread.UncaughtExceptionHandler currentHandler = Thread.getDefaultUncaughtExceptionHandler();

        ExceptionHandler dogeProfilerHandler;
        if (currentHandler instanceof ExceptionHandler customExceptionHandler) {
            dogeProfilerHandler = customExceptionHandler;
        } else {
            dogeProfilerHandler = new ExceptionHandler(currentHandler);
            Thread.setDefaultUncaughtExceptionHandler(dogeProfilerHandler);
        }
        dogeProfilerHandler.profilers.add(dogeProfiler);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        for (DogeProfiler dogeProfiler : profilers)
            dogeProfiler.notify(throwable, new NotifyState(NotifyState.NotifyType.UNHANDLED_EXCEPTION, Severity.ERROR), thread);

        if (originalHandler != null) {
            originalHandler.uncaughtException(thread, throwable);
        } else {
            System.err.print("Exception in thread \"" + thread.getName() + "\" ");
            throwable.printStackTrace(System.err);
        }
    }
}
