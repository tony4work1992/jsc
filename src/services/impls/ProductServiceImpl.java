package services.impls;

import entitites.Product;
import services.IProductService;
import utils.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements IProductService {
    private static final String JSON_FILE_PATH = "products.json";

    @Override
    public void addProduct(Product product) {
        try {
            List<Product> products = listProducts(); // Đọc danh sách sản phẩm hiện có

            // Tạo ID tự sinh (ID sẽ là số lớn nhất trong các sản phẩm hiện có + 1)
            long newId = products.isEmpty() ? 1 : products.stream().mapToLong(Product::getId).max().getAsLong() + 1;
            product.setId(newId);

            products.add(product); // Thêm sản phẩm vào danh sách

            // Ghi lại danh sách sản phẩm vào file JSON
            saveProductsToFile(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> listProducts() {
        List<Product> products = new ArrayList<>();
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                // Kiểm tra nếu file rỗng
                if (file.length() == 0) {
                    return products; // Trả về mảng rỗng nếu file trống
                }
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder content = new StringBuilder();

                // Đọc file JSON
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }

                // Chuyển dữ liệu thành danh sách sản phẩm
                String[] productStrings = content.toString().split("},\\{");
                if(productStrings.length>0){
                    for (String productString : productStrings) {
                        productString = productString.replaceAll("[{}]", "").trim();
                        String[] fields = productString.split(",");
                        Product product = new Product();

                        for (String field : fields) {
                            String[] keyValue = field.split(":");
                            String key = keyValue[0].trim().replaceAll("\"", "");
                            String value = keyValue[1].trim().replaceAll("\"", "");

                            // Gán giá trị cho các trường tương ứng
                            switch (key) {
                                case "id":
                                    product.setId(Long.parseLong(value));
                                    break;
                                case "name":
                                    product.setName(value);
                                    break;
                                case "description":
                                    product.setDescription(value);
                                    break;
                                case "price":
                                    product.setPrice(Double.parseDouble(value));
                                    break;
                                case "stockQuantity":
                                    product.setStockQuantity(Integer.parseInt(value));
                                    break;
                            }
                        }
                        products.add(product);
                    }
                }

                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return listProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public void updateStock(Long productId, int quantity) {
        try {
            List<Product> products = listProducts();

            for (Product product : products) {
                if (product.getId().equals(productId)) {
                    product.setStockQuantity(quantity); // Cập nhật số lượng kho
                    break;
                }
            }

            // Ghi lại danh sách sản phẩm đã cập nhật vào file JSON
            saveProductsToFile(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductById(Long id) {
        try {
            List<Product> products = listProducts();
            products.removeIf(product -> product.getId().equals(id)); // Xóa sản phẩm theo ID

            // Ghi lại danh sách sản phẩm sau khi xóa vào file JSON
            saveProductsToFile(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProductById(Long productId, Product updatedProduct) {
        try {
            List<Product> products = listProducts();

            for (Product product : products) {
                if (product.getId().equals(productId)) {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    product.setStockQuantity(updatedProduct.getStockQuantity());
                    break;
                }
            }

            // Ghi lại danh sách sản phẩm đã cập nhật vào file JSON
            saveProductsToFile(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ghi danh sách sản phẩm vào file JSON
    private void saveProductsToFile(List<Product> products) throws IOException {
        StringBuilder json = new StringBuilder("");

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            json.append("{")
                    .append("\"id\":\"").append(product.getId()).append("\",")
                    .append("\"name\":\"").append(product.getName()).append("\",")
                    .append("\"description\":\"").append(product.getDescription()).append("\",")
                    .append("\"price\":").append(product.getPrice()).append(",")
                    .append("\"stockQuantity\":").append(product.getStockQuantity())
                    .append("}");
            if (i < products.size() - 1) {
                json.append(",");
            }
        }

        // Ghi vào file
        BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_PATH));
        writer.write(json.toString());
        writer.close();
    }
}
