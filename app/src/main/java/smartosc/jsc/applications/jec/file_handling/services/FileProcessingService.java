package smartosc.jsc.applications.jec.file_handling.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smartosc.jsc.applications.jec.order_mgt.models.Order;
import smartosc.jsc.applications.jec.product_mgt.models.Product;
import smartosc.jsc.applications.jec.user_mgt.models.User;
import smartosc.jsc.applications.jec.user_mgt.services.UserService;

public class FileProcessingService {

    public static final String OUTPUT_FOLDER_PATH = "app/src/main/java/smartosc/jsc/applications/jec/output/orders.csv";

    // Function to read data from CSV file with headers
    public List<Map<String, String>> readCsvWithHeaders(String filePath) {
        List<Map<String, String>> result = new ArrayList<>();
        String line;
        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the header line
            if ((line = br.readLine()) != null) {
                headers = line.split(",");
            }

            // Read the data lines
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], values[i]);
                }
                result.add(row);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public String saveOrder(Order order) {
        Integer orderId = order.getOrderId();
        String filePath = OUTPUT_FOLDER_PATH;
        String[] headers = { "orderId", "ProductName", "Price", "Email" };
        UserService userService = new UserService();
        User user = userService.getUserDetail(order.getUserId());
        String email = user.getEmail();
        File file = new File(filePath);
        try {
            if (file.exists()) {
            } else {
                // Tạo thư mục cha nếu chưa tồn tại
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
            writer.write("Order ID,Product Name,Price, Customer Email");
            writer.newLine();
            List<Product> products = order.getProducts();

            for (Product product : products) {
                writer.write(orderId + "," + product.getName() + "," + product.getPrice() + "," + email);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}