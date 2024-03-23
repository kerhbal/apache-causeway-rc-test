package domainapp.webapp;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.testobject.TestObject;

import io.unlogged.Unlogged;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.Import;

import org.apache.causeway.core.config.presets.CausewayPresets;

@SpringBootApplication
@Import({
    AppManifest.class
//    , XrayEnable.class
})
@EnableLoadTimeWeaving
@EntityScan("domainapp.modules.simple.dom.so")
public class SimpleApp extends SpringBootServletInitializer {

    /**
     * @implNote this is to support the <em>Spring Boot Maven Plugin</em>, which auto-detects an
     * entry point by searching for classes having a {@code main(...)}
     */
    @Unlogged
    public static void main(String[] args) {
        CausewayPresets.prototyping();
        SpringApplication.run(new Class[] { SimpleApp.class }, args);

        try {
            TestObject.class.getDeclaredMethod("_persistence_checkFetchedForSet", String.class);
            System.out.println("LTW-created method present TestObject");
        } catch (NoSuchMethodException e) {
            System.out.println("LTW-created method not present TestObject");
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }

        try {
            SimpleObject.class.getDeclaredMethod("_persistence_checkFetchedForSet", String.class);
            System.out.println("LTW-created method present SimpleObject,m");
        } catch (NoSuchMethodException e) {
            System.out.println("LTW-created method not present for SimpleObject");
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }

}
