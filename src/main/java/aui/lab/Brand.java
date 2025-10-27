package aui.lab;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @Column(updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Model> models = new ArrayList<>();

    public Brand() {}

    public Brand(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Model> getModels() { return models; }
    public void setModels(List<Model> models) { this.models = models; }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", models=" + models.size() +
                '}';
    }
}
