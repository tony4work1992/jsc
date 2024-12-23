package services;

import entitites.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
     void addProduct(Product product);
     List<Product> listProducts();
     Optional<Product> findProductById(Long id);
     void updateStock(Long productId, int quantity);
     void deleteProductById(Long id);
     void updateProductById(Long productId, Product product);
}
