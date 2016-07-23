package uk.gov.hmrc.shoppingcart;

import uk.gov.hmrc.shoppingcart.product.ProductType;

/**
 * Created by habeeb on 23/07/2016.
 */
public class ShoppingCartItem {

    private final ProductType productType;
    private final int quantity;

    public ShoppingCartItem(ProductType productType, int quantity) {
        this.productType = productType;
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public int getQuantity() {
        return quantity;
    }
}
