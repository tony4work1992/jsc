package promotion_service.promotion_mgt.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import promotion_service.promotion_mgt.model.Promotion;

public class PromotionRepository {
    private static final String PROMOTIONS_FILE = System.getProperty("user.dir") + "/src/source/promotions.txt";

    private Map<Integer, Promotion> promotions = new HashMap<>();
    private boolean isLoad = false;

    public void loadPromotions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROMOTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String type = parts[2];
                double value = Double.parseDouble(parts[3]);
                double minOrderValue = Double.parseDouble(parts[4]);
                promotions.put(id, new Promotion(id, name, type, value, minOrderValue));
            }
            isLoad = true;
        } catch (IOException e) {
            System.out.println("Error loading promotions: " + e.getMessage());
        }
    }

    public Map<Integer, Promotion> getListPromotion() {
        if(!isLoad) {
            loadPromotions();
        }
        return promotions;
    }

    public Promotion getPromotionById(int promtionId) {
        if(!isLoad) {
            loadPromotions();
        }
        if(!promotions.containsKey(promtionId)) {
            throw new NoSuchElementException("Promotion with ID " + promtionId + " does not exist.");
        }

        return promotions.get(promtionId);
    }
}
