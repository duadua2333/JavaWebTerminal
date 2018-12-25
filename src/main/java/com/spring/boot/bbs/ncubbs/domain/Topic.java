package com.spring.boot.bbs.ncubbs.domain;


import  com.github.rjeschke.txtmark.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


// 对实体注释
@Entity
// 声明此对象映射到数据库的数据表，对应数据库中的topic表
@Table(name = "topic")
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;//判断类的serialVersionUID来验证版本一致性的
    @Id//编号 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TID")
    private Long tid;

    //一级分类编号
    @Column(name = "TMSID")
    private Long tmsid;

    //二级分类编号
    @Column(name = "TSID")
    private Long tsid;

    //作者id

    @Column(name="TUID")
    private Long tuid;

    //帖子名称

    @Size(min=2, max=50)
    @Column(length = 50)
    private String tname;

    //帖子内容
    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @Size(min=2)
    private String tcontents;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @Size(min=2)
    private String htmlcontent; // 将 md 转为 html


    //点击量
    @Column(name = "TclickCount")
    private Integer tclickcount=0;

    //回复量
    @Column(name = "TreplyCount")
    private Integer treplycount=0;

    //点赞量
    @Column(name = "TlikeCount")
    private Integer tlikecount=0;

    //时间
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp ttime;

    //是否加精
    @Column(name = "Tstate")
    private boolean tstate;


    @Column(name = "Ttop")
    private boolean ttop;

    @Column(name = "Thot")
    private boolean thot;

     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "topic_replytopic", joinColumns = @JoinColumn(name = "topic_tid", referencedColumnName = "tid"),
            inverseJoinColumns = @JoinColumn(name = "replytopic_rtid", referencedColumnName = "rtid"))
    private List<ReplyTopic> tcomments;


    //无参构造方法
    public Topic(){
    }
    public  Topic(Long tid, Long tmsid, Long tsid,Long tuid,String tname,String tcontents,String htmlcontent,Integer tclickcount,Integer treplycount,Integer tlikecount
    ,Timestamp ttime,boolean tstate,boolean ttop,boolean thot){
        this.tid=tid;
        this.tmsid=tmsid;
        this.tsid=tsid;
        this.tuid=tuid;
        this.tname=tname;
        this.tcontents=tcontents;
        this.htmlcontent=htmlcontent;
        this.tclickcount=tclickcount;
        this.treplycount=treplycount;
        this.tlikecount=tlikecount;
        this.tstate=tstate;
        this.thot=thot;
        this.ttop=ttop;
        this.ttime=ttime;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getTmsid() {
        return tmsid;
    }

    public void setTmsid(Long tmsid) {
        this.tmsid = tmsid;
    }

    public Long getTsid() {
        return tsid;
    }

    public void setTsid(Long tsid) {
        this.tsid = tsid;
    }

    public Long getTuid() {
        return tuid;
    }

    public void setTuid(Long tuid) {
        this.tuid = tuid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTcontents() {
        return tcontents;
    }

    public void setTcontents(String tcontents) {
        this.tcontents = tcontents;
        this.htmlcontent = Processor.process(tcontents);
    }

    public String getHtmlcontent() {
        return htmlcontent;
    }

    public void setHtmlcontent(String htmlcontent) {
        this.htmlcontent = htmlcontent;
    }

    public Integer getTclickcount() {
        return tclickcount;
    }

    public void setTclickcount(Integer tclickcount) {
        this.tclickcount = tclickcount;
    }

    public Integer getTreplycount() {
        return treplycount;
    }

    public void setTreplycount(Integer treplycount) {
        this.treplycount = treplycount;
    }

    public Integer getTlikecount() {
        return tlikecount;
    }

    public void setTlikecount(Integer tlikecount) {
        this.tlikecount = tlikecount;
    }

    public Timestamp getTtime() {
        return ttime;
    }

    public void setTtime(Timestamp ttime) {
        this.ttime = ttime;
    }

    public boolean isTstate() {
        return tstate;
    }

    public void setTstate(boolean tstate) {
        this.tstate = tstate;
    }

    public boolean isTtop() {
        return ttop;
    }

    public void setTtop(boolean ttop) {
        this.ttop = ttop;
    }

    public boolean isThot() {
        return thot;
    }

    public void setThot(boolean thot) {
        this.thot = thot;
    }
    public List<ReplyTopic> getTcomments() {
        return tcomments;
    }

    public void setTcomments(List<ReplyTopic> tcomments) {
        this.tcomments = tcomments;
        this.treplycount = this.tcomments.size();
    }


    /**
     * 添加评论
     * @param comment
     */
    public void addReplytopic(ReplyTopic comment) {
        System.out.println(this.tcomments.size());
        this.tcomments.add(comment);
        //setTcomments(this.tcomments);
        System.out.println(this.tcomments.size());
        this.treplycount = this.tcomments.size();
    }
    /*
    *
     * 删除评论
     * @param commentId*/

    /*public void removeComment(Long commentId) {
        for (int index=0; index < this.tcomments.size(); index ++ ) {
            if (tcomments.get(index).getRtid() == commentId) {
                this.tcomments.remove(index);
                break;
            }
        }

        this.treplycount = this.tcomments.size();
  }*/
}
