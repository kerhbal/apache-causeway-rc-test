package domainapp.modules.simple.dom.testobject;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.types.Name;

import lombok.*;

import org.apache.causeway.applib.jaxb.PersistentEntityAdapter;
import org.apache.causeway.persistence.jpa.applib.integration.CausewayEntityListener;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Comparator;



@Entity
@Table(
    schema= SimpleModule.SCHEMA,
    uniqueConstraints = {
        @UniqueConstraint(name = "SimpleObject__name__UNQ", columnNames = {"name"})
    }
)

@EntityListeners(CausewayEntityListener.class)
//@Named(SimpleModule.NAMESPACE + ".TestObject")
//@DomainObject(entityChangePublishing = Publishing.ENABLED)
//@DomainObjectLayout(
//        tableDecorator = TableDecorator.DatatablesNet.class,
//        bookmarking = BookmarkPolicy.AS_ROOT)
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
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
//    @PropertyLayout(fieldSetId = "simpleObject", sequence = "2")
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
