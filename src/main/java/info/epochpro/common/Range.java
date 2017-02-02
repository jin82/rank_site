package info.epochpro.common;

import info.epochpro.exceptions.ServiceException;

/**
 * 范围类
 * Created by jin on 2016/12/15.
 */
public class Range {

    private Long from;

    private Long to;

    private Long range;

    private Long defaultValue;

    public Range(Long defaultValue,Long range) {
        if(defaultValue<range){
            throw new ServiceException("获取范围参数有误");
        }
        this.defaultValue = defaultValue;
        this.range = range;
        this.from = defaultValue - range;
        this.to = defaultValue + range;
    }

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }

    public Long getRange() {
        return range;
    }

    public Long getDefaultValue() {
        return defaultValue;
    }
}
