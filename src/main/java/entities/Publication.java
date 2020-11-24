package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Publication implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "publications")
    private List<Researcher> researchers;

    public Publication(String name) {
        this.name = name;
    }

    public Publication() {

    }

    public Publication(String name, List<Researcher> researchers) {
        this.name = name;
        this.researchers = researchers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Researcher> getResearchers() {
        return researchers;
    }

    public void setResearchers(ArrayList<Researcher> researchers) {
        this.researchers = researchers;
    }

    public Long getId() {
        return id;
    }
}
