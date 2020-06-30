package parser.googleplay.connection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import parser.googleplay.connection.exception.InvalidGooglePlayLinkException;
import parser.googleplay.connection.exception.NotGooglePlayLinkException;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Pavel Mironov
 */
public class GooglePlayGamesConnection {

    public static Connection connectToGooglePlay(String URL) throws NotGooglePlayLinkException, InvalidGooglePlayLinkException, URISyntaxException {
        final URI link = new URI(URL);
        if (GooglePlayCorrectURL.isLinkValid(link)){
            if (!link.getPath().contains("apps")){
                throw new InvalidGooglePlayLinkException("К сожалению, бот работает исключительно с играми. Введите другую ссылку.");
            }
            URL = forceToRusLocalization(URL);
            return Jsoup.connect(URL);
        }
        else {
            throw new NotGooglePlayLinkException();
        }
    }

    private static String forceToRusLocalization(String URL) {
        if (URL.endsWith("&hl=ru")){
            return URL;
        }
        else {
            if (URL.contains("&hl=")){
                URL = URL.replace(
                        URL.substring(
                                URL.length()-"&hl=ru".length()), "&hl=ru");
            }
            else {
                URL += "&hl=ru";
            }
        }
        return URL;
    }

    private static class GooglePlayCorrectURL {

        private static final String VALID_HOST = "play.google.com";

        private static final String VALID_PROTOCOL = "https";

        private static final int VALID_PORT = -1;

        private static boolean isLinkValid(URI link) {
            return (isHostValid(link) && isProtocolValid(link) && isPortValid(link));
        }

        private static boolean isProtocolValid(URI link) {
            if (link.getScheme() != null) {
                return link.getScheme().equals(VALID_PROTOCOL);
            }
            else {
                return false;
            }
        }

        private static boolean isHostValid(URI link) {
            if (link.getHost() != null) {
                return link.getHost().equals(VALID_HOST);
            }
            else {
                return false;
            }
        }

        private static boolean isPortValid(URI link) {
            return link.getPort() == VALID_PORT;
        }
    }
}
