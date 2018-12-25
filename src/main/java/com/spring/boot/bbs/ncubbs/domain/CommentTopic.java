package com.spring.boot.bbs.ncubbs.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


//对实体注释
@Entity
//声明此对象映射到数据库的数据表，此处对应commentTopic表
@Table(name = "commentTopic")
public class CommentTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "ctid")
    //需求评论id
    private Long ctid;

    //用户ID
    @Column(name = "uid")
    private Long uid;

    //回复帖子ID
    @Column(name = "rtid")
    private Long rtid;

    //评论内容
    @Column(name = "ctcontents")
    private String ctcontents;

    //时间
    @Column(name = "cttime")
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Date cttime;


    //无参构造方法
    protected CommentTopic(){}
    //有参构造方法
    public  CommentTopic(Long uid,String ctcontents){
        this.uid = uid;
        this.ctcontents = ctcontents;
    }
    public CommentTopic(Long ctid,Long uid,String ctcontents,Date cttime){
        this.ctid=ctid;
        this.uid=uid;
        this.ctcontents=ctcontents;
        this.cttime=cttime;
    }

    public Long getCtid() {
        return ctid;
    }

    public void setCtid(Long ctid) {
        this.ctid = ctid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCtcontents() {
        return ctcontents;
    }

    public void setCtcontents(String ctcontents) {
        this.ctcontents = ctcontents;
    }

    public Date getCttime() {
        return cttime;
    }

    public void setCttime(Date cttime) {
        this.cttime = cttime;
    }


    @Override
    public String toString() {
        return "CommentTopic{" +
                "ctid=" + ctid +
                ", uid=" + uid +
                ", ctcontents='" + ctcontents + '\'' +
                ", cttime=" + cttime +
                '}';
    }
}
