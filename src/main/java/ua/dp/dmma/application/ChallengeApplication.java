package ua.dp.dmma.application;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dmma
 */
public class ChallengeApplication
{
    private static final URI BASE_URI = URI.create("http://localhost:7707/panono-code/");

    public static void main(String[] args)
    {
        try
        {
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, new ChallengeApplicationConfig(), false);

            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
            server.start();
            Logger.getLogger(ChallengeApplication.class.getName())
                            .log(Level.INFO, String.format("Application started.%nTry out %s%nStop the application using CTRL+C", BASE_URI));

            Thread.currentThread().join();
        }
        catch (IOException | InterruptedException ex)
        {
            Logger.getLogger(ChallengeApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
