package domainapp.modules.simple.dom.so;


import javax.inject.Named;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.Nature;
import org.apache.causeway.applib.annotation.Title;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Named("simple.SimpleObjectDto")
@DomainObject(nature = Nature.VIEW_MODEL)
@XmlRootElement(name = "SimpleObjectDto")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    propOrder = {
            "name"
    }
)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SimpleObjectDto {
    @Getter @Setter
    @Title
    private String name;

    public SimpleObjectDto(String name) {
        this.name = name;
    }
}
