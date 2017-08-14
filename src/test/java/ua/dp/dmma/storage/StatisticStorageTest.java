package ua.dp.dmma.storage;

import org.junit.Test;
import ua.dp.dmma.pojo.StatisticData;

import java.time.Instant;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author dmma
 */
public class StatisticStorageTest
{
    @Test
    public void testStorage() throws InterruptedException
    {
        ExecutorService executorService = new ThreadPoolExecutor(4, 16, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        for (int i = 0; i < 100; i++)
        {
            executorService.execute(() -> StatisticStorage.STATISTIC_STORAGE.add(Instant.now().getEpochSecond(), 1));
        }

        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        Thread.sleep(2000);

        StatisticData latestStatisticData = StatisticStorage.STATISTIC_STORAGE.getLatestStatisticData();
        assertNotNull(latestStatisticData);
        assertTrue(latestStatisticData.getCount() == 100L);
    }
}
