import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;

class Request {
    String method;
    String url;
    String requestLine;
    Map<String, String> headers = new HashMap<>();
    Map<String, String> queryParams = new HashMap<>();

    public Request(BufferedReader in) throws IOException {
        this.requestLine = in.readLine();
        if (requestLine == null)
            return;

        String[] requestParts = requestLine.split(" ");
        this.method = requestParts[0];
        String fullUrl = requestParts[1];

        if (fullUrl.contains("?")) {
            String[] urlParts = fullUrl.split("\\?");
            this.url = urlParts[0];
            String query = urlParts[1];
            this.queryParams = parseQueryParams(query);
        } else {
            this.url = fullUrl;
        }

        String line;
        while (!(line = in.readLine()).isEmpty()) {
            String[] headerParts = line.split(": ", 2);
            if (headerParts.length == 2) {
                headers.put(headerParts[0], headerParts[1]);
            }
        }
    }

    private Map<String, String> parseQueryParams(String query) {
        Map<String, String> queryParams = new HashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue.length == 2) {
                queryParams.put(keyValue[0], keyValue[1]);
            } else {
                queryParams.put(keyValue[0], "");
            }
        }
        return queryParams;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestLine() {
        return requestLine;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }
}