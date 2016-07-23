package uk.gov.hmrc.shoppingcart;

import org.junit.Test;
import uk.gov.hmrc.shoppingcart.product.ProductType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by habeeb on 23/07/2016.
 */
public class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @Test
    public void checkOut_shouldZeroWhenItemsListIsEmpty(){
        shoppingCart = new ShoppingCart(new ArrayList<ShoppingCartItem>());
        assertEquals(BigDecimal.ZERO, shoppingCart.checkOut());
    }

    @Test
    public void checkOut_shouldZeroWhenItemsListIsNull(){
        shoppingCart = new ShoppingCart(null);
        assertEquals(BigDecimal.ZERO, shoppingCart.checkOut());
    }

    @Test
    public void checkOut_shouldRetrunCorrectTotalAmount(){
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
        shoppingCartItems.add(new ShoppingCartItem(ProductType.APPLE, 3));
        shoppingCartItems.add(new ShoppingCartItem(ProductType.ORANGE, 2));


        shoppingCart = new ShoppingCart(shoppingCartItems);
        assertEquals(new BigDecimal(2.30).setScale(2, RoundingMode.CEILING), shoppingCart.checkOut());
    }

}