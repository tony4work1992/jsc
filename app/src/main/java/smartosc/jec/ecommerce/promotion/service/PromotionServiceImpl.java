package smartosc.jec.ecommerce.promotion.service;

import smartosc.jec.ecommerce.promotion.model.Promotion;
import smartosc.jec.ecommerce.promotion.model.PromotionFactory;
import smartosc.jec.ecommerce.util.CSVUtil;
import java.util.ArrayList;
import java.util.List;

public class PromotionServiceImpl implements IPromotionService {
    private static final String PROMOTION_FILE = "app/src/main/java/smartosc/jec/ecommerce/data/promotions.csv";

    @Override
    public void addPromotion(Promotion promotion) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{promotion.getPromoCode(), promotion.getClass().getSimpleName(), String.valueOf(promotion.applyDiscount(100))});
        CSVUtil.writeCSV(PROMOTION_FILE, data, true);
    }

    @Override
    public Promotion getPromotionByCode(String promoCode) {
        List<String[]> data = CSVUtil.readCSV(PROMOTION_FILE);
        for (String[] record : data) {
            if (record[0].equals(promoCode)) {
                return PromotionFactory.createPromotion(record[0], record[1], Double.parseDouble(record[2]));
            }
        }
        return null;
    }
}

