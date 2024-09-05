package eu.darkcode.dogeprofiler.sender;

/**
 * @author _dark_code_
 * @date 02.09.2024
 **/
public interface Sender {

    void send(Object object);

    void close();

}