package eu.darkcode.dogeprofiler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author _dark_code_
 * @date 02.09.2024
 **/
@AllArgsConstructor
@Getter
public class NotifyState {

    private final @NotNull NotifyType notifyType;
    private final @NotNull Severity severity;

    @AllArgsConstructor
    @Getter
    public enum NotifyType {
        UNHANDLED_EXCEPTION("unhandledException"),
        HANDLED_EXCEPTION("handledException");
        private final String name;

        @Override
        public String toString() {
            return getName();
        }
    }

}