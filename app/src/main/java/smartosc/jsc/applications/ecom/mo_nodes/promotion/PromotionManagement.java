package smartosc.jsc.applications.ecom.mo_nodes.promotion;

import smartosc.jsc.applications.ecom.ba_nodes.FileManager;
import smartosc.jsc.applications.ecom.mo_nodes.cart.model.Cart;
import smartosc.jsc.applications.ecom.mo_nodes.customer.model.Customer;
import smartosc.jsc.applications.ecom.mo_nodes.product.model.Product;
import smartosc.jsc.applications.ecom.mo_nodes.promotion.constant.ConditionType;
import smartosc.jsc.applications.ecom.mo_nodes.promotion.constant.Oprator;
import smartosc.jsc.applications.ecom.mo_nodes.promotion.model.Condition;
import smartosc.jsc.applications.ecom.mo_nodes.promotion.model.Promotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class PromotionManagement {
    String fileName = "promotions.txt";

    public List<Promotion> getByIds(String promotionIds, Customer customer) {
        List<Promotion> promotionList = new ArrayList<>();
        List<String> ids = Arrays.asList(promotionIds.split(","));

        getAvailablePromotions(customer).stream().filter(promotion -> ids.contains(promotion.getPromotionId())).forEach(promotion -> {
            promotionList.add(promotion);
        });

        return promotionList;
    }

    public List<Promotion> getAvailablePromotions(Customer customer) {
        //todo check and get available discounts with customer

        String row;
        FileManager fileManager = new FileManager();
        List<Promotion> promotions = new ArrayList<>();
        BufferedReader fileReader = fileManager.getFileReader(fileName);

        try {
            while ((row = fileReader.readLine()) != null) {
                String[] parts = row.split(",");

                if (parts.length == 5) {
                    Promotion promotion = new Promotion(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim(),
                            parts[2].trim(),
                            Double.parseDouble(parts[3].trim()),
                            getConditions(parts[4].trim())
                    );

                    promotions.add(promotion);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return promotions;
    }

    public List<Condition> getConditions(String conditionRow) {
        List<Condition> conditions = new ArrayList<>();
        List<String> rows = Arrays.asList(conditionRow.split(","));

        for (String row : rows) {
            String[] parts = row.split(",");
            Condition condition = new Condition(parts[0].trim(), parts[1].trim(), parts[3].trim());
            conditions.add(condition);
        }

        return conditions;
    }

    public boolean canApply(Cart cart, Promotion promotion) {
        AtomicBoolean canApply = new AtomicBoolean(true);

        promotion.getCondition().forEach(condition -> {
            switch (condition.getType()) {
                case ConditionType.TOTAL:
                    if (!checkOperator(String.valueOf(cart.getTotal()), condition)) {
                        canApply.set(false);
                    }
                    break;
                case ConditionType.PRODUCT_ID:
                    Stream<Product> cartItems = cart.getProducts().stream();

                    if(!cartItems.anyMatch(product -> product.getId().equals(condition.getValue()))) {
                        canApply.set(false);
                    }
                    break;
            }
        });


        return canApply.get();
    }

    private boolean checkOperator(String columnValue, Condition condition) {
        switch (condition.getOperator()) {
            case Oprator.EQUALS:
                return columnValue.contains(condition.getValue());
            case Oprator.NOT_EQUALS:
                return  !columnValue.contains(condition.getValue());
            case Oprator.GREATER_THAN:
                return Integer.parseInt(columnValue) > Integer.parseInt(condition.getValue());
            case Oprator.LESS_THAN:
                return Integer.parseInt(columnValue) < Integer.parseInt(condition.getValue());
            default:
                return false;
        }
    }
}
