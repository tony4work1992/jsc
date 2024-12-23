package smartosc.jsc.applications.ecommerce.orders.services;

public interface IOrderService {
    void manageOrders();
    void createOrder();
    void updateStatusOrder();
    void getListOrder();
    void getListOrderById();
    double addToCart(double totalPrice, int productId, int qty);
    double applyDiscount(double totalPrice, int promotionId);
}
