package uk.gov.hmrc.shoppingcart;

import org.junit.Test;
import uk.gov.hmrc.shoppingcart.product.ProductType;
import uk.gov.hmrc.shoppingcart.product.offer.OfferType;

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
    public void checkOut_shouldReturnCorrectTotalAmount_noOffers(){
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
        shoppingCartItems.add(new ShoppingCartItem(ProductType.APPLE, 3));
        shoppingCartItems.add(new ShoppingCartItem(ProductType.ORANGE, 2));


        shoppingCart = new ShoppingCart(shoppingCartItems);
        assertEquals(new BigDecimal(2.30).setScale(2, RoundingMode.CEILING), shoppingCart.checkOut());
    }

    @Test
    public void checkOut_shouldReturnCorrectTotalAmount_withOffersApplied_apples(){
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
        ProductType apple = ProductType.APPLE;
        apple.setOfferType(OfferType.BUY_ONE_GET_ONE_FREE);
        shoppingCartItems.add(new ShoppingCartItem(apple, 2));


        shoppingCart = new ShoppingCart(shoppingCartItems);
        assertEquals(new BigDecimal(0.60).setScale(2, RoundingMode.CEILING), shoppingCart.checkOut());
    }

    @Test
    public void checkOut_shouldReturnCorrectTotalAmount_withOffersApplied_oranges(){
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
        ProductType orange = ProductType.ORANGE;
        orange.setOfferType(OfferType.THREE_FOR_TWO);
        shoppingCartItems.add(new ShoppingCartItem(orange, 3));

        shoppingCart = new ShoppingCart(shoppingCartItems);
        assertEquals(new BigDecimal(0.50).setScale(2, RoundingMode.CEILING), shoppingCart.checkOut());
    }

    @Test
    public void checkOut_shouldReturnCorrectTotalAmount_withOffersApplied_mixture(){
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
        ProductType orange = ProductType.ORANGE;
        orange.setOfferType(OfferType.THREE_FOR_TWO);
        ProductType apple = ProductType.APPLE;
        apple.setOfferType(OfferType.BUY_ONE_GET_ONE_FREE);
        shoppingCartItems.add(new ShoppingCartItem(orange, 7));
        shoppingCartItems.add(new ShoppingCartItem(apple, 7));

        shoppingCart = new ShoppingCart(shoppingCartItems);
        assertEquals(new BigDecimal(3.65).setScale(2, RoundingMode.CEILING), shoppingCart.checkOut());
    }

}