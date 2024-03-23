package domainapp.modules.simple.dom.so;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

public interface SimpleObjectRepository extends JpaRepository<SimpleObject, Long> {


    List<SimpleObject> findByNameContaining(final String name);

    SimpleObject findByName(final String name);


//    @QueryHints(value = { @QueryHint(name = "eclipselink.join-fetch", value = "simpleObject.testObject"),
//                          @QueryHint(name = "eclipselink.join-fetch", value = "simpleObject.testObject.simpleObject")},
//            forCounting = false)
//    List<SimpleObject> findAll();
}
