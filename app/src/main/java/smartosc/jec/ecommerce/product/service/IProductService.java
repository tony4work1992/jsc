package smartosc.jec.ecommerce.product.service;

import smartosc.jec.ecommerce.product.model.Product;
import java.util.List;

public interface IProductService {

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(String productId);

    List<Product> getAllProducts();

    Product findProductById(String productId);

}

