package pl.wsb.ecommerce.cart.promotion;

import pl.wsb.ecommerce.cart.CartItem;

import java.util.List;

public class TenPercentOffPromotion implements Promotion {
    @Override
    public double apply(List<CartItem> items) {
        double total = items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
        return total * 0.9;
    }
}
