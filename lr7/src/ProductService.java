import java.util.ArrayList;

public class ProductService {
    private static final ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getAllProducts(){
        return new ArrayList<>(products);
    }

    public void addProduct(String name, double price){
        products.add(new Product(name, price));
    }

    public boolean deleteProduct(String name){
        for(Product product: products){
            if(product.getName().equals(name)){
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct(String name, String newName, double newPrice){
        for(Product product: products){
            if(product.getName().equals(name) && newName != "" && newPrice > 0){
                product.setName(newName);
                product.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }
}
