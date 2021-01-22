package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart() {
        assertNotNull(cartService.getLineItems());
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddOnProduct() {
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));
        expectedProduct.setName("Product name");

        cartService.addProductQty(expectedProduct, "color", "material", 1);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());

        LineItem lineItem = lineItems.get(0);
        assertEquals("color", lineItem.getColor());
        assertEquals("material", lineItem.getMaterial());
        assertEquals(1, lineItem.getQty());

        assertEquals(expectedProduct.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductRepr());
        assertEquals(expectedProduct.getName(), lineItem.getProductRepr().getName());
    }

    @Test
    public void testAddOnSeveralProducts() {
        ProductRepr expectedProduct_1 = new ProductRepr();
        expectedProduct_1.setId(1L);
        expectedProduct_1.setPrice(new BigDecimal(123));
        expectedProduct_1.setName("Product one");

        cartService.addProductQty(expectedProduct_1, "color one", "material one", 1);

        ProductRepr expectedProduct_2 = new ProductRepr();
        expectedProduct_2.setId(2L);
        expectedProduct_2.setPrice(new BigDecimal(456));
        expectedProduct_2.setName("Product two");

        cartService.addProductQty(expectedProduct_2, "color two", "material two", 2);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(2, lineItems.size());

        LineItem lineItem = lineItems.get(1);
        assertEquals(2, lineItem.getProductId());
        assertEquals("color two", lineItem.getColor());
        assertEquals("material two", lineItem.getMaterial());
        assertEquals(2, lineItem.getQty());

        assertEquals(BigDecimal.valueOf(1035), cartService.getSubTotal());
    }

    @Test
    public void testProductRemoval() {
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));
        expectedProduct.setName("Product name");

        cartService.addProductQty(expectedProduct, "color", "material", 1);
        List<LineItem> lineItems1 = cartService.getLineItems();
        assertEquals(1, lineItems1.size());


        cartService.removeProduct(expectedProduct, "color", "material");
        List<LineItem> lineItems2 = cartService.getLineItems();
        assertEquals(0, lineItems2.size());
    }

    @Test
    public void testProductQtyRemoval() {
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));
        expectedProduct.setName("Product name");

        cartService.addProductQty(expectedProduct, "color", "material", 3);
        cartService.removeProductQty(expectedProduct,"color", "material",1);
        List<LineItem> lineItems = cartService.getLineItems();
        LineItem lineItem = lineItems.get(0);
        assertEquals(2, lineItem.getQty());

    }
}
