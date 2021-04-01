package net.bunny.api;

import net.bunny.api.storage.BunnyJavaStorageAPI;
import net.bunny.api.storage.StorageAPI;

public class BunnyJavaClient implements BunnyClient {
    private final String apiKey;

    public BunnyJavaClient(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public StorageAPI getStorageAPI(String region) {
        return new BunnyJavaStorageAPI(apiKey, region);
    }
}
