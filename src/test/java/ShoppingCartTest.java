import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wsb.ecommerce.cart.ShoppingCart;
import pl.wsb.ecommerce.cart.promotion.CheapestForOneZlotyPromotion;
import pl.wsb.ecommerce.cart.promotion.TenPercentOffPromotion;
import pl.wsb.ecommerce.catalog.Category;
import pl.wsb.ecommerce.catalog.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShoppingCartTest {

    private ShoppingCart cart;
    private Product apple;
    private Product banana;
    private Product milk;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
        apple = new Product("Apple", new BigDecimal("2.50"), Category.FRUITS, true);
        banana = new Product("Banana", new BigDecimal("1.80"), Category.FRUITS, true);
        milk = new Product("Milk", new BigDecimal("3.20"), Category.DAIRY, true);
    }

    @Test
    void shouldIncreaseQuantityWhenProductAddedMultipleTimes() {
        // Given
        cart.addProduct(apple);
        cart.addProduct(apple);

        // When
        int quantity = cart.getItems().get(apple).getQuantity();

        // Then
        assertEquals(2, quantity);
    }

    @Test
    void shouldApplyTenPercentOffPromotion() {
        // Given
        cart.addProduct(apple);
        cart.addProduct(banana);
        cart.setPromotion(new TenPercentOffPromotion());

        // When
        double totalPrice = cart.calculateTotalPrice();

        // Then
        double expectedPrice = (apple.price().doubleValue() + banana.price().doubleValue()) * 0.9;
        assertEquals(expectedPrice, totalPrice, 0.01);
    }

    @Test
    void shouldApplyCheapestForOneZlotyPromotion() {
        // Given
        cart.addProduct(apple);
        cart.addProduct(banana);
        cart.addProduct(milk);
        cart.setPromotion(new CheapestForOneZlotyPromotion());

        // When
        double totalPrice = cart.calculateTotalPrice();

        // Then
        double expectedPrice = apple.price().doubleValue() + milk.price().doubleValue() + 1.0;
        assertEquals(expectedPrice, totalPrice, 0.01);
    }
}
