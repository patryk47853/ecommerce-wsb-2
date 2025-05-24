package pl.wsb.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCatalog {
    private final List<Product> products = new ArrayList<>();

    public ProductCatalog() {
        products.add(new Product("Apple", new BigDecimal("2.50"), Category.FRUITS, true));
        products.add(new Product("Banana", new BigDecimal("1.80"), Category.FRUITS, true));
        products.add(new Product("Milk", new BigDecimal("3.20"), Category.DAIRY, true));
        products.add(new Product("Bread", new BigDecimal("2.00"), Category.BAKERY, false));
        products.add(new Product("Carrot", new BigDecimal("1.20"), Category.VEGETABLES, true));
        products.add(new Product("Cheese", new BigDecimal("5.50"), Category.DAIRY, true));
    }

    public List<Product> getAllProductsSortedByName() {
        return products.stream()
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    public List<Product> getAvailableProductsByCategorySortedByPrice(Category category) {
        return products.stream()
                .filter(p -> p.category() == category && p.available())
                .sorted(Comparator.comparing(Product::price))
                .collect(Collectors.toList());
    }
}
