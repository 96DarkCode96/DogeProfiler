package eu.darkcode.dogeprofiler.sender;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.Proxy;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
public interface HttpSender extends Sender {
    void setEndpoint(@NotNull String endpoint);

    @NotNull
    String getEndpoint();

    void setTimeout(int timeout);

    int getTimeout();

    @Nullable
    Proxy getProxy();

    void setProxy(@Nullable Proxy proxy);
}