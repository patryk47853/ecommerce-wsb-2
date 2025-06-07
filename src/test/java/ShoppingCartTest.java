import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wsb.ecommerce.cart.ShoppingCart;
import pl.wsb.ecommerce.cart.promotion.CheapestForOneZlotyPromotion;
import pl.wsb.ecommerce.cart.promotion.TenPercentOffPromotion;
import pl.wsb.ecommerce.catalog.Category;
import pl.wsb.ecommerce.catalog.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
        double expectedPrice = 3.87; // (2.50 + 1.80) * 0.9 = 3.87
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
        double expectedPrice = 2.50 + 3.20 + 1.00; // = 6.70
        assertEquals(expectedPrice, totalPrice, 0.01);
    }

    @Test
    void shouldRemoveProductWhenQuantityDecreasesToZero() {
        // Given
        cart.addProduct(apple);

        // When
        cart.removeProduct(apple);

        // Then
        assertFalse(cart.getItems().containsKey(apple));
    }

    @Test
    void shouldDecreaseQuantityWhenProductRemoved() {
        // Given
        cart.addProduct(apple);
        cart.addProduct(apple);

        // When
        cart.removeProduct(apple);

        // Then
        assertEquals(1, cart.getItems().get(apple).getQuantity());
    }

    @Test
    void shouldClearPromotion() {
        // Given
        cart.addProduct(apple);
        cart.setPromotion(new TenPercentOffPromotion());

        // When
        cart.clearPromotion();
        double totalPrice = cart.calculateTotalPrice();

        // Then
        double expectedPrice = 2.50;
        assertEquals(expectedPrice, totalPrice, 0.01);
    }
}
