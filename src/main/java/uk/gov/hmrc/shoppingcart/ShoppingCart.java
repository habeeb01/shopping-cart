package uk.gov.hmrc.shoppingcart;

import uk.gov.hmrc.shoppingcart.product.ProductType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by habeeb on 23/07/2016.
 */
public class ShoppingCart {

    private final List<ShoppingCartItem> shoppingCartItems;

    public ShoppingCart(List<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public BigDecimal checkOut(){
        if(shoppingCartItems == null || shoppingCartItems.isEmpty()){
            System.out.println("Shopping cart is empty");
            return BigDecimal.ZERO;
        }
        return getTotalPrice();
    }

    private BigDecimal getTotalPrice(){
        System.out.println("Shopping cart has the following items...");
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(ShoppingCartItem item : shoppingCartItems){
            System.out.println(item.getQuantity() + " " + item.getProductType().getDescription() +"(s)"
            + " at " + item.getProductType().getUnitPrice().setScale(2, RoundingMode.CEILING) + " each");
            BigDecimal totalItemPrice =
                    item.getProductType().getUnitPrice().multiply(new BigDecimal(item.getQuantity()));
            totalPrice = totalPrice.add(totalItemPrice);
        }

        return totalPrice.setScale(2, RoundingMode.CEILING);
    }

    public static void main(String args[]){

        List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
        shoppingCartItems.add(new ShoppingCartItem(ProductType.APPLE, 6));
        shoppingCartItems.add(new ShoppingCartItem(ProductType.ORANGE, 5));

        ShoppingCart shoppingCart = new ShoppingCart(shoppingCartItems);

        System.out.println("Total price is: " + shoppingCart.checkOut());

    }
}
