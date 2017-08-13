package ua.dp.dmma.pojo;

import javax.json.bind.annotation.JsonbProperty;

/**
 * @author dmma
 */
public class StatisticData
{
    @JsonbProperty
    private int sum;
    @JsonbProperty
    private double avg;
    @JsonbProperty
    private int max;
    @JsonbProperty
    private int min;
    @JsonbProperty
    private int count;

    public int getSum()
    {
        return sum;
    }

    public void setSum(int sum)
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

    public int getMax()
    {
        return max;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    public int getMin()
    {
        return min;
    }

    public void setMin(int min)
    {
        this.min = min;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
