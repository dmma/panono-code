package ua.dp.dmma.pojo;

import javax.json.bind.annotation.JsonbProperty;

/**
 * @author dmma
 */
public class StatisticData
{
    @JsonbProperty
    private long sum;
    @JsonbProperty
    private double avg;
    @JsonbProperty
    private long max;
    @JsonbProperty
    private long min;
    @JsonbProperty
    private long count;

    public long getSum()
    {
        return sum;
    }

    public void setSum(long sum)
    {
        this.sum = sum;
    }

    public double getAvg()
    {
        return avg;
    }

    public void setAvg(double avg)
    {
        this.avg = avg;
    }

    public long getMax()
    {
        return max;
    }

    public void setMax(long max)
    {
        this.max = max;
    }

    public long getMin()
    {
        return min;
    }

    public void setMin(long min)
    {
        this.min = min;
    }

    public long getCount()
    {
        return count;
    }

    public void setCount(long count)
    {
        this.count = count;
    }

    @Override
    public String toString()
    {
        return "StatisticData{" + "sum=" + sum + ", avg=" + avg + ", max=" + max + ", min=" + min + ", count=" + count + '}';
    }
}
