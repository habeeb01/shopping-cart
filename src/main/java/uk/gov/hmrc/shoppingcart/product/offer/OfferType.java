package uk.gov.hmrc.shoppingcart.product.offer;

import java.math.BigDecimal;

/**
 * Enum to hold the list of offer types
 *
 * NOTE: this would ideally live in the database
 */
public enum OfferType {
    BUY_ONE_GET_ONE_FREE("Buy one get one free",new BigDecimal(0.50), 2),
    THREE_FOR_TWO("3 for the price of 2",new BigDecimal(0.66), 3);

    private final String description;
    private final BigDecimal discountPercentage;
    private final int numOfItemsApplicable;

    OfferType(String description, BigDecimal discountPercentage, int numOfItemsApplicable) {
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.numOfItemsApplicable = numOfItemsApplicable;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public int getNumOfItemsApplicable() {
        return numOfItemsApplicable;
    }
}
