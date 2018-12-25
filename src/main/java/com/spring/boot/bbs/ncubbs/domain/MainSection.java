package com.spring.boot.bbs.ncubbs.domain;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;

/**
 * 主版块类
 */
@Entity
@Table(name = "mainsection")
public class MainSection {
    @Id//编号 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "msid")
    private Long msid;

    //版主编号
    @Column(name = "msmasterid")
    private Long msmasterid;

    //板块简介
    @Column(name = "msprofile")
    private String msprofile;

    //板块名称
    @Column(name = "msname")
    private String msname;

    //板块内容总数量
    @Column(name = "mstopiccount")
    private int mstopiccount;

    protected MainSection(){

    }

    public MainSection(Long msid , Long msmasterid , String msprofile ,String msname ,int mstopiccount){
        this.msid = msid;
        this.msmasterid = msmasterid;
        this.msprofile = msprofile;
        this.msname = msname;
        this.mstopiccount = mstopiccount;
    }

    public Long getMsid() {
        return msid;
    }

    public void setMsid(Long msid) {
        this.msid = msid;
    }

    public Long getMsmasterid() {
        return msmasterid;
    }

    public void setMsmasterid(Long msmasterid) {
        this.msmasterid = msmasterid;
    }

    public String getMsprofile() {
        return msprofile;
    }

    public void setMsprofile(String msprofile) {
        this.msprofile = msprofile;
    }

    public String getMsname() {
        return msname;
    }

    public void setMsname(String msname) {
        this.msname = msname;
    }

    public int getMstopiccount() {
        return mstopiccount;
    }

    public void setMstopiccount(int mstopiccount) {
        this.mstopiccount = mstopiccount;
    }

    @Override
    public String toString() {
        return "MainSection{" +
                "msid=" + msid +
                ", msmasterid=" + msmasterid +
                ", msprofile='" + msprofile + '\'' +
                ", msname='" + msname + '\'' +
                ", mstopiccount=" + mstopiccount +
                '}';
    }
}
