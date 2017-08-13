package ua.dp.dmma.pojo;

import javax.json.bind.annotation.JsonbProperty;

/**
 * @author dmma
 */
public class PanoramaUploadData
{
    @JsonbProperty
    private int count;
    @JsonbProperty
    private long timestamp;

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }
}
