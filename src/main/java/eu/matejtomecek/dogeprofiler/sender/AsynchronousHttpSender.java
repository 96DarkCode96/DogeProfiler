package eu.matejtomecek.dogeprofiler.sender;

import eu.matejtomecek.dogeprofiler.sender.serializer.ObjectSerializer;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author darkcode
 * @date 07.09.24
 **/
@Setter
@Getter
public class AsynchronousHttpSender implements HttpSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsynchronousHttpSender.class);

    private final @NotNull ExecutorService executorService = new ThreadPoolExecutor(0, 1, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    private @NotNull HttpSender baseSender = new SynchronousHttpSender();

    public AsynchronousHttpSender() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            terminated = true;
            executorService.shutdown();

            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS))
                    executorService.shutdownNow();
            } catch (InterruptedException ex) {
                executorService.shutdownNow();
            }
        }));
    }

    private boolean terminated = false;

    @Override
    public void send(@NotNull ObjectSerializer serializer, Object object, @NotNull Map<String, String> htmlHeaders) {
        if (terminated) {
            LOGGER.warn("Trying to send an object when sender is terminated!");
            return;
        }
        executorService.submit(() -> baseSender.send(serializer, object, htmlHeaders));
    }

    @Override
    public void setEndpoint(@NotNull String endpoint) {
        baseSender.setEndpoint(endpoint);
    }

    @Override
    public void setTimeout(int timeout) {
        baseSender.setTimeout(timeout);
    }

    @Override
    public void setProxy(@Nullable Proxy proxy) {
        baseSender.setProxy(proxy);
    }
}