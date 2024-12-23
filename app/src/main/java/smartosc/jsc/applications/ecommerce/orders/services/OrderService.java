package smartosc.jsc.applications.ecommerce.orders.services;

import smartosc.jsc.applications.ecommerce.orders.models.Order;
import smartosc.jsc.applications.ecommerce.products.models.Product;
import smartosc.jsc.applications.ecommerce.products.services.IProductService;
import smartosc.jsc.applications.ecommerce.products.services.ProductService;
import smartosc.jsc.applications.ecommerce.promotion.models.Promotion;
import smartosc.jsc.applications.ecommerce.promotion.services.IPromotionService;
import smartosc.jsc.applications.ecommerce.promotion.services.PromotionService;
import smartosc.jsc.applications.ecommerce.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderService implements IOrderService{
    private final Map<Integer, Order> orders = new HashMap<>();
    private final Scanner scanner;
    private String filePath = "/orders.csv";
    private final String folderPath;

    public OrderService(Scanner scanner, String folderPath) {
        this.scanner = scanner;
        this.folderPath = folderPath;
        this.filePath = folderPath + filePath;
        loadData();
    }

    @Override
    public void manageOrders() {
        while (true) {
            System.out.println("\nOrder Management");
            System.out.println("1. Create Order");
            System.out.println("2. Update Order Status");
            System.out.println("3. List Orders");
            System.out.println("4. Get Order By Id");
            System.out.println("5. Go Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createOrder();
                case 2 -> updateStatusOrder();
                case 3 -> getListOrder();
                case 4 -> getListOrderById();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    @Override
    public void createOrder() {
        System.out.print("Enter Order ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        double totalPrice = 0;
        System.out.print("Enter Order Email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Enter Order Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Order Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Order Phone Number: ");
        String phoneNumber = scanner.nextLine();

        StringBuilder orderDetail = new StringBuilder();

        boolean atc=true;
        while (atc){
            System.out.print("Enter Product Id: ");
            int productId = scanner.nextInt();
            scanner.nextLine();
            orderDetail.append(productId);
            System.out.print("Enter Product Qty: ");
            int qty = scanner.nextInt();
            scanner.nextLine();
            orderDetail.append("(").append(qty).append(")|");
            totalPrice = addToCart(totalPrice, productId, qty);
            System.out.println("ATC Product " + productId  +"Qty: " + qty);
            System.out.println("Total: " + totalPrice);
            System.out.println("Continue ATC (0: no | 1: yes)?: ");
            atc = scanner.nextInt()!=0;
        }


        System.out.print("Enter Order PromotionId: ");
        int promotionId = scanner.nextInt();
        scanner.nextLine();

        totalPrice = applyDiscount(totalPrice, promotionId);

        String status = Order.PENDING;

        orders.put(id, new Order(id, customerEmail, customerName, address, phoneNumber, orderDetail.toString(), promotionId, status, totalPrice));
        saveData();
        System.out.println("Order added successfully!");
        System.out.println(orders.get(id));
    }

    @Override
    public void updateStatusOrder() {
        System.out.print("Enter Order ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Order order = orders.get(id);
        if (order != null) {
            String status = order.getStatus();
            boolean selectStatus = false;
            while (!selectStatus){
                System.out.println("\n Select New Order Status (current: "+status+"):");
                System.out.println("1. "+Order.PENDING);
                System.out.println("2. "+Order.ON_DELIVERY);
                System.out.println("3. "+Order.COMPLETED);
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice){
                    case 1 -> {
                        status = Order.PENDING;
                        selectStatus = true;
                    }
                    case 2 -> {
                        status = Order.ON_DELIVERY;
                        selectStatus = true;
                    }
                    case 3 -> {
                        status = Order.COMPLETED;
                        selectStatus = true;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }

            order.setStatus(status);
            saveData();
            System.out.println("Order updated successfully!");
        } else {
            System.out.println("Order not found.");
        }
    }

    @Override
    public void getListOrder() {
        if (orders.isEmpty()) {
            System.out.println("No order available.");
        } else {
            System.out.println("\nProduct List:");
            for (Order order : orders.values()) {
                System.out.println(order);
            }
        }
    }

    @Override
    public void getListOrderById() {

    }

    public double addToCart(double totalPrice, int productId, int qty){
        IProductService productService = new ProductService(scanner, folderPath);
        Product product = productService.getProductById(productId);
        totalPrice += product.getPrice() * qty;
        return totalPrice;
    }

    public double applyDiscount(double totalPrice, int promotionId){
        IPromotionService promotionService = new PromotionService(scanner, folderPath);
        Promotion promotion = promotionService.getPromotionById(promotionId);
        if(totalPrice>Integer.parseInt(promotion.getCondition())){
            totalPrice -= promotion.getDiscount();
        }
        return totalPrice;
    }

    private void loadData() {
        ArrayList<String> data = FileUtils.loadDataFromFile(filePath);
        for(String item: data){
            String[] parts = item.split(",");
            int id = Integer.parseInt(parts[0]);
            String customerEmail = parts[1];
            String customerName = parts[2];
            String address = parts[3];
            String phoneNumber = parts[4];
            String orderDetail = parts[5];
//            ArrayList<Integer> productIds = new ArrayList<>(Integer.parseInt(parts[5]));
            int promotionId = Integer.parseInt(parts[6]);
            String status = parts[7];
            double totalPrice = Double.parseDouble(parts[8]);
            orders.put(id, new Order(id, customerEmail, customerName, address, phoneNumber, orderDetail, promotionId, status, totalPrice));
        }
    }

    private void saveData() {
        ArrayList<String> data = new ArrayList<>();
        for (Order order : orders.values()) {
            data.add(order.toCsv());
        }
        FileUtils.saveDataToFile(folderPath, filePath, data);
    }
}
