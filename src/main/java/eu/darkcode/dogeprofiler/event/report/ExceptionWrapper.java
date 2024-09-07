package eu.darkcode.dogeprofiler.event.report;

import eu.darkcode.dogeprofiler.config.Configuration;
import eu.darkcode.dogeprofiler.sender.serializer.SendSerialize;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author darkcode
 * @date 06.09.24
 **/
@Setter
public class ExceptionWrapper {

    private final Configuration configuration;
    @Getter
    private final Throwable throwable;
    private String errorClassName;

    public ExceptionWrapper(Configuration configuration, Throwable throwable) {
        this.configuration = configuration;
        this.throwable = throwable;
        this.errorClassName = throwable.getClass().getName();
    }

    @SendSerialize
    public String getMessage() {
        return throwable.getLocalizedMessage();
    }

    @SendSerialize
    public String getErrorClassName() {
        return errorClassName;
    }

    @SendSerialize
    public List<StackFrame> getStackFrames() {
        return StackFrame.getStackFrames(configuration, throwable);
    }

}