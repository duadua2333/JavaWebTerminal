package com.spring.boot.bbs.ncubbs.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * 需求回复类
 */
@Entity
@Table(name = "replydemand")
public class ReplyDemand {
    @Id//编号 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "rdid")
    private Long rdid;

    //需求编号
    @Column(name = "did")
    private Long did;

    //发表回复的人的编号
    @Column(name = "uid")
    private Long uid;

    //发表回复内容
    @Column(name = "rdcontent")
    private String rdcontent;

    //发表回复的时间
    @Column(name = "rdtime")
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Date rdtime;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "replydemand_CommentDemand", joinColumns = @JoinColumn(name = "replydemand_rdid", referencedColumnName = "rdid"),
            inverseJoinColumns = @JoinColumn(name = "commentDemand_cdid", referencedColumnName = "cdid"))
    private List<CommentDemand> dcomment;

    protected ReplyDemand(){
    }

    public ReplyDemand(Long rdid,Long uid,String rdcontent,Date rdtime){
        this.rdid = rdid;
        this.uid = uid;
        this.rdcontent = rdcontent;
        this.rdtime = rdtime;
    }
    public ReplyDemand(Long uid,String rdcontents){
        this.uid=uid;
        this.rdcontent=rdcontents;
    }

    public Long getRdid() {
        return rdid;
    }

    public void setRdid(Long rdid) {
        this.rdid = rdid;
    }


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRdcontent() {
        return rdcontent;
    }

    public void setRdcontent(String rdcontent) {
        this.rdcontent = rdcontent;
    }

    public Date getRdtime() {
        return rdtime;
    }

    public void setRdtime(Date rdtime) {
        this.rdtime = rdtime;
    }



    public List<CommentDemand> getDcomment() {
        return dcomment;
    }

    public void setDcomment(List<CommentDemand> dcomment) {
        this.dcomment = dcomment;
    }

    @Override
    public String toString() {
        return "ReplyDemand{" +
                "rdid=" + rdid +
                ", uid=" + uid +
                ", rdcontent='" + rdcontent + '\'' +
                ", rdtime=" + rdtime +
                '}';
    }

    /**
     * 添加对回帖的评论
     * @param comment
     */
    public void addCommentdemand(CommentDemand comment) {
        System.out.println(this.dcomment.size());
        this.dcomment.add(comment);
        System.out.println(this.dcomment.size());
    }
}
