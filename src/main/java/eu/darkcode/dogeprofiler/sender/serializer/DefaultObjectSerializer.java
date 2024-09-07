package eu.darkcode.dogeprofiler.sender.serializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author darkcode
 * @date 06.09.24
 **/
public class DefaultObjectSerializer implements ObjectSerializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DefaultObjectSerializer() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setVisibility(objectMapper.getVisibilityChecker()
                .with(JsonAutoDetect.Visibility.NONE));
    }

    @Override
    public void serialize(OutputStream stream, Object object) throws IOException {
        objectMapper.writeValue(stream, object);
    }
}
