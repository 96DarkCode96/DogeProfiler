package eu.darkcode.dogeprofiler.sender.serializer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author darkcode
 * @date 06.09.24
 **/
public interface ObjectSerializer {
    void serialize(OutputStream stream, Object object) throws IOException;
}