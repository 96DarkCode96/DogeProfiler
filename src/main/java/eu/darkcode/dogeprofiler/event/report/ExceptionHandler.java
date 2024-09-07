package eu.darkcode.dogeprofiler.event.report;

import eu.darkcode.dogeprofiler.DogeProfiler;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * @author darkcode
 * @date 20.07.24
 **/
@SuppressWarnings("unused")
public final class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final Thread.UncaughtExceptionHandler originalHandler;
    private final Set<DogeProfiler> profilers = new HashSet<>();

    private ExceptionHandler(Thread.UncaughtExceptionHandler originalHandler) {
        this.originalHandler = originalHandler;
    }

    public static void setup(@NotNull DogeProfiler dogeProfiler) {
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
            dogeProfiler.notify(throwable, Report.ReportType.UNHANDLED_EXCEPTION, thread);

        if (originalHandler != null) {
            originalHandler.uncaughtException(thread, throwable);
        } else {
            System.err.print("Exception in thread \"" + thread.getName() + "\" ");
            throwable.printStackTrace(System.err);
        }
    }
}
