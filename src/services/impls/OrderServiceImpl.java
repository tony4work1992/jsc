package services.impls;

import dtos.OrderDto;
import entitites.Order;
import entitites.Product;
import entitites.Promotion;
import enums.OrderStatus;
import enums.PromotionType;
import services.IOrderService;
import services.IProductService;
import services.IPromotionService;
import utils.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements IOrderService {

    private static final String JSON_FILE_PATH = "orders.json";

    private final IPromotionService promotionService;
    private final IProductService productService;

    // Constructor Injection
    public OrderServiceImpl(IPromotionService promotionService, IProductService productService) {
        this.promotionService = promotionService;
        this.productService = productService;
    }

    @Override
    // Phương thức tạo đơn hàng
    public Order createOrder(OrderDto orderDto) throws IOException {
        List<Promotion> promotions = promotionService.listPromotions();
        List<Product> products = productService.listProducts().stream()
                .filter(product -> orderDto.getProductIds().contains(product.getId()))
                .collect(Collectors.toList());
        Promotion selectedPromotion = null;
        
        // Kiểm tra và lấy thông tin khuyến mãi nếu mã hợp lệ
        for (Promotion promotion : promotions) {
            if (promotion.getCode().equals(orderDto.getPromotionCode())) {
                selectedPromotion = promotion;
                break;
            }
        }

        // Tính tổng tiền
        double totalPrice = 0;
        for(Product product: products){
            totalPrice+= product.getPrice();
        }
        if (selectedPromotion != null) {
            if (selectedPromotion.getPromotionType() == PromotionType.PERCENTAGE) {
                totalPrice *= (1 - selectedPromotion.getValue() / 100); // Giảm giá theo tỷ lệ phần trăm
            } else if (selectedPromotion.getPromotionType() == PromotionType.FLAT) {
                totalPrice -= selectedPromotion.getValue(); // Giảm giá cố định
            }
        }

        // Tạo đối tượng đơn hàng
        Order order = new Order();
        order.setId(System.currentTimeMillis());  // Tạo id đơn hàng tự động
        order.setCustomerName(orderDto.getCustomerName());
        order.setPromotionId(orderDto.getPromotionId());
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);

        // Lưu đơn hàng vào file JSON
        saveOrdersToFile(List.of(order));

        return order;
    }

    private void saveOrdersToFile(List<Order> orders) throws IOException {
        StringBuilder json = new StringBuilder("");

        // Lặp qua danh sách đơn hàng và tạo chuỗi JSON cho mỗi đơn hàng
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            json.append("{")
                    .append("\"id\":").append(order.getId()).append(",")
                    .append("\"customerName\":\"").append(order.getCustomerName()).append("\",")
                    .append("\"promotionId\":").append(order.getPromotionId()).append(",")
                    .append("\"totalPrice\":").append(order.getTotalPrice()).append(",")
                    .append("\"status\":\"").append(order.getStatus()).append("\"")
                    .append("}");

            // Nếu không phải đơn hàng cuối cùng, thêm dấu phẩy
            if (i < orders.size() - 1) {
                json.append(",");
            }
        }


        // Ghi vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_PATH))) {
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    // Phương thức tải đơn hàng từ file JSON
    public List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Phân tích cú pháp JSON thủ công từ chuỗi line
                Order order = parseOrderFromJson(line);
                if (order != null) {
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Phương thức phân tích cú pháp JSON và chuyển thành đối tượng Order
    private Order parseOrderFromJson(String json) {
        Order order = new Order();

        // Loại bỏ dấu ngoặc nhọn và phân tách theo dấu phẩy
        json = json.replaceAll("[{}]", "").trim();
        String[] fields = json.split(",");

        for (String field : fields) {
            String[] keyValue = field.split(":");
            String key = keyValue[0].trim().replaceAll("\"", "");
            String value = keyValue[1].trim().replaceAll("\"", "");

            // Gán giá trị cho các trường trong đối tượng Order
            switch (key) {
                case "id":
                    order.setId(Long.parseLong(value));
                    break;
                case "customerName":
                    order.setCustomerName(value);
                    break;
                case "promotionId":
                    order.setPromotionId(Long.parseLong(value));
                    break;
                case "totalPrice":
                    order.setTotalPrice(Double.parseDouble(value));
                    break;
                case "status":
                    order.setStatus(OrderStatus.valueOf(value.toUpperCase()));
                    break;
            }
        }
        return order;
    }
    @Override
    public Optional<Order> findOrderById(String id) {
        // Tải danh sách các đơn hàng từ file
        List<Order> orders = loadOrders();

        // Tìm kiếm đơn hàng theo id
        return orders.stream()
                .filter(order -> order.getId().toString().equals(id))
                .findFirst();
    }

    @Override
    public Order updateStatusOrder(Long orderId, OrderStatus orderStatus) throws IOException {
        // Tải danh sách các đơn hàng từ file
        List<Order> orders = loadOrders();

        // Tìm kiếm đơn hàng theo orderId
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                // Cập nhật trạng thái của đơn hàng
                order.setStatus(orderStatus);

                // Lưu lại đơn hàng với trạng thái mới
                saveOrdersToFile(List.of(order));

                return order;
            }
        }

        return null;
    }
}
