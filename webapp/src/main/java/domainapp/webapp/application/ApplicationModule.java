package domainapp.webapp.application;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import domainapp.modules.simple.SimpleModule;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@Import(SimpleModule.class)
@ComponentScan
public class ApplicationModule {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> commonTags() {
        return r -> {
            try {
                r.config().commonTags("host", "ioms"+InetAddress.getLocalHost().getHostName());
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
