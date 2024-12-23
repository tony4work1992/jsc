package smartosc.jsc.applications.ecommerce.products.services;

import smartosc.jsc.applications.ecommerce.products.models.Product;

public interface IProductService {
    void manageProducts();
    void createProduct();
    void updateProduct();
    Product getProductById(int id);
    void getAllProducts();
    void deleteProductById();
}
