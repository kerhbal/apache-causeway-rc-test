package domainapp.webapp.application;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import domainapp.modules.simple.SimpleModule;

@Configuration
@Import(SimpleModule.class)
@ComponentScan
public class ApplicationModule {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> commonTags() {
        return r -> r.config().commonTags("host", "myapp-dev");
    }

}
