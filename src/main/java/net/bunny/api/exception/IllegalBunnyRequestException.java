package net.bunny.api.exception;

public class IllegalBunnyRequestException extends RuntimeException {
    public IllegalBunnyRequestException(String message) {
        super(message);
    }
}
