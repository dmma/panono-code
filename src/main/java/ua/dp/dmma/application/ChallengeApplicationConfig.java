package ua.dp.dmma.application;

import org.glassfish.jersey.server.ResourceConfig;
import ua.dp.dmma.resource.PanoramaResource;

import javax.ws.rs.ApplicationPath;

/**
 * @author dmma
 */
@ApplicationPath("/")
public class ChallengeApplicationConfig extends ResourceConfig
{
    public ChallengeApplicationConfig()
    {
        register(PanoramaResource.class);
    }
}
