package net.bunny.api.storage;

import net.bunny.api.exception.BunnyRequestException;
import net.bunny.api.exception.IllegalBunnyRequestException;
import net.bunny.api.util.Request;
import net.bunny.api.util.Response;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BunnyJavaStorageAPI implements StorageAPI {
    private final String baseUrl;
    private final String apiKey;

    public BunnyJavaStorageAPI(String apiKey) {
        this(apiKey, null);
    }

    public BunnyJavaStorageAPI(String apiKey, String region) {
        this.apiKey = apiKey;

        if (region == null) {
            this.baseUrl = "https://storage.bunnycdn.com";
            return;
        }

        this.baseUrl = "https://" + region + ".storage.bunnycdn.com";
    }

    /**
     * Downloads a file from a specific zone at the indicated path
     *
     * @param zone     The name of your storage zone where you are connecting to.
     * @param path     The directory path to your file. If this is the root of your
     *                 storage zone, you can ignore this parameter.
     * @param fileName The name of the file that you wish to download.
     * @return Returns the stored file at the given path. If the file does not exist,
     * a net.bunny.api.exception.BunnyRequestException will be thrown
     * @throws BunnyRequestException If the return URL is not 200, such exception will be thrown
     */
    @Override
    public byte[] downloadFile(String zone, String path, String fileName) throws BunnyRequestException {
        final URL url = this.buildUri(zone, path, fileName);
        final Request request = new Request(url);
        request.addProperty("AccessKey", apiKey);
        request.setUseragent("Bunny-CDN-JavaClient-1.0");
        request.setType(Request.MethodType.GET);

        final Response response;

        try {
            response = request.getResponse();
        } catch (IOException e) {
            throw new BunnyRequestException(e);
        }

        if (response.getResponseCode() != 200) {
            throw new BunnyRequestException(response.getResponse());
        }

        return response.getResponse().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Upload a file to a storage zone based on the URL path. If the directory tree does not exist,
     * it will be created automatically. The file content should be sent as the body of the request
     * without any type of encoding.
     *
     * @param zone     The name of your storage zone where you are connecting to.
     * @param path     The directory path to where your file will be stored. If this is the root of your
     *                 storage zone, you can ignore this parameter.
     * @param fileName The name that the file will be uploaded as.
     * @param data     File path position of data that needs to be sent.
     * @throws BunnyRequestException If the return URL is not 200, such exception will be thrown
     */
    @Override
    public void uploadFile(String zone, String path, String fileName, File data) throws BunnyRequestException {
        final URL url = this.buildUri(zone, path, fileName);
        final Request request = new Request(url);
        request.addProperty("AccessKey", apiKey);
        request.setUseragent("Bunny-CDN-JavaClient-1.0");
        request.setType(Request.MethodType.PUT);
        request.setDataFile(data);

        final Response response;

        try {
            response = request.getResponse();
        } catch (IOException e) {
            throw new BunnyRequestException(e);
        }

        if (response.getResponseCode() != 201) {
            throw new BunnyRequestException(response.getResponse());
        }
    }

    /**
     * Upload a file to a storage zone based on the URL path. If the directory tree does not exist,
     * it will be created automatically. The file content should be sent as the body of the request
     * without any type of encoding.
     *
     * @param zone     The name of your storage zone where you are connecting to.
     * @param path     The directory path to where your file will be stored. If this is the root of your
     *                 storage zone, you can ignore this parameter.
     * @param fileName The name that the file will be uploaded as.
     * @param data     Byte array of the file that will be sent. Easier to use for protection systems
     * @throws BunnyRequestException If the return URL is not 200, such exception will be thrown
     */
    @Override
    public void uploadFile(String zone, String path, String fileName, byte[] data) throws BunnyRequestException {
        final URL url = this.buildUri(zone, path, fileName);
        final Request request = new Request(url);
        request.addProperty("AccessKey", apiKey);
        request.setUseragent("Bunny-CDN-JavaClient-1.0");
        request.setType(Request.MethodType.PUT);
        request.setData(new String(data));

        final Response response;

        try {
            response = request.getResponse();
        } catch (IOException e) {
            throw new BunnyRequestException(e);
        }

        if (response.getResponseCode() != 201) {
            throw new BunnyRequestException(response.getResponse());
        }
    }

    /**
     * Delete an object from the storage zone. In case the object is a directory all the data in
     * it will be recursively deleted as well.
     *
     * @param zone     The name of your storage zone where you are connecting to.
     * @param path     The directory path to your file. If this is the root of your
     *                 storage zone, you can ignore this parameter.
     * @param fileName The name of the file that you wish to download.
     * @throws BunnyRequestException If the return URL is not 200, such exception will be thrown
     */
    @Override
    public void deleteFile(String zone, String path, String fileName) throws BunnyRequestException {
        final URL url = this.buildUri(zone, path, fileName);
        final Request request = new Request(url);
        request.addProperty("AccessKey", apiKey);
        request.setUseragent("Bunny-CDN-JavaClient-1.0");
        request.setType(Request.MethodType.DEL);

        final Response response;

        try {
            response = request.getResponse();
        } catch (IOException e) {
            throw new BunnyRequestException(e);
        }

        if (response.getResponseCode() != 200) {
            throw new BunnyRequestException(response.getResponse());
        }
    }

    private URL buildUri(String zone, String path, String fileName) {
        if (zone == null) {
            throw new IllegalBunnyRequestException("You must provide a valid BunnyCDN zone!");
        }

        if (fileName == null) {
            throw new IllegalBunnyRequestException("You must provide a valid BunnyCDN file!");
        }

        final StringBuilder uriBuilder = new StringBuilder(baseUrl);
        uriBuilder.append("/").append(zone);
        if (path != null) {
            uriBuilder.append("/").append(path);
        }
        uriBuilder.append("/").append(zone);

        final URL url;

        try {
            url = new URL(uriBuilder.toString());
        } catch (MalformedURLException e) {
            throw new IllegalBunnyRequestException("Malformed url! " + uriBuilder.toString());
        }

        return url;
    }
}
