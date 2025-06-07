package pl.wsb.ecommerce.cart.promotion;

import pl.wsb.ecommerce.cart.CartItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CheapestForOneZlotyPromotion implements Promotion {
    @Override
    public double apply(List<CartItem> items) {
        List<Double> prices = new ArrayList<>();
        for (CartItem item : items) {
            for (int i = 0; i < item.getQuantity(); i++) {
                prices.add(item.getProduct().price());
            }
        }
        prices.sort(Comparator.naturalOrder());
        double total = 0.0;
        int index = 0;
        while (index < prices.size()) {
            int groupSize = Math.min(3, prices.size() - index);
            List<Double> group = prices.subList(index, index + groupSize);
            if (groupSize == 3) {
                total += 1.0; // Cheapest for 1 PLN
                total += group.get(1);
                total += group.get(2);
            } else {
                for (double price : group) {
                    total += price;
                }
            }
            index += groupSize;
        }
        return total;
    }
}

