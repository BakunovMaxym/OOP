import java.util.ArrayList;


public class ProductList {
    ArrayList<Product> productList = new ArrayList<>();

    public void add(Product product) {
        productList.add(product);
    }

    public StringBuilder productsComp(double minPrice, double maxPrice) {
        StringBuilder productsString = new StringBuilder("<ul>");
        for (Product product : productList) {
            if (product.price >= minPrice && product.price <= maxPrice) {
                productsString.append("<li>").append(product.name).append(" - ").append(product.price).append("</li>");
            }
        }
        productsString.append("</ul>");
        return productsString;
    }
}