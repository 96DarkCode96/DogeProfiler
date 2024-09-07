package eu.darkcode.dogeprofiler.sender;

import eu.darkcode.dogeprofiler.sender.serializer.ObjectSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Map;

/**
 * @author darkcode
 * @date 07.09.24
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SynchronousHttpSender implements HttpSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronousHttpSender.class);

    private @NotNull String endpoint = "https://profiler.matejtomecek.eu/endpoint/";
    private int timeout = 5000;
    private @Nullable Proxy proxy;

    @Override
    public void send(@NotNull ObjectSerializer serializer, Object object, @NotNull Map<String, String> htmlHeaders) {
        HttpURLConnection connection = null;
        try {
            URL url = new URI(endpoint).toURL();
            if (proxy == null)
                connection = (HttpURLConnection) url.openConnection();
            else
                connection = (HttpURLConnection) url.openConnection(proxy);

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            for (Map.Entry<String, String> headerEntry : htmlHeaders.entrySet()) {
                connection.addRequestProperty(headerEntry.getKey(), headerEntry.getValue());
            }

            try (OutputStream outputStream = connection.getOutputStream()) {
                serializer.serialize(outputStream, object);
            }

            int status = connection.getResponseCode();
            if (status != 200)
                LOGGER.warn("Failed to report error to the endpoint! Got {} response code!", status);
        } catch (IOException | URISyntaxException e) {
            LOGGER.warn("Failed to report error to the endpoint! Got {}", e.getMessage());
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}