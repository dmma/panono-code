package ua.dp.dmma.storage.model;

/**
 * @author dmma
 */
public class StatisticStorageData implements Comparable<StatisticStorageData>
{
    private int hashKey;
    private long expireTime;
    private int count;

    public StatisticStorageData(int hashKey, long expireTime, int count)
    {
        this.hashKey = hashKey;
        this.expireTime = expireTime;
        this.count = count;
    }

    public int getHashKey()
    {
        return hashKey;
    }

    public void setHashKey(int hashKey)
    {
        this.hashKey = hashKey;
    }

    public long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(long expireTime)
    {
        this.expireTime = expireTime;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof StatisticStorageData))
            return false;

        StatisticStorageData that = (StatisticStorageData) o;

        return hashKey == that.hashKey && expireTime == that.expireTime && count == that.count;

    }

    @Override
    public int hashCode()
    {
        return hashKey;
    }

    @Override
    public int compareTo(StatisticStorageData o)
    {
        return 0;
    }
}
