package aui.lab;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BrandService {

    private final BrandRepository repo;

    public BrandService(BrandRepository repo) {
        this.repo = repo;
    }

    public List<Brand> findAllWithModels() { return repo.findAllWithModels(); }
    public Brand save(Brand b) { return repo.save(b); }
}
