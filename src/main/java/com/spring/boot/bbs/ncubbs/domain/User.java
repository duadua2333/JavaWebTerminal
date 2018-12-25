package com.spring.boot.bbs.ncubbs.domain;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class User {

    @Id//编号 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "uid")
    private Long uid;

    //用户姓名
    @Column(name = "uname")
    private String uname;

    //密码
    @Column(name = "upassword")
    private String upassword;

    //电话
    private String uphone;

    //邮箱
    private String uemail;

    //用户工作
    private String ujob;

    //工作地点
    private String workspace;

    private String usex;        //性别
    private Date ubirthday;     //生日
    private String uremark;     //个人积分
    private int point;          //积分，初始积分为0
    private boolean uismsmaster;//是否为版主


    private String avatar;      //头像图片地址

    private int state;      //用户的状态，前台登录时需要判定当前用户的状态，若<0,则不予登录

    //无参构造方法
    public   User(){
    }

    //有参构造方法
    public User(Long UID, String Uname,String Upassword,String Uphone, String Uemail, String Ujob,
                String workspace,String Usex,Date Ubirthday,String avatar,String Uremark,int point,
                boolean UIsMSmaster){
        this.uid=uid;
        this.uname=uname;
        this.upassword=Upassword;
        this.uphone=uphone;
        this.uemail=uemail;
        this.ujob=ujob;
        this.workspace=workspace;
        this.usex=Usex;
        this.point=point;
        this.avatar=avatar;
        this.uismsmaster=uismsmaster;
        this.ubirthday=ubirthday;
    }


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUjob() {
        return ujob;
    }

    public void setUjob(String ujob) {
        this.ujob = ujob;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public Date getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(Date ubirthday) {
        this.ubirthday = ubirthday;
    }

    public String getUremark() {
        return uremark;
    }

    public void setUremark(String uremark) {
        this.uremark = uremark;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isUismsmaster() {
        return uismsmaster;
    }

    public void setUismsmaster(boolean uismsmaster) {
        this.uismsmaster = uismsmaster;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upassword='" + upassword + '\'' +
                ", uphone='" + uphone + '\'' +
                ", uemail='" + uemail + '\'' +
                ", ujob='" + ujob + '\'' +
                ", workspace='" + workspace + '\'' +
                ", usex='" + usex + '\'' +
                ", ubirthday=" + ubirthday +
                ", uremark='" + uremark + '\'' +
                ", point=" + point +
                ", uismsmaster=" + uismsmaster +
                ", avatar='" + avatar + '\'' +
                ", state=" + state +
                '}';
    }
}
