package smartosc.jsc.applications.jec.commands.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import smartosc.jsc.applications.jec.cart_mgt.models.Cart;
import smartosc.jsc.applications.jec.cart_mgt.services.CartService;
import smartosc.jsc.applications.jec.file_handling.services.FileProcessingService;
import smartosc.jsc.applications.jec.order_mgt.models.Order;
import smartosc.jsc.applications.jec.order_mgt.services.OrderService;
import smartosc.jsc.applications.jec.product_mgt.services.CategoryService;
import smartosc.jsc.applications.jec.product_mgt.services.ProductService;
import smartosc.jsc.applications.jec.promotion_mgt.services.PromotionService;
import smartosc.jsc.applications.jec.user_mgt.services.UserService;

public class CommandService {
  
    public void action() {
        Integer userId ;
        Integer categoryId ;
        List<Integer> productIds;
        List<Integer> promotionIds;
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        CategoryService categoryService = new CategoryService();
        ProductService productService = new ProductService();
        PromotionService promotionService = new PromotionService();
        CartService cartService = new CartService();
        OrderService orderService = new OrderService();
        FileProcessingService fileProcessingService = new FileProcessingService();

        userId = userService.chooseUser(scanner);
        System.out.println("------------------------");
        categoryId = categoryService.chooseCatgegory(scanner);
        System.out.println("------------------------");
        productIds = productService.chooseProduct(categoryId, scanner);
        System.out.println("------------------------");
        promotionIds = promotionService.choosePromo(categoryId, scanner);
        System.out.println("------------------------");
        Cart cart = cartService.addToCart(productIds, userId);
        cart = cartService.applyPromotions(cart, promotionIds);
        Order order = orderService.createOrder(cart);
        String filePath = fileProcessingService.saveOrder(order);
        System.out.print("Order da duoc save trong file: " + filePath);

        scanner.close();

    }

}
