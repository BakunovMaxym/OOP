import java.io.PrintWriter;
import java.io.IOException;

class Response {
    private PrintWriter out;

    public Response(PrintWriter out) throws IOException {
        this.out = out;
    }

    public void sendHomePage() {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
        out.println("<html><body><h1>HOME</h1></body></html>");
        out.flush();
    }

    public void sendAboutPage() {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
        out.println("<html><body><h1>Bakunov Maxym</h1><p>PS4-1<br>Some film<br>Some song</p></body></html>");
        out.flush();
    }

    public void sendProductsPage(StringBuilder products) {
        StringBuilder UI = new StringBuilder("<html><body><h1>Product list</h1>");
        UI.append(products);
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
        out.println(UI.toString());
        out.println("</body></html>");
        out.flush();
    }

    public void sendAddProductPage() {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
        out.println("<html><body><h1>Add Product</h1>");
        out.println("<form action=\"/add-product\">");
        out.println("Name: <input type=\"text\" name=\"name\"><br>");
        out.println("Price: <input type=\"text\" name=\"price\"><br>");
        out.println("<button type=\"submit\">Add New Product</button>");
        out.println("</form>");
        out.println("</body></html>");
        out.flush();
    }

    public void redirect(String location) {
        out.println("HTTP/1.1 303 See Other");
        out.println("Location: " + location);
        out.println();
        out.flush();
    }

    public void sendNotFoundResponse() {
        out.println("HTTP/1.1 404 Not Found");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
        out.println("<html><body><h1>404 Not Found</h1></body></html>");
        out.flush();
    }

}
