
import java.io.IOException;

import java.net.UnknownHostException;

public class UtamaClient {
    public static void main(String[] args)
                  throws UnknownHostException, IOException {
        Chat client = new Chat();
        client.whois("GET index.html");
    }
}

