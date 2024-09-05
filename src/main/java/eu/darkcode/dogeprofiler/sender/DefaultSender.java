package eu.darkcode.dogeprofiler.sender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.darkcode.dogeprofiler.Configuration;
import eu.darkcode.dogeprofiler.Report;
import lombok.Getter;

/**
 * @author _dark_code_
 * @date 05.09.2024
 **/
@Getter
public class DefaultSender implements Sender {
    private final Gson gson = new GsonBuilder().create();
    private final Configuration configuration;

    public DefaultSender(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void send(Object object) {
        if(object instanceof Report report){
            System.out.println(report.getException());
        }
    }

    @Override
    public void close() {

    }
}
