package uk.gov.hmrc.shoppingcart.product;

import java.math.BigDecimal;

/**
 * Enum to lest all the different product types that the shop sells
 *
 * NOTE: this would ideally live in the database
 *
 */
public enum ProductType {
    APPLE("Apple", new BigDecimal(0.60)),
    ORANGE("Orange", new BigDecimal(0.25));

    private String description;
    private BigDecimal unitPrice;

    ProductType(String description, BigDecimal unitPrice) {
        this.description = description;
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
