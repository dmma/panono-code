package ua.dp.dmma.resource;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ua.dp.dmma.application.ChallengeApplicationConfig;
import ua.dp.dmma.category.External;
import ua.dp.dmma.pojo.PanoramaUploadData;
import ua.dp.dmma.pojo.StatisticData;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author dmma
 */
@Category(External.class)
public class PanoramaResourceExternalTest extends JerseyTest
{
    @Override
    protected Application configure()
    {
        return new ChallengeApplicationConfig();
    }

    @Test()
    public void testGetStatisticData() throws InterruptedException
    {
        PanoramaUploadData panoramaUploadData = new PanoramaUploadData();
        panoramaUploadData.setTimestamp(Instant.now().getEpochSecond());
        panoramaUploadData.setCount(1);

        Response response = target("panono-code/panorama/statistics").request("application/xml")
                        .post(Entity.entity(panoramaUploadData, MediaType.APPLICATION_JSON_TYPE));

        assertNotNull(response);
        assertTrue(response.getStatus() == Response.Status.CREATED.getStatusCode());

        StatisticData statisticData = target("panono-code/panorama/statistics").request("application/xml").get(StatisticData.class);
        assertNotNull(statisticData);
    }
}
