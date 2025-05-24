package pl.wsb.ecommerce.cart.promotion;

import pl.wsb.ecommerce.cart.CartItem;

import java.util.List;

public interface Promotion {
    double apply(List<CartItem> items);
}
