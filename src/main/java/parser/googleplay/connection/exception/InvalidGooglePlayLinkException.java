package parser.googleplay.connection.exception;

/**
 * @author Pavel Mironov
 */
public class InvalidGooglePlayLinkException extends Exception {
    public InvalidGooglePlayLinkException() {
        super();
    }

    public InvalidGooglePlayLinkException(String message) {
        super(message);
    }

    public InvalidGooglePlayLinkException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGooglePlayLinkException(Throwable cause) {
        super(cause);
    }
}
