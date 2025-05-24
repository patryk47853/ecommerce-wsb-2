package pl.wsb.ecommerce;

import pl.wsb.ecommerce.cart.ShoppingCart;
import pl.wsb.ecommerce.cart.promotion.CheapestForOneZlotyPromotion;
import pl.wsb.ecommerce.cart.promotion.SecondHalfPricePromotion;
import pl.wsb.ecommerce.cart.promotion.TenPercentOffPromotion;
import pl.wsb.ecommerce.catalog.Category;
import pl.wsb.ecommerce.catalog.Product;
import pl.wsb.ecommerce.catalog.ProductCatalog;

public class Main {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        ShoppingCart cart = new ShoppingCart();

        catalog.getAllProductsSortedByName().forEach(product ->
                System.out.println(product.name() + " - " + product.price()));

        System.out.println("\nAvailable Dairy Products:");
        catalog.getAvailableProductsByCategorySortedByPrice(Category.DAIRY).forEach(product ->
                System.out.println(product.name() + " - " + product.price()));

        Product apple = catalog.getAllProductsSortedByName().stream()
                .filter(p -> p.name().equals("Apple"))
                .findFirst()
                .orElse(null);

        Product banana = catalog.getAllProductsSortedByName().stream()
                .filter(p -> p.name().equals("Banana"))
                .findFirst()
                .orElse(null);

        cart.addProduct(apple);
        cart.addProduct(apple);
        cart.addProduct(banana);

        System.out.println("\nCart Contents:");
        cart.displayCartContents();

        System.out.println("\nTotal Price without Promotion: " + cart.calculateTotalPrice());

        cart.setPromotion(new TenPercentOffPromotion());
        System.out.println("Total Price with 10% Off Promotion: " + cart.calculateTotalPrice());

        cart.setPromotion(new CheapestForOneZlotyPromotion());
        System.out.println("Total Price with Cheapest for 1 Zloty Promotion: " + cart.calculateTotalPrice());

        cart.setPromotion(new SecondHalfPricePromotion());
        System.out.println("Total Price with Second Half Price Promotion: " + cart.calculateTotalPrice());
    }
}
