package smartosc.jsc.applications.ecom.modules.products.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.ecom.modules.products.model.Product;
import smartosc.jsc.applications.ecom.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductFileRepository implements ProductRepositoryInterface {

    protected List<Product> allProducts;

    protected Boolean needLatestData = false;

    private static final String PRODUCT_FILE = Product.TABLE_NAME + ".json";

    @Override
    public void save(Product product) throws IOException {
        List<Product> allProducts = this.getAll();
        Map<String, Product> allProductsMap;
        allProductsMap = this.getAllProductsMap(allProducts);

        if (allProductsMap == null) {
            allProductsMap = new HashMap<>();
        }

        if (product.getId() == null) {
            int lastKey = Integer.parseInt(allProductsMap.keySet().toArray()[allProductsMap.size() - 1].toString());
            product.setId(String.valueOf(lastKey + 1));
        }

        allProductsMap.put(product.getId(), product);
        allProducts = this.getAllProductsList(allProductsMap);
        ObjectMapper objectMapper = new ObjectMapper();
        FileUtils.writeToFile(PRODUCT_FILE, objectMapper.writeValueAsString(allProducts));
        this.needLatestData = true;
    }

    @Override
    public void deleteById(String id) throws IOException {
        List<Product> allProducts;
        Product product = this.getById(id);
        if (product.getId() != null) {
            allProducts = this.getAll();
            Map<String, Product> allProductsMap = this.getAllProductsMap(allProducts);
            allProductsMap.remove(id);
            allProducts = this.getAllProductsList(allProductsMap);
            ObjectMapper objectMapper = new ObjectMapper();
            FileUtils.writeToFile(PRODUCT_FILE, objectMapper.writeValueAsString(allProducts));
            this.needLatestData = true;
            System.out.println("Product with id " + id + " deleted");
            return;
        }
        System.out.println("Product with id " + id + " not found");
    }

    @Override
    public Product getById(String id) throws IOException {
        List<Product> allProducts;
        Product product;
        allProducts = this.getAll();
        Map<String, Product> allProductsMap = this.getAllProductsMap(allProducts);
        product = allProductsMap.get(id);
        if(product == null) {
            product = new Product();
        }
        return product;
    }

    @Override
    public List<Product> getAll() throws IOException {
        if(this.allProducts == null || this.needLatestData) {
            String productJsonContent = FileUtils.readFromFile(PRODUCT_FILE);
            ObjectMapper objectMapper = new ObjectMapper();

            this.allProducts = objectMapper.readValue(productJsonContent, new TypeReference<>() {
            });
            this.needLatestData = false;
        }
        return this.allProducts;
    }

    @Override
    public List<Product> getByName(String name) throws IOException {
        List<Product> allProducts = this.getAll();
        return allProducts.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    private Map<String, Product> getAllProductsMap(List<Product> allProducts) {
        return allProducts.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));
    }

    private List<Product> getAllProductsList(Map<String, Product> allProductsMap) {
        return new ArrayList<>(allProductsMap.values());
    }
}
