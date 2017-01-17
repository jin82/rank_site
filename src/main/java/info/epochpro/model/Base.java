package info.epochpro.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础字段
 * Created by jin on 2016/12/15.
 */
@MappedSuperclass
public abstract class Base implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "delete",insertable = false)
    private Byte delete;

    @Column(name = "create_time",insertable = false)
    private Date createTime;

    @Column(name = "modify_time",insertable = false)
    private Date modifyTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getDelete() {
        return delete;
    }

    public void setDelete(Byte delete) {
        this.delete = delete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
