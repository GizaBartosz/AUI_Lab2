package aui.lab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    List<Model> findByBrandId(UUID brandId);

    @Query("select m from Model m join fetch m.brand")
    List<Model> findAllWithBrand();
}
