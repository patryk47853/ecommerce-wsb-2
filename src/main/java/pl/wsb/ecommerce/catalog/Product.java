package pl.wsb.ecommerce.catalog;

import java.math.BigDecimal;

public record Product(String name, double price, Category category, boolean available) {

}
