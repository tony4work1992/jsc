package smartosc.jec.ecommerce.product.service;

import smartosc.jec.ecommerce.product.model.Product;
import smartosc.jec.ecommerce.util.CSVUtil;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    private static final String PRODUCT_FILE = "app/src/main/java/smartosc/jec/ecommerce/data/products.csv";

    @Override
    public void addProduct(Product product) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{product.getId(), product.getName(), product.getDescription(), String.valueOf(product.getPrice()), String.valueOf(product.getStockQuantity())});
        CSVUtil.writeCSV(PRODUCT_FILE, data, true);
    }

    @Override
    public void updateProduct(Product product) {
        List<Product> products = getAllProducts();
        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                p.setName(product.getName());
                p.setDescription(product.getDescription());
                p.setPrice(product.getPrice());
                p.setStockQuantity(product.getStockQuantity());
                break;
            }
        }
        saveAllProducts(products);
    }

    @Override
    public void deleteProduct(String productId) {
        List<Product> products = getAllProducts();
        products.removeIf(p -> p.getId().equals(productId));
        saveAllProducts(products);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        List<String[]> data = CSVUtil.readCSV(PRODUCT_FILE);
        for (String[] record : data) {
            Product product = new Product(record[0], record[1], record[2], Double.parseDouble(record[3]), Integer.parseInt(record[4]));
            products.add(product);
        }
        return products;
    }

    @Override
    public Product findProductById(String productId) {
        for (Product product : getAllProducts()) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private void saveAllProducts(List<Product> products) {
        List<String[]> data = new ArrayList<>();
        for (Product product : products) {
            data.add(new String[]{product.getId(), product.getName(), product.getDescription(), String.valueOf(product.getPrice()), String.valueOf(product.getStockQuantity())});
        }
        CSVUtil.writeCSV(PRODUCT_FILE, data, false);
    }
}
