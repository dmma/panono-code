package ua.dp.dmma.storage;

import ua.dp.dmma.pojo.StatisticData;
import ua.dp.dmma.storage.model.StatisticStorageData;

import java.time.Instant;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author dmma
 */
public enum StatisticStorage
{
    STATISTIC_STORAGE;

    public static final long STATISTIC_KEEPING_TIME = 60L;

    private Set<StatisticStorageData> expiringStatisticStorage = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private boolean isCleanerInitialized = false;

    private volatile StatisticData latestStatisticData = new StatisticData();

    public void add(long timestamp, int count)
    {
        synchronized (STATISTIC_STORAGE)
        {
            if (!isCleanerInitialized)
            {
                initializeStorageCleaner();
                isCleanerInitialized = true;
            }
        }
        expiringStatisticStorage.add(new StatisticStorageData(ThreadLocalRandom.current().nextInt(),
                        Instant.ofEpochSecond(timestamp).plusSeconds(STATISTIC_KEEPING_TIME).getEpochSecond(), count));
    }

    public StatisticData getLatestStatisticData()
    {
        return latestStatisticData;
    }

    private void initializeStorageCleaner()
    {
        Thread thread = new Thread(() -> {
            try
            {
                while (true)
                {
                    Instant oldestValidTime = Instant.now();
                    Iterator<StatisticStorageData> iterator = expiringStatisticStorage.iterator();

                    while (iterator.hasNext())
                    {
                        StatisticStorageData storageData = iterator.next();
                        if (oldestValidTime.isAfter(Instant.ofEpochSecond(storageData.getExpireTime())))
                        {
                            iterator.remove();
                        }
                    }

                    if (expiringStatisticStorage.isEmpty())
                    {
                        latestStatisticData = new StatisticData();
                    }
                    else
                    {
                        latestStatisticData = createStatisticData(
                                        expiringStatisticStorage.stream().collect(Collectors.summarizingInt(StatisticStorageData::getCount)));
                    }

                    Thread.sleep(TimeUnit.SECONDS.toMillis(1L));
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        });
        thread.setDaemon(true);
        thread.start();
    }

    public void clearStorage()
    {
        expiringStatisticStorage.clear();
        latestStatisticData = new StatisticData();
    }

    private StatisticData createStatisticData(IntSummaryStatistics statistics)
    {
        StatisticData statisticData = new StatisticData();
        statisticData.setAvg(statistics.getAverage());
        statisticData.setCount(statistics.getCount());
        statisticData.setSum(statistics.getSum());
        statisticData.setMax(statistics.getMax());
        statisticData.setMin(statistics.getMin());
        return statisticData;
    }
}
