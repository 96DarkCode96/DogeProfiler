package eu.darkcode.dogeprofiler.sender.serializer;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author darkcode
 * @date 06.09.24
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@JacksonAnnotationsInside
@JsonProperty
public @interface SendSerialize {
}
