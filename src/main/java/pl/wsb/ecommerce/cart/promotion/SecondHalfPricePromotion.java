package pl.wsb.ecommerce.cart.promotion;

import pl.wsb.ecommerce.cart.CartItem;

import java.util.List;

public class SecondHalfPricePromotion implements Promotion {
    @Override
    public double apply(List<CartItem> items) {
        return items.stream()
                .mapToDouble(item -> {
                    int quantity = item.getQuantity();
                    double price = item.getProduct().price().doubleValue();

                    int pairs = quantity / 2;
                    int singleItems = quantity % 2;

                    return pairs * price * 1.5 + singleItems * price;
                })
                .sum();
    }
}
