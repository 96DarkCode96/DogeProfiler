package eu.darkcode.dogeprofiler;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author _dark_code_
 * @date 02.09.2024
 **/
@AllArgsConstructor
@Getter
public enum Severity {
    INFO("info"),
    WARNING("warning"),
    ERROR("error");
    
    private final String value;
}