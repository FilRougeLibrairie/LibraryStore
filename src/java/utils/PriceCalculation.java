package utils;

/**
 *
 * @author ggarvanese
 */
public class PriceCalculation {

    public static float calculatePriceTTC(float priceHT, float vat) {
        return priceHT * (1 + (vat / 100));
    }

    public static float getRoundedPrice(Float price) {
        return ((int) (price * 100) / 100.00f);
    }

    public static float calculateDiscount(float priceHT, float discountRate) {
        return priceHT * (1 - (discountRate / 100));
    }

}
