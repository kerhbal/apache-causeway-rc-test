package domainapp.modules.simple.dom.testobject;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Notes;

import lombok.*;

import org.apache.causeway.applib.annotation.*;
import org.apache.causeway.applib.jaxb.PersistentEntityAdapter;
import org.apache.causeway.applib.layout.LayoutConstants;
import org.apache.causeway.applib.services.message.MessageService;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.applib.services.title.TitleService;
import org.apache.causeway.applib.value.Blob;
import org.apache.causeway.extensions.fullcalendar.applib.CalendarEventable;
import org.apache.causeway.extensions.fullcalendar.applib.value.CalendarEvent;
import org.apache.causeway.extensions.pdfjs.applib.annotations.PdfJsViewer;
import org.apache.causeway.persistence.jpa.applib.integration.CausewayEntityListener;
import org.apache.causeway.persistence.jpa.applib.types.BlobJpaEmbeddable;

import org.springframework.lang.Nullable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Comparator;

import static org.apache.causeway.applib.annotation.SemanticsOf.IDEMPOTENT;
import static org.apache.causeway.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;


@Entity
@Table(
    schema= SimpleModule.SCHEMA,
    uniqueConstraints = {
        @UniqueConstraint(name = "SimpleObject__name__UNQ", columnNames = {"name"})
    }
)

@EntityListeners(CausewayEntityListener.class)
@Named(SimpleModule.NAMESPACE + ".TestObject")
@DomainObject(entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout(
        tableDecorator = TableDecorator.DatatablesNet.class,
        bookmarking = BookmarkPolicy.AS_ROOT)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class TestObject implements Comparable<TestObject> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "testObject")
    @Getter
    @Setter
    @PropertyLayout(fieldSetId = "simpleObject", sequence = "2")
    @EqualsAndHashCode.Include
    private SimpleObject simpleObject;

    private final static Comparator<TestObject> comparator =
            Comparator.comparing(TestObject::getId);

    @Column(length = Name.MAX_LEN, nullable = false, name = "randomname")
    @Getter @Setter @ToString.Include
    private String randomname;

    @Override
    public int compareTo(final TestObject other) {
        return comparator.compare(this, other);
    }

}
