package smartosc.jsc.applications.ecommerce.promotion.services;

import smartosc.jsc.applications.ecommerce.promotion.models.Promotion;
import smartosc.jsc.applications.ecommerce.utils.FileUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PromotionService implements IPromotionService {

    private final Map<Integer, Promotion> promotions = new HashMap<>();
    private final Scanner scanner;
    private String filePath = "/promotions.csv";
    private final String folderPath;

    public PromotionService(Scanner scanner, String folderPath) {
        this.scanner = scanner;
        this.folderPath = folderPath;
        this.filePath = folderPath + filePath;
        loadData();
    }

    @Override
    public void managePromotions() {
        while (true) {
            System.out.println("\nPromotion Management");
            System.out.println("1. Add New Promotion");
            System.out.println("2. Update Promotion");
            System.out.println("3. Delete Promotion");
            System.out.println("4. List Promotion");
            System.out.println("5. Go Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createPromotion();
                case 2 -> updatePromotion();
                case 3 -> deletePromotionById();
                case 4 -> getListPromotion();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    @Override
    public void createPromotion() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Condition (Total Greater or Equal): ");
        String condition = scanner.nextLine();

        System.out.print("Enter Type(0: percent | 1: fixed): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        while (type!=0 && type!=1){
            System.out.print("Re-enter Validate Type(0: percent | 1: fixed): ");
            type = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Enter Discount Amount: ");
        double discount = scanner.nextDouble();
        while ((type==0 && (discount<=0 || discount>100))){
            System.out.print("Re-enter Validate Discount Amount(0-100): ");
            discount = scanner.nextDouble();
        }

        promotions.put(id, new Promotion(id, name, condition, type, discount));
        saveData();
        System.out.println("Promotion added successfully!");
    }

    @Override
    public void updatePromotion() {
        System.out.print("Enter Promotion ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Promotion promotion = promotions.get(id);
        if (promotion != null) {
            System.out.print("Enter new Name (current: " + promotion.getName() + "): ");
            promotion.setName(scanner.nextLine());

            System.out.print("Enter new Condition (current: " + promotion.getCondition() + "): ");
            promotion.setCondition(scanner.nextLine());

            System.out.print("Enter new Type (current: " + promotion.getType() + "): ");
            int type = scanner.nextInt();
            scanner.nextLine();

            while (type!=0 && type!=1){
                System.out.print("Re-enter Validate Type(0: percent | 1: fixed): ");
                type = scanner.nextInt();
                scanner.nextLine();
            }
            promotion.setType(type);

            System.out.print("Enter new Discount (current: " + promotion.getDiscount() + "): ");
            double discount = scanner.nextDouble();
            while ((type==0 && (discount<=0 || discount>100))){
                System.out.print("Re-enter Validate Discount Amount(0-100): ");
                discount = scanner.nextDouble();
            }
            promotion.setDiscount(discount);

            saveData();
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void getListPromotion() {
        if (promotions.isEmpty()) {
            System.out.println("No promotion available.");
        } else {
            System.out.println("\nPromotion List:");
            for (Promotion promotion : promotions.values()) {
                System.out.println(promotion);
            }
        }
    }

    @Override
    public void deletePromotionById() {
        System.out.print("Enter Promotion ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (promotions.remove(id) != null) {
            saveData();
            System.out.println("Promotion deleted successfully!");
        } else {
            System.out.println("Promotion not found.");
        }
    }

    @Override
    public Promotion getPromotionById(int id) {
        return promotions.get(id);
    }

    private void loadData() {
        ArrayList<String> data = FileUtils.loadDataFromFile(filePath);
        for(String item: data){
            String[] parts = item.split(",");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String condition = parts[2];
            int type = Integer.parseInt(parts[3]);
            double discount = Double.parseDouble(parts[4]);
            promotions.put(id, new Promotion(id, name, condition, type, discount));
        }
    }

    private void saveData() {
        ArrayList<String> data = new ArrayList<>();
        for (Promotion promotion : promotions.values()) {
            data.add(promotion.toCsv());
        }
        FileUtils.saveDataToFile(folderPath, filePath, data);
    }
}
