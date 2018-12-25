package com.spring.boot.bbs.ncubbs.domain;

import javax.persistence.*;


@Entity
@Table(name = "follow")
public class Follow {
    //关注id,唯一
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FID")
    private Long fid;

    //我关注的人
    @Column(name = "UID1")
    private Long uid1;
//关注我的人
    @Column(name = "UID2")
    private Long uid2;

    //无参构造方法
    protected  Follow(){}

    //有参构造方法
    public Follow(Long fid,Long uid1,Long uid2){
        this.fid=fid;
        this.uid1=uid1;
        this.uid2=uid2;
    }public Follow(Long uid1,Long uid2){
        this.uid1=uid1;
        this.uid2=uid2;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getUid1() {
        return uid1;
    }

    public void setUid1(Long uid1) {
        this.uid1 = uid1;
    }

    public Long getUid2() {
        return uid2;
    }

    public void setUid2(Long uid2) {
        this.uid2 = uid2;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "fid=" + fid +
                ", uid1=" + uid1 +
                ", uid2=" + uid2 +
                '}';
    }
}
