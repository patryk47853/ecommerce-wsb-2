package pl.wsb.ecommerce.cart;

import pl.wsb.ecommerce.cart.promotion.Promotion;
import pl.wsb.ecommerce.catalog.Product;

import java.util.*;

public class ShoppingCart {
    private final Map<Product, CartItem> items = new HashMap<>();
    private Promotion promotion;

    public void addProduct(Product product) {
        if (items.containsKey(product)) {
            items.get(product).incrementQuantity();
        } else {
            items.put(product, new CartItem(product));
        }
    }

    public void removeProduct(Product product) {
        if (items.containsKey(product)) {
            CartItem item = items.get(product);
            item.decrementQuantity();
            if (item.getQuantity() <= 0) {
                items.remove(product);
            }
        }
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void clearPromotion() {
        this.promotion = null;
    }

    public void displayCartContents() {
        items.values().forEach(item ->
                System.out.println(item.getProduct().name() + ": " + item.getQuantity() + " szt."));
    }

    public double calculateTotalPrice() {
        List<CartItem> itemList = new ArrayList<>(items.values());
        if (promotion != null) {
            return promotion.apply(itemList);
        } else {
            return itemList.stream()
                    .mapToDouble(CartItem::getTotalPrice)
                    .sum();
        }
    }

    public Map<Product, CartItem> getItems() {
        return items;
    }
}
