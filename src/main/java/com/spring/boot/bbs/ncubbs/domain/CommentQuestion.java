package com.spring.boot.bbs.ncubbs.domain;

import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name = "CommentQuestion")
public class CommentQuestion {
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "cqid")
    private Long cqid;

    //用户ID
    @Column(name = "uid")
    private Long uid;

    /*//回复问答ID
    @Column(name = "cqid")
    private Long rqid;*/

    @Column(name = "cqcontents")
    private String cqcontents;

    @Column(name = "cqtime")
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Time cqtime;


    //无参构造方法
    protected CommentQuestion(){}
    //有参构造方法
    public CommentQuestion(Long cqid,Long uid,String cqcontents,Time cqtime){
        this.cqid=cqid;
        this.uid=uid;
        /*this.rqid=rqid;*/
        this.cqcontents=cqcontents;
        this.cqtime=cqtime;
    }
    public CommentQuestion(Long uid,String cqcontents){
        this.uid=uid;
        this.cqcontents=cqcontents;
    }

    public Long getCqid() {
        return cqid;
    }

    public void setCqid(Long cqid) {
        this.cqid = cqid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCqcontents() {
        return cqcontents;
    }

    public void setCqcontents(String cqcontents) {
        this.cqcontents = cqcontents;
    }

    public Time getCqtime() {
        return cqtime;
    }

    public void setCqtime(Time cqtime) {
        this.cqtime = cqtime;
    }

    @Override
    public String toString() {
        return "CommentQuestion{" +
                "cqid=" + cqid +
                ", uid=" + uid +
                ", cqcontents='" + cqcontents + '\'' +
                ", cqtime=" + cqtime +
                '}';
    }
}
