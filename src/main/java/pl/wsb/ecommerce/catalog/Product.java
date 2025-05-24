package pl.wsb.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.Objects;

public record Product(String name, BigDecimal price, Category category, boolean available) {

}
