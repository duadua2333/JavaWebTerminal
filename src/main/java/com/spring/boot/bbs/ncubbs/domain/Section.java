package com.spring.boot.bbs.ncubbs.domain;



import javax.persistence.*;

/**
 * 副版块类
 */
@Entity
@Table(name = "section")
public class Section {
    @Id//编号 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "sid")
    private Long sid;

    //上级版块编号
    @Column(name = "msid")
    private Long msid;

    //版主编号
    @Column(name = "smasterid")
    private Long smasterid;

    //板块简介
    @Column(name = "sprofile")
    private String sprofile;

    //板块名称
    @Column(name = "sname")
    private String sname;

    //板块总点击量
    @Column(name = "sclickcount")
    private int sclickcount;

    protected Section(){

    }

    public Section(Long sid , Long msid , Long smasterid,String sprofile ,String sname ,int sclickcount){
        this.msid = msid;
        this.sid = sid;
        this.smasterid =smasterid;
        this.sprofile = sprofile;
        this.sname = sname;
        this.sclickcount = sclickcount;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getMsid() {
        return msid;
    }

    public void setMsid(Long msid) {
        this.msid = msid;
    }

    public Long getSmasterid() {
        return smasterid;
    }

    public void setSmasterid(Long smasterid) {
        this.smasterid = smasterid;
    }

    public String getSprofile() {
        return sprofile;
    }

    public void setSprofile(String sprofile) {
        this.sprofile = sprofile;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSclickcount() {
        return sclickcount;
    }

    public void setSclickcount(int sclickcount) {
        this.sclickcount = sclickcount;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sid=" + sid +
                ", msid=" + msid +
                ", smasterid=" + smasterid +
                ", sprofile='" + sprofile + '\'' +
                ", sname='" + sname + '\'' +
                ", sclickcount=" + sclickcount +
                '}';
    }
}
