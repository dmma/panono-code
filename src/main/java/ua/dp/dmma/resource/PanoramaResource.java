package ua.dp.dmma.resource;

import ua.dp.dmma.pojo.PanoramaUploadData;
import ua.dp.dmma.pojo.StatisticData;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.Instant;

import static ua.dp.dmma.storage.StatisticStorage.STATISTIC_KEEPING_TIME;
import static ua.dp.dmma.storage.StatisticStorage.STATISTIC_STORAGE;

/**
 * @author dmma
 */
@Path("panorama")
public class PanoramaResource
{
    @Path("upload")
    @POST
    @Consumes("application/json")
    public Response uploadPanorama(PanoramaUploadData panoramaUploadData)
    {
        if (!isValidTimestamp(panoramaUploadData.getTimestamp()))
        {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        STATISTIC_STORAGE.add(panoramaUploadData.getTimestamp(), panoramaUploadData.getCount());
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("statistics")
    @GET
    @Produces("application/json")
    public StatisticData getStatisticData()
    {
        return STATISTIC_STORAGE.getLatestStatisticData();
    }

    /**
     * Checks if the epoch time is older than 60 seconds
     *
     * @param timestamp the epoch time to check
     * @return true if the epoch time is younger than 60 seconds
     */
    private boolean isValidTimestamp(long timestamp)
    {
        return Instant.now().minusSeconds(STATISTIC_KEEPING_TIME).isBefore(Instant.ofEpochSecond(timestamp));
    }
}
