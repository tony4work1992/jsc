package smartosc.jsc.applications.jec.product_mgt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import smartosc.jsc.applications.jec.file_handling.services.FileProcessingService;
import smartosc.jsc.applications.jec.product_mgt.models.Category;

public class CategoryService {
    public static final String SOURCE = "app/src/main/java/smartosc/jsc/applications/jec/resources/categories.csv";

    public List<Category> getCategoryList() {
        // Simulate fetching categories

        FileProcessingService fileService = new FileProcessingService();

        List<Map<String, String>> categoryList = fileService.readCsvWithHeaders(SOURCE);
        return categoryList.stream()
                .map(item -> {
                    return this.convert(item);
                })
                .collect(Collectors.toList());

        // Example: Fetch from database or any data source
    }

    public Category convert(Map<String, String> data) {
        Category category = new Category();
        category.setCategoryId(Integer.parseInt(data.get("categoryId")));
        category.setName(data.get("name"));

        return category;
    }

    public Integer chooseCatgegory(Scanner scanner) {
        List<Category> categoryList = getCategoryList();
        List<Integer> categoryIds = new ArrayList<>();
        Integer categoryId;
        String input;
        System.out.println("Danh sach category:");

        for (Category category : categoryList) {
            categoryIds.add(category.getCategoryId());
            System.out.println(category.getCategoryId() + "." + category.getName());
        }

        while (true) {
            System.out.println("Chon 1 category hop le:");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                categoryId = Integer.parseInt(input);
                if (categoryIds.contains(categoryId)) {
                    break;
                }
            }
        }

        return categoryId;
    }
}
