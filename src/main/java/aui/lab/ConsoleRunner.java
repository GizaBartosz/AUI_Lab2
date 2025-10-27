package aui.lab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@Order(2)
public class ConsoleRunner implements CommandLineRunner {

    private final BrandService brandService;
    private final ModelService modelService;
    private final Scanner in = new Scanner(System.in);

    public ConsoleRunner(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @Override
    public void run(String... args) {
        printHelp();
        while (true) {
            String cmd = in.nextLine().trim().toLowerCase(Locale.ROOT);
            switch (cmd) {
                case "h" -> printHelp();
                case "b" -> listBrands();
                case "m" -> listModels();
                case "a" -> addModel();
                case "d" -> deleteModel();
                case "q" -> {
                    return;
                }
                default -> System.out.println(" ");
            }
        }
    }

    private void printHelp() {
        System.out.println("""
                komendy:
                  h - pomoc
                  b - wypisz marki
                  m - wypisz modele
                  a - dodaj nowy model
                  d - usuń model po ID
                  q - zakończ
                """);
    }

    private void listBrands() {
        List<Brand> brands = brandService.findAllWithModels();
        if (brands.isEmpty()) {
            System.out.println("Brak");
            return;
        }
        for (Brand b : brands) {
            System.out.printf("- %s [id=%s, models=%d]%n",
                    b.getName(), b.getId(), b.getModels().size());
        }
    }

    private void listModels() {
        List<Model> models = modelService.findAllWithBrand();
        if (models.isEmpty()) {
            System.out.println("Brak");
            return;
        }
        models.forEach(m -> {
            String brandName = m.getBrand().getName();
            System.out.printf("- %s [id=%s, year=%d, brand=%s]%n",
                    m.getName(), m.getId(), m.getProductionYear(), brandName);
        });
    }


    private void addModel() {
        List<Brand> brands = brandService.findAllWithModels();
        if (brands.isEmpty()) {
            System.out.println("Brak");
            return;
        }
        System.out.println("Wybierz markę:");
        for (int i = 0; i < brands.size(); i++)
            System.out.printf("%d) %s%n", i + 1, brands.get(i).getName());

        System.out.print("Numer marki: ");
        int idx;
        try {
            idx = Integer.parseInt(in.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Zła liczba");
            return;
        }
        if (idx < 0 || idx >= brands.size()) {
            System.out.println("Zła liczba");
            return;
        }
        Brand chosen = brands.get(idx);

        System.out.print("Nazwa modelu: ");
        String name = in.nextLine().trim();
        System.out.print("Rok produkcji: ");
        int year = Integer.parseInt(in.nextLine().trim());

        Model m = new Model(UUID.randomUUID(), name, year, chosen);
        modelService.save(m);
        System.out.println("Dodano: " + m);
    }

    private void deleteModel() {
        System.out.print("Podaj ID modelu (UUID): ");
        String raw = in.nextLine().trim();
        try {
            UUID id = UUID.fromString(raw);
            Optional<Model> m = modelService.findById(id);
            if (m.isEmpty()) {
                System.out.println("Nie znaleziono");
                return;
            }
            modelService.deleteById(id);
            System.out.println("Usunięto: " + m.get());
        } catch (IllegalArgumentException e) {
            System.out.println("Nieprawidłowy ID");
        }
    }


}
