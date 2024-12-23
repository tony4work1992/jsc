package smartosc.jsc.applications.jec.promotion_mgt.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import smartosc.jsc.applications.jec.file_handling.services.FileProcessingService;
import smartosc.jsc.applications.jec.promotion_mgt.models.Promotion;

public class PromotionService {
    public static final String SOURCE = "app/src/main/java/smartosc/jsc/applications/jec/resources/promotions.csv";

    public List<Promotion> getPromotions(Integer categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Show ID cannot be null");
        }
        // Example: Fetch promotions for a specific show or event
        FileProcessingService fileService = new FileProcessingService();

        List<Map<String, String>> promotionList = fileService.readCsvWithHeaders(SOURCE);

        return promotionList.stream()
                .filter(promotion -> categoryId == Integer.parseInt(promotion.get("categoryId")))
                .map(item -> {
                    return this.convert(item);
                })
                .collect(Collectors.toList());

    }

    public Promotion getPromotionDetail(Integer promotionId) {
        if (promotionId == null) {
            throw new IllegalArgumentException("Promotion ID cannot be null");
        }

        FileProcessingService fileService = new FileProcessingService();

        List<Map<String, String>> promotionList = fileService.readCsvWithHeaders(SOURCE);

        List<Promotion> promotions = promotionList.stream()
                .filter(promotion -> promotionId == Integer.parseInt(promotion.get("promotionId")))
                .map(item -> {
                    return this.convert(item);
                })
                .collect(Collectors.toList());

        if (promotions.isEmpty()) {
            return null;
        }

        return promotions.getFirst();

    }

    public Promotion convert(Map<String, String> data) {
        Promotion promotion = new Promotion();
        promotion.setCategoryId(Integer.parseInt(data.get("promotionId")));
        promotion.setPromotionId(Integer.parseInt(data.get("promotionId")));
        promotion.setDiscountType(data.get("discountType"));
        promotion.setDiscountAmount(Double.parseDouble(data.get("discountAmount")));
        return promotion;
    }

    public List<Integer> choosePromo(Integer categoryId, Scanner scanner) {
        List<Promotion> promotionList = getPromotions(categoryId);
        List<Integer> promotionIds = new ArrayList<>();
        List<Integer> chosePromotionIds;
        String input;
        System.out.println("Fetching promotions for show ID: " + categoryId);
        System.out.println("promotionId, categoryId, discountType, discountAmount");

        for (Promotion promotion : promotionList) {
            promotionIds.add(promotion.getPromotionId());
            System.out.println(promotion.getPromotionId() + "," + categoryId + ","
                    + promotion.getDiscountType() + ","
                    + promotion.getDiscountAmount());
        }

        while (true) {
            System.out.print("Choose valid prodmotions:");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                chosePromotionIds = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                if (promotionIds.containsAll(chosePromotionIds)) {
                    break;

                }

            }
        }
        return chosePromotionIds;
    }

}
