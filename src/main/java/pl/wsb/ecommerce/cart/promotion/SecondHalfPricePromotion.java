package pl.wsb.ecommerce.cart.promotion;

import pl.wsb.ecommerce.cart.CartItem;

import java.util.List;

public class SecondHalfPricePromotion implements Promotion {
    @Override
    public double apply(List<CartItem> items) {
        double total = 0.0;
        for (CartItem item : items) {
            int quantity = item.getQuantity();
            double price = item.getProduct().price().doubleValue();
            int pairs = quantity / 2;
            int remainder = quantity % 2;
            total += pairs * price * 1.5 + remainder * price;
        }
        return total;
    }
}
