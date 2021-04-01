package net.bunny.api.storage;

import net.bunny.api.exception.BunnyRequestException;

import java.io.File;

public interface StorageAPI {
    /**
     * Downloads a file from a specific zone at the indicated path
     *
     * @param zone The name of your storage zone where you are connecting to.
     * @param path The directory path to your file. If this is the root of your
     *             storage zone, you can ignore this parameter.
     * @param fileName The name of the file that you wish to download.
     * @return Returns the stored file at the given path. If the file does not exist,
     * a net.bunny.api.exception.BunnyRequestException will be thrown
     * @throws BunnyRequestException If the return URL is not 200, such exception will be thrown
     */
    byte[] downloadFile(String zone, String path, String fileName) throws BunnyRequestException;

    /**
     * Upload a file to a storage zone based on the URL path. If the directory tree does not exist,
     * it will be created automatically. The file content should be sent as the body of the request
     * without any type of encoding.
     *
     * @param zone The name of your storage zone where you are connecting to.
     * @param path The directory path to where your file will be stored. If this is the root of your
     *            storage zone, you can ignore this parameter.
     * @param fileName The name that the file will be uploaded as.
     * @param data File path position of data that needs to be sent.
     * @throws BunnyRequestException If the return URL is not 200, such exception will be thrown
     */
    void uploadFile(String zone, String path, String fileName, File data) throws BunnyRequestException;

    /**
     * Upload a file to a storage zone based on the URL path. If the directory tree does not exist,
     * it will be created automatically. The file content should be sent as the body of the request
     * without any type of encoding.
     *
     * @param zone The name of your storage zone where you are connecting to.
     * @param path The directory path to where your file will be stored. If this is the root of your
     *            storage zone, you can ignore this parameter.
     * @param fileName The name that the file will be uploaded as.
     * @param data Byte array of the file that will be sent. Easier to use for protection systems
     * @throws BunnyRequestException If the return URL is not 200, such exception will be thrown
     */
    void uploadFile(String zone, String path, String fileName, byte[] data) throws BunnyRequestException;

    /**
     * Delete an object from the storage zone. In case the object is a directory all the data in
     * it will be recursively deleted as well.
     *
     * @param zone The name of your storage zone where you are connecting to.
     * @param path The directory path to your file. If this is the root of your
     *             storage zone, you can ignore this parameter.
     * @param fileName The name of the file that you wish to download.
     * @throws BunnyRequestException If the return URL is not 200, such exception will be thrown
     */
    void deleteFile(String zone, String path, String fileName) throws BunnyRequestException;
}
