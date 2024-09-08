package eu.matejtomecek.dogeprofiler.event.report;

import eu.matejtomecek.dogeprofiler.config.Configuration;
import eu.matejtomecek.dogeprofiler.sender.serializer.SendSerialize;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * @author darkcode
 * @date 06.09.24
 **/
@AllArgsConstructor
public class StackFrame {

    private final @NotNull Configuration configuration;
    private final @NotNull StackTraceElement stackTraceElement;

    static List<StackFrame> getStackFrames(@NotNull Configuration configuration, @NotNull Throwable throwable) {
        return Arrays.stream(throwable.getStackTrace()).map(ste -> new StackFrame(configuration, ste)).toList();
    }

    @SendSerialize
    public String getFile() {
        return stackTraceElement.getFileName() == null ? "Unknown" : stackTraceElement.getFileName();
    }

    @SendSerialize
    public String getMethod() {
        return stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
    }

    @SendSerialize
    public int getLineNumber() {
        return stackTraceElement.getLineNumber();
    }

    @SendSerialize
    public boolean isProjectFile() {
        return configuration.isProjectFile(stackTraceElement.getClassName());
    }

}