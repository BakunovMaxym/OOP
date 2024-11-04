import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class App {
    private static final int PORT = 8088;

    static ProductList productList = new ProductList();

    public static void main(String[] args) {
        productList.add(new Product("Apple", 10));
        productList.add(new Product("Socks", 100));
        productList.add(new Product("Wall", 999));
        productList.add(new Product("PC", 100000));
        productList.add(new Product("Pencil", 5));

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("socket on port: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClientRequest(clientSocket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        System.out.println(clientSocket.isClosed());
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            Request request = new Request(in);
            Response response = new Response(out);

            System.out.println("Request method: " + request.getMethod());
            System.out.println("Request path: " + request.getUrl());
            System.out.println("Request's user-agent: " + request.getHeaders().get("User-Agent"));

            if ("GET".equals(request.getMethod()) && "/home".equals(request.getUrl())) {
                response.sendHomePage();
            } else if ("GET".equals(request.getMethod()) && "/about".equals(request.getUrl())) {
                response.sendAboutPage();
            } else if ("GET".equals(request.getMethod()) && "/products".equals(request.getUrl())) {
                double minPrice = Double.parseDouble(request.getQueryParams().getOrDefault("minPrice", "0"));
                double maxPrice = Double.parseDouble(
                        request.getQueryParams().getOrDefault("maxPrice", String.valueOf(Double.MAX_VALUE)));
                response.sendProductsPage(productList.productsComp(minPrice, maxPrice));
            } else if ("GET".equals(request.getMethod()) && "/add-product".equals(request.getUrl())) {
                String name = request.getQueryParams().get("name");
                String priceParam = request.getQueryParams().get("price");
            
                if (name != null && priceParam != null) {
                    try {
                        double price = Double.parseDouble(priceParam);
                        productList.add(new Product(name, price));
                        response.redirect("/products");
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    response.sendAddProductPage();
                }
            } else {
                response.sendNotFoundResponse();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}





