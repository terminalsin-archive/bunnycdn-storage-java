package net.bunny.api.exception;

public class BunnyRequestException extends RuntimeException {
    public BunnyRequestException(Throwable cause) {
        super(cause);
    }

    public BunnyRequestException(String message) {
        super(message);
    }
}
