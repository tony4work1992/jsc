package services.impls;

import entitites.Promotion;
import enums.PromotionType;
import services.IPromotionService;
import utils.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotionServiceImpl implements IPromotionService {
    private static final String JSON_FILE_PATH = "promotions.json";

    @Override
    public void addPromotion(Promotion promotion) {
        List<Promotion> promotions = loadPromotions();

        // Thêm chương trình khuyến mãi mới vào danh sách
        promotions.add(promotion);

        // Lưu lại danh sách chương trình khuyến mãi vào file
        savePromotions(promotions);
    }

    @Override
    public List<Promotion> listPromotions() {
        return loadPromotions();  // Đọc danh sách chương trình khuyến mãi từ file
    }

    @Override
    public Optional<Promotion> findPromotionById(String id) {
        List<Promotion> promotions = loadPromotions();

        // Tìm kiếm chương trình khuyến mãi theo id
        return promotions.stream()
                .filter(promotion -> promotion.getId().equals(id))
                .findFirst();
    }

    private List<Promotion> loadPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists() && file.length() > 0) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder content = new StringBuilder();

                // Đọc file JSON
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }

                // Phân tích chuỗi JSON thủ công và tạo đối tượng Promotion
                String json = content.toString();
                if (!json.trim().isEmpty()) {
                    String[] promotionStrings = json.replace("[", "").replace("]", "").split("\\},\\{");

                    for (String promotionString : promotionStrings) {
                        promotionString = promotionString.replaceAll("[{}]", "").trim();
                        String[] fields = promotionString.split(",");

                        Promotion promotion = new Promotion();
                        for (String field : fields) {
                            String[] keyValue = field.split(":");
                            String key = keyValue[0].trim().replaceAll("\"", "");
                            String value = keyValue[1].trim().replaceAll("\"", "");

                            // Gán giá trị cho các trường tương ứng
                            switch (key) {
                                case "id":
                                    promotion.setId(value);
                                    break;
                                case "type":
                                    promotion.setType(value);
                                    break;
                                case "value":
                                    promotion.setValue(Double.parseDouble(value));
                                    break;
                                case "condition":
                                    promotion.setCondition(Double.parseDouble(value));
                                    break;
                                case "code":
                                    promotion.setCode(value);
                                    break;
                                case "promotionType":
                                    promotion.setPromotionType(PromotionType.valueOf(value.toUpperCase()));
                                    break;
                            }
                        }
                        promotions.add(promotion);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return promotions;
    }
    private void savePromotions(List<Promotion> promotions) {
        try {
            StringBuilder json = new StringBuilder("");

            for (int i = 0; i < promotions.size(); i++) {
                Promotion promotion = promotions.get(i);
                json.append("{")
                        .append("\"id\":\"").append(promotion.getId()).append("\",")
                        .append("\"type\":\"").append(promotion.getType()).append("\",")
                        .append("\"value\":").append(promotion.getValue()).append(",")
                        .append("\"condition\":").append(promotion.getCondition()).append(",")
                        .append("\"code\":\"").append(promotion.getCode()).append("\",")
                        .append("\"promotionType\":\"").append(promotion.getPromotionType().name()).append("\"")
                        .append("}");

                // Nếu không phải phần tử cuối, thêm dấu phẩy
                if (i < promotions.size() - 1) {
                    json.append(",");
                }
            }


            // Ghi dữ liệu vào file
            BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_PATH));
            writer.write(json.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
