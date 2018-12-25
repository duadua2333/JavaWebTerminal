package com.spring.boot.bbs.ncubbs.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "supermaster")
public class SuperMaster {
    /*默认id为1的用户为超级管理员，并且只有一个*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "id")
    private Long id;

    //管理员姓名
    @Column(name = "smname")
    private String smname;

    //密码
    @Column(name = "smpassword")
    private String smpassword;


    //无参构造方法
    protected  SuperMaster(){}

    //有参构造方法
    public SuperMaster(Long SMID,String smname,String SMpassword){
        this.id=SMID;
        this.smname=smname;
        this.smpassword=SMpassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmname() {
        return smname;
    }

    public void setSmname(String smname) {
        this.smname = smname;
    }

    public String getSmpassword() {
        return smpassword;
    }

    public void setSmpassword(String smpassword) {
        this.smpassword = smpassword;
    }

    @Override
    public String toString() {
        return "SuperMaster{" +
                "id=" + id +
                ", smname='" + smname + '\'' +
                ", smpassword='" + smpassword + '\'' +
                '}';
    }
}
