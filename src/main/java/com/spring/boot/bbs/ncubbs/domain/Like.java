package com.spring.boot.bbs.ncubbs.domain;

import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name = "Like")
public class Like {
    //点赞ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "lid")
    private Long lid;

    //用户id
    @Column(name = "uid")
    private Long uid;

    //帖子id
    @Column(name = "tid")
    private Long tid;

    //问答id
    @Column(name = "qid")
    private Long qid;

    //需求id
    @Column(name = "did")
    private Long did;

    //无参构造方法
    public Like(){}
    //有参构造方法
    public Like(Long lid,Long uid,Long tid,Long qid,Long did){
       this.lid=lid;
       this.uid=uid;
       this.tid=tid;
       this.qid=qid;
        this.did=did;
    }

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "Like{" +
                "lid=" + lid +
                ", uid=" + uid +
                ", tid=" + tid +
                ", qid=" + qid +
                ", did=" + did +
                '}';
    }
}
