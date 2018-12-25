package com.spring.boot.bbs.ncubbs.domain;



import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name = "CommentDemand")

public class CommentDemand {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    //Id
    @Column(name = "cdid")
    private Long cdid;

    //用户ID
    @Column(name = "uid")
    private Long uid;



    @Column(name = "cdcontents")
    private String cdcontents;

    @Column(name = "cdtime")
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Time cdtime;

    //无参构造方法
    protected CommentDemand(){}
    //有参构造方法
    public CommentDemand(Long cdid,Long uid,String cdcontents,Time cdtime){
        this.cdid=cdid;
        this.uid=uid;
        this.cdcontents=cdcontents;
        this.cdtime=cdtime;
    }
    public CommentDemand(Long uid,String cdcontents){
        this.uid=uid;
        this.cdcontents=cdcontents;
    }

    public Long getCdid() {
        return cdid;
    }

    public void setCdid(Long cdid) {
        this.cdid = cdid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCdcontents() {
        return cdcontents;
    }

    public void setCdcontents(String cdcontents) {
        this.cdcontents = cdcontents;
    }

    public Time getCdtime() {
        return cdtime;
    }

    public void setCdtime(Time cdtime) {
        this.cdtime = cdtime;
    }

    @Override
    public String toString() {
        return "CommentDemand{" +
                "cdid=" + cdid +
                ", uid=" + uid +
                ", cdcontents='" + cdcontents + '\'' +
                ", cdtime=" + cdtime +
                '}';
    }
}
