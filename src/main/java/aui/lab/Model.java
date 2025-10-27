package aui.lab;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "models")
public class Model {

    public Model() {}

    @Id
    @Column(updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "production_year", nullable = false)
    private int productionYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public Model(UUID id, String name, int productionYear, Brand brand) {
        this.id = id;
        this.name = name;
        this.productionYear = productionYear;
        this.brand = brand;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getProductionYear() { return productionYear; }
    public void setProductionYear(int productionYear) { this.productionYear = productionYear; }

    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productionYear=" + productionYear +
                ", brandPresent=" + (brand != null) +
                '}';
    }
}
