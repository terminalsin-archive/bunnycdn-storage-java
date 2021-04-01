package net.bunny.api.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author Ghast
 * @since 19/02/2021
 * UTrust-Java-Library © 2021
 */
public class Request {
    private final URL endpoint;

    private int timeout = 30000;
    private final Map<String, String> properties = new WeakHashMap<>();
    private MethodType type;
    private String data;
    private File dataFile;
    private Proxy proxy;
    private boolean followRedirects;

    public Request(URL endpoint) {
        this.endpoint = endpoint;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setCookie(String cookie) {
        this.properties.put("Cookie", cookie);
    }

    public void setReferer(String referer) {
        this.properties.put("Referer", referer);
    }

    public void setUseragent(String useragent) {
        this.properties.put("User-Agent", useragent);
    }

    public void setContentData(String contentData) {
        this.properties.put("Content-Type", contentData);
    }

    public void setData(String postData) {
        this.data = postData;
    }

    public void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    public void setType(MethodType type) {
        this.type = type;
    }

    public void addProperty(String property, String value) {
        this.properties.put(property, value);
    }

    public Response getResponse() throws IOException {
        final HttpURLConnection connection = proxy == null
                ? (HttpURLConnection) endpoint.openConnection()
                : (HttpURLConnection) endpoint.openConnection(proxy);

        properties.forEach(connection::setRequestProperty);

        connection.setRequestMethod(type.getName());
        connection.setReadTimeout(timeout);
        connection.setConnectTimeout(timeout);
        connection.setUseCaches(false);
        connection.setDoOutput(true);

        HttpURLConnection.setFollowRedirects(followRedirects);

        if (data != null) {
            connection.setDoInput(true);
            final DataOutputStream writer = new DataOutputStream(connection.getOutputStream());

            writer.writeBytes(data);
            writer.flush();
            writer.close();
        } else if (dataFile != null) {
            final DataOutputStream os = new DataOutputStream(connection.getOutputStream());
            final BufferedInputStream is = new BufferedInputStream(new FileInputStream(dataFile));

            int data;
            final byte[] buffer = new byte[4096];

            while ((data = is.read(buffer)) >= 0) {
                os.write(buffer, 0, data);
            }

            is.close();
            os.close();
        }

        final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        final StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        final Response response = new Response();
        response.setResponse(builder.toString());
        response.setResponseCode(connection.getResponseCode());

        return response;
    }

    public enum MethodType {
        GET("GET"),
        POST("POST"),
        DEL("DELETE"),
        PUT("PUT"),
        PATCH("PATCH");

        private final String name;

        MethodType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
