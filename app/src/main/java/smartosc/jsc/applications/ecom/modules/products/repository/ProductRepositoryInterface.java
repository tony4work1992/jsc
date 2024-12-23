package smartosc.jsc.applications.ecom.modules.products.repository;

import smartosc.jsc.applications.ecom.modules.products.model.Product;

import java.io.IOException;
import java.util.List;

interface ProductRepositoryInterface
{
    void save(Product product) throws IOException;
    void deleteById(String id) throws IOException;
    Product getById(String id) throws IOException;
    List<Product> getAll() throws IOException;
    List<Product> getByName(String name) throws IOException;
}
