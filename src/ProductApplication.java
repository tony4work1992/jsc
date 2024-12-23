import entitites.Product;
import services.IProductService;
import services.impls.ProductServiceImpl;

public class ProductApplication {
    public static void main(String[] args) {
        // Tạo đối tượng của ProductServiceImpl
        IProductService productService = new ProductServiceImpl();

        // Thử nghiệm thêm sản phẩm
        System.out.println("Thêm sản phẩm mới:");
        Product product1 = new Product("Product A", "Description A", 100.0, 10);
        productService.addProduct(product1);

        // Thử nghiệm thêm một sản phẩm khác
        Product product2 = new Product("Product B", "Description B", 150.0, 5);
        productService.addProduct(product2);

        // Liệt kê các sản phẩm
        System.out.println("\nDanh sách sản phẩm:");
        productService.listProducts().forEach(product ->
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName())
        );

        // Thử nghiệm tìm kiếm sản phẩm theo ID
        System.out.println("\nTìm sản phẩm theo ID 1:");
        productService.findProductById(1L).ifPresent(product ->
                System.out.println("Found: " + product.getName() + " - " + product.getDescription())
        );

        // Thử nghiệm cập nhật số lượng kho
        System.out.println("\nCập nhật số lượng kho của sản phẩm với ID 1:");
        productService.updateStock(1L, 20); // Cập nhật kho cho sản phẩm với ID 1

        // Liệt kê lại sản phẩm để xem sự thay đổi
        System.out.println("\nDanh sách sản phẩm sau khi cập nhật kho:");
        productService.listProducts().forEach(product ->
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Stock: " + product.getStockQuantity())
        );

        // Thử nghiệm cập nhật sản phẩm
        System.out.println("\nCập nhật thông tin sản phẩm với ID 2:");
        Product updatedProduct = new Product("Updated Product B", "Updated Description B", 200.0, 8);
        productService.updateProductById(2L, updatedProduct);

        // Liệt kê lại sản phẩm sau khi cập nhật
        System.out.println("\nDanh sách sản phẩm sau khi cập nhật:");
        productService.listProducts().forEach(product ->
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice())
        );

        // Thử nghiệm xóa sản phẩm theo ID
        System.out.println("\nXóa sản phẩm với ID 1:");
        productService.deleteProductById(1L);

        // Liệt kê lại sản phẩm sau khi xóa
        System.out.println("\nDanh sách sản phẩm sau khi xóa:");
        productService.listProducts().forEach(product ->
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName())
        );
    }
}
