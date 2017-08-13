package ua.dp.dmma.resource;

import org.junit.Before;
import org.junit.Test;
import ua.dp.dmma.pojo.PanoramaUploadData;

import javax.ws.rs.core.Response;

import java.time.Instant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author dmma
 */
public class PanoramaResourceTest
{
    private PanoramaResource panoramaResource;

    @Before
    public void setUp()
    {
        panoramaResource = new PanoramaResource();
    }

    @Test
    public void testUploadPanoramaOK()
    {
        Response response = panoramaResource.uploadPanorama(createPanoramaUploadData(1, Instant.now().getEpochSecond()));
        assertNotNull(response);
        assertTrue(Response.Status.CREATED.getStatusCode() == response.getStatus());
    }

    @Test
    public void testUploadPanoramaWithExpiredTimestamp()
    {
        Response response = panoramaResource.uploadPanorama(createPanoramaUploadData(1, Instant.now().minusSeconds(100).getEpochSecond()));
        assertNotNull(response);
        assertTrue(Response.Status.NO_CONTENT.getStatusCode() == response.getStatus());
    }

    private PanoramaUploadData createPanoramaUploadData(int count, long timestamp)
    {
        PanoramaUploadData panoramaUploadData = new PanoramaUploadData();
        panoramaUploadData.setCount(count);
        panoramaUploadData.setTimestamp(timestamp);
        return panoramaUploadData;
    }
}
