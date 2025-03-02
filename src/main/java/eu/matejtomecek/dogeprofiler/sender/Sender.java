package eu.matejtomecek.dogeprofiler.sender;

import eu.matejtomecek.dogeprofiler.sender.serializer.ObjectSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author _dark_code_
 * @date 02.09.2024
 **/
public interface Sender {

    void send(@NotNull ObjectSerializer serializer, Object object, @NotNull Map<String, String> htmlHeaders);

}