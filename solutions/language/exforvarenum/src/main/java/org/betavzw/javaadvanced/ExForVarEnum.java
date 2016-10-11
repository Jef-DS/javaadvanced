package org.betavzw.javaadvanced;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Jef on 24/08/2016.
 */
public class ExForVarEnum {
    public static void main(String[] args) {
        Product p1 = new Product(VatRates.NORMAL_RATE, "Car", new BigDecimal("10000"));
        Product p2 = new Product(VatRates.LOW_RATE_1, "Paper", new BigDecimal("1.6"));
        Product p3 = new Product(VatRates.LOW_RATE_2, "Java Advanced", new BigDecimal("38"));
        ShoppingBasket basket = new ShoppingBasket(p1, p2, p3);
        for (Product p : basket) {
            System.out.printf("%s costs  € %.2f VAT excl (VAT: € %.2f)%n", p.getName(), p.getPrice(), p.getVatPrice());
        }
        System.out.printf("Total price %.2f (VAT excl), %.2f (VAT incl)", basket.getTotalPriceVATExcl(), basket.getTotalPriceVATIncl());
    }
}

class ShoppingBasket implements Iterable<Product> {

    private final Product[] products;

    public ShoppingBasket(Product... p) {
        products = new Product[p.length];
        System.arraycopy(p, 0, products, 0, p.length);
    }

    @Override
    public Iterator<Product> iterator() {
        return Arrays.asList(products).iterator();
    }

    public BigDecimal getTotalPriceVATExcl() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product p : this) {
            total = total.add(p.getPrice());
        }
        return total;
    }

    public BigDecimal getTotalPriceVATIncl() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product p : this) {
            BigDecimal subtotal = p.getPrice().add(p.getVatPrice());
            total = total.add(subtotal);
        }
        return total;
    }
}

class Product {
    private VatRates vat;
    private String name;
    private BigDecimal price;

    public Product(VatRates vat, String name, BigDecimal price) {
        this.vat = vat;
        this.name = name;
        this.price = price;
    }

    public VatRates getVat() {
        return vat;
    }

    public void setVat(VatRates vat) {
        this.vat = vat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVatPrice() {
        return price.multiply(vat.getRate());
    }
}

enum VatRates {
    NORMAL_RATE("0.21"),
    LOW_RATE_1("0"),
    LOW_RATE_2("0.06"),
    LOW_RATE_3("0.12");
    private final BigDecimal _rate;

    private VatRates(String rate) {
        this._rate = new BigDecimal(rate);
    }

    public BigDecimal getRate() {
        return _rate;
    }
}
