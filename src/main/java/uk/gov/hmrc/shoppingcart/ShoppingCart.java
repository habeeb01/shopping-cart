package uk.gov.hmrc.shoppingcart;

import uk.gov.hmrc.shoppingcart.product.ProductType;
import uk.gov.hmrc.shoppingcart.product.offer.OfferType;

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
            BigDecimal totalItemPrice = hasOffer(item) ?
                    getPriceWithOffer(item) :
                    getPriceWithoutOffer(item);
            totalPrice = totalPrice.add(totalItemPrice);
        }

        return totalPrice.setScale(2, RoundingMode.CEILING);
    }

    private boolean hasOffer(ShoppingCartItem item){
        if(item.getProductType().getOfferType() == null){
            return false;
        }
        return true;
    }

    private BigDecimal getPriceWithoutOffer(ShoppingCartItem item){
        System.out.println(getGenericLogStatement(item));
        BigDecimal totalItemPrice =
                item.getProductType().getUnitPrice().multiply(new BigDecimal(item.getQuantity()));
        return totalItemPrice.setScale(2, RoundingMode.CEILING);
    }

    private BigDecimal getPriceWithOffer(ShoppingCartItem item){
        OfferType offerType = item.getProductType().getOfferType();
        System.out.println(getGenericLogStatement(item) +
                " With offer: " + offerType.getDescription());

        int numToApplyFullPrice = item.getQuantity() % offerType.getNumOfItemsApplicable();
        int numToApplyOffer = item.getQuantity() - numToApplyFullPrice;

        BigDecimal totalForFullPriceItems =
                item.getProductType().getUnitPrice().multiply(new BigDecimal(numToApplyFullPrice));
        BigDecimal totalForOfferItems =
                item.getProductType().getUnitPrice().multiply(new BigDecimal(numToApplyOffer))
                        .multiply(offerType.getDiscountPercentage());

        return totalForFullPriceItems.add(totalForOfferItems).setScale(2, RoundingMode.CEILING);
    }

    private String getGenericLogStatement(ShoppingCartItem item) {
        return item.getQuantity() + " " + item.getProductType().getDescription() +"(s)"
                + " at " + item.getProductType().getUnitPrice().setScale(2, RoundingMode.CEILING) + " each.";
    }

    public static void main(String args[]){

        List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
        ProductType apple = ProductType.APPLE;
        apple.setOfferType(OfferType.BUY_ONE_GET_ONE_FREE);
        ProductType orange = ProductType.ORANGE;
        orange.setOfferType(OfferType.THREE_FOR_TWO);
        shoppingCartItems.add(new ShoppingCartItem(apple, 6));
        shoppingCartItems.add(new ShoppingCartItem(orange, 5));

        ShoppingCart shoppingCart = new ShoppingCart(shoppingCartItems);

        System.out.println("Total price is: " + shoppingCart.checkOut());

    }
}
