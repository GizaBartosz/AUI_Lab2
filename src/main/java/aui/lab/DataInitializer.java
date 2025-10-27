package aui.lab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final BrandService brandService;
    private final ModelService modelService;

    public DataInitializer(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @Override
    public void run(String... args) {

        Brand toyota = new Brand(UUID.randomUUID(), "Toyota");
        brandService.save(toyota);
        Brand bmw = new Brand(UUID.randomUUID(), "BMW");
        brandService.save(bmw);
        Brand fso = new Brand(UUID.randomUUID(), "FSO");
        brandService.save(fso);
        modelService.save(new Model(UUID.randomUUID(), "Avensis", 2012, toyota));
        modelService.save(new Model(UUID.randomUUID(), "Supra", 2021, toyota));
        modelService.save(new Model(UUID.randomUUID(), "X5", 2020, bmw));
        modelService.save(new Model(UUID.randomUUID(), "E36", 1995, bmw));
        modelService.save(new Model(UUID.randomUUID(), "E46", 2003, bmw));
        modelService.save(new Model(UUID.randomUUID(), "Polonez", 1990, fso));
        modelService.save(new Model(UUID.randomUUID(), "Warszawa", 1963, fso));
    }
}
