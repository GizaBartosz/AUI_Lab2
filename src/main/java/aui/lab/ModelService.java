package aui.lab;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ModelService {

    private final ModelRepository repo;

    public ModelService(ModelRepository repo) {
        this.repo = repo;
    }

    public List<Model> findAllWithBrand() {
        return repo.findAllWithBrand();
    }

    public Optional<Model> findById(UUID id) { return repo.findById(id); }
    public Model save(Model m) { return repo.save(m); }
    public void deleteById(UUID id) { repo.deleteById(id); }
}
