package ra.busines.entyti;

import java.io.Serializable;

public class Product implements Serializable {
   private String productId;
   private String productName;
   private float priceProduct;
   private Catalog catalog;
   private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, float priceProduct, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.priceProduct = priceProduct;
        this.catalog = catalog;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(float priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
