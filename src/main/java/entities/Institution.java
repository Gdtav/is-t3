package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Institution implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;
    private String department;

    @OneToMany(mappedBy = "institution")
    private List<Researcher> researchers;

    public Institution(String name) {
        this.name = name;
    }

    public Institution() {

    }

    public Institution(String name, String location, String department) {
        this.name = name;
        this.location = location;
        this.department = department;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
