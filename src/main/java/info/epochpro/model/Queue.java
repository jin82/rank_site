package info.epochpro.model;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 队列表
 * Created by jin on 2016/12/15.
 */
@Document
public class Queue {

    @Id
    private String id;

    @Indexed
    private String uuid;

    @Indexed
    private Long point;

    @CreatedDate
    private Date createTime;

    @GeoSpatialIndexed
    private double[] location;

    public Queue(String uuid, Long point, double[] location) {
        this.uuid = uuid;
        this.point = point;
        this.location = location;
    }

    public Queue(String uuid, Long point,double x,double y) {
        this.uuid = uuid;
        this.point = point;
        this.location = new double[]{x,y};
    }

    public Queue() {
    }

    public String getId() {
        return id;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Double getX() {
        return location[0];
    }

    public Double getY() {
        return location[1];
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper =  MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("uuid", uuid)
                .add("point", point);
        if (location.length == 2) {
            helper.add("X",getX())
                    .add("y",getY());
        }
        return helper.toString();
    }
}

