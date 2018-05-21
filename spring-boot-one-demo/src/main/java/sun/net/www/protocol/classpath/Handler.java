package sun.net.www.protocol.classpath;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {

    private final String PROTOCOL_PREFIX = "classpath:/";

    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        String urlString = url.toString();
        String classpath = urlString.substring(PROTOCOL_PREFIX.length());
        final URL classpathURL = Thread.currentThread().getContextClassLoader().getResource(classpath);
        return classpathURL.openConnection();
    }
}
