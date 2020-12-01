package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

public class FileInputReader {

    public static List<String> getConnection(String inputURL) throws IOException {
        URLConnection urlConn = new URL(inputURL).openConnection();
        urlConn.setRequestProperty("Cookie",
            "session=53616c7465645f5fdcc2d2b853bdc98188c73e4219e5e663e3d985d989e70d40aca4ab6f875dee7b60073251413d5c9e");
        urlConn.setUseCaches(true);
        try (InputStream in = urlConn.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            return reader.lines()
                .collect(Collectors.toList());
        }
    }


}
