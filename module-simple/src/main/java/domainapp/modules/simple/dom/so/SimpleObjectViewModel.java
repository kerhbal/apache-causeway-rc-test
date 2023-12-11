package domainapp.modules.simple.dom.so;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.causeway.applib.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;
import java.util.stream.Collectors;

@Named("simple.SimpleObjectViewModel")
@DomainObject(nature = Nature.VIEW_MODEL)
@XmlRootElement(name = "SimpleObjectViewModel")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SimpleObjectViewModel {

    @Property
    protected String name;

    @XmlTransient
    @Collection
    public List<SimpleObjectDto> getSimpleObjectList(){
        return simpleObjectRepository.findByNameContaining(name).stream().map(s -> new SimpleObjectDto(s.getName())).collect(Collectors.toList());
    }

    @Autowired
    @XmlTransient
    private SimpleObjectRepository simpleObjectRepository;

    public SimpleObjectViewModel(String name) {
        this.name = name;
    }

    @Action(
            semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE,
            choicesFrom = "simpleObjectList"
    )
    @ActionLayout(
            associateWith = "simpleObjectList"
    )
    public SimpleObjectViewModel changeSomething(List<SimpleObjectDto> dtos, String sss) {
        return this;
    }
}
